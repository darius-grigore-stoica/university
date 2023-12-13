package domain;

import java.io.FileWriter;

public interface IEntityFactory<T extends Entity> {
    T createEntity(String line);

    String saveEntity(T entity);
}
