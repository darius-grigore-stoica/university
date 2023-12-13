package UI;

import domain.Pacient;
import domain.Programare;
import exceptions.DuplicateElementException;
import service.ServicePacient;
import service.ServiceProgramare;

import java.util.*;

public class UI {

    private final ServicePacient servicePacient;
    private final ServiceProgramare serviceProgramare;

    public UI(ServicePacient servicePacient, ServiceProgramare serviceProgramare) {
        this.servicePacient = servicePacient;
        this.serviceProgramare = serviceProgramare;
    }

    public void run_menu() {
        Scanner myObj = new Scanner(System.in);
        print_menu();
        String optiune = myObj.nextLine();
        while (!Objects.equals(optiune, "x")) {
            switch (optiune) {
                case "1":
                    System.out.println("Give id:");
                    try {
                        int id = Integer.parseInt(String.valueOf(myObj.nextLine()));
                        System.out.println("Give name:");
                        String nume = myObj.nextLine();
                        System.out.println("Give surname:");
                        String prenume = myObj.nextLine();
                        System.out.println("Give age:");
                        int varsta = Integer.parseInt(String.valueOf(myObj.nextLine()));
                        servicePacient.addPacient(id, nume, prenume, varsta);
                    } catch (NumberFormatException | DuplicateElementException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":
                    System.out.println("Give ID:");
                    try {
                        int id = Integer.parseInt(String.valueOf(myObj.nextLine()));
                        System.out.println("Give patient's name:");
                        String pacient = myObj.nextLine();
                        System.out.println("Give date (DD-MM-YYYY):");
                        String data = myObj.nextLine();
                        System.out.println("Give hour:");
                        String ora = myObj.nextLine();
                        System.out.println("Give purpose:");
                        String scop_programare = myObj.nextLine();
                        serviceProgramare.addProgramare(id, pacient, data, ora, scop_programare);
                    } catch (NumberFormatException | DuplicateElementException dee) {
                        System.out.println(dee.getMessage());
                    }
                    break;
                case "3":
                    Collection<Pacient> pacienti = servicePacient.getAll();
                    for (Pacient p : pacienti)
                        System.out.println(p);
                    break;
                case "4":
                    try {
                        Collection<Programare> programari = serviceProgramare.getAll();
                        for (Programare p : programari)
                            System.out.println(p);
                    } catch (NullPointerException npe){
                        System.out.println(npe);
                    }
                    break;
                case "5":
                    System.out.println("Give position");
                    try {
                        int pos = Integer.parseInt(String.valueOf(myObj.nextLine()));
                        System.out.println("Give new patient data");
                        System.out.println("Give name:");
                        String nume = myObj.nextLine();
                        System.out.println("Give surname:");
                        String prenume = myObj.nextLine();
                        System.out.println("Give age:");
                        int varsta = Integer.parseInt(String.valueOf(myObj.nextLine()));
                        servicePacient.updatePacient(pos, nume, prenume, varsta);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "6":
                    System.out.println("Give position:");
                    try {
                        int pos = Integer.parseInt(String.valueOf(myObj.nextLine()));
                        System.out.println("Give new appointment data:");
                        System.out.println("Give patient's name:");
                        String pacient = myObj.nextLine();
                        System.out.println("Give date (DD-MM-YYYY):");
                        String data = myObj.nextLine();
                        System.out.println("Give hour:");
                        String ora = myObj.nextLine();
                        System.out.println("Give purpose:");
                        String scop_programare = myObj.nextLine();
                        serviceProgramare.updateProgramare(pos, pacient, data, ora, scop_programare);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "7":
                    try {
                        System.out.println("Give ID:");
                        int id = Integer.parseInt(String.valueOf(myObj.nextLine()));
                        servicePacient.deletePacient(id);
                    }catch (NumberFormatException nfe){
                        System.out.println(nfe.getMessage());
                    }
                    break;
                case "8":
                    try {
                        System.out.println("Give ID:");
                        int id = Integer.parseInt(String.valueOf(myObj.nextLine()));
                        serviceProgramare.deleteProgramare(id);
                    }catch (NumberFormatException nfe){
                        System.out.println(nfe.getMessage());
                    }
                    break;
                case "x":
                    break;
            }
            print_menu();
            optiune = myObj.nextLine();
        }
    }

    public void print_menu() {
        String message = "";
        message += "1. Add a patient\n";
        message += "2. Add an appointment\n";
        message += "3. Print all patients\n";
        message += "4. Print all appointments\n";
        message += "5. Update a patient\n";
        message += "6. Update an appointment\n";
        message += "7. Delete a patient\n";
        message += "8. Delete an appointment\n";
        message += "x. Exit\n";
        message += "Option:";
        System.out.println(message);
    }
}
