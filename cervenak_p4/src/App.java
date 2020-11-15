import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class App {

    private static Scanner input = new Scanner(System.in);

    private TaskList list;


    public App() {
        list = new TaskList();
    }

    private void mainMenu(){
        System.out.println("Main Menu");
        System.out.println("----------");

        System.out.println("1) create a new list");
        System.out.println("2) load an existing list");
        System.out.println("3) quit");


        switch(askChoice()){
            case 1:
                listOperationMenu();
                break;
            case 2:
                //loadFile();

                break;
            case 3:
                writeStudentData();
                quitManager();
        }


    }

    private void loadFile(){

        String  userInput = " ";
        System.out.println("Enter filename to load:");
        while(true) {
            userInput = input.nextLine();
            File file = new File(userInput);
            try {
                Scanner scan = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if(file.length() > 0){
                break;
            }
        }


        listOperationMenu();
    }

    private void quitManager() {
        System.exit(1);
    }

    private static int askChoice() {
        return input.nextInt();
    }

    private void writeStudentData() {
        list.write("output.txt");
    }

    private void storeListData(TaskItem data) {
        list.add(data);
    }

    private TaskItem getData() {
        TaskItem data = null;
        while(true) {
            try {
                String title = getTitle();
                String description = getDescription();
                String dueDate = getDueDate();

                data = new TaskItem(title, description, dueDate);
                break;
            } catch (InvalidTitleException ex) {
                System.out.println("Warning: your name was invalid, please double check it and try again");
            } catch (InvalidDescriptionException ex) {
                System.out.println("Warning: your grade was invalid, please double check it and try again");
            }
        }
        return data;
    }

    private String getDueDate() {
        String dueDate = " ";
        System.out.println("Task due date (YYYY-MM-DD):");
        while(true){
            dueDate = input.nextLine();
            if(dueDate.length()>=0){
                break;
            }
        }

        return dueDate;
    }

    private String getTitle() {
        String title = " ";
        System.out.println("Task title:");
        while(true) {
            title = input.nextLine();

            if (title.length() > 1) {
                break;
            }
        }

        return title;
    }

    private String getDescription() {
        String description = " ";
        System.out.println("Task description:");
        while(true){
            description = input.nextLine();

            if(description.length() >=0 ){
                break;
            }
        }
        return description;
    }




    public static void main(String[] args){
        App m = new App();

        m.mainMenu();
    }

    private void listOperationMenu(){


        System.out.println("List Operation Menu");
        System.out.println("----------");

        System.out.println("1) view the list");
        System.out.println("2) add an item");
        System.out.println("3) edit an item");
        System.out.println("4) remove an item ");
        System.out.println("5) mark an item as completed");
        System.out.println("6) unmark an item as completed");
        System.out.println("7) save the current list");
        System.out.println("8) quit to the main menu");

        switch (askChoice()) {
            case 1:

                System.out.println();
                listOperationMenu();
                break;
            case 2:

                TaskItem data = getData();
                storeListData(data);

                listOperationMenu();
                break;
            case 3:
                listOperationMenu();
                break;
            case 4:
                removeData(data);
                listOperationMenu();
                break;
            case 5:
                markcompleted(data);
                listOperationMenu();
                break;
            case 6:
                markUncompleted(data);
                listOperationMenu();
                break;
            case 7:
                saveList(data);
                listOperationMenu();
                break;

            case 8:
                mainMenu();
                break;

        }
    }

    private void saveList(TaskItem data) {
        //save list
    }

    private void markUncompleted(TaskItem data) {
        System.out.println("Completed Tasks");
        System.out.println("----------");

        //System.out.println(data.completed);
        int usrInput = input.nextInt();
        //list.unmarkcompleted(data);
    }

    private void markcompleted(TaskItem data) {
        System.out.println("Uncompleted Tasks");
        System.out.println("----------");

        //System.out.println(data.uncompleted);

        System.out.println("Which task to mark completed:");
        int usrInput = input.nextInt();
        //list.markCompleted(data);
    }

    private void removeData(TaskItem data) {
        System.out.println("Current Tasks");
        System.out.println("---------");

        System.out.println(data);

        System.out.println("Which task to remove:");
        int usrInput = input.nextInt();

        list.remove(usrInput);
    }



}