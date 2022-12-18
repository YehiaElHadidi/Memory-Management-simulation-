package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class FirstFit extends PolicyAlgo {
    @Override
    public void executePolicy(ArrayList<Partition> partition, ArrayList<Process> process) {
        int no_of_partition = partition.size();
        no_of_partition = allocateProcess(partition, process, no_of_partition);
        System.out.println("\nDo you want to compact? 1.yes 2.no");
        Scanner sc = new Scanner(System.in);
        int choice;
        choice = sc.nextInt();
        if(choice==1){
            compaction(partition,process,no_of_partition);
        }
    }
    @Override
     int allocateProcess(ArrayList<Partition> partition, ArrayList<Process> unallocated_process, int no_of_partition) {
        int i = 0;
        while(i < unallocated_process.size()){
            boolean found = false;
            for(int j = 0; j < partition.size(); j++){
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
            }
            if(!found)
                i++;
        }
        for(int j = 0; j < partition.size(); j++){
            System.out.print("Partition " + partition.get(j).partitionName + " (" + partition.get(j).partitionSize + ") => ");
            if(!partition.get(j).processName.equals(""))
                System.out.println("Process " + partition.get(j).processName);
            else
                System.out.println("External fragment");
        }
        System.out.println();
        for(int j = 0; j < unallocated_process.size(); j++){
            System.out.println("Process " + unallocated_process.get(j).process_name + " can not be allocated");
        }
        return no_of_partition;
    }
}
