import javax.swing.*;
import java.util.LinkedList;
import java.util.Stack;

public class TodoListManager {

    
    private LinkedList<String> tasks;
    private LinkedList<String> completedTasks;
    private Stack<Action> undoStack;

    public TodoListManager() {
        tasks = new LinkedList<>();
        completedTasks = new LinkedList<>();
        undoStack = new Stack<>();
    }

   
    public void addTask() {
        String task = JOptionPane.showInputDialog("Enter a new task:");
        if (task != null && !task.trim().isEmpty()) {
            tasks.add(task);
            undoStack.push(new Action("add", task));
            JOptionPane.showMessageDialog(null, "Task added successfully!", "Successful", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Task cannot be empty.", "Canceled", JOptionPane.WARNING_MESSAGE);
        }
    }

  
    public void markTaskAsDone() {
        if (tasks.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks available to mark as done.", "No task available", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] taskArray = tasks.toArray(new String[0]);
        String selectedTask = (String) JOptionPane.showInputDialog(
                null,
                "Select a task to mark as done:",
                "Mark Task as Done",
                JOptionPane.QUESTION_MESSAGE,
                null,
                taskArray,
                taskArray[0]
        );

        if (selectedTask != null) {
            tasks.remove(selectedTask);
            completedTasks.add(selectedTask);
            undoStack.push(new Action("markDone", selectedTask));
            JOptionPane.showMessageDialog(null, "Task marked as done!", "Task done", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    
    public void undo() {
        if (undoStack.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No actions to undo.");
            return;
        }

        Action lastAction = undoStack.pop();
        switch (lastAction.type) {
            case "add":
                tasks.remove(lastAction.task);
                JOptionPane.showMessageDialog(null, "Undo add: Task removed.", "Removed Task/s", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "markDone":
                completedTasks.remove(lastAction.task);
                tasks.add(lastAction.task);
                JOptionPane.showMessageDialog(null, "Undo mark as done: Task restored.", "Undo", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }

  
    public void viewTasks() {
        if (tasks.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks available.", "No Task", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder taskList = new StringBuilder("To-Do List:\n");
        for (int i = 0; i < tasks.size(); i++) {
            taskList.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        JOptionPane.showMessageDialog(null, taskList.toString(), "List of Task", JOptionPane.INFORMATION_MESSAGE);
    }

   
    public void viewCompletedTasks() {
        if (completedTasks.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No completed tasks available.", "No Completed Tasks", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder completedList = new StringBuilder("Completed Tasks:\n");
        for (int i = 0; i < completedTasks.size(); i++) {
            completedList.append(i + 1).append(". ").append(completedTasks.get(i)).append("\n");
        }
        JOptionPane.showMessageDialog(null, completedList.toString(), "Completed Tasks", JOptionPane.INFORMATION_MESSAGE);
    }

   
    public void showMenu() {
        String[] options = {"Add Task", "Mark as Done", "Undo the last Action", "View Tasks", "View Completed Tasks", "Exit"};
        int choice;

        do {
            choice = JOptionPane.showOptionDialog(
                    null,
                    "Select an option:",
                    "Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            switch (choice) {
                case 0:
                    addTask();
                    break;
                case 1:
                    markTaskAsDone();
                    break;
                case 2:
                    undo();
                    break;
                case 3:
                    viewTasks();
                    break;
                case 4:
                    viewCompletedTasks();
                    break;
            }
        } while (choice != 5);
    }

   
    private static class Action {
        String type;
        String task;

        Action(String type, String task) {
            this.type = type;
            this.task = task;
        }
    }

   
    public static void main(String[] args) {
        TodoListManager m = new TodoListManager();
        m.showMenu();
    }
}
