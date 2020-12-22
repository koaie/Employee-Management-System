import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.*;

import java.io.File;
import java.net.URL;
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
        String input = dialog("id");
        table.getItems().forEach(e -> {
            if (e.getId().equals(input)) {
                System.out.println(e.toString());
                alret(AlertType.INFORMATION, "Employee found", e.toString());
            }
        });
    }

    @FXML
    void add(ActionEvent event) {
        try {
            Employee e = new Employee();
            e.setName(dialog("name"));
            e.setSurname(dialog("Surname"));
            e.setBirthdate(dialog("Birthday"));
            e.setGender(dialog("Gender"));
            e.setAge(e.calcAge());
            e.setId(String.valueOf(table.getItems().size() + 1));
            e.setRemHolidays(dialog("Remaining Holidays"));
            e.setReqHolidays(dialog("Requested Holidays"));
            if (e.valid()) {
                table.getItems().add(e);
            } else {
                alret(AlertType.ERROR, "Invalid employee", VALIDATE_INPUT);
            }
        } catch (Exception e) {
            alret(AlertType.ERROR, VALIDATE_INPUT, e.toString());
        }
    }

    @FXML
    void remove(ActionEvent event) {
        table.getItems().removeAll(table.getSelectionModel().getSelectedItems());
    }

    @FXML
    void refresh(ActionEvent event) {

        table.getItems().clear(); // Remove All data
        table.setItems(FXCollections.observableArrayList(fileMgt.load(empRegex, fileName))); // Add data to table
    }

    String dialog(String input) {
        try {
            TextInputDialog dialog = new TextInputDialog("");

            dialog.setTitle(input);
            dialog.setHeaderText("Enter your " + input + ":");
            dialog.setContentText(input + ":");

            return dialog.showAndWait().get();
        } catch (Exception e) {
            alret(AlertType.ERROR, VALIDATE_INPUT, e.toString());
        }
        return "";
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
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        birthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        reqHolidays.setCellValueFactory(new PropertyValueFactory<>("reqHolidays"));
        remHolidays.setCellValueFactory(new PropertyValueFactory<>("remHolidays"));

        list.set(fileMgt.load(empRegex, fileName));
        table.setItems(FXCollections.observableArrayList(list.get())); // Load data to table
    }
}