import dk.sdu.mmmi.cbse.common.services.ISplitService;
import dk.sdu.mmmi.cbse.split.SplitImplementation;

module SplitPackage2 {
    requires Common;
    provides ISplitService with SplitImplementation;
}