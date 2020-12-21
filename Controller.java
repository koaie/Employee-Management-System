import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private String fileName = "./data.csv"; // Default File to Load/Save from/to
    FileMgt fileMgt = new FileMgt();
    EmployeeMgt list = new EmployeeMgt();
    String empRegex = "[\\w '\\-,.]+,[\\w ,'\\-.]+,(Male|Female|Other),\\d{4}-\\d{2}-\\d{2},\\d+,\\d+,\\d+";

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

    @FXML
    public Button load;

    @FXML
    public Button save;

    @FXML
    public Button refresh;

    @FXML
    public Button add;

    @FXML
    public Button remove;

    @FXML
    public Button edit;


    @FXML
    void load(ActionEvent event) {
        Stage stage = (Stage) save.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        fileName = file.toString();

        table.getItems().clear(); // Remove All data
        table.setItems(FXCollections.observableArrayList(fileMgt.load(empRegex, fileName))); // Load data to table
    }

    @FXML
    void save(ActionEvent event) {
        Stage stage = (Stage) save.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);

        table.getItems(); // Remove All data
        fileMgt.load(empRegex, file.toString()); // Load data to table
    }

    @FXML
    void refresh(ActionEvent event) {
        table.getItems().clear(); // Remove All data
        table.setItems(FXCollections.observableArrayList(fileMgt.load(empRegex, fileName))); // Add data to table
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        birthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        list.set(fileMgt.load(empRegex, fileName));
        table.setItems(FXCollections.observableArrayList(list.get())); // Load data to table
    }

}