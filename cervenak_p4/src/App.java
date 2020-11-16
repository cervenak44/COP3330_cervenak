import java.io.File;
import java.util.Scanner;

public class App {
    public static void main (String[] args){

        Scanner input = new Scanner(System.in);
        int menuChoice = 1;


        while(menuChoice != 3){

            System.out.println("Main Menu");
            System.out.println("---------");

            System.out.println("1) create a new list");
            System.out.println("2) load an existing list");
            System.out.println("3) quit");
            System.out.print(">");

            menuChoice = input.nextInt();

            switch(menuChoice){
                case 1:
                    System.out.println("new task list has been created");
                    TaskList list = new TaskList();
                    list.listOptions();
                    break;
                case 2:
                    System.out.println("Enter the file name to load include '.txt': ");
                    Scanner userInputFile = new Scanner(System.in);
                    String fileName = userInputFile.nextLine();
                    TaskList loadedList = new TaskList();
                    File listFile = new File(fileName);
                    loadedList.loadFile(listFile);
                    break;
                case 3:
                    menuChoice = 3;
                    break;
                default:
                    System.out.println("select a valid option");
            } //end switch case start menu
        }
    }
} //end app