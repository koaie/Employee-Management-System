import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private static final String VALIDATE_INPUT = "Please validate your inputs";
    private String fileName = "./data.csv"; // Default File to Load/Save from/to
    private FileMgt fileMgt = new FileMgt();
    private EmployeeMgt list = new EmployeeMgt();
    private String empRegex = "[\\w '\\-,.]+,[\\w ,'\\-.]+,(Male|Female|Other),\\d{4}-\\d{2}-\\d{2},\\d+,\\d+,\\d+";

    @FXML
    private TableView<Employee> table;

    @FXML
    private TableColumn<Employee, String> name;

    @FXML
    private TableColumn<Employee, String> surname;

    @FXML
    private TableColumn<Employee, String> gender;

    @FXML
    private TableColumn<Employee, String> birthdate;

    @FXML
    private TableColumn<Employee, String> age;

    @FXML
    private TableColumn<Employee, String> id;

    @FXML
    private TableColumn<Employee, String> reqHolidays;

    @FXML
    private TableColumn<Employee, String> remHolidays;

    @FXML
    void load(ActionEvent event) {
        Stage stage = (Stage) table.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        fileName = file.toString();

        table.getItems().clear(); // Remove All data
        table.setItems(FXCollections.observableArrayList(fileMgt.load(empRegex, fileName))); // Load data to table
    }

    @FXML
    void save(ActionEvent event) {
        Stage stage = (Stage) table.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);

        fileMgt.save(table.getItems(), file.toString()); // Load data to table
    }

    @FXML
    void search(ActionEvent event) {
        String input = dialog("id"); // Input prompt

        table.getItems().forEach(e -> { // Go through table and compare to input
            if (e.getId().equals(input)) {
                alret(AlertType.INFORMATION, "Employee found", e.toString());
            }
        });
    }

    @FXML
    void add(ActionEvent event) {
        try {
            Employee e = new Employee(); // Create temporary employee
            String input = ""; // Create input variable

            // Loop through requests as long as input isnt empty
            while (input != null) {
                input = dialog("name");
                e.setName(input);
                input = dialog("Surname");
                e.setSurname(input);
                input = dialog("Birthday");
                e.setBirthdate(input);
                e.setGender(dialog("Gender"));
                e.setAge(e.calcAge());
                e.setId(String.valueOf(table.getItems().size() + 1));
                e.setRemHolidays(dialog("Remaining Holidays"));
                e.setReqHolidays(dialog("Requested Holidays"));
                if (e.valid()) { // Validate input with regex
                    table.getItems().add(e);
                } else {
                    alret(AlertType.ERROR, "Invalid employee", VALIDATE_INPUT);
                }
            }
        } catch (Exception e) {
            // Show error if exception is caught
            alret(AlertType.ERROR, VALIDATE_INPUT, e.toString());
        }
    }

    @FXML
    void remove(ActionEvent event) {
        table.getItems().removeAll(table.getSelectionModel().getSelectedItems()); // Remove selected employee from table
    }

    @FXML
    void refresh(ActionEvent event) {

        table.getItems().clear(); // Remove All data
        table.setItems(FXCollections.observableArrayList(fileMgt.load(empRegex, fileName))); // Add data to table
    }

    @FXML
    private void holiday(ActionEvent event) {
        int index = table.getSelectionModel().getSelectedIndex(); // Get current selected employee index
        Employee e = table.getItems().get(index); // Get current employee from index

        int rem = Integer.parseInt(e.getRemHolidays()); // Get current remaining holidays
        int req = Integer.parseInt(e.getReqHolidays()); // Get current requested holidays

        String input = dialog("How many days off would you like to take"); // Input prompt
        try {
            if (input.matches("\\d+")) { // Validate input
                int in = Integer.parseInt(input);
                if (in <= rem) { // If there is enough remaining holidays
                    e.setRemHolidays(String.valueOf(rem - in));
                    e.setReqHolidays(String.valueOf(req + in));
                    table.getItems().set(index, e); // Rectify table to amend for the accepted holiday
                } else {
                    alret(AlertType.ERROR, "Error!", "Input exceeds remaning holidays");
                }
            }
        } catch (Exception exp) {
            // Show error if exception is caught
            alret(AlertType.ERROR, VALIDATE_INPUT, exp.toString());
        }
    }

    String dialog(String input) {
        try {
            TextInputDialog dialog = new TextInputDialog("");

            dialog.setTitle(input);
            dialog.setHeaderText("Enter your " + input + ":");
            dialog.setContentText(input + ":");
            Optional results = dialog.showAndWait();
            if (results.isPresent()) {
                return results.get().toString();
            }
        } catch (Exception e) {
            alret(AlertType.ERROR, VALIDATE_INPUT, e.toString());
        }
        return null;
    }

    void alret(Alert.AlertType type, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Get table data
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        birthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        reqHolidays.setCellValueFactory(new PropertyValueFactory<>("reqHolidays"));
        remHolidays.setCellValueFactory(new PropertyValueFactory<>("remHolidays"));

        list.set(fileMgt.load(empRegex, fileName)); // Load data to employee list
        table.setItems(FXCollections.observableArrayList(list.get())); // Load data to table
    }
}