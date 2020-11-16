import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class TaskList {

    public final ArrayList<TaskItem> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void listOptions(){

        int userChoice = 1;

        Scanner input = new Scanner(System.in);

        while(userChoice != 8) {
            System.out.println("\nList Operation Menu");
            System.out.println("---------");

            System.out.println("1) view the list");
            System.out.println("2) add an item");
            System.out.println("3) edit an item");
            System.out.println("4) remove an item");
            System.out.println("5) mark an item as completed");
            System.out.println("6) unmark an item as completed");
            System.out.println("7) save the current list");
            System.out.println("8) quit to the main menu");
            System.out.println(">");

            userChoice = input.nextInt();

            switch(userChoice){
                case 1:
                    displayList(this.taskList);
                    listOptions();
                    break;
                case 2:
                    addTask(this.taskList);
                    listOptions();
                    break;
                case 3:
                    int choice;
                    displayList(this.taskList);
                    System.out.println("which task to edit:");
                    Scanner taskChoice = new Scanner(System.in);
                    if(!taskChoice.hasNextInt()){
                        System.out.println("Invalid input, task not marked");
                        return;
                    }else {
                        choice = taskChoice.nextInt();
                        editTask(this.taskList, choice);
                    }
                    listOptions();
                    break;
                case 4:
                    displayList(taskList);
                    int removeChoice;
                    System.out.println("which task to remove:");
                    Scanner taskChoice2 = new Scanner(System.in);
                    if(!taskChoice2.hasNextInt()){
                        System.out.println("Invalid input, task not removed.");

                    }else {
                        removeChoice = taskChoice2.nextInt();
                        removeTask(this.taskList, removeChoice);
                    }
                    listOptions();
                    break;
                case 5:
                    int counter = viewUncompletedList(this.taskList);
                    int markChoice;
                    if(counter == 0){System.out.println("no tasks completed");}
                    else {
                        System.out.println("which task completed:");
                        Scanner completeChoice = new Scanner(System.in);
                        if (!completeChoice.hasNextInt()) {
                            System.out.println("Invalid input, task not marked as completed");
                            return;
                        } else {
                            markChoice = completeChoice.nextInt();
                            markCompleted(this.taskList, markChoice);
                        }
                    }
                    listOptions();
                    break;
                case 6:
                    int unMarkChoice;
                    int unMarkCounter = viewCompletedList(this.taskList);
                    if(unMarkCounter == 0){System.out.println("task not unmarked");}
                    else {
                        System.out.println("which task to unmark:");
                        Scanner unCompleteChoice = new Scanner(System.in);
                        if (!unCompleteChoice.hasNextInt()) {
                            System.out.println("Invalid input, no task umarked");
                            return;
                        }
                        else {
                            unMarkChoice = unCompleteChoice.nextInt();
                            unmarkCompleted(this.taskList, unMarkChoice);
                        }
                    }
                    listOptions();
                    break;
                case 7:
                    saveFile(this.taskList);
                    listOptions();
                    break;
                case 8:
                    userChoice = 8;
                    break;
                default:
                    System.out.println("Invalid Input. Try again.\n");
                    listOptions();
                    break;
            } //end switch cases


        }//end menu while loop


    }


    public void displayList(ArrayList<TaskItem> taskList){
        System.out.println("Current Tasks");
        System.out.println("-------------\n");
        if(taskList.size() == 0){
            System.out.println("The list is currently empty");
        }
        for(int i = 0; i < taskList.size(); i++){
            if(taskList.get(i).isCompleted()){
                System.out.println(i + ") *** " + taskList.get(i).toString());
            }
            else {
                System.out.println(i + ") " + taskList.get(i).toString());
            }
        }
    } //end display

    public int viewUncompletedList(ArrayList<TaskItem> taskList){
        int counter = 0;
        System.out.println("Uncompleted Tasks");
        System.out.println("-------------\n");
        for(int i = 0; i < taskList.size(); i++){
            if (!taskList.get(i).isCompleted()) {
                System.out.println(i + ") " + taskList.get(i));
                counter++;
            }
        }

        if(counter == 0){System.out.println("You currently have no uncompleted tasks");}

        return counter;
    } //end view uncompleted

    public int viewCompletedList(ArrayList<TaskItem> taskList){
        int counter = 0;
        System.out.println("Completed Tasks");
        System.out.println("-------------\n");

        for(int i = 0; i < taskList.size(); i++){
            if (taskList.get(i).isCompleted()) {
                System.out.println(i + ") *** " + taskList.get(i));
                counter++;
            }
        }

        if(counter == 0){System.out.println("You currently have no completed tasks");}

        return counter;
    } //end view completed list

    public void addTask(ArrayList<TaskItem> taskList) {
        String taskName;
        String dueDate;
        String description;

        System.out.println("Task title: ");
        Scanner nameInput = new Scanner(System.in);
        taskName = nameInput.nextLine();

        if (!(taskName.length() >= 1)) {
            System.out.println("Title must be 1+ characters");
            return;
        }

        System.out.println("Task description: ");
        Scanner descriptionInput = new Scanner(System.in);
        description = descriptionInput.nextLine();


        System.out.println("Task due date (YYYY-MM-DD): ");
        Scanner dueDateInput = new Scanner(System.in);
        dueDate = dueDateInput.nextLine();

        if (!(dueDate.matches("....-..-.."))) {
            System.out.println("Wrong format; use YYYY-MM-DD");
            return;
        }

        Date inputDate = null;
        try {
            inputDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(dueDate.charAt(6) == '3' || dueDate.charAt(6) == '4' || dueDate.charAt(6) == '5' || dueDate.charAt(6) == '6' || dueDate.charAt(6) == '7' || dueDate.charAt(6) == '8' || dueDate.charAt(6) == '9'){
            System.out.println("Date is not valid");
            return;
        }

        TaskItem newItem = new TaskItem(taskName, dueDate, description, false);
        taskList.add(newItem);
    } //end add task

    public void editTask(ArrayList<TaskItem> taskList, int choice){

        String taskName;
        String dueDate;
        String description;

        try{
            taskList.get(choice);
        }catch (IndexOutOfBoundsException ex) {
            System.out.println("Invalid selection; task does not exist");
            throw ex;
        }
        finally{
            if(choice > taskList.size() || choice < 0){
                return;
            }

            System.out.println("Enter a new title for task " + choice + " :" );
            Scanner nameInput = new Scanner(System.in);
            taskName = nameInput.nextLine();

            if(!(taskName.length() >= 1)){
                System.out.println("Title must be 1+ characters, task not edited");
                return;
            }

            System.out.println("Enter a new description for task " + choice + " :");
            Scanner descriptionInput = new Scanner (System.in);
            description = descriptionInput.nextLine();

            System.out.println("Enter a new task due date (YYYY-MM-DD) for task " + choice + " :");
            Scanner dueDateInput = new Scanner(System.in);
            dueDate = dueDateInput.nextLine();

            if (!(dueDate.matches("....-..-.."))) {
                System.out.println("Date impropper, use YYYY-MM-DD");
                return;
            }

            Date inputDate = null;
            try {
                inputDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            TaskItem editedItem = new TaskItem(taskName, dueDate, description, false);

            taskList.set(choice, editedItem);
        }

    } //end edit task

    public void removeTask(ArrayList<TaskItem> taskList, int choice) throws IndexOutOfBoundsException{

        try{
            taskList.get(choice);
        }
        catch(IndexOutOfBoundsException ex){
            System.out.println("Invalid selection; Task does not exist; nothing removed");
            throw ex;
        }
        finally{
            if(choice > taskList.size() || choice < 0){
                return;
            }
            taskList.remove(choice);
        }
    } //end remove task

    public void markCompleted(ArrayList<TaskItem> taskList, int choice){
        try{
            taskList.get(choice);
        }
        catch (IndexOutOfBoundsException ex){
            System.out.println("Invalid selection; Task does not exist; not marked as complete");
            throw ex;
        }
        finally{
            if(choice > taskList.size() || choice < 0){
                return;
            }
            taskList.get(choice).setCompleted();
        }
    }//end mark

    public void unmarkCompleted(ArrayList<TaskItem> taskList, int choice){

        try{
            taskList.get(choice);
        }
        catch (IndexOutOfBoundsException ex){
            System.out.println("Invalid selection; task does not exist; not unmarked");
            throw ex;
        }
        finally{
            if(choice > taskList.size() || choice < 0){
                return;
            }
            taskList.get(choice).unsetCompleted();
        }
    }//end unmark

    public void saveFile(ArrayList<TaskItem> taskList){

        String fileName;

        if(taskList.size() == 0){
            System.out.println("List empty");
            return;
        }

        System.out.println("Enter filename to save include '.txt': ");
        Scanner input = new Scanner(System.in);
        fileName = input.nextLine();

        File saveFile = new File(fileName);

        try {
            FileWriter taskWriter = new FileWriter(fileName);
            int listSize = taskList.size();
            taskWriter.write(listSize + "\n");
            for(int i = 0; i < taskList.size(); i++){
                if(taskList.get(i).isCompleted()){
                    taskWriter.write("true\n" + taskList.get(i).toStringFile() + "\n");
                }
                else {
                    taskWriter.write("false\n" + taskList.get(i).toStringFile() + "\n");
                }
            }

            taskWriter.close();
        } catch (IOException e) {
            System.out.println("File error occured. Could not save");
        }

        System.out.println("task list has been saved");
    } //end save file

    public void loadFile(File listFile){

        String taskName;
        String dueDate;
        String description;
        String isCompleted;
        boolean taskDone;
        int listSize = 0;

        try {
            Scanner fileReader = new Scanner(listFile);

            listSize = fileReader.nextInt();
            fileReader.nextLine();

            for(int i = 0; i < listSize; i++){

                isCompleted = fileReader.nextLine();
                dueDate = fileReader.nextLine();
                taskName = fileReader.nextLine();
                description = fileReader.nextLine();

                if(taskName.length() == 0){
                    System.out.println("Invalid task name detected in file; file not loaded");
                    return;
                }

                dueDate = dueDate.replace("[", "");
                dueDate = dueDate.replace("]", "");
                if(isCompleted.contains("true")){
                    taskDone = true;
                }
                else{
                    taskDone = false;
                }

                TaskItem newItem = new TaskItem(taskName, dueDate, description, taskDone);
                taskList.add(newItem);

            }

            listOptions();

        }catch (FileNotFoundException e) {
            System.out.println("Could not load file; make sure to add '.txt' extension ");
            try {
                throw e;
            } catch (FileNotFoundException fileNotFoundException) {
                return;
            }
        }
    } //end load file
}//end task list