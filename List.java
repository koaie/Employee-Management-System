import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.Period;

public class List {
    private ArrayList<Employee> list = new ArrayList<>(); // Declaring an arraylist to store linkedlists of employees

        // Create a new employee with the format:
    // Name,Surname,Gender,Birthdate,ID,Course,Result,Status
    void add(String name, String surname, String gender, String birthDate, String id, String remHolidays,
            String reqHolidays) {
        list.add(new Employee(name, surname, gender, birthDate, id, remHolidays, reqHolidays));
    }

    ArrayList<Employee> get() {
        return list; // Return a specific index
    }

    void set(ArrayList<Employee> list)
    {
        this.list = list;
    }

    void remove(int index) {
        list.remove(index); // Removes the linkedlist of specified index
    }

    Employee get(int index) {
        return list.get(index); // Return a specific index
    }

    int total() {
        return list.size(); // Return the size of Employelist
    }
}