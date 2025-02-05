package dsproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Greedy {
    public static int calculateMakespan(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int numProcessors = Integer.parseInt(br.readLine().trim());
            int numJobs = Integer.parseInt(br.readLine().trim());

            Job[] jobs = new Job[numJobs];
            for (int i = 0; i < numJobs; i++) {
                String[] line = br.readLine().split(" ");
                int jobId = Integer.parseInt(line[0]);
                int jobTime = Integer.parseInt(line[1]);
                jobs[i] = new Job(jobId, jobTime);
            }

            MaxPQ pq = new MaxPQ(numProcessors);
            for (int i = 1; i <= numProcessors; i++) {
                pq.insert(new Processor(i));
            }

            for (Job job : jobs) {
                Processor minProcessor = pq.min();
                minProcessor.addJob(job);
                pq.insert(minProcessor);
            }

            int makespan = 0;
            Processor[] allProcessors = new Processor[numProcessors];
            for (int i = 0; i < numProcessors; i++) {
                allProcessors[i] = pq.getmax();
                makespan = Math.max(makespan, allProcessors[i].getTotalProcessingTime());
            }

            return makespan;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }
}