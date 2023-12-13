package repository;

import domain.IEntityFactory;
import domain.Pacient;
import domain.PacientFactory;
import domain.Programare;
import exceptions.DuplicateElementException;
import org.junit.jupiter.api.Test;
import repository.TextFileRepository;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TextFileRepositoryTest {

    PacientFactory pacientFactory = new PacientFactory();

    @Test
    void add() {
        try {
            TextFileRepository testRepository = new TextFileRepository("pacienti.txt", pacientFactory);
            Pacient p = new Pacient(2, "Stoica", "Darius", 20);
            testRepository.add(p);
            assert testRepository.findById(2) == p;
        } catch (IOException e) {
            assert false;
        } catch (DuplicateElementException e) {
            assert true;
        }

        try {
            TextFileRepository testRepository = new TextFileRepository("pacienti_gresit.txt", pacientFactory);
        } catch (IOException e) {
            assert true;
        } catch (DuplicateElementException e) {
            assert false;
        }
    }

    @Test
    void update() {
        try {
            TextFileRepository testRepository = new TextFileRepository("pacienti.txt", pacientFactory);
            Pacient p = new Pacient(6, "Stoica", "Darius", 20);
            testRepository.update(0, p);
            assert testRepository.getAt(0) == p;
        } catch (IOException e) {
            assert false;
        } catch (DuplicateElementException e) {
            assert false;
        }

        try {
            TextFileRepository testRepository = new TextFileRepository("pacienti_gresit.txt", pacientFactory);
        } catch (IOException e) {
            assert true;
        } catch (DuplicateElementException e) {
            assert false;
        }
    }

    @Test
    void delete() {
        try {
            TextFileRepository testRepository = new TextFileRepository("pacienti.txt", pacientFactory);
            testRepository.add(new Pacient(1, "Stoica", "Darius", 20));
            testRepository.add(new Pacient(2, "Popescu", "Mihai", 20));
            int n = testRepository.size();
            testRepository.delete(1);
            assert testRepository.size() == n - 1;
            testRepository.delete(0);
            assert testRepository.size() == n - 2;
        } catch (IOException e) {
            assert false;
        } catch (DuplicateElementException e) {
            assert true;
        }

        try {
            TextFileRepository testRepository = new TextFileRepository("pacienti_gresit.txt", pacientFactory);
        } catch (IOException e) {
            assert true;
        } catch (DuplicateElementException e) {
            assert false;
        }
    }
}