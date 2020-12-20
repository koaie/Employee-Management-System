import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private ArrayList<Employee> list = new ArrayList<>(); // Declaring an arraylist to store linkedlists of employees
    private String fileName = "./data.csv"; // Default File to Load/Save from/to


    @FXML
    private TableView<Employee> table;

    @FXML
    public TableColumn<Employee, String> name;

    @FXML
    public TableColumn<Employee, String> surname;

    @FXML
    public TableColumn<Employee, String> gender;

    @FXML
    public TableColumn<Employee, String> birthdate;

    @FXML
    public TableColumn<Employee, String> age;

    @FXML
    public TableColumn<Employee, String> id;

    String load() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line; // A variable to store line from file
            // Loop to go through the entire file
            while ((line = br.readLine()) != null) {
                String[] data = line.split(","); // Split the data to variables, marked by ","
                // Name, Surname, Gender, Birthdate(yyyy-mm-dd), ID, remHolidays, reqHolidays
                if (line.matches(
                        "[\\w '\\-,.]+,[\\w ,'\\-.]+,(Male|Female|Other),\\d{4}-\\d{2}-\\d{2},\\d+,\\d+,\\d+")) {
                    add(data[0], data[1], data[2], data[3], data[4], data[5], data[6]); // Call new employee and
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

    String load(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line; // A variable to store line from file
            // Loop to go through the entire file
            while ((line = br.readLine()) != null) {
                String[] data = line.split(","); // Split the data to variables, marked by ","
                // Name, Surname, Gender, Birthdate(yyyy-mm-dd), ID, remHolidays, reqHolidays
                if (line.matches(
                        "[\\w '\\-,.]+,[\\w ,'\\-.]+,(Male|Female|Other),\\d{4}-\\d{2}-\\d{2},\\d+,\\d+,\\d+")) {
                    add(data[0], data[1], data[2], data[3], data[4], data[5], data[6]); // Call new employee and
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
        // Write each node to file in the correct format
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    // If last element dont inset a comma, otherwise insert comma
                    if (j == list.size() - 1) {
                        bw.write(list.get(i).toString());
                    } else {
                        bw.write(list.get(i).toString() + ",");
                    }
                }
                bw.write("\n"); // Insert newline to file
            }
        } catch (Exception e) // Catch error
        {
            System.out.println(e.toString()); // Print error
        }
    }

    // Create a new employee with the format:
    // Name,Surname,Gender,Birthdate,ID,Course,Result,Status
    void add(String name, String surname, String gender, String birthDate, String id, String remHolidays,
            String reqHolidays) {
        list.add(new Employee(name, surname, gender, birthDate, id, remHolidays, reqHolidays));
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

    // Set the node that contains the age value with calculated age based on
    // birthdate node and local time.

    // Get name
    String name(int index) {
        return list.get(index).getName();
    }

    // Get surname
    String surname(int index) {
        return list.get(index).getSurname();
    }

    // Get gender
    String gender(int index) {
        return list.get(index).getGender();
    }

    // Get birthdate
    String birthdate(int index) {
        return list.get(index).getBirthdate();
    }

    // Get age
    int age(int index) {
        return list.get(index).getAge();
    }

    // Get id
    String id(int index) {
        return list.get(index).getId();
    }

    // Set name
    void setName(int index, String name) {
        list.get(index).setName(name);
    }

    // Set surname
    void setSurname(int index, String surname) {
        list.get(index).setSurname(surname);
    }

    // Set gender
    void setGender(int index, String gender) {
        list.get(index).setGender(gender);
    }

    // Set birthdate
    void setBirthdate(int index, String birthdate) {
        list.get(index).setBirthdate(birthdate);
    }

    // Set age
    void setAge(int index, String birthday) {
        int age = calculateAge(birthday);
        list.get(index).setAge(age);
    }

    // Set id
    void setId(int index, String id) {
        list.get(index).setId(id);
    }

    // Calculate age based on provided birthdate and localtime
    int calculateAge(String birthdate) {
        return Period.between(LocalDate.parse(birthdate), LocalDate.now()).getYears();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        birthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));


        load(); // Load data to array
        table.setItems(FXCollections.observableArrayList(list)); // Add data to table
    }

}