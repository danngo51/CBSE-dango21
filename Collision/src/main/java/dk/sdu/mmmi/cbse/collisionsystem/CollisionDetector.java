package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityParts.LifePart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class CollisionDetector implements IPostEntityProcessingService {

    public CollisionDetector() {
    }
    @Override
    public void process(GameData gameData, World world) {
        Entity[] entities = world.getEntities().toArray(new Entity[0]);
        for (int i = 0; i < entities.length; i++) {
            for (int j = i + 1; j < entities.length; j++) {
                Entity entity1 = entities[i];
                Entity entity2 = entities[j];
                if (entity1.getID().equals(entity2.getID())) {
                    continue;
                }
                // Checking if entities are colliding
                if (isColliding(entity1, entity2)) {
                    LifePart lifePart1 = entity1.getPart(LifePart.class);
                    LifePart lifePart2 = entity2.getPart(LifePart.class);
                    lifePart1.setLife(lifePart1.getLife() - 1);
                    lifePart2.setLife(lifePart2.getLife() - 1);
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
