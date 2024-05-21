package dk.sdu.mmmi.cbse.split;

import dk.sdu.mmmi.cbse.common.services.ISplitService;

public class SplitOneImpl implements ISplitService {
    @Override
    public String provide() {
        return "Hi from SplitOneImpl!";
    }
}
