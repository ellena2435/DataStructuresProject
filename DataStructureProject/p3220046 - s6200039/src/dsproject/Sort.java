package dsproject;

import java.util.List;

public class Sort {
    public static List<Job> quicksort(List<Job> jobs, int low, int high) {
        if (low < high) {
            int pi = partition(jobs, low, high);
            quicksort(jobs, low, pi - 1);
            quicksort(jobs, pi + 1, high);
        }
        return jobs;
    }

    private static int partition(List<Job> jobs, int low, int high) {
        int pivot = jobs.get(high).getTime(); // Use the processing time as pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (jobs.get(j).getTime() >= pivot) { // Sort in descending order
                i++;
                swap(jobs, i, j);
            }
        }

        swap(jobs, i + 1, high);
        return i + 1;
    }

    private static void swap(List<Job> jobs, int i, int j) {
        Job temp = jobs.get(i);
        jobs.set(i, jobs.get(j));
        jobs.set(j, temp);
    }
}
