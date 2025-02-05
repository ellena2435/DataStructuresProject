package dsproject;

import java.io.*;
import java.util.*;

public class Comparisons {
    private static final int[] N_VALUES = {100, 250, 500};
    private static final int TEST_CASES = 10;

    public static void main(String[] args) {
        for (int N : N_VALUES) {
            int M = (int) Math.floor(Math.sqrt(N));
            double sumGreedy = 0, sumSortedGreedy = 0;

            for (int test = 1; test <= TEST_CASES; test++) {
                String filename = "input_" + N + "_" + test + ".txt";
                generateRandomInputFile(filename, M, N);

                List<Job> jobs = readInputFile(filename);


                int makespanGreedy = Greedy.calculateMakespan(filename);

                if (makespanGreedy == 0) {
                    System.out.println("Error: The number of jobs does not match the input.");
                } else {
                    sumGreedy += makespanGreedy;
                }

                jobs = Sort.quicksort(jobs, 0, jobs.size() - 1);


                String sortedFilename = "sorted_" + filename;
                createSortedInputFile(sortedFilename, jobs, M, N);


                int makespanSortedGreedy = Greedy.calculateMakespan(sortedFilename);
                sumSortedGreedy += makespanSortedGreedy;

                System.out.println("M=" + M + " N=" + N + ", Test=" + test +
                        " | Greedy: " + makespanGreedy +
                        " | SortedGreedy: " + makespanSortedGreedy);
            }

            System.out.println("\n=== AVERAGE RESULTS FOR N=" + N + " ===");
            System.out.println("Average Greedy Makespan: " + (sumGreedy / TEST_CASES));
            System.out.println("Average SortedGreedy Makespan: " + (sumSortedGreedy / TEST_CASES));
            System.out.println("=======================================\n");
        }
    }

    private static void generateRandomInputFile(String filename, int M, int N) {
        Random rand = new Random();
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println(M);
            writer.println(N);
            for (int i = 1; i <= N; i++) {
                int processingTime = rand.nextInt(100) + 1;
                writer.println(i + " " + processingTime);
            }
        } catch (IOException e) {
            System.err.println("Error writing file: " + filename);
        }
    }

    private static List<Job> readInputFile(String filename) {
        List<Job> jobs = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            scanner.nextInt();
            int N = scanner.nextInt();
            for (int i = 0; i < N; i++) {
                int id = scanner.nextInt();
                int time = scanner.nextInt();
                jobs.add(new Job(id, time));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        }
        return jobs;
    }
    private static void createSortedInputFile(String filename, List<Job> jobs, int M, int N) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println(M);
            writer.println(N);
            for (Job job : jobs) {
                writer.println(job.getId() + " " + job.getTime());
            }
        } catch (IOException e) {
            System.err.println("Error writing sorted file: " + filename);
        }
    }
}
