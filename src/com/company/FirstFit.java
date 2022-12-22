package com.company;

import java.util.ArrayList;

public class FirstFit extends PolicyAlgo {
    FirstFit(ArrayList<Partition> partition, ArrayList<Process> process) {
        super(partition, process);
    }

    @Override
    int allocateProcess(int no_of_partition) {
        int i = 0;
        while (i < process.size()) {
            boolean found = false;
            for (int j = 0; j < partitions.size(); j++) {
                if (process.get(i).process_size <= partitions.get(j).partitionSize && partitions.get(j).processName.equals("")) {
                    partitions.get(j).processName = process.get(i).process_name;
                    if (partitions.get(j).partitionSize != process.get(i).process_size) {
                        partitions.add(j + 1, new Partition(no_of_partition++, partitions.get(j).partitionSize - process.get(i).process_size));
                        partitions.get(j).partitionSize = process.get(i).process_size;
                    }
                    process.remove(i);
                    found = true;
                    break;
                }
            }
            if (!found)
                i++;
        }
        printMemory();
        return no_of_partition;
    }
}
