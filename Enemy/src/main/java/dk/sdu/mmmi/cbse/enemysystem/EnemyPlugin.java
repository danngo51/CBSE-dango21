package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityParts.LifePart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.lang.reflect.GenericArrayType;

public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;

    public EnemyPlugin(){
    }


    @Override
    public void start(GameData gameData, World world) {
        //Adding entities to the world
        enemy = createEnemyShip(gameData);
        world.addEntity(enemy);
    }

    private Entity createEnemyShip(GameData gameData) {

        Entity enemyShip = new Enemy();
        enemyShip.setPolygonCoordinates(-5,-5,10,0,-5,5);
        enemyShip.setX(gameData.getDisplayHeight()-50);
        enemyShip.setY(gameData.getDisplayWidth()-50);
        enemyShip.setRadius(5);
        enemyShip.add(new LifePart(5));
        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        //Remove entities
        world.removeEntity(enemy);
    }
}
