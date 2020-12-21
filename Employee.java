import java.time.LocalDate;
import java.time.Period;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;

public class Employee implements Holidays {
    private StringProperty name;
    private StringProperty surname;
    private StringProperty gender;
    private StringProperty birthdate;
    private IntegerProperty age;
    private StringProperty id;
    private StringProperty remHolidays;
    private StringProperty reqHolidays;

    // Add new student
    Employee(String name, String surname, String gender, String birthDate, String id, String remHolidays,
            String reqHolidays) {
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.gender = new SimpleStringProperty(gender);
        this.birthdate = new SimpleStringProperty(birthDate);
        this.age = new SimpleIntegerProperty(calcAge());
        this.id = new SimpleStringProperty(id);
        this.remHolidays = new SimpleStringProperty(remHolidays);
        this.reqHolidays = new SimpleStringProperty(reqHolidays);
    }

    public Employee() {
    }

    public String getName() {
        return this.name.get();
    }

    public String getSurname() {
        return this.surname.get();
    }

    public String getGender() {
        return this.gender.get();
    }

    public String getBirthdate() {
        return this.birthdate.get();
    }

    public int getAge() {
        return this.age.get();
    }

    public String getId() {
        return this.id.get();
    }

    @Override // Override the getRemHolidays function from IStudent
    public String getRemHolidays() {
        return remHolidays.get(); // Return result node
    }

    @Override // Override the getReqHolidays function from IStudent
    public String getReqHolidays() {
        return reqHolidays.get(); // Return status node
    }

    public Employee setName(String name) {
        this.name = new SimpleStringProperty(name);
        return this;
    }

    public Employee setSurname(String surname) {
        this.surname = new SimpleStringProperty(surname);
        return this;
    }

    public Employee setGender(String gender) {
        this.gender = new SimpleStringProperty(gender);
        return this;
    }

    public Employee setBirthdate(String birthDate) {
        this.birthdate = new SimpleStringProperty(birthDate);
        return this;
    }

    public Employee setAge(int age) {
        this.age = new SimpleIntegerProperty(age);
        return this;
    }

    public Employee setId(String id) {
        this.id = new SimpleStringProperty(id);
        return this;
    }

    public Employee setRemHolidays(String remHolidays) {
        this.remHolidays = new SimpleStringProperty(remHolidays);
        return this;
    }

    public Employee setReqHolidays(String reqHolidays) {
        this.reqHolidays = new SimpleStringProperty(reqHolidays);
        return this;
    }

    int calcAge() {
        return Period.between(LocalDate.parse(birthdate.get()), LocalDate.now()).getYears();
    }

    int calcAge(String birthdate) {
        return Period.between(LocalDate.parse(birthdate), LocalDate.now()).getYears();
    }

    boolean valid() {
        return (getName().matches("[A-Za-z]+") && getSurname().matches("[A-Za-z]+") && getGender().matches("(Male|Female|Other)")
        && getBirthdate().matches("\\d{4}-\\d{2}-\\d{2}") && getId().matches("\\d+") && getRemHolidays().matches("\\d+")
        && getReqHolidays().matches("\\d+"));
    }

    @Override
    public String toString() {
        return getName() + "," + getSurname() + "," + getGender() + "," + getBirthdate() + "," + getId() + ","
                + getRemHolidays() + "," + getReqHolidays();
    }

}
