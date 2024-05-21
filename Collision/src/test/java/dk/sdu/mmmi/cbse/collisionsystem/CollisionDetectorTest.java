package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityParts.LifePart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CollisionDetectorTest {
    private CollisionDetector collisionDetector;
    private GameData gameData;
    private World world;
    private Entity entity1;
    private Entity entity2;

    // Setting up the environment for the tests
    // This will be run before each test
    @BeforeEach
    void setUp() {
        collisionDetector = new CollisionDetector();
        gameData = new GameData();
        world = new World();

        //Entities should not be colliding
        // Entity 1 position set to (1,1) and radius set to 1
        entity1 = new Entity();
        entity1.setX(1);
        entity1.setY(1);
        entity1.setRadius(2);
        //entity1.setPolygonCoordinates(-5,-5,10,0,-5,5);
        LifePart lifePart1 = new LifePart(3);
        entity1.add(lifePart1);

        // Entity 2 position set to (100,100) and radius set to 1
        entity2 = new Entity();
        entity2.setX(100);
        entity2.setY(100);
        entity2.setRadius(2);
        //entity2.setPolygonCoordinates(-5,-5,10,0,-5,5);
        LifePart lifePart2 = new LifePart(3);
        entity2.add(lifePart2);

        world.addEntity(entity1);
        world.addEntity(entity2);
    }

    // Cleaning up after the tests. Ensuring that each test is independent.
    @AfterEach
    void tearDown() {
        entity1 = null;
        entity2 = null;
        collisionDetector = null;
        gameData = null;
        world = null;
    }

    @Test
    void testCollision() {
        // Initial life values
        LifePart lifePart1 = entity1.getPart(LifePart.class);
        LifePart lifePart2 = entity2.getPart(LifePart.class);
        double initialLife1 = lifePart1.getLife();
        double initialLife2 = lifePart2.getLife();
        System.out.println("Initial life1: " + initialLife1);
        System.out.println("Initial life2: " + initialLife2);

        // Position entity2 to collide with entity1
        entity2.setX(1);
        entity2.setY(1);

        // Process the collisions
        collisionDetector.process(gameData, world);
        System.out.println("After process life1: " + lifePart1.getLife());
        System.out.printf("After process life2: " + lifePart2.getLife());
        // Check life after collision
        assertEquals(initialLife1 - 1, lifePart1.getLife(), "Life should be decreased by 1");
        assertEquals(initialLife2 - 1, lifePart2.getLife(), "Life should be decreased by 1");
    }

    @Test
    void testNoCollision() {
        collisionDetector.process(gameData, world);
        LifePart lifePart1 = entity1.getPart(LifePart.class);
        LifePart lifePart2 = entity2.getPart(LifePart.class);

        // Both entities expected to still have 3 life
        assertEquals(3, lifePart1.getLife(), "Life should not be decreased");
        assertEquals(3, lifePart2.getLife(), "Life should not be decreased");
    }

    @Test
    void testIsColliding() {
        // Before each test, the entities are set to not be colliding
        // Changing the position of entity2 to (1,1) to make them collide
        entity2.setX(1);
        entity2.setY(1);
        assertTrue(collisionDetector.isColliding(entity1, entity2), "Entities should be colliding");
    }

    @Test
    void testIsNotColliding() {
        // Before each test, the entities are set to not be colliding
        assertFalse(collisionDetector.isColliding(entity1, entity2), "Entities should not be colliding");
    }

}
