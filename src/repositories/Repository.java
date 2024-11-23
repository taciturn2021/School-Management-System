package repositories;

import java.util.ArrayList;
import java.util.List;

// Will keep track of student, teacher, course, and administrative staff objects

public class Repository<T> {
    private List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public void remove(T item) {
        items.remove(item);
    }


    public List<T> getAll() {
        return items;
    }
}

