package service;

import domain.Pacient;
import domain.Programare;
import exceptions.DuplicateElementException;
import org.junit.jupiter.api.Test;
import repository.MemoryRepository;
import service.ServiceProgramare;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ServiceProgramareTest {

    @Test
    void addProgramare() {
        MemoryRepository<Programare> appointmentsMemoryRepository = new MemoryRepository<>();
        ServiceProgramare serviceProgramare = new ServiceProgramare(appointmentsMemoryRepository);
        try {
            Programare p = new Programare(1, "Stoica", "Darius", "20", "Consultatie");
            serviceProgramare.addProgramare(1, "Stoica", "Darius", "20", "Consultatie");
            ArrayList<Programare> appoinmentsAll = (ArrayList<Programare>)serviceProgramare.getAll();
            assert appoinmentsAll.get(0).getId() == p.getId();
            serviceProgramare.addProgramare(1, "Stoica", "Darius", "20", "Consultatie");
        } catch (DuplicateElementException e) {
            assert true;
        }
    }

    @Test
    void updateProgramare() {
        MemoryRepository<Programare> appointmentsMemoryRepository = new MemoryRepository<>();
        ServiceProgramare serviceProgramare = new ServiceProgramare(appointmentsMemoryRepository);
        try {
            try {
                serviceProgramare.addProgramare(1, "Stoica", "Darius", "20", "Consultatie");
                serviceProgramare.updateProgramare(0, "Mihai", "Popescu", "20", "Consultatie");
                ArrayList<Programare> appoinmentsAll = (ArrayList<Programare>)serviceProgramare.getAll();
                assert appoinmentsAll.get(0).getNumePacient().equals("Mihai");
                serviceProgramare.updateProgramare(-1, "", "", "", "");
            } catch (DuplicateElementException e) {
                throw new RuntimeException(e);
            }
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    @Test
    void deleteProgramare() {
        MemoryRepository<Programare> appointmentsMemoryRepository = new MemoryRepository<>();
        ServiceProgramare serviceProgramare = new ServiceProgramare(appointmentsMemoryRepository);
        try {
            try {
                serviceProgramare.addProgramare(1, "Stoica", "Darius", "20", "Consultatie");
                serviceProgramare.addProgramare(2, "Popescu", "Mihai", "20", "Consultatie");
                serviceProgramare.deleteProgramare(1);
                ArrayList<Programare> appoinmentsAll = (ArrayList<Programare>) serviceProgramare.getAll();
                assert Objects.equals(appoinmentsAll.get(0).getNumePacient(), "Popescu");
                serviceProgramare.deleteProgramare(-1);
            } catch (DuplicateElementException e) {
                assert false;
            }
        } catch (IllegalArgumentException e){
            assert true;
        }
    }
}