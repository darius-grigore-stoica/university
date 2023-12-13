package repository;

import domain.Entity;
import exceptions.DuplicateElementException;
import repository.MemoryRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryFileRepository<T extends Entity> extends MemoryRepository<T> {
    private String fileName;

    public BinaryFileRepository(String fileName) throws IOException, ClassNotFoundException{
        this.fileName = fileName;
        loadFromFile();
    }

    public void saveToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(this.getAll());
        } catch (IOException e) {e.printStackTrace();
        }
    }

    public void add(T e) throws DuplicateElementException {
        try {
            super.add(e);
            saveToFile();} catch (DuplicateElementException ex) {throw new DuplicateElementException(ex.getMessage());}
    }

    public void delete(int id) {
        super.delete(id);
        saveToFile();
    }

    public void update(int pos, T e) {
        super.update(pos, e);
        saveToFile();
    }

    private void loadFromFile() throws IOException {
        File file = new File(fileName);
        if (file.exists()) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
                List<T> entities = (List<T>) inputStream.readObject();
                for (T e : entities) {
                    try {this.add(e);} catch (DuplicateElementException ex) {
                        System.out.println(ex.getMessage());}
                }
            } catch (EOFException e) {
                System.out.println(e.getMessage());} catch (IOException | ClassNotFoundException e) {System.out.println(e.getMessage());
            }} else {System.out.println("The file " + fileName + " does not exist or is empty.");
        }
    }
};
