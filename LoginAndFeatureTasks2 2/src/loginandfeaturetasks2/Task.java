/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginandfeaturetasks2;

/**
 *
 * @author adrianchetty
 */
public class Task {
    
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

    public int getTaskDuration() {
        return taskDuration;
    }
    
}
