import java.util.ArrayList;

public class EmployeeMgt
{
    private ArrayList<Employee> list = new ArrayList<>(); // Declaring an arraylist to store employees

    ArrayList<Employee> get() {
        return list; // Return a specific index
    }

    void set(ArrayList<Employee> list)
    {
        this.list = list;
    }

    void remove(int index) {
        list.remove(index); // Removes specified index
    }

    Employee get(int index) {
        return list.get(index); // Return a specific index
    }

    int total() {
        return list.size(); // Return the size of Employelist
    }
}