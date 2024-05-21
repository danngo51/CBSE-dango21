package dk.sdu.mmmi.cbse.common.data.entityParts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
public interface EntityPart {
    void process(GameData gameData, Entity entity);
}

