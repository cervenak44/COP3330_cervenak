import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactListTest {

    @Test
    void addingItemsIncreasesSize(){
        ContactList list = new ContactList();
        ContactItem newItem = new ContactItem("Dylan", "Cervenak", "123-456-7890", "cervenak@ucf.edu");
        list.add(newItem);
        assertEquals(1, list.size());
    }

    @Test
    void editingItemsFailsWithAllBlankValues(){
        ContactList list = new ContactList();
        ContactItem newItem = new ContactItem("Dylan", "Cervenak", "123-456-7890", "cervenak@ucf.edu");
        list.add(newItem);
        assertThrows(IllegalArgumentException.class, ()-> list.set(new ContactItem("", "", "", ""), 0));
    }

    @Test
    void editingItemsFailsWithInvalidIndex(){
        ContactList list = new ContactList();
        ContactItem newItem = new ContactItem("Dylan", "Cervenak", "123-456-7890", "cervenak@ucf.edu");
        list.add(newItem);
        ContactItem editItem = new ContactItem("Ashley", "Garito", "234-567-8901", "garito@ucf.edu");
        assertThrows(IndexOutOfBoundsException.class, ()-> list.set(editItem, 4));
    }

    @Test
    void editingSucceedsWithBlankFirstName(){
        ContactList list = new ContactList();
        ContactItem newItem = new ContactItem("Dylan", "Cervenak", "123-456-7890", "cervenak@ucf.edu");
        list.add(newItem);
        ContactItem editItem = new ContactItem("", "Garito", "234-567-8901", "garito@ucf.edu");
        list.set(editItem, 0);
        assertEquals("", list.get(0).getFirstName());
    }

    @Test
    void editingSucceedsWithBlankLastName(){
        ContactList list = new ContactList();
        ContactItem newItem = new ContactItem("Dylan", "Cervenak", "123-456-7890", "cervenak@ucf.edu");
        list.add(newItem);
        ContactItem editItem = new ContactItem("Ashley", "", "234-567-8901", "garito@ucf.edu");
        list.set(editItem, 0);
        assertEquals("", list.get(0).getLastName());
    }

    @Test
    void editingSucceedsWithBlankPhone(){
        ContactList list = new ContactList();
        ContactItem newItem = new ContactItem("Dylan", "Cervenak", "123-456-7890", "cervenak@ucf.edu");
        list.add(newItem);
        ContactItem editItem = new ContactItem("Ashley", "Garito", "", "garito@ucf.edu");
        list.set(editItem, 0);
        assertEquals("", list.get(0).getPhoneNumber());
    }

    @Test
    void editingSucceedsWithNonBlankValues(){
        ContactList list = new ContactList();
        ContactItem newItem = new ContactItem("Dylan", "Cervenak", "123-456-7890", "cervenak@ucf.edu");
        list.add(newItem);
        ContactItem editItem = new ContactItem("Ashley", "Garito", "234-567-8901", "garito@ucf.edu");
        list.set(editItem, 0);
        assertEquals("Name: Ashley Garito\n" + "Phone: 234-567-8901\n" + "Email: garito@ucf.edu", list.get(0).toString());
    }

    @Test
    void newListIsEmpty(){
        ContactList list = new ContactList();
        assertEquals(0,list.size());
    }

    @Test
    void removingItemsDecreasesSize(){
        ContactList list = new ContactList();
        ContactItem newItem = new ContactItem("Dylan", "Cervenak", "123-456-7890", "cervenak@ucf.edu");
        list.add(newItem);
        ContactItem editItem = new ContactItem("Dylan", "Cervenak", "234-567-8901", "cervenak@ucf.edu");
        list.add(editItem);
        list.remove(1);
        assertEquals(1, list.size());
    }

    @Test
    void removingItemsFailsWithInvalidIndex(){
        ContactList list = new ContactList();
        ContactItem newItem = new ContactItem("Dylan", "Cervenak", "123-456-7890", "cervenak@ucf.edu");
        list.add(newItem);
        ContactItem editItem = new ContactItem("Dylan", "Cervenak", "123-456-7890", "cervenak@ucf.edu");
        list.add(editItem);
        assertThrows(IndexOutOfBoundsException.class, ()-> list.remove(4));
    }

    @Test
    void savedContactListCanBeLoaded(){
        ContactList list = new ContactList();
        list.loadFile("output2.txt");
        assertEquals("0) Name: dylan cervenak\n" + "Phone: 123-456-7890\n" + "Email: cervenak@ucf.edu", list.view());
    }

    @Test
    void viewFunctionTest(){
        ContactList list = new ContactList();
        ContactItem newItem = new ContactItem("Dylan", "Cervenak", "123-456-7890", "cervenak@ucf.edu");
        ContactItem newItem2 = new ContactItem("Ashley", "Garito", "234-567-8901", "garito@ucf.edu");
        list.add(newItem);
        list.add(newItem2);
        assertEquals("0) Name: Dylan Cervenak\n" + "Phone: 123-456-7890\n" + "Email: cervenak@ucf.edu\n" + "1) Name: Ashley Garito\n" + "Phone: 234-567-8901\n" + "Email: garito@ucf.edu\n", list.view());
    }

    @Test
    void viewPrintsNoItemsinListWhenEmptyList(){
        ContactList list = new ContactList();
        assertEquals("No contacts have been added yet.", list.view());
    }
}