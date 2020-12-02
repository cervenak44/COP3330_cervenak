import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.exit;

public class App {
    public static void main(String[] args){
        while(true) {
            System.out.println("Select Your Application");
            System.out.println("-----------------------");
            System.out.println("1) task list");
            System.out.println("2) contact list");
            System.out.println("3) quit");
            Scanner usrInput = new Scanner(System.in);
            System.out.print("> ");
            int value = 0;
            try {
                value = usrInput.nextInt();
                usrInput.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice");
                usrInput.nextLine();
            }

            switch(value){
                case 1:
                    TaskApp taskRun = new TaskApp();
                    taskRun.taskMenu();
                    break;
                case 2:
                    ContactApp contactRun = new ContactApp();
                    contactRun.contactMenu();
                    break;
                case 3:
                    exit(0);
                    break;

            }

        }
    }
}