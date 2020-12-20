import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.Period;

public class File {
    ArrayList<Employee> load(String fileName) {
        ArrayList<Employee> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line; // A variable to store line from file
            // Loop to go through the entire file
            while ((line = br.readLine()) != null) {
                String[] data = line.split(","); // Split the data to variables, marked by ","
                // Name, Surname, Gender, Birthdate(yyyy-mm-dd), ID, remHolidays, reqHolidays
                if (line.matches(
                        "[\\w '\\-,.]+,[\\w ,'\\-.]+,(Male|Female|Other),\\d{4}-\\d{2}-\\d{2},\\d+,\\d+,\\d+")) {
                    Employee employee = new Employee(data[0], data[1], data[2], data[3], data[4], data[5], data[6]);
                    list.add(employee); // Call new employee and
                    // pass the array as data
                } else {
                    System.out.println("Warning: \"" + line + "\" is not in the correct format, or courrpted.");
                }
            }
            return list;
        }

        catch (

        Exception e) // Catch error
        {
            System.out.println(e.toString());
        }
        return null;
    }

    // Save the linkedlists in a readable format as .csv
    void save(ArrayList<Employee> list, String fileName) {
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
}