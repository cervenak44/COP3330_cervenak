import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskItemTest {

    @Test
    void constructorFailsWithInvalidDueDate(){
        assertThrows(NullPointerException.class, () -> new TaskItem("Birthdays", "2021-1223-16", "Birthday Fails", false));
    }
    @Test
    void constructorFailsWithInvalidTitle(){
        assertThrows(NullPointerException.class, () -> new TaskItem("", "2021-12-23", "23rd birthday", false));
    }

    @Test
    void constructorSucceedsWithValidDueDate(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Task 1", "2021-12-23", "Birthday", false);
        list.add(newItem);
        assertEquals("2021-12-23", list.get(0).getDueDate());
    }
    @Test
    void constructorSucceedsWithValidTitle(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Task 1", "2021-12-23", "Birthday", false);
        list.add(newItem);
        assertEquals("Task 1", list.get(0).getName());
    }
    @Test
    void editingDescriptionSucceedsWithExpectedValue(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Task 1", "2021-12-23", "Birthday", false);
        list.add(newItem);
        TaskItem editedItem = new TaskItem("Task 2", "2025-12-23", "27th birthday", false);
        list.set(editedItem, 0);
        assertEquals(editedItem.toString(), list.get(0).toString());
    }
    @Test
    void editingDueDateFailsWithInvalidDateFormat(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Task 1", "2021-12-23", "Birthday", false);
        list.add(newItem);
        assertThrows(NullPointerException.class, () -> list.set(new TaskItem("Task 1", "2020-123-12", "Birthday 2", false), 0));
    }
    @Test
    void editingDueDateFailsWithInvalidYYYMMDD(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Task 1", "2021-12-23", "Birthday", false);
        list.add(newItem);
        assertThrows(NullPointerException.class, () -> list.set(new TaskItem("Task 1", "2020-15-16", "This is task 1", false), 0));
    }
    @Test
    void editingDueDateSucceedsWithExpectedValue(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Task 1", "2021-12-23", "Birthday", false);
        list.add(newItem);
        TaskItem editItem = new TaskItem("Task 2", "2021-08-11", "new date to remember", false);
        list.set(editItem,0);
        assertEquals("2021-08-11", list.get(0).getDueDate());
    }
    @Test
    void editingTitleFailsWithEmptyString(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Task 1", "2021-12-23", "Birthday", false);
        list.add(newItem);
        assertThrows(NullPointerException.class, () -> list.set(new TaskItem("", "2021-12-23", "Birthday", false), 0));
    }
    @Test
    void editingTitleSucceedsWithExpectedValue() {
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Task 1", "2021-12-23", "Birthday", false);
        list.add(newItem);
        TaskItem editedItem = new TaskItem("Task 2", "2021-08-11", "another birthday", false);
        list.set(editedItem, 0);
        assertEquals("Task 2", list.get(0).getName());
    }

    @Test
    void dateNameCheckerReturnsAppropriateFalsewithInvalidName(){
        TaskItem newItem = new TaskItem("Task 1", "2021-12-23", "Birthday", false);
        assertFalse(newItem.dateNameChecker("", "2021-12-23"));
    }

    @Test
    void dateNameCheckerReturnsAppropriateFalsewithInvalidDate(){
        TaskItem newItem = new TaskItem("Task 1", "2021-12-23", "Birthday", false);
        assertFalse(newItem.dateNameChecker("Task 1", "2021-12-321"));
    }

    @Test
    void dateNameCheckerReturnsAppropriateTrue(){
        TaskItem newItem = new TaskItem("Task 1", "2021-12-23", "Birthday", false);
        assertTrue(newItem.dateNameChecker("Task 1", "2021-12-23"));
    }
}