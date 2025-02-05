package dsproject;

public class Processor implements Comparable<Processor> {
    private int id;
    private int totalTime;
    private Job[] processedJobs;
    private int jobCount;

    public Processor(int id) {
        this.id = id;
        this.totalTime = 0;
        this.processedJobs = new Job[10]; // Initial capacity
        this.jobCount = 0;
    }

    public void addJob(Job job) {
        if (jobCount == processedJobs.length) {
            resize();
        }
        processedJobs[jobCount++] = job;
        totalTime += job.getTime();
    }

    public int getTotalProcessingTime() {
        return totalTime;
    }

    public int getId() {
        return id;
    }

    public Job[] getJobs() {
        Job[] jobs = new Job[jobCount];
        System.arraycopy(processedJobs, 0, jobs, 0, jobCount);
        return jobs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Processor{id=").append(id)
                .append(", totalTime=").append(getTotalProcessingTime())
                .append(", jobs=[");
        for (int i = 0; i < jobCount; i++) {
            sb.append(processedJobs[i]);
            if (i < jobCount - 1) sb.append(", ");
        }
        sb.append("]}");
        return sb.toString();
    }

    @Override
    public int compareTo(Processor other) {
        int timeComparison = Integer.compare(this.getTotalProcessingTime(), other.getTotalProcessingTime());
        if (timeComparison != 0) {
            return timeComparison;
        }
        return Integer.compare(this.id, other.id);
    }

    private void resize() {
        Job[] newJobs = new Job[processedJobs.length * 2];
        System.arraycopy(processedJobs, 0, newJobs, 0, processedJobs.length);
        processedJobs = newJobs;
    }
}
