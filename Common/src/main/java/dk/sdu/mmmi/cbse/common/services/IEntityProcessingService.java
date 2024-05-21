package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * The interface Entity processing service.
 */
public interface IEntityProcessingService {


    /**
     * Process.
     * Processes the game data and world
     * Pre-condition: Game data must be loaded and a world must be set.
     *
     * @param gameData the game data
     * @param world    the world
     */
    void process(GameData gameData, World world);
}
