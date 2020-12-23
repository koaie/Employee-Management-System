import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;


public class EmployeeTest {
    Employee e = new Employee();
    
    @Test
    public void setNameTest() {
        String name = "Lmao";
        assertEquals(name, e.setName(name).getName());
    }
    
    @Test
    public void aSlowTest() {
       System.out.println("Slow test");
    }
}
