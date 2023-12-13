package service;

import domain.Pacient;
import domain.Programare;
import exceptions.DuplicateElementException;
import repository.IRepository;

import java.util.Collection;

public class ServiceProgramare {

    IRepository<Programare> appoinments;

    public ServiceProgramare(IRepository<Programare> appoinments) {
        this.appoinments = appoinments;

    }

    public void addProgramare(int id, String p, String data, String ora, String scop) throws DuplicateElementException {
        try {
            appoinments.add(new Programare(id, p, data, ora, scop));
        } catch (DuplicateElementException dee) {
            throw new DuplicateElementException(dee.getMessage());
        }
    }

    public void updateProgramare(int pos, String p, String data, String ora, String scop) {
        try {
            appoinments.update(pos, new Programare(appoinments.getAt(pos).getId(), p, data, ora, scop));
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(iae.getMessage());
        }
    }

    public void deleteProgramare(int id) {
        appoinments.delete(id);
    }

    public Collection<Programare> getAll() {
        return appoinments.getAll();
    }
}


