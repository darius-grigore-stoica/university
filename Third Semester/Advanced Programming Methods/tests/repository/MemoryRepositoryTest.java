package repository;

import domain.Entity;
import domain.Programare;
import exceptions.DuplicateElementException;
import org.junit.jupiter.api.Test;
import repository.MemoryRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MemoryRepositoryTest<T extends Entity> {

    @Test
    void add() {
        MemoryRepository<Programare> repo = new MemoryRepository();
        assert repo.size() == 0;

        Programare p = new Programare(1, "Popescu", "12.12.2020", "12:00", "Consultatie");

        try {
            repo.add(p);
            assert repo.size() == 1;
        }catch (DuplicateElementException e){
            assert false;
        }

        try {
            repo.add(p);
        } catch (DuplicateElementException e) {
            assert true;
        }

        try{
            repo.add(null);
        } catch (IllegalArgumentException | DuplicateElementException e){
            assert true;
        }
    }

    @Test
    void update() {
        MemoryRepository<Programare> repo = new MemoryRepository();
        Programare p = new Programare(1, "Popescu", "12.12.2020", "12:00", "Consultatie");
        Programare p_new = new Programare(2, "Mihailescu", "10.12.2020", "11:00", "Consultatie");

        try {
            repo.add(p);
            repo.update(0, p_new);
            assert repo.getAt(0).getId() == 2;
        } catch (DuplicateElementException ex) {
            assert false;
        }

        try{
            repo.update(0, null);

        }catch (IllegalArgumentException e){
            assert true;
        }

        try{
            repo.update(-1, p);
        }catch (IllegalArgumentException e){
            assert true;
        }
    }

    @Test
    void delete() {
        MemoryRepository<Programare> repo = new MemoryRepository();
        Programare p = new Programare(1, "Popescu", "12.12.2020", "12:00", "Consultatie");
        try {
            repo.add(p);
            assert Objects.equals(repo.getAt(0), p);
            repo.delete(1);
            assert repo.size() == 0;
        } catch (DuplicateElementException e) {
            assert false;
        }
    }

    @Test
    void findById() {
        MemoryRepository<Programare> repo = new MemoryRepository();
        try {
            Programare p = new Programare(1, "Popescu", "12.12.2020", "12:00", "Consultatie");
            repo.add(p);
            assert repo.findById(1).equals(p);
        } catch (DuplicateElementException ex) {
            assert false;
        }
    }

    @Test
    void getAll() {
        MemoryRepository<Programare> repo = new MemoryRepository();
        try {
            Programare p = new Programare(1, "Popescu", "12.12.2020", "12:00", "Consultatie");
            Programare p_new = new Programare(2, "Mihailescu", "10.12.2020", "11:00", "Consultatie");
            repo.add(p);
            repo.add(p_new);
            ArrayList<Programare> list = new ArrayList<>();
            list.add(p);
            list.add(p_new);
            assert repo.getAll().equals(list);
        } catch (DuplicateElementException ex) {
            assert false;
        }
    }

    @Test
    void getAt() {
        MemoryRepository<Programare> repo = new MemoryRepository();
        try {
            Programare p = new Programare(1, "Popescu", "12.12.2020", "12:00", "Consultatie");
            Programare p_new = new Programare(2, "Mihailescu", "10.12.2020", "11:00", "Consultatie");
            repo.add(p);
            repo.add(p_new);
            assert repo.getAt(1).equals(p_new);
        } catch (DuplicateElementException ex) {
            assert false;
        }

        try {
            repo.getAt(repo.size() + 1);
        } catch (IllegalArgumentException e) {
            assert true;
        }

        try {
            repo.getAt(-1);
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    @Test
    void iterator() {
        MemoryRepository<Programare> repo = new MemoryRepository();

        try {
            Programare p = new Programare(1, "Popescu", "12.12.2020", "12:00", "Consultatie");
            repo.add(p);

            Iterator<Programare> it = repo.iterator();


            assert it.hasNext();
            assertEquals(p, it.next());

        } catch (DuplicateElementException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void size(){
        MemoryRepository<Programare> repo = new MemoryRepository();
        assert repo.size() == 0;
    }
}