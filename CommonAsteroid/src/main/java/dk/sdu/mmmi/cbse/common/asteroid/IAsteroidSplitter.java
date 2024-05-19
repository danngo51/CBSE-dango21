package dk.sdu.mmmi.cbse.common.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Interface for creating split asteroids
 *
 */
public interface IAsteroidSplitter {
    void createSplitAsteroid(Entity asteroid, World world);
}
