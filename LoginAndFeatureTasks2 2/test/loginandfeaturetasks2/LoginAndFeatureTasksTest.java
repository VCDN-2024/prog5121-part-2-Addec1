/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package loginandfeaturetasks2;

import javax.swing.JOptionPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author adrianchetty
 */
public class LoginAndFeatureTasksTest {
    
    public LoginAndFeatureTasksTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class LoginAndFeatureTasks.
     */
    private static void register() {
        String username = JOptionPane.showInputDialog("Enter username:");
        String password = enterPassword();
        String firstName = JOptionPane.showInputDialog("Enter first name:");
        String lastName = JOptionPane.showInputDialog("Enter last name:");

        if (username != null && firstName != null && lastName != null && password != null) {
            registeredUser = new User(username, password, firstName, lastName);
            JOptionPane.showMessageDialog(null, "Account created successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Registration failed. Please fill all fields.");
        }
    }
    }
    
}
