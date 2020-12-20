import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private String fileName = "./data.csv"; // Default File to Load/Save from/to
    File file = new File();
    List list = new List();


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

    void refresh()
    {
        table.getItems().clear(); // Remove All data
        table.setItems(FXCollections.observableArrayList(list.get())); // Add data to table
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        birthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        table.setItems(FXCollections.observableArrayList(file.load(fileName))); // Load data to table
    }

}