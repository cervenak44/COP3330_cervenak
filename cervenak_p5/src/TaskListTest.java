import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    void addingItemsIncreasesSize(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Birthday", "2020-12-23", "22nd Birthday", false);
        list.add(newItem);
        assertEquals(1, list.getSize());
    }
    @Test
    void completingTaskItemChangesStatus(){
        TaskItem newItem = new TaskItem("Birthday", "2020-12-23", "22nd Birthday", false);
        newItem.setCompleted();
        assertTrue(newItem.isCompleted());
    }
    @Test
    void completingTaskItemFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Birthday", "2020-12-23", "22nd Birthday", false);
        list.add(newItem);
        assertThrows(IndexOutOfBoundsException.class, () -> list.markCompleted(3));
    }
    @Test
    void editingItemDescriptionFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Birthday", "2020-12-23", "22nd Birthday", false);
        list.add(newItem);
        TaskItem editItem = new TaskItem("Birthday 2", "2021-12-23", "Next Birthday", false);
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(editItem, 4));
    }
    @Test
    void editingItemDescriptionSucceedsWithExpectedValue(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Birthday", "2020-12-23", "22nd Birthday", false);
        list.add(newItem);
        TaskItem editItem = new TaskItem("Birthday 2", "2021-12-23", "Birthday 2", false);
        list.set(editItem, 0);
        assertEquals(editItem.getDescription(), list.get(0).getDescription());
    }
    @Test
    void editingItemDueDateSucceedsWithExpectedValue(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Birthday", "2020-12-23", "22nd Birthday", false);
        list.add(newItem);
        TaskItem editItem = new TaskItem("Birthday 2", "2021-12-23", "Birthday 2", false);
        list.set(editItem, 0);
        assertEquals(editItem.getDueDate(), list.get(0).getDueDate());
    }
    @Test
    void editingItemTitleFailsWithEmptyString(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Birthday", "2020-12-23", "22nd Birthday", false);
        list.add(newItem);
        new TaskItem("Final", "2020-12-10", "Final Exam", false);
        assertThrows(NullPointerException.class, () -> list.set(new TaskItem("", "2020-12-10", "Final Exam", false), 0));
    }
    @Test
    void editingItemTitleFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Birthday", "2020-12-23", "22nd Birthday", false);
        list.add(newItem);
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(new TaskItem("Birthday", "2020-12-23", "22nd Birthday", false), 4));
    }
    @Test
    void editingItemTitleSucceedsWithExpectedValue(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Birthday", "2020-12-23", "22nd Birthday", false);
        list.add(newItem);
        TaskItem editItem = new TaskItem("Graduation", "2021-05-06", "Expected Graduation", false);
        list.set(editItem, 0);
        assertEquals(editItem.getName(), list.get(0).getName());
    }
    @Test
    void editingTaskItemDueDateFailsWithInvalidDateFormat(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Birthday", "2020-12-23", "22nd Birthday", false);
        list.add(newItem);
        assertThrows(NullPointerException.class, () -> list.set(new TaskItem("Next Birthday", "2021-1223", "Next birthday", false), 0));
    }
    @Test
    void editingTaskItemDueDateFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Birthday", "2020-12-23", "22nd Birthday", false);
        list.add(newItem);
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(new TaskItem("birthday 2", "2021-12-23", "next year", false), 5));
    }
    @Test
    void editingTaskItemDueDateFailsWithInvalidYYYMMDD(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Birthday", "2020-12-23", "22nd Birthday", false);
        list.add(newItem);
        assertThrows(NullPointerException.class, () -> list.set(new TaskItem("Lost time", "2021-18-12", "forgot how many months there are", false), 0));
    }
    @Test
    void gettingItemDescriptionFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(4).getDescription());
    }
    @Test
    void gettingItemDescriptionSucceedsWithValidIndex(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Birthday", "2020-12-23", "22nd Birthday", false);
        list.add(newItem);
        assertEquals( "22nd Birthday",list.get(0).getDescription());
    }
    @Test
    void gettingItemDueDateFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(4).getDueDate());
    }
    @Test
    void gettingItemDueDateSucceedsWithValidIndex(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Birthday", "2020-12-23", "22nd Birthday", false);
        list.add(newItem);
        assertEquals( "2020-12-23",list.get(0).getDueDate());
    }
    @Test
    void gettingItemTitleFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(4).getName());
    }
    @Test
    void gettingItemTitleSucceedsWithValidIndex(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Birthday", "2020-12-23", "22nd Birthday", false);
        list.add(newItem);
        assertEquals( "Birthday",list.get(0).getName());
    }
    @Test
    void newListIsEmpty(){
        TaskList list = new TaskList();
        assertEquals(0, list.getSize());
    }
    @Test
    void removingItemsDecreasesSize(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Birthday", "2020-12-23", "22nd Birthday", false);
        list.add(newItem);
        TaskItem newItem2 = new TaskItem("Birthday", "2020-12-23", "22nd Birthday", false);
        list.add(newItem2);
        list.remove(1);
        assertEquals( 1,list.getSize());
    }
    @Test
    void removingItemsFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Birthday", "2020-12-23", "22nd Birthday", false);
        list.add(newItem);
        TaskItem newItem2 = new TaskItem("Birthday", "2020-12-23", "22nd Birthday", false);
        list.add(newItem2);
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(5));
    }
    @Test
    void savedTaskListCanBeLoaded(){
        TaskList list = new TaskList();
        list.loadFile("output.txt");
        assertEquals("0) [2020-12-02] assignment 5: contact list\n",list.view());
    }
    @Test
    void uncompletingTaskItemChangesStatus(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Birthday", "2020-12-23", "22nd Birthday", false);
        list.add(newItem);
        list.markCompleted(0);
        list.unmarkCompleted(0);
        assertFalse(list.get(0).isCompleted());
    }
    @Test
    void uncompletingTaskItemFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Birthday", "2020-12-23", "22nd Birthday", false);
        list.add(newItem);
        list.markCompleted(0);
        assertThrows(IndexOutOfBoundsException.class, () -> list.unmarkCompleted(4));
    }
}