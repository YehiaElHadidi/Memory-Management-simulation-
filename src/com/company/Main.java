package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Partition> partition = new ArrayList<>();
//        partition.add(new Partition(0, 90));
//        partition.add(new Partition(1, 20));
//        partition.add(new Partition(2, 5));
//        partition.add(new Partition(3, 30));
//        partition.add(new Partition(4, 120));
//        partition.add(new Partition(5, 80));
        ArrayList<Process> process = new ArrayList<>();
//        process.add(new Process("1", 15));
//        process.add(new Process("2", 90));
//        process.add(new Process("3", 30));
//        process.add(new Process("4", 100));

        System.out.println("Enter the Number of Partition: ");
        int n = sc.nextInt(), partition_size;
        for(int i = 0 ; i < n; i++){
            System.out.println("Enter Partition Size: ");
            partition_size = sc.nextInt();
            partition.add(new Partition(i,partition_size));
        }


        System.out.println("Enter the Number of Process: ");
        n = sc.nextInt();
        String process_name;
        int  process_size;
        for (int i = 0; i < n; i++){
            System.out.println("Process name and its size: ");
            process_name = sc.next();
            process_size = sc.nextInt();
            process.add(new Process(process_name,process_size));
        }
        System.out.println("Select the policy you want to apply:\n" +
                "1. First fit\n" +
                "2. Worst fit\n" +
                "3. Best fit\n"+
                "4. exit");
        int choice = 0;
        // use the same testcases on all the algorithms
        // as in the assignment
        while(choice != 4) {
            System.out.println("\nSelect Policy: ");
            choice = sc.nextInt();
            ArrayList<Partition> partitionCopy = new ArrayList<>(partition);
            ArrayList<Process> processCopy = new ArrayList<>(process);
            if (choice == 1) {
                FirstFitPolicy(partitionCopy,processCopy);
            }
        }


    }
    public static void FirstFitPolicy(ArrayList<Partition> partition, ArrayList<Process> process){
        int no_of_partition = partition.size();
        no_of_partition = allocateProcess(partition, process, no_of_partition);
        System.out.println("\nDo you want to compact? 1.yes 2.no");
        Scanner sc = new Scanner(System.in);
        int choice;
        choice = sc.nextInt();
        if(choice == 1){
            int i = 0;
            int externalFragment = 0;
            while(i < partition.size()){
                if(partition.get(i).processName.equals("")){
                    externalFragment += partition.get(i).partitionSize;
                    partition.remove(i);
                }
                else{
                    i++;
                }
            }
            partition.add(new Partition(no_of_partition++,externalFragment));
            allocateProcess(partition, process, no_of_partition);
        }
    }

    private static int allocateProcess(ArrayList<Partition> partition, ArrayList<Process> unallocated_process, int no_of_partition) {
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
