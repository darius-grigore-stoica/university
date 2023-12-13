package repository;

import domain.Entity;
import exceptions.DuplicateElementException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MemoryRepository<T extends Entity> implements IRepository<T> {
    private List<T> entities = new ArrayList<T>();
    @Override
    public void add(T e) throws DuplicateElementException, IllegalArgumentException {
        if(e == null)
            throw new IllegalArgumentException("Entity can't be null");
        if(findById(e.getId()) == null)
            entities.add(e);
        else throw new DuplicateElementException("There's already an entity with this ID");
    }

    @Override
    public void update(int pos, T e) throws IllegalArgumentException {
        if(pos < 0 || pos > entities.size())
            throw new IllegalArgumentException("Invalid position for update");
        else entities.set(pos, e);
    }

    @Override
    public void delete(int id) {
        for(T e : entities)
            if(e.getId() == id) {
                entities.remove(e);
                return;
            }
    }

    @Override
    public T findById(int id) {
        for(T e : entities)
            if(e.getId() == id)
                return e;
        return null;
    }

    @Override
    public Collection<T> getAll() {
        return new ArrayList<T>(entities);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayList<T>(entities).iterator();
    }

    @Override
    public T getAt(int pos) throws IllegalArgumentException{
        if(pos < 0 || pos > entities.size())
            throw new IllegalArgumentException("Invalid position for extraction");
        else return entities.get(pos);
    }

    @Override
    public int size() {
        return entities.size();
    }
}
