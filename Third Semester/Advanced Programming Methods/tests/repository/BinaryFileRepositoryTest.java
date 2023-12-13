package repository;

import domain.Programare;
import exceptions.DuplicateElementException;
import org.junit.jupiter.api.Test;
import repository.BinaryFileRepository;

import java.io.*;
import java.util.ArrayList;

class BinaryFileRepositoryTest {

    private String fileName = "programari_test.bin";
    @Test
    void add() {
        try {
            BinaryFileRepository binaryRepository = new BinaryFileRepository(fileName);
            Programare p = new Programare(1, "Forman", "11-12-2023", "10:45", "blank");
            try {
                int n = binaryRepository.size();
                binaryRepository.add(p);
                assert binaryRepository.size() == n + 1;
            } catch (DuplicateElementException e) {
                assert false;
            }
        } catch (IOException e) {
            assert false;
        } catch (ClassNotFoundException e) {
            assert false;
        }
    }

    @Test
    void delete() {
        try {
            BinaryFileRepository binaryRepository = new BinaryFileRepository(fileName);
            Programare p = new Programare(1, "Forman", "11-12-2023", "10:45", "blank");
            try {
                binaryRepository.add(p);
                assert binaryRepository.size() == 1;
                binaryRepository.delete(1);
                assert binaryRepository.size() == 0;
            } catch (DuplicateElementException e) {
                assert false;
            }
        } catch (IOException e) {
            assert false;
        } catch (ClassNotFoundException e) {
            assert false;
        }
    }

    @Test
    void update() {
        try {
            BinaryFileRepository<Programare> binaryRepository = new BinaryFileRepository(fileName);
            try {
                Programare p = new Programare(1, "Forman", "11-12-2023", "10:45", "blank");
                Programare p2 = new Programare(2, "Popescu", "11-12-2023", "10:45", "blank");
                binaryRepository.add(p);
                binaryRepository.update(0, p2);
                assert binaryRepository.getAt(0).getNumePacient().equals("Popescu");
            } catch (DuplicateElementException e) {
                assert false;
            }
        } catch (IOException e) {
            assert false;
        } catch (ClassNotFoundException e) {
            assert false;
        }
    }

    @Test
    void saveToFile() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        } catch (IOException e) {
            assert true;
        }

        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(""));
        } catch (IOException e) {
            assert true;
        }
    }
}