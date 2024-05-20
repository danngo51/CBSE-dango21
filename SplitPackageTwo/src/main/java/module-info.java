import dk.sdu.mmmi.cbse.common.services.ISplitService;

module SplitPackageTwo {
    exports dk.sdu.mmmi.cbse.split;
    requires Common;
    provides ISplitService with dk.sdu.mmmi.cbse.split.SplitImplementation;
}