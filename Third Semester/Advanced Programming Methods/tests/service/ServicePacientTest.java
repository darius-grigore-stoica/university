package service;

import domain.Pacient;
import exceptions.DuplicateElementException;
import org.junit.jupiter.api.Test;
import repository.MemoryRepository;
import service.ServicePacient;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ServicePacientTest {

    @Test
    void addPacient() {
        MemoryRepository<Pacient> pacientMemoryRepository = new MemoryRepository<>();
        ServicePacient servicePacient = new ServicePacient(pacientMemoryRepository);
        try {
            Pacient p = new Pacient(1, "Stoica", "Darius", 20);
            servicePacient.addPacient(1, "Stoica", "Darius", 20);
            ArrayList<Pacient> pacienti = (ArrayList<Pacient>)servicePacient.getAll();
            assert pacienti.get(0).getId() == p.getId();
            servicePacient.addPacient(1, "Stoica", "Darius", 20);
        } catch (DuplicateElementException e) {
            assert true;
        }
    }

    @Test
    void updatePacient() {
        MemoryRepository<Pacient> pacientMemoryRepository = new MemoryRepository<>();
        ServicePacient servicePacient = new ServicePacient(pacientMemoryRepository);
        try {
            servicePacient.addPacient(1, "Stoica", "Darius", 20);
            servicePacient.updatePacient(0, "Mihai", "Popescu", 20);
            ArrayList<Pacient> pacienti = (ArrayList<Pacient>)servicePacient.getAll();
            assert Objects.equals(pacienti.get(0).getNume(), "Mihai");
            servicePacient.updatePacient(-1, "", "", 0);
        } catch (IllegalArgumentException e) {
            assert true;
        } catch (DuplicateElementException e){
            assert false;
        }
    }

    @Test
    void deletePacient() {
        MemoryRepository<Pacient> pacientMemoryRepository = new MemoryRepository<>();
        ServicePacient servicePacient = new ServicePacient(pacientMemoryRepository);
        try {
            servicePacient.addPacient(1, "Stoica", "Darius", 20);
            servicePacient.addPacient(2, "Popescu", "Mihai", 20);
            servicePacient.deletePacient(1);
            ArrayList<Pacient> pacienti = (ArrayList<Pacient>) servicePacient.getAll();
            assert Objects.equals(pacienti.get(0).getNume(), "Popescu");
            servicePacient.deletePacient(-1);
        } catch (DuplicateElementException e){
            assert false;
        }
    }

    @Test
    void getAll() {
        MemoryRepository<Pacient> pacientMemoryRepository = new MemoryRepository<>();
        ServicePacient servicePacient = new ServicePacient(pacientMemoryRepository);
        try {
            pacientMemoryRepository.add(new Pacient(1, "Stoica", "Darius", 20));
            servicePacient.addPacient(1, "Stoica", "Darius", 20);
            assert servicePacient.getAll() == pacientMemoryRepository.getAll();
        } catch (DuplicateElementException e){
            assert true;
        }
    }
}