package dk.sdu.mmmi.cbse.split;

import dk.sdu.mmmi.cbse.common.services.ISplitService;

public class SplitImplementation implements ISplitService {
    @Override
    public String provide() {
        return "Split2";
    }
}
