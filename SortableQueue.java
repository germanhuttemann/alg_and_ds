
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class SortableQueue<T extends Comparable<T>> {
    private final LinkedList<T> values;
    
    public SortableQueue() {
        values = new LinkedList<>();
    }

    public SortableQueue(Collection<T> q) {
        values = new LinkedList<>(q);
    }

    public void put(T element) {
        values.addLast(element);
    }

    public T get() {
        return values.isEmpty() ? null : values.removeFirst();
    }

    public int size() {
        return values.size();
    }

    @Override
    public String toString() {
        return values.toString();
    }

    public static <T extends Comparable<T>> SortableQueue<T> merge(List<SortableQueue<T>> queues) {
        SortableQueue<T> result = new SortableQueue<>();
        for (SortableQueue<T> q : queues)
            result.values.addAll(q.values);
        
        return result;
    }

    public static <T extends Comparable<T>> SortableQueue<T> sort(SortableQueue<T> q) {
        if (q.size() <= 1)
            return q;
        
        T pivot = q.get();
        SortableQueue<T> left = new SortableQueue<>();
        SortableQueue<T> middle = new SortableQueue<>();
        SortableQueue<T> right = new SortableQueue<>();

        while (q.size() > 0) {
            T element = q.get();
            if (element.compareTo(pivot) < 0)
                left.put(element);
            else if (element.compareTo(pivot) == 0)
                middle.put(element);
            else
                right.put(element);
        }

        return merge(Arrays.asList(sort(left), middle, sort(right)));
    }

    public static void main(String[] args) {
        Integer[] values1 = {48, 57, 77, 68, 93};
        Integer[] values2 = {86, 26, 35, 16};
        SortableQueue<Integer> q1 = new SortableQueue<>(Arrays.asList(values1));
        SortableQueue<Integer> q2 = new SortableQueue<>(Arrays.asList(values2));
        SortableQueue<Integer> qq = SortableQueue.merge(Arrays.asList(q1, q2));
        System.out.println(qq);
        System.out.println(SortableQueue.sort(qq));
    }
}