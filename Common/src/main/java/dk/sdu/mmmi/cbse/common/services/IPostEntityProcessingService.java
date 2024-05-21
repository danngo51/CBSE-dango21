package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * The interface Post entity processing service.
 */
public interface IPostEntityProcessingService {


    /**
     * Process.
     * Pre-condition: Game data must be loaded and a world must be set.
     *
     * @param gameData the game data
     * @param world    the world
     */
    void process(GameData gameData, World world);
}
