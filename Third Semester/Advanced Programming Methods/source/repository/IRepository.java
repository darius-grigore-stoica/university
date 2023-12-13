package repository;

import domain.Entity;
import exceptions.DuplicateElementException;

import java.util.Collection;

public interface IRepository<T extends Entity> extends Iterable<T> {
    /*
    * Abstract method that add a new element in the collection
    * Input: Parameter e of type T - the new element
    * */
    void add(T e) throws DuplicateElementException, IllegalArgumentException;

    /*
     * Abstract method that update an element in the collection
     * Input:
     * Parameter pos of type int - the position of the to-be-updated elem in the elems array
     * Parameter e of type T - the new element
     * */
    void update(int pos, T e) throws IllegalArgumentException;

    /*
     * Abstract method that delete an element in the collection at a given position
     * Input: Parameter pos of type int - the position of the to-be-deleted elem in the elems array
     * */
    void delete(int id);

    T findById(int id);

    Collection<T> getAll();

    T getAt(int pos) throws IllegalArgumentException;

    int size();
}
