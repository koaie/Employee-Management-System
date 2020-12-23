import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;


public class EmployeeTest {
    Employee e = new Employee();
    
    @Test
    public void nameTest() {
        String input = "Lmao";
        assertEquals(input, e.setName(input).getName());
    }

    @Test
    public void surnameTest() {
        String input = "Lmao";
        assertEquals(input, e.setSurname(input).getSurname());
    }

    @Test
    public void genderTest() {
        String input = "male";
        assertEquals(input, e.setGender(input).getGender());
    }

    @Test
    public void birthdateTest() {
        String input = "1990-10-10";
        assertEquals(input, e.setBirthdate(input).getBirthdate());
    }

    @Test
    public void ageTest() {
        int input = 19;
        assertEquals(input, e.setAge(input).getAge());
    }

    @Test
    public void reqHolidayTest() {
        String input = "20";
        assertEquals(input, e.setReqHolidays(input).getReqHolidays());
    }

    
    @Test
    public void remHolidayTest() {
        String input = "20";
        assertEquals(input, e.setRemHolidays(input).getRemHolidays());
    }


    @Test
    public void calcAgeTest() {
        String input = "1900-01-01";
        assertEquals(120, e.calcAge(input));
    }
}
