package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class PolicyAlgo {
    ArrayList<Partition> partitions = new ArrayList<>();
    ArrayList<Process> process = new ArrayList<>();

    PolicyAlgo(ArrayList<Partition> partitions, ArrayList<Process> process) {
        this.partitions.addAll(partitions);
        this.process.addAll(process);
    }

    abstract int allocateProcess(int no_of_partition);

    public void executePolicy() {
        int no_of_partition = partitions.size();
        no_of_partition = allocateProcess(no_of_partition);
        System.out.println("\nDo you want to compact? 1.yes 2.no");
        Scanner sc = new Scanner(System.in);
        int choice;
        choice = sc.nextInt();
        if (choice == 1) compaction(no_of_partition);
    }

    void compaction(int no_of_partition) {
        int i = 0;
        int externalFragment = 0;
        while (i < partitions.size()) {
            if (partitions.get(i).processName.equals("")) {
                externalFragment += partitions.get(i).partitionSize;
                partitions.remove(i);
            } else {
                i++;
            }
        }
        partitions.add(new Partition(no_of_partition++, externalFragment));
        allocateProcess(no_of_partition);
    }

    void printMemory() {
        for (Partition value : partitions) {
            System.out.print("Partition " + value.partitionName + " (" + value.partitionSize + ") => ");
            if (!value.processName.equals(""))
                System.out.println("Process " + value.processName);
            else
                System.out.println("External fragment");
        }
        System.out.println();
        for (Process unallocatedProcess : process) {
            System.out.println("Process " + unallocatedProcess.process_name + " can not be allocated");
        }
    }
}
