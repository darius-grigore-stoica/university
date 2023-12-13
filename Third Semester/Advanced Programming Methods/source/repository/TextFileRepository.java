package repository;

import domain.Entity;
import domain.IEntityFactory;
import domain.Pacient;
import exceptions.DuplicateElementException;

import java.io.*;
import java.util.Scanner;

public class TextFileRepository<T extends Entity> extends MemoryRepository<T> {
    private String fileName;
    private IEntityFactory<T> entityFactory;

    public TextFileRepository(String fileName, IEntityFactory<T> entityFactory) throws IOException, DuplicateElementException {
        this.fileName = fileName;
        this.entityFactory = entityFactory;
        loadFromFile();
    }

    private void loadFromFile() throws IOException, DuplicateElementException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            T entity = entityFactory.createEntity(line);
            add(entity);
            saveToFile();
        }
        scanner.close();
    }

    @Override
    public void add(T entity) throws DuplicateElementException {
        super.add(entity);
        try {
            saveToFile();} catch (IOException e) {throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int pos, T entity) {
        super.update(pos, entity);
        try {
            saveToFile();} catch (IOException e) {throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int pos) {
        super.delete(pos);
        try {
            saveToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveToFile() throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        for (T entity : getAll()) {
            String line = entityFactory.saveEntity(entity);
            if (line != null)
                fileWriter.write(line);
        }
        fileWriter.close();
    }
}
