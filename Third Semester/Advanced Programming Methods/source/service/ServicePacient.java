package service;

import domain.Pacient;
import exceptions.DuplicateElementException;
import repository.IRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class ServicePacient {

    IRepository<Pacient> pacients;

    public ServicePacient(IRepository<Pacient> pacients) {
        this.pacients = pacients;
    }

    public void addPacient(int id, String name, String surname, int age) throws DuplicateElementException {
        try {
            pacients.add(new Pacient(id, name, surname, age));
        } catch (DuplicateElementException dee) {
            throw new DuplicateElementException(dee.getMessage());
        }
    }

    public void updatePacient(int pos, String name, String surname, int age) {
        try {
            pacients.update(pos, new Pacient(pacients.getAt(pos).getId(), name, surname, age));
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(iae.getMessage());
        }
    }

    public void deletePacient(int id) {
        pacients.delete(id);
    }

    public Collection<Pacient> getAll() {
        return pacients.getAll();
    }
}
