package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityParts.LifePart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService {

    private Random random = new Random();
    private double speed = 1; //speed for the enemy
    private final double ROTATION_CHANGE_DEGREE = 10; // Maximum rotation change in degrees
    private final double MAX_TURN_DEGREE = 120; // Maximum turn in degrees
    // Shooting interval variables


    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Enemy.class)) {
            // Casting to enemy to acces some attributes
            Enemy enemy = (Enemy) entity;

            // Randomly adjust rotation within a range of [-5, 5] degrees
            double rotationChange = (random.nextDouble() * 2 * ROTATION_CHANGE_DEGREE) - ROTATION_CHANGE_DEGREE;
            enemy.setRotation(enemy.getRotation() + rotationChange);

            // Move forward based on current rotation
            double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemy.getRotation()));

            enemy.setX(enemy.getX() + changeX * speed);
            enemy.setY(enemy.getY() + changeY * speed);

            // Check and handle boundaries
            checkAndHandleBoundaries(enemy, gameData);

            // Increment shoot counter and shoot if interval is met
            enemy.setShootCounter(enemy.getShootCounter()+1);
            if (enemy.getShootCounter() >= enemy.getShootInterval()) {
                enemy.setShootCounter(0);
                enemy.setShootInterval(random.nextInt(85) + 25);
                getBulletSPIs().stream().findFirst().ifPresent(spi -> {
                    world.addEntity(spi.createBullet(enemy, gameData));
                });
            }

            LifePart lifePart = enemy.getPart(LifePart.class);

            if(lifePart.getLife() <= 0){
                world.removeEntity(enemy);
            }
        }
    }

    private void updateRotation(Entity enemy, double rotationChange) {
        double newRotation = enemy.getRotation() + rotationChange;
        // Normalize rotation to remain within 0-360 degrees
        newRotation = (newRotation + 360) % 360;
        enemy.setRotation(newRotation);
    }

    private void checkAndHandleBoundaries(Entity enemy, GameData gameData) {
        boolean hitBoundary = false;
        if (enemy.getX() < 0) {
            enemy.setX(1);
            hitBoundary = true;
        }
        if (enemy.getX() > gameData.getDisplayWidth()) {
            enemy.setX(gameData.getDisplayWidth() - 1);
            hitBoundary = true;
        }
        if (enemy.getY() < 0) {
            enemy.setY(1);
            hitBoundary = true;
        }
        if (enemy.getY() > gameData.getDisplayHeight()) {
            enemy.setY(gameData.getDisplayHeight() - 1);
            hitBoundary = true;
        }

        // Make a sharp turn if a boundary is hit
        if (hitBoundary) {
            double randomTurnDegree = (random.nextDouble() * 2 * MAX_TURN_DEGREE) - MAX_TURN_DEGREE;
            updateRotation(enemy, randomTurnDegree);
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
