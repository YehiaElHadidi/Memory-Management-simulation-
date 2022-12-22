package com.company;

import java.util.ArrayList;

public class BestFit extends PolicyAlgo {

    BestFit(ArrayList<Partition> partition, ArrayList<Process> process) {
        super(partition, process);
    }

    @Override
    int allocateProcess(int no_of_partition) {
        int i = 0;
        while (i < process.size()) {
            boolean found = false;
            Partition bestPartition = null;
            int index = -1;
            for (int j = 0; j < partitions.size(); j++) {
                if (process.get(i).process_size <= partitions.get(j).partitionSize && partitions.get(j).processName.equals("")) {
                    if (bestPartition == null) {
                        bestPartition = partitions.get(j);
                        index = j;
                    } else if (bestPartition.partitionSize > partitions.get(j).partitionSize) {
                        bestPartition = partitions.get(j);
                        index = j;
                    }
                }
            }
            if (bestPartition != null) {
                if (bestPartition.partitionSize != process.get(i).process_size) {
                    partitions.add(index + 1, new Partition(no_of_partition++, bestPartition.partitionSize - process.get(i).process_size));
                    bestPartition.partitionSize = process.get(i).process_size;
                }
                bestPartition.processName = process.get(i).process_name;
                process.remove(i);
                found = true;
            }
            if (!found)
                i++;
        }
        printMemory();
        return no_of_partition;
    }
}
/*
                if(unallocated_process.get(i).process_size <= partition.get(j).partitionSize && partition.get(j).processName.equals("")){
                    partition.get(j).processName = unallocated_process.get(i).process_name;
                    if(partition.get(j).partitionSize != unallocated_process.get(i).process_size) {
                        partition.add(j+1,new Partition(no_of_partition++, partition.get(j).partitionSize - unallocated_process.get(i).process_size));
                        partition.get(j).partitionSize = unallocated_process.get(i).process_size;
                    }
                    unallocated_process.remove(i);
                    found = true;
                    break;
                }
 */