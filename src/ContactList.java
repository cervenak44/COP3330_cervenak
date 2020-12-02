import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactList {
    private List<ContactItem> contactList;

    public ContactList(){this.contactList = new ArrayList<>();}

    public String view(){
        System.out.println("Current Contacts");
        System.out.println("-------------");
        System.out.println();

        if(contactList.size() == 0){
            return "No contacts have been added yet.";
        }

        String list = "";

        for(int i = 0; i < contactList.size(); i++){
            list += i + ") " + contactList.get(i).toString() + "\n";
        }

        return list;
    }

    public void add(ContactItem contact){
        contactList.add(contact);
    }

    public void set(ContactItem contact, int index){contactList.set(index, contact);}

    public void remove(int index){contactList.remove(index);}

    public int size(){return contactList.size();}

    public ContactItem get(int index){
        return contactList.get(index);
    }

    public void saveFile(String fileName){

        File savedFile = new File(fileName);

        try{
            FileWriter taskWriter = new FileWriter(savedFile);
            taskWriter.write(contactList.size() + "\n");
            for(int i = 0; i < contactList.size(); i++){
                taskWriter.write(contactList.get(i).toStringFile() + "\n");
            }

            taskWriter.close();
            System.out.println("contact list has been saved.");
        }
        catch(IOException e){
            System.out.println("A file error occured.\nCould not save contact list.");
        }
    }

    public void loadFile(String fileName){
        String firstName;
        String lastName;
        String phoneNumber;
        String emailAddress;

        int listSize = 0;

        File listFile = new File(fileName);
        Scanner fileReader = null;

        try{
            fileReader = new Scanner(listFile);
        }
        catch(FileNotFoundException e){
            System.out.println("File does not exist.");
        }

        listSize = fileReader.nextInt();
        fileReader.nextLine();

        for(int i = 0; i < listSize; i++){
            firstName = fileReader.nextLine();
            lastName = fileReader.nextLine();
            phoneNumber = fileReader.nextLine();
            emailAddress = fileReader.nextLine();

            try{
                ContactItem newItem = new ContactItem(firstName, lastName, phoneNumber, emailAddress);
                this.add(newItem);
            }
            catch(IllegalArgumentException err){
                System.out.println("An invalid task name was found in the file. List not loaded");
                return;
            }
        }
    }
}