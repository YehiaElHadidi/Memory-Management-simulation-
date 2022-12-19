package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static PolicyAlgo policyAlgo;
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
        System.out.println("""
                Select the policy you want to apply:
                1. First fit
                2. Best fit
                3. Worst fit
                4. exit"""
        );
        // use the same testcases on all the algorithms
        // as in the assignment
        System.out.println("\nSelect Policy: ");
        int choice = sc.nextInt();

        while(choice != 4) {
            ArrayList<Partition> partitionCopy = new ArrayList<>(partition);
            ArrayList<Process> processCopy = new ArrayList<>(process);
            if (choice == 1) {
                policyAlgo= new FirstFit(partitionCopy,processCopy);
            }
            else if(choice==2){
                policyAlgo=new BestFit(partitionCopy,processCopy);
            }
            policyAlgo.executePolicy();
            System.out.println("""
                    Select the policy you want to apply:
                    1. First fit
                    2. Best fit
                    3. Worst fit
                    4. exit"""
            );
            System.out.println("\nSelect Policy: ");
            choice = sc.nextInt();
        }
    }
}
