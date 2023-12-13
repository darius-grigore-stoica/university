package service;

import domain.Pacient;
import domain.Programare;
import exceptions.DuplicateElementException;
import javafx.util.Pair;
import repository.IRepository;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;

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

    /**
     * Return a pair of Pacient and the number of appointments for that pacient
     * @param pacient
     * @return
     */
    public Pair<Pacient, Integer> getNoAppointmentsForAPacient(Pacient pacient) {
        Collection<Programare> programares = appoinments.getAll();
        Integer noAppointment = programares.stream().filter(programare -> {
            return programare.getNumePacient().equals(pacient.getNume());
        }).toArray().length;
        return new Pair<>(pacient, noAppointment);
    }

    /**
     * Return the number of appointments for a given month
     * @param month
     * @return
     */
    public Integer getAllAppointmentsInMonth(String month) {
       Collection<Programare> programares = appoinments.getAll();
       Integer noAppointment = programares.stream().filter(programare -> {
           return programare.getData().split("/")[1].equals(month);
       }).toArray().length;
       return noAppointment;
    }

    /**
     * Return a list of pairs of months and the number of appointments for that month sorted descending by the number of appointments
     * @return
     */
    public ArrayList<Pair<String, Integer>> getAllApointmentsByMonth(){
        Collection<String> months = Arrays.asList("01","02","03","04","05","06","07","08","09","10","11","12");
        ArrayList<Pair<String, Integer>> res = new ArrayList<>();
        months.forEach(month -> {
            Integer appointments = getAllAppointmentsInMonth(month);
            res.add(new Pair<>(month, appointments));
        });
        res.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        return res;
    }

    /**
     * Return the number of days since the last appointment for a given pacient
     * @param p
     * @return
     */
    public int getNoOfDaysSinceLastAppointment(Pacient p){
        Collection<Programare> programares = appoinments.getAll();
        ArrayList<Programare> appointments = new ArrayList<>();
        programares.stream().filter(programare -> {
            return programare.getNumePacient().equals(p.getNume());
        }).forEach(programare -> {
            appointments.add(programare);
        });
        appointments.sort((o1, o2) -> o2.getData().compareTo(o1.getData()));
        if(appointments.size() == 0)
            return 0;
        else{
            LocalDate lastAppointment = LocalDate.parse(appointments.get(0).getData());
            LocalDate today = LocalDate.now();
            return (int) (today.toEpochDay() - lastAppointment.toEpochDay());
        }
    }
}


