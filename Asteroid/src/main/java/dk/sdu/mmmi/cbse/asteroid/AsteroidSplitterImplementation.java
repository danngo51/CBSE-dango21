package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroid.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityParts.LifePart;

public class AsteroidSplitterImplementation implements IAsteroidSplitter {
    @Override
    public void createSplitAsteroid(Entity asteroid, World world) {
        double oriSize = asteroid.getRadius();
        System.out.println(oriSize);
        if (oriSize > 8 ) { // Splitting the asteroid if it is bigger than 4
            double size = oriSize / 2;
            Entity a1 = new Asteroid();
            Entity a2 = new Asteroid();
            a1.add(new LifePart(1));
            a2.add(new LifePart(1));
            a1.setRadius(size);
            a2.setRadius(size);
            a1.setX(asteroid.getX()+size);
            a1.setY(asteroid.getY()+size);
            a2.setX(asteroid.getX()-size);
            a2.setY(asteroid.getY());
            a1.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
            a2.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
            a1.setRotation(asteroid.getRotation() - 90);
            a2.setRotation(asteroid.getRotation() + 90);
            world.addEntity(a1);
            world.addEntity(a2);
        }
    }
}
