module SplitTwo {
    requires Core;
    requires Common;
    provides dk.sdu.mmmi.cbse.common.services.ISplitService with dk.sdu.mmmi.cbse.split.SplitTwoImpl;
}