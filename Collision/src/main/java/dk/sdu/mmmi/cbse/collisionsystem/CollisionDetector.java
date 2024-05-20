package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class CollisionDetector implements IPostEntityProcessingService {

    public CollisionDetector() {
    }
    @Override
    public void process(GameData gameData, World world) {
        // Getting entities
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {
                if (entity1.getID().equals(entity2.getID())) {
                    continue;
                }
                // Checking if entities are colliding
                if (isColliding(entity1, entity2)) {
                   entity1.setHit(true);
                   entity2.setHit(true);
                    // If they are colliding, we remove them
                }
            }
        }
    }

    public boolean isColliding(Entity entity1, Entity entity2) {
        // Getting the distance between the two entities
        double distance = Math.sqrt(Math.pow(entity1.getX() - entity2.getX(), 2) + Math.pow(entity1.getY() - entity2.getY(), 2));
        // Checking if the distance is less than the sum of the two entities' radius
        return distance < entity1.getRadius() + entity2.getRadius();
    }
}
