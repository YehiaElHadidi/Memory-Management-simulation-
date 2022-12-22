package com.company;

import java.util.ArrayList;

public class WorstFit extends PolicyAlgo {
    WorstFit(ArrayList<Partition> pa, ArrayList<Process> pr) {
        super(pa, pr);
    }

    @Override
    int allocateProcess(int no_of_partition) {
        int i = 0;
        while (i < process.size()) {
            boolean found = false;
            Partition worstPartition = null;
            int index = -1;
            for (int j = 0; j < partitions.size(); j++) {
                if (process.get(i).process_size <= partitions.get(j).partitionSize && partitions.get(j).processName.equals("")) {
                    if (worstPartition == null) {
                        worstPartition = partitions.get(j);
                        index = j;
                    } else if (worstPartition.partitionSize < partitions.get(j).partitionSize) {
                        worstPartition = partitions.get(j);
                        index = j;
                    }
                }
            }
            if (worstPartition != null) {
                if (worstPartition.partitionSize != process.get(i).process_size) {
                    partitions.add(index + 1, new Partition(no_of_partition++, worstPartition.partitionSize - process.get(i).process_size));
                    worstPartition.partitionSize = process.get(i).process_size;
                }
                worstPartition.processName = process.get(i).process_name;
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
