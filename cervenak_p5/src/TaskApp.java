import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskApp {

    protected Scanner usrInput = new Scanner(System.in);
    private TaskList list;

    public void taskMenu (){
        int menuChoice = 1;


        while(menuChoice != 3){

            System.out.println("Task Menu");
            System.out.println("---------");

            System.out.println("1) create a new task list");
            System.out.println("2) load an existing task list");
            System.out.println("3) quit");
            System.out.print("> ");

            menuChoice = usrInput.nextInt();

            switch(menuChoice){
                case 1:
                    makeList();
                    listMenu();
                    break;
                case 2:
                    loadFile();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid Choice");
            }

        }
    } //end menu choice

    public void listMenu(){
        int choice = 0;

        while(choice != 8){

            System.out.println("List Operation Menu");
            System.out.println("---------");

            System.out.println("1) view the list");
            System.out.println("2) add an item");
            System.out.println("3) edit an item");
            System.out.println("4) remove an item");
            System.out.println("5) mark an item as completed");
            System.out.println("6) unmark an item as completed");
            System.out.println("7) save the current list");
            System.out.println("8) quit to the main menu");
            System.out.print("> ");


            try {
                choice = usrInput.nextInt();
                usrInput.nextLine();
            }catch(InputMismatchException er){
                System.out.println("Invalid choice made. Please try again.\n");
                usrInput.nextLine();
            }

            switch(choice) {
                case 1:
                    displayList();
                    break;
                case 2:
                    addTask();
                    break;
                case 3:
                    editTask();
                    break;
                case 4:
                    removeTask();
                    break;
                case 5:
                    markCompleted();
                    break;
                case 6:
                    unmarkCompleted();
                    break;
                case 7:
                    saveFile();
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Enter a valid choice");
            }//end switch
        }//end loop
    } //end list menu

    public void makeList(){
        list = new TaskList();
    }//end make list

    public void displayList(){
        System.out.println(list.view());
    }//end display

    public void displayIncompleteTasks(){
        System.out.println(list.viewUncompletedTasks());
    }//end display incomplete

    public void displayCompleteTasks(){
        System.out.println(list.viewCompleteTasks());
    } //end display completed

    public void addTask(){
        String taskName;
        String dueDate;
        String description;

        System.out.print("Task title: ");
        taskName = usrInput.nextLine();


        System.out.print("Task description: ");
        description = usrInput.nextLine();

        System.out.print("Task due date (YYYY-MM-DD): ");
        dueDate = usrInput.nextLine();

        try {
            TaskItem addItem = new TaskItem(taskName, dueDate, description, false);
            list.add(addItem);
        }
        catch(NullPointerException err){}
    }//end add task

    public void editTask(){
        String taskName;
        String dueDate;
        String description;
        int choice = 0;

        if(list.getSize() == 0){
            System.out.println("Nothing to edit");
            return;
        }

        displayList();
        System.out.println("Which task would you like to edit?");
        try {
            choice = usrInput.nextInt();
            usrInput.nextLine();
        }
        catch(InputMismatchException e){
            System.out.println("Invalid input. No task was edited.");
            usrInput.nextLine();
            return;
        }


        System.out.println("Enter a new title for " + choice + ": ");
        taskName = usrInput.nextLine();

        System.out.println("Enter a new description for " + choice + ": ");
        description = usrInput.nextLine();

        System.out.println("Enter a new due date (YYYY-MM-DD) for " + choice + ": ");
        dueDate = usrInput.nextLine();

        try{
            list.set(new TaskItem(taskName, dueDate, description, false), choice);
        }
        catch(NullPointerException ex){
            System.out.println("Task name is too short");
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Task choice does not exist.");
            System.out.println("Not task edited.");
        }
    } //nd edit task

    public void removeTask(){
        int choice;

        displayList();

        if(list.getSize() == 0){
            System.out.println("No items removed.");
            return;
        }

        System.out.println("Which item would you like to remove?");
        try {
            choice = usrInput.nextInt();
        }
        catch(InputMismatchException e){
            System.out.println("Invalid input. No task was removed.");
            usrInput.nextLine();
            return;
        }

        try{
            list.remove(choice);
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Task choice does not exist.");
            System.out.println("No task removed.");
        }
    }//end remove task

    public void markCompleted(){
        int choice;
        int counter = 0;
        displayIncompleteTasks();

        for(int i = 0; i < list.getSize(); i++){
            if(!(list.get(i).isCompleted())){counter++;}
        }
        if(counter == 0){return;}
        System.out.println("which task is completed");
        try{
            choice = usrInput.nextInt();
            usrInput.nextLine();
        }
        catch(InputMismatchException e){
            System.out.println("Invalid input; not marked");
            return;
        }

        try{
            list.markCompleted(choice);
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Task does not exist.");
        }
    }//end mark

    public void unmarkCompleted(){
        int choice;
        int counter = 0;

        displayCompleteTasks();

        for(int i = 0; i < list.getSize(); i++){
            if(list.get(i).isCompleted()){counter++;}
        }
        if(counter == 0){return;}

        System.out.println("Which task to unmark");
        try{
            choice = usrInput.nextInt();
            usrInput.nextLine();
        }
        catch(InputMismatchException e){
            System.out.println("Invalid input; not unmarked");
            usrInput.nextLine();
            return;
        }

        try{
            list.unmarkCompleted(choice);
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Task does not exist");
        }
    } //end unmark

    public void saveFile(){

        String fileName;

        if(list.getSize() == 0){
            System.out.println("List empty");
            return;
        }

        System.out.println("Enter filename to save include '.txt': ");
        fileName = usrInput.nextLine();

        list.saveFile(fileName);

    }//end save

    public void loadFile(){

        TaskList loadedList = new TaskList();
        String fileName;
        System.out.println("Enter the file name to load include '.txt': ");
        fileName = usrInput.nextLine();
        try{
            loadedList.loadFile(fileName);
        }
        catch(NullPointerException e){
            System.out.println("Could not load file; make sure to add '.txt' extension ");
            return;
        }
        list = loadedList;
        listMenu();
    }//end load

}