import java.util.InputMismatchException;
import java.util.Scanner;

public class ContactApp {

    private Scanner userInput = new Scanner(System.in);
    private ContactList list;

    public void contactMenu(){
        int choice = 0;

        while(choice != 3){

            System.out.println("Contact Menu");
            System.out.println("---------");

            System.out.println("1) create a new list");
            System.out.println("2) load an existing list");
            System.out.println("3) quit");
            System.out.print("> ");

            choice = userInput.nextInt();

            switch(choice){
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
            }//end switch case
        }//end loop
    }//end contact menu

    public void listMenu(){

        int choice = 0;

        while(choice != 6) {

            System.out.println("List Operation Menu");
            System.out.println("---------");
            System.out.println("1) view the list");
            System.out.println("2) add an item");
            System.out.println("3) edit an item");
            System.out.println("4) remove an item");
            System.out.println("5) save the current list");
            System.out.println("6) quit to the main menu");
            System.out.print("> ");

            try {
                choice = userInput.nextInt();
                userInput.nextLine();
            } catch (InputMismatchException er) {
                System.out.println("Invalid choice made. Please try again.\n");
            }

            switch (choice) {
                case 1:
                    displayContacts();
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    editContact();
                    break;
                case 4:
                    removeContact();
                    break;
                case 5:
                    saveFile();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Enter a valid choice");
            }
        }
    }//end list menu

    public void makeList(){
        list = new ContactList();
    }//end make list

    public void displayContacts(){
        System.out.println(list.view());
    }//end display contacts

    public void addContact(){
        String firstName;
        String lastName;
        String emailAddress;
        String phoneNumber;

        System.out.print("First name: ");
        firstName = userInput.nextLine();

        System.out.print("Last name: ");
        lastName = userInput.nextLine();

        System.out.print("Phone Number XXX-XXX-XXXX: ");
        phoneNumber = userInput.nextLine();

        System.out.print("Email Address (example@host.extension): ");
        emailAddress = userInput.nextLine();

        try{
            ContactItem addItem = new ContactItem(firstName, lastName, phoneNumber, emailAddress);
            list.add(addItem);
        }
        catch(IllegalArgumentException err){}
    }//end add contact

    public void editContact(){
        String firstName;
        String lastName;
        String phoneNumber;
        String emailAddress;
        int choice;

        if(list.size() == 0){
            System.out.println("There are no contacts to edit");
            return;
        }

        displayContacts();
        System.out.println("Which contact to edit?");

        try{
            choice = userInput.nextInt();
            userInput.nextLine();
        }
        catch(InputMismatchException e){
            System.out.println("Invalid input.");
            userInput.nextLine();
            return;
        }

        System.out.println("Enter new first name for " + choice + ": ");
        firstName = userInput.nextLine();

        System.out.println("Enter new last name for " + choice + ": ");
        lastName = userInput.nextLine();

        System.out.println("Enter new phone number for " + choice + ": ");
        phoneNumber = userInput.nextLine();

        System.out.println("Enter new email address for " + choice + ": ");
        emailAddress = userInput.nextLine();

        try{
            ContactItem editItem = new ContactItem(firstName, lastName, phoneNumber, emailAddress);
            list.set(editItem, choice);
        }
        catch(IllegalArgumentException ex){}
        catch(IndexOutOfBoundsException e){
            System.out.println("Contact does not exist.");
        }

    } //end edit contact

    public void removeContact(){

        int choice;

        displayContacts();
        if(list.size() == 0){
            System.out.println("No contact removed.");
            return;
        }
        System.out.println("Which contact to remove?");

        try{
            choice = userInput.nextInt();
        }
        catch(InputMismatchException e){
            System.out.println("Invalid Input. No contact was removed.");
            userInput.nextLine();
            return;
        }

        try{
            list.remove(choice);
        }
        catch(IndexOutOfBoundsException ex){
            System.out.println("Contact does not exist.");
        }
    }//end remove contact

    public void saveFile(){

        String fileName;

        if(list.size() == 0){
            System.out.println("You have no contacts to save, add someone.");
            return;
        }

        System.out.print("Enter filename, include '.txt': ");
        fileName = userInput.nextLine();

        list.saveFile(fileName);
    } //end sve file

    public void loadFile(){

        ContactList loadedList = new ContactList();
        String fileName;
        System.out.println("Please enter filename, include '.txt': ");
        fileName = userInput.nextLine();


        try{
            loadedList.loadFile(fileName);
        }
        catch(NullPointerException e){
            System.out.println("Could not load file; make sure to add '.txt' extension ");
            return;
        }

        list = loadedList;
        listMenu();
    }//end load file
}