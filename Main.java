import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.Period;

class Main {
    public static void main(String[] args) {
        // Calling EDT
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true); // Declaring menu, calling menu, and set frame to visable
            }
        });
    }
}

class List {
    ArrayList<Employee> eList = new ArrayList<>(); // Declaring an arraylist to store linkedlists of employees
    String fileName = "./employees.csv"; // File to Load/Save from/to
    // declaring variables for node index values of data.
    int namePos = 0, surnamePos = 1, genderPos = 2, birthDatePos = 3, agePos = 4, idPos = 5, remHoliday = 6,
            reqHoliday = 7;

    String load(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line; // A variable to store line from file
            // Loop to go through the entire file
            while ((line = br.readLine()) != null) {
                String[] data = line.split(","); // Split the data to variables, marked by ","
                // Name, Surname, Gender, Birthdate(yyyy-mm-dd), ID, remHolidays, reqHolidays
                if (line.matches("[\\w ',.]+,[\\w ,.]+,(Male|Female|Other),\\d{4}-\\d{2}-\\d{2},\\d+,\\d+,\\d+")) {
                    nEmployee(data[0], data[1], data[2], data[3], data[4], data[5], data[6]); // Call new employee and
                                                                                              // pass the array as data
                } else {
                    System.out.println("Warning: \"" + line + "\" is not in the correct format, or courrpted.");
                }
            }
        }

        catch (

        Exception e) // Catch error
        {
            System.out.println(e.toString());
        }
        return "";
    }

    // Save the linkedlists in a readable format as .csv
    void save(String fileName) {
        globalDelNode(agePos); // Delete age node from all linked lists
        // Write each node to file in the correct format
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < eList.size(); i++) {
                for (int j = 0; j < eList.get(i).info.size(); j++) {
                    // If last element dont inset a comma, otherwise insert comma
                    if (j == eList.get(i).info.size() - 1) {
                        bw.write(eList.get(i).info.get(j));
                    } else {
                        bw.write(eList.get(i).info.get(j) + ",");
                    }
                }
                bw.write("\n"); // Insert newline to file
                insertAge(i); // Readd the age node
            }
        } catch (Exception e) // Catch error
        {
            System.out.println(e.toString()); // Print error
        }
    }

    // Create a new employee with the format:
    // Name,Surname,Gender,Birthdate,ID,Course,Result,Status
    void nEmployee(String name, String surname, String gender, String birthDate, String ID, String remHolidays,
            String reqHolidays) {
        eList.add(new Employee(name, surname, gender, birthDate, ID, remHolidays, reqHolidays));
    }

    void rEmployee(int index) {
        eList.remove(index); // Removes the linkedlist of specified index
    }

    int totalEmployees() {
        return eList.size(); // Return the size of EmployeeList
    }

    void setNode(int index, int node, String value) {
        eList.get(index).setNode(node, value); // Call a specific linkedlist, and change a specific node
    }

    void delNode(int index, int node) {
        eList.get(index).delNode(node); // Call a specific linkedlist, and delete a specific node
    }

    // Delete a specific nodes in all linkedlists
    void globalDelNode(int node) {
        for (int i = 0; i < eList.size(); i++) {
            eList.get(i).delNode(node);
        }
    }

    String getNode(int index, int node) {
        return eList.get(index).getNode(node); // Get the value of a specific linkedlist node
    }

    Object[] returnArray(int index) // Return a specific linkedlist as an array
    {
        return eList.get(index).info.toArray();
    }

    Employee returnIndex(int index) // Return a specific linkedlist
    {
        return eList.get(index);
    }

    // Set the node that contains the age value with calculated age based on
    // birthdate node and local time.
    void setAge(int index) {
        setNode(index, agePos, Integer
                .toString(Period.between(LocalDate.parse(getNode(index, birthDatePos)), LocalDate.now()).getYears()));
    }

    // Inserts a node that contains the age value with calculated age based on
    // birthdate node and local time.
    void insertAge(int index) {
        eList.get(index).addNode(agePos, Integer
                .toString(Period.between(LocalDate.parse(getNode(index, birthDatePos)), LocalDate.now()).getYears()));
    }

    // Calculate age based on provided birthdate and localtime
    String calculateAge(String birthdate) {
        return Integer.toString(Period.between(LocalDate.parse(birthdate), LocalDate.now()).getYears());
    }

    void exit() {
        System.exit(0); // Exit the program
    }
}

interface Holidays {
    String getRemHolidays();
    String getReqHolidays();
}

class Employee extends Person implements Holidays {
    // Add new student
    Employee(String name, String surname, String gender, String birthDate, String ID, String remHolidays,
            String reqHolidays) {
        info.add(name);
        info.add(surname);
        info.add(gender);
        info.add(birthDate);
        info.add(Integer.toString(Period.between(LocalDate.parse(birthDate), LocalDate.now()).getYears()));
        info.add(ID);
        info.add(remHolidays);
        info.add(reqHolidays);
    }

    // Declaring variables for node index values of data.
    int namePos = 0, surnamePos = 1, genderPos = 2, birthDatePos = 3, agePos = 4, idPos = 5, remHolidayPos = 6,
            reqHolidayPos = 7;

    void setNode(int index, String value) {
        info.set(index, value); // Set a value of a specific node
    }

    void addNode(int index, String value) {
        info.add(index, value); // Insert a node
    }

    String getNode(int node) {
        return info.get(node); // Get a value of a specific node
    }

    String delNode(int node) {
        return info.remove(node); // Delete a specific node
    }

    @Override // Override the getRemHolidays function from IStudent
    public String getRemHolidays() {
        return info.get(remHolidayPos); // Return result node
    }

    @Override // Override the getReqHolidays function from IStudent
    public String getReqHolidays() {
        return info.get(reqHolidayPos); // Return status node
    }

    @Override // Override the toString function
    public String toString() {
        return info.toString(); // Return studentinfo as a string
    }
}

abstract class Person {
    protected LinkedList<String> info = new LinkedList<>(); // Create a linked list to storage information
}
