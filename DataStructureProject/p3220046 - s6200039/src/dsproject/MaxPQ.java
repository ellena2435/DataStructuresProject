package dsproject;

public class MaxPQ {
    private Processor[] pq;
    private int n;

    public MaxPQ(int capacity) {
        pq = new Processor[capacity + 1];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void insert(Processor x) {
        if (n == pq.length - 1) resize(2 * pq.length);
        pq[++n] = x;
        swim(n);
    }

    public Processor max() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        return pq[1];
    }

    public Processor getmax() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        Processor max = pq[1];
        swap(1, n--);
        sink(1);
        pq[n + 1] = null;
        if (n > 0 && n == (pq.length - 1) / 4) resize(pq.length / 2);
        return max;
    }

    public Processor min() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        Processor min = pq[1];
        for (int i = 2; i <= n; i++) {
            if (pq[i].compareTo(min) < 0) {
                min = pq[i];
            }
        }
        return min;
    }

    private void resize(int capacity) {
        Processor[] temp = new Processor[capacity];
        System.arraycopy(pq, 1, temp, 1, n);
        pq = temp;
    }

    private void swim(int k) {
        while (k > 1 && pq[k].compareTo(pq[k / 2]) > 0) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && pq[j].compareTo(pq[j + 1]) < 0) j++;
            if (pq[k].compareTo(pq[j]) >= 0) break;
            swap(k, j);
            k = j;
        }
    }

    private void swap(int i, int j) {
        Processor temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }
}
