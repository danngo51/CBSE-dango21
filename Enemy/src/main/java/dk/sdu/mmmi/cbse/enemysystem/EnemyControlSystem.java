package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import java.util.Random;

public class EnemyControlSystem implements IEntityProcessingService {
    private Random random = new Random();
    private double speed = 1; //speed for the enemy
    private final double ROTATION_CHANGE_DEGREE = 10; // Maximum rotation change in degrees
    private final double MAX_TURN_DEGREE = 120; // Maximum turn in degrees



    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            // Randomly adjust rotation within a range of [-5, 5] degrees
            double rotationChange = (random.nextDouble() * 2 * ROTATION_CHANGE_DEGREE) - ROTATION_CHANGE_DEGREE;
            enemy.setRotation(enemy.getRotation() + rotationChange);

            // Move forward based on current rotation
            double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemy.getRotation()));

            // Assume a basic speed factor to control movement speed

            enemy.setX(enemy.getX() + changeX * speed);
            enemy.setY(enemy.getY() + changeY * speed);

            // Check and handle boundaries
            checkAndHandleBoundaries(enemy, gameData);
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
}
