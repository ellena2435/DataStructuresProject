package dsproject;

public class Job {
    private int id;
    private int time;

    public Job(int id, int time) {
        this.id = id;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public int getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Job{id=" + id + ", time=" + time + "}";
    }
}
