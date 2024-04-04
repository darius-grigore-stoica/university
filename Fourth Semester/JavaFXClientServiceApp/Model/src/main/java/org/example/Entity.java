package org.example;

public interface Entity<ID> {
    void setEntityID(ID id);
    ID getEntityID();
}
