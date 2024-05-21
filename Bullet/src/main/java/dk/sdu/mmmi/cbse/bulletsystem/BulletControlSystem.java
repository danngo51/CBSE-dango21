package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityParts.LifePart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    @Override
    public void process(GameData gameData, World world) {
        //For each of the bullets makes them move.
        for (Entity bullet : world.getEntities(Bullet.class)) {
            double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
            double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
            bullet.setX(bullet.getX() + changeX * 3);
            bullet.setY(bullet.getY() + changeY * 3);

            LifePart lifePart = (LifePart) bullet.getPart(LifePart.class);
            if(lifePart.getLife()<=0){
                world.removeEntity(bullet);
            }
            lifePart.process(gameData, bullet);
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        // New instance of the bullet
        Bullet bullet = new Bullet();


        //sets the bullet based on player.
        //Shaping the bullet.
        bullet.setPolygonCoordinates(0,0,10,0,10,2,0,2);
        double changeX = Math.cos(Math.toRadians(shooter.getRotation()));
        double changeY = Math.sin(Math.toRadians(shooter.getRotation()));
        bullet.setX(shooter.getX() + changeX * 10);
        bullet.setY(shooter.getY() + changeY * 10);
        bullet.setRotation(shooter.getRotation());

        bullet.add(new LifePart(1));

        bullet.setRadius(1);
        return bullet;
    }


}
