package loginandfeaturetasks2;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class LoginAndFeatureTasks {
    
// Check users Login/Registration
    
    
    private static User registeredUser = null;
    private static boolean isLoggedIn = false;
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        runApplication();
    }

    private static void runApplication() {
        boolean running = true;

        //gives the user options to choose an action
        while (running) {
            String[] options = {"Login", "Register", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Please choose an option:", "Login and Registration",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    login();
                    break;
                case 1:
                    register();
                    break;
                case 2:
                    running = false;
                    JOptionPane.showMessageDialog(null, "Exiting...");
                    System.exit(0); // Exit the program
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Sorry, that's an invalid choice! Please try again.");
            }

            if (isLoggedIn) {
                showMainMenu();
            }
        }
    }
//allows User to register if they meet given critreas 
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

    private static String enterPassword() {
        String password;
        do {
            password = JOptionPane.showInputDialog("Enter password:");
            if (!isValidPassword(password)) {
                JOptionPane.showMessageDialog(null, "Please enter a password that has an Uppercase, lowercase, number, and a symbol.");
            }
        } while (!isValidPassword(password));
        return password;
    }

    private static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=.*[a-zA-Z]).{8,}$";
        return password != null && password.matches(passwordRegex);
    }

    private static void login() {
        if (registeredUser == null) {
            JOptionPane.showMessageDialog(null, "No user registered. Please register first.");
            return;
        }

        String inputUsername = JOptionPane.showInputDialog("Enter username:");
        String inputPassword;

        do {
            inputPassword = JOptionPane.showInputDialog("Enter password:");
            if (!isValidPassword(inputPassword)) {
                JOptionPane.showMessageDialog(null, "Please enter a password that has an Uppercase, lowercase, number, and a symbol.");
            }
        } while (!isValidPassword(inputPassword));

        if (!inputUsername.equals(registeredUser.getUsername()) || !inputPassword.equals(registeredUser.getPassword())) {
            JOptionPane.showMessageDialog(null, "Invalid username or password!");
        } else {
            isLoggedIn = true;
            JOptionPane.showMessageDialog(null, "Login successful! Welcome, " 
                    + registeredUser.getFirstName() + " " + registeredUser.getLastName() + "!");
        }
    }

    private static void showMainMenu() {
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");
//Method created for Tasks operations
        boolean menuRunning = true;
        while (menuRunning) {
            String[] menuOptions = {"Add tasks", "Show report", "Exit to Home", "Quit"};
            int menuChoice = JOptionPane.showOptionDialog(null, "Choose an option:", "Main Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, menuOptions, menuOptions[0]);

            switch (menuChoice) {
                case 0:
                    addTasks();
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Coming Soon");
                    break;
                case 2:
                    menuRunning = false;
                    isLoggedIn = false;
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Exiting...");
                    System.exit(0); // Exit the program
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice! Please try again.");
            }
        }
    }

    private static void addTasks() {
        String numTasksStr = JOptionPane.showInputDialog("How many tasks do you wish to enter?");
        int numTasks = Integer.parseInt(numTasksStr);

        for (int i = 0; i < numTasks; i++) {
            String taskName = JOptionPane.showInputDialog("Enter task name:");
            String taskDescription = JOptionPane.showInputDialog("Enter task description (max 50 characters):");
            while (!Task.checkTaskDescription(taskDescription)) {
                taskDescription = JOptionPane.showInputDialog("Please enter a task description of less than 50 characters:");
            }
            String developerDetails = JOptionPane.showInputDialog("Enter developer details (first and last name):");
            String taskDurationStr = JOptionPane.showInputDialog("Enter task duration in hours:");
            int taskDuration = Integer.parseInt(taskDurationStr);
            String[] statusOptions = {"To Do", "Doing", "Done"};
            String taskStatus = (String) JOptionPane.showInputDialog(null, "Select task status:",
                    "Task Status", JOptionPane.QUESTION_MESSAGE, null, statusOptions, statusOptions[0]);

            Task task = new Task(taskName, taskDescription, developerDetails, taskDuration, taskStatus);
            tasks.add(task);
            JOptionPane.showMessageDialog(null, "Task successfully captured!");

            // Display task details
            JOptionPane.showMessageDialog(null, task.printTaskDetails());
        }

       // Display total number of hours
        int totalHours = tasks.stream().mapToInt(Task::getTaskDuration).sum();
        JOptionPane.showMessageDialog(null, "Total task duration: " + totalHours + " hours");
    }
}
//⁠ -code actribution
//- ⁠Date
//-⁠Title of program/source code
// -⁠Code version
//-⁠Type (e g. computer program, source code)
//-Web address or publisher (eg program publisher URL)

class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
//
class Task {
    private static int taskCounter = 0;
    private String taskName;
    private String taskDescription;
    private String developerDetails;
    private int taskDuration;
    private String taskStatus;
    private int taskNumber;
    private String taskID;

    public Task(String taskName, String taskDescription, String developerDetails, int taskDuration, String taskStatus) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskNumber = taskCounter++;
        this.taskID = createTaskID();
    }

    public static boolean checkTaskDescription(String taskDescription) {
        return taskDescription.length() <= 50;
    }

    private String createTaskID() {
        String[] developerNames = developerDetails.split(" ");
        String firstName = developerNames[0];
        String lastName = developerNames.length > 1 ? developerNames[1] : "";
        return taskName.substring(0, 2).toUpperCase() + ":" + taskNumber + ":" + firstName.substring(0, 3).toUpperCase();
    }

    public String printTaskDetails() {
        return "Task Status: " + taskStatus + "\n" +
                "Developer Details: " + developerDetails + "\n" +
                "Task Number: " + taskNumber + "\n" +
            
              "Task Name: " + taskName + "\n" +
                "Task Description: " + taskDescription + "\n" +
                "Task ID: " + taskID + "\n" +
                "Task Duration: " + taskDuration + " hours";
    }
//Outputs the users results as per what was entered
    public int getTaskDuration() {
        return taskDuration;
    }
}

//Refrences- Programming learning resources(Student materials)
         //  -Java Programming book