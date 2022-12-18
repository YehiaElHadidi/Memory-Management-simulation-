package com.company;

import java.util.ArrayList;

public class BestFit extends PolicyAlgo{

    @Override
    void executePolicy(ArrayList<Partition> partition, ArrayList<Process> process) {

    }

    @Override
    int allocateProcess(ArrayList<Partition> partition, ArrayList<Process> unallocated_process, int no_of_partition) {
        return 0;
    }
}
