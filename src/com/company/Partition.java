package com.company;

public class Partition {
    int partitionName;
    int partitionSize;
    // name of the Process Located in this Partition
    String processName = "";
    Partition(int nm, int part){
        partitionName = nm;
        partitionSize = part;
    }

}
