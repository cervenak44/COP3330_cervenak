import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactItemTest {

    @Test
    void creationFailsWithAllBlankValues(){
        assertThrows(IllegalArgumentException.class, ()-> new ContactItem("","","",""));
    }

    @Test
    void creationSucceedsWithBlankEmail(){
        ContactItem newItem = new ContactItem("Dylan", "cervenak", "123-456-7890", "");
        assertEquals("Name: Dylan cervenak\n" + "Phone: 123-456-7890\n" + "Email: ", newItem.toString());
    }

    @Test
    void creationSucceedsWithBlankFirstName(){
        ContactItem newItem = new ContactItem("", "cervenak", "123-456-7890", "cervenak@ucf.edu");
        assertEquals("Name:  cervenak\n" + "Phone: 123-456-7890\n" + "Email: cervenak@ucf.edu", newItem.toString());
    }

    @Test
    void creationSucceedsWithBlankLastName(){
        ContactItem newItem = new ContactItem("Dylan", "", "123-456-7890", "cervenak@ucf.edu");
        assertEquals("Name: Dylan \n" + "Phone: 123-456-7890\n" + "Email: cervenak@ucf.edu", newItem.toString());
    }

    @Test
    void creationSucceedsWithBlankPhone(){
        ContactItem newItem = new ContactItem("Dylan", "Cervenak", "", "cervenak@ucf.edu");
        assertEquals("Name: Dylan Cervenak\n" + "Phone: \n" + "Email: cervenak@ucf.edu", newItem.toString());
    }

    @Test
    void creationSucceedsWithNonBlankValues(){
        ContactItem newItem = new ContactItem("Dylan", "Cervenak", "123-456-7890", "cervenak@ucf.edu");
        assertEquals("Name: Dylan Cervenak\n" + "Phone: 123-456-7890\n" + "Email: cervenak@ucf.edu", newItem.toString());
    }

    @Test
    void editingFailsWithAllBlankValues(){
        ContactItem newItem = new ContactItem("", "", "", "cervenak@ucf.edu");
        assertThrows(IllegalArgumentException.class, ()-> newItem.setEmailAddress(""));
    }

    @Test
    void editingSucceedsWithBlankEmail(){
        ContactItem newItem = new ContactItem("Dylan", "Cervenak", "123-456-7890", "cervenak@ucf.edu");
        newItem.setEmailAddress("");
        assertEquals("", newItem.getEmailAddress());
    }

    @Test
    void editingSucceedsWithBlankFirstName(){
        ContactItem newItem = new ContactItem("Dylan", "Cervenak", "123-456-7890", "cervenak@ucf.edu");
        newItem.setFirstName("");
        assertEquals("", newItem.getFirstName());
    }

    @Test
    void editingSucceedsWithBlankLastName(){
        ContactItem newItem = new ContactItem("Dylan", "Cervenak", "123-456-7890", "cervenak@ucf.edu");
        newItem.setLastName("");
        assertEquals("", newItem.getLastName());
    }

    @Test
    void editingSucceedsWithBlankPhone(){
        ContactItem newItem = new ContactItem("Dylan", "Cervenak", "123-456-7890", "cervenak@ucf.edu");
        newItem.setPhoneNumber("");
        assertEquals("", newItem.getPhoneNumber());
    }

    @Test
    void editingSucceedsWithNonBlankValues(){
        ContactItem newItem = new ContactItem("Dylan", "Cervenak", "123-456-7890", "cervenak@ucf.edu");
        newItem.setPhoneNumber("Bob");
        assertEquals("Bob", newItem.getPhoneNumber());
    }

    @Test
    void testToString(){
        ContactItem newItem = new ContactItem("Dylan", "Cervenak", "123-456-7890", "cervenak@ucf.edu");
        assertEquals("Name: Dylan Cervenak\n" + "Phone: 123-456-7890\n" + "Email: cervenak@ucf.edu", newItem.toString());
    }

    @Test
    void testToStringFile(){
        ContactItem newItem = new ContactItem("Dylan", "Cervenak", "123-456-7890", "cervenak@ucf.edu");
        assertEquals("Dylan\n" + "Cervenak\n" + "123-456-7890\n" + "cervenak@ucf.edu", newItem.toStringFile());
    }

    @Test
    void nameCheckerReturnsAppropriateFalse(){
        ContactItem newItem = new ContactItem("C", "B", "123-456-7890", "hello@ucf.edu");
        boolean check = newItem.nameChecker("","","","");
        assertFalse(check);
    }

    @Test
    void nameCheckerReturnsAppropriateTrue(){
        ContactItem newItem = new ContactItem("D", "B", "123-456-7890", "cervenak@ucf.edu");
        assertTrue(newItem.nameChecker("D","B","123-456-7890","cervenak@ucf.edu"));
    }

    @Test
    void nameCheckerReturnsAppropriateFalseWithBadPhoneNumberFormat(){
        ContactItem newItem = new ContactItem("D", "B", "123-456-7890", "cervenak@ucf.edu");
        assertFalse(newItem.nameChecker("D","B","123-1111-1234","cervenak@ucf.edu"));
    }

    @Test
    void nameCheckerReturnsAppropriateFalseWithBadEmail(){
        ContactItem newItem = new ContactItem("D", "B", "123-456-7890", "cervenak@ucf.edu");
        assertFalse(newItem.nameChecker("D","B","123-456-7890","cervenakucf.edu"));
    }

    @Test
    void nameCheckerReturnsAppropriateFalseWithLettersInPhoneNumber(){
        ContactItem newItem = new ContactItem("D", "B", "123-456-7890", "cervenak@ucf.edu");
        assertFalse(newItem.nameChecker("D","B","abc-123-4567","cervenak@ucf.edu"));
    }
}