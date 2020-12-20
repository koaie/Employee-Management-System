import java.util.ArrayList;

public class List
{
    private ArrayList<Employee> data = new ArrayList<>(); // Declaring an arraylist to store linkedlists of employees

        // Create a new employee with the format:
    // Name,Surname,Gender,Birthdate,ID,Course,Result,Status
    void add(String name, String surname, String gender, String birthDate, String id, String remHolidays,
            String reqHolidays) {
        data.add(new Employee(name, surname, gender, birthDate, id, remHolidays, reqHolidays));
    }

    ArrayList<Employee> get() {
        return data; // Return a specific index
    }

    void set(ArrayList<Employee> data)
    {
        this.data = data;
    }

    void remove(int index) {
        data.remove(index); // Removes the linkedlist of specified index
    }

    Employee get(int index) {
        return data.get(index); // Return a specific index
    }

    int total() {
        return data.size(); // Return the size of Employelist
    }
}