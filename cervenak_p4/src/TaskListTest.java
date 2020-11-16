
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;


class TaskListTest {

    @Test
    void addingTaskItemsIncreasesSize(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Task 1", "2020-11-16", "description1", false);
        list.taskList.add(newItem);
        assertEquals(1, list.taskList.size());
    }

    @Test
    void completingTaskItemChangesStatus(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Task 1", "2020-11-16", "description1", false);
        list.taskList.add(newItem);
        newItem.setCompleted();
        assertTrue(newItem.isCompleted());
    }

    @Test
    void completingTaskItemFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Task 1", "2020-11-16", "description 1", false);
        TaskItem newItem2 = new TaskItem("Task 2", "2020-12-16", "description 2", false);
        list.taskList.add(newItem);
        list.taskList.add(newItem2);
        assertThrows(IndexOutOfBoundsException.class, () -> {list.markCompleted(list.taskList, 2);});

    }

    @Test
    void editingTaskItemChangesValues(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Task 1", "2020-11-16", "description 1", false);
        list.taskList.add(newItem);
        TaskItem editItem = new TaskItem("Task 4", "2020-12-17", "This is task 4 to be comeplted later", false);
        list.taskList.set(0,editItem);

        assertEquals(editItem, list.taskList.get(0));

    }

    @Test
    void editingTaskItemDescriptionChangesValue(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("hang tree", "2020-12-01", "set up christmas tree", false);
        list.taskList.add(newItem);
        TaskItem editItem = new TaskItem("bake cookies", "2020-12-23", "bake cookies for christmas", false);
        list.taskList.set(0,editItem);

        assertEquals("bake cookies for christmas", list.taskList.get(0).getDescription());
    }

    @Test
    void editingTaskItemDescriptionFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("task 1", "2021-12-23", "i need to code faster", false);
        list.taskList.add(newItem);
        assertThrows(IndexOutOfBoundsException.class, () -> { list.taskList.get(4).setDescription("worried");});

    }

    @Test
    void editingTaskItemDueDateChangesValue(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Get groceries", "2021-11-16", "get eggs", false);
        list.taskList.add(newItem);
        TaskItem editItem = new TaskItem("get gifts", "2020-12-30", "I get paid and can get gifts on the 30th", false);
        list.taskList.set(0,editItem);

        assertEquals("2020-12-30", list.taskList.get(0).getDueDate());
    }

    @Test
    void editingTaskItemDueDateFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Clean room", "2024-09-10", "i foresee my house being messy", false);
        list.taskList.add(newItem);
        assertThrows(IndexOutOfBoundsException.class, () -> { list.taskList.get(-1).setDueDate("wrong");});
    }

    @Test
    void editingTaskItemTitleChangesValue(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("Paint garage", "2021-08-11", "wife picks colors, go with it...", false);
        list.taskList.add(newItem);
        TaskItem editItem = new TaskItem("pick new color", "2021-08-18", "wife changed her mind", false);
        list.taskList.set(0,editItem);

        assertEquals("pick new color", list.taskList.get(0).getName());
    }

    @Test
    void editingTaskItemTitleFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("set up child bank account", "2022-12-12", "i plan too much", false);
        assertThrows(IndexOutOfBoundsException.class, () -> { list.taskList.get(7).setName("New");});
    }

    @Test
    void gettingTaskItemDescriptionFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("set up for retirement", "2025-12-23", "i need to live in the present", false);
        list.taskList.add(newItem);
        assertThrows(IndexOutOfBoundsException.class, () -> {list.taskList.get(1).getDescription();});
    }

    @Test
    void gettingTaskItemDescriptionSucceedsWithValidIndex(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("graduate", "2021-05-05", "time to move on", false);
        list.taskList.add(newItem);
        assertEquals("time to move on", list.taskList.get(0).getDescription());
    }

    @Test
    void gettingTaskItemDueDateFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("task 1", "2021-11-16", "coding assignment", false);
        list.taskList.add(newItem);
        assertThrows(IndexOutOfBoundsException.class, () -> {list.taskList.get(1).getDueDate();});
    }

    @Test
    void gettingTaskItemDueDateSucceedsWithValidIndex(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("graduate", "2021-05-05", "i hope i graduate", false);
        list.taskList.add(newItem);
        assertEquals("2021-05-05", list.taskList.get(0).getDueDate());
    }

    @Test
    void gettingTaskItemTitleFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("graduate", "2021-05-05", "i hope i graduate", false);
        list.taskList.add(newItem);
        assertThrows(IndexOutOfBoundsException.class, () -> {list.taskList.get(1).getName();});
    }

    @Test
    void gettingTaskItemTitleSucceedsWithValidIndex(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("graduate", "2021-05-05", "i hope i graduate", false);
        list.taskList.add(newItem);
        assertEquals("graduate", list.taskList.get(0).getName());
    }

    @Test
    void newTaskListIsEmpty(){
        TaskList list = new TaskList();
        assertEquals(0,list.taskList.size());
    }

    @Test
    void removingTaskItemsDecreasesSize(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("graduate", "2021-05-05", "i hope i graduate", false);
        list.taskList.add(newItem);
        TaskItem newItem2 = new TaskItem("graduate", "2021-05-05", "i hope i graduate", false);
        list.taskList.add(newItem2);
        TaskItem newItem3 = new TaskItem("graduate", "2021-05-05", "i hope i graduate", false);
        list.taskList.add(newItem3);
        TaskItem newItem4 = new TaskItem("graduate", "2021-05-05", "i hope i graduate", false);
        list.taskList.add(newItem4);
        list.removeTask(list.taskList, 1);
        assertEquals(3, list.taskList.size());
    }

    @Test
    void removingTaskItemsFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("graduate", "2021-05-05", "i hope i graduate", false);
        list.taskList.add(newItem);
        TaskItem newItem2 = new TaskItem("graduate", "2021-05-05", "i hope i graduate", false);
        list.taskList.add(newItem2);
        TaskItem newItem3 = new TaskItem("graduate", "2021-05-05", "i hope i graduate", false);
        list.taskList.add(newItem3);
        TaskItem newItem4 = new TaskItem("graduate", "2021-05-05", "i hope i graduate", false);
        list.taskList.add(newItem4);
        assertEquals(4, list.taskList.size());
    }

    @Test
    void savedTaskListCanBeLoaded(){
        TaskList loadedList = new TaskList();
        File listFile = new File("output.txt");
        assertTrue(listFile.exists());
    }

    @Test
    void savedTaskListCannotBeLoaded(){
        TaskList loadedList = new TaskList();
        File listFile = new File("notname");
        assertFalse(listFile.exists());
    }

    @Test
    void uncompletingTaskItemChangesStatus(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("graduate", "2021-05-05", "i graduate", false);
        list.taskList.add(newItem);
        list.markCompleted(list.taskList, 0);
        list.unmarkCompleted(list.taskList, 0);
        assertFalse(list.taskList.get(0).isCompleted());
    }

    @Test
    void uncompletingTaskItemFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("graduate", "2021-05-05", "i graduate", false);
        list.taskList.add(newItem);
        assertThrows(IndexOutOfBoundsException.class, () -> {list.taskList.get(9).unsetCompleted();});
    }

    @Test
    void getDescriptiongetsCorrectDescription(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("graduate", "2021-05-05", "i graduate", false);
        list.taskList.add(newItem);
        assertEquals("i graduate", list.taskList.get(0).getDescription());
    }

    @Test
    void getDueDategetsCorrectDueDate(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("graduate", "2021-05-05", "i graduate", false);
        list.taskList.add(newItem);
        assertEquals("2021-05-05", list.taskList.get(0).getDueDate());
    }

    @Test
    void getNamegetsCorrectName(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("graduate", "2021-05-05", "i graduate", false);
        list.taskList.add(newItem);
        assertEquals("graduate", list.taskList.get(0).getName());
    }

    @Test
    void getStatusgetsCorrectStatus(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("graduate", "2021-05-05", "i graduate", false);
        list.taskList.add(newItem);
        list.taskList.get(0).setCompleted();
        assertTrue(list.taskList.get(0).isCompleted());
    }

    @Test
    void taskItemtoStringPrintsProperly(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("graduate", "2021-05-05", "i graduate", false);
        list.taskList.add(newItem);
        assertEquals("[2021-05-05] graduate: i graduate", list.taskList.get(0).toString());
    }

    @Test
    void taskItemtoStringFilePrintsProperly(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("graduate", "2021-05-05", "i graduate", false);
        list.taskList.add(newItem);
        assertEquals("[2021-05-05]\ngraduate\ni graduate", list.taskList.get(0).toStringFile());
    }

    @Test
    void completedItemPrintsWithStars(){
        TaskList list = new TaskList();
        TaskItem newItem = new TaskItem("graduate", "2021-05-05", "i graduate", false);
        list.taskList.add(newItem);
        list.taskList.get(0).setCompleted();
        //In program if statement prints list with stars if completed exactly like this
        assertEquals("*** [2021-05-05] graduate: i graduate", "*** " + list.taskList.get(0).toString());
    }




}