package com.company;

import java.util.ArrayList;

public abstract class PolicyAlgo {
    abstract void executePolicy(ArrayList<Partition> partition, ArrayList<Process> process);

    abstract int allocateProcess(ArrayList<Partition> partition, ArrayList<Process> unallocated_process, int no_of_partition);
    void compaction(ArrayList<Partition> partition, ArrayList<Process> process, int no_of_partition) {
        int i = 0;
        int externalFragment = 0;
        while (i < partition.size()) {
            if (partition.get(i).processName.equals("")) {
                externalFragment += partition.get(i).partitionSize;
                partition.remove(i);
            } else {
                i++;
            }
        }
        partition.add(new Partition(no_of_partition++, externalFragment));
        allocateProcess(partition, process, no_of_partition);
    }
}
