import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    private List<TaskItem> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public String view(){
        System.out.println("Current Tasks");
        System.out.println("-------------");
        System.out.println();

        if(taskList.size() == 0){
            return "The list is currently empty";
        }

        String list = "";

        for(int i = 0; i < taskList.size(); i++){
            if(taskList.get(i).isCompleted()){
                list += i + ") *** " + taskList.get(i).toString() + "\n";
            }
            else {
                list += i + ") " + taskList.get(i).toString() + "\n";
            }
        }

        return list;
    }

    public String viewUncompletedTasks(){
        System.out.println("Uncompleted Tasks");
        System.out.println("-------------");

        String incompleteList = "";
        int counter = 0;

        for(int i = 0; i < taskList.size(); i++){
            if(!(taskList.get(i).isCompleted())){
                incompleteList += i + ") " + taskList.get(i).toString() + "\n";
                counter++;
            }
        }

        if(counter == 0){
            return "You currently have no uncompleted tasks";
        }

        return incompleteList;
    } //end view uncompleted

    public String viewCompleteTasks(){
        System.out.println("Completed Tasks");
        System.out.println("-------------");
        System.out.println();

        String completedList = "";
        int counter = 0;

        for(int i = 0; i < taskList.size(); i++){
            if(taskList.get(i).isCompleted()){
                completedList += i + ") *** " + taskList.get(i).toString() + "\n";
                counter++;
            }
        }

        if(counter == 0){
            return "You currently have no uncompleted tasks";
        }

        return completedList;
    } //end view completed tasks

    public void add(TaskItem task){
        taskList.add(task);
    } //end add

    public void set(TaskItem task, int index){
        taskList.set(index, task);
    } //end set

    public void remove(int index){
        taskList.remove(index);
    } //end remove

    public int getSize(){
        return taskList.size();
    } //end get size

    public void markCompleted(int choice){
        try{taskList.get(choice).setCompleted();}
        catch(IndexOutOfBoundsException e){throw e;}
    } //end mark complete

    public void unmarkCompleted(int choice){
        try{taskList.get(choice).unsetCompleted();}
        catch(IndexOutOfBoundsException er){throw er;}
    } //end unmark

    public TaskItem get(int index){
        return taskList.get(index);
    } //end get

    public void saveFile(String fileName){

        File savedFile = new File(fileName);

        try{
            FileWriter taskWriter = new FileWriter(savedFile);
            taskWriter.write(taskList.size() + "\n");
            for(int i = 0; i < taskList.size(); i++){
                if(taskList.get(i).isCompleted()){
                    taskWriter.write("true\n" + taskList.get(i).toStringFile() + "\n");
                }
                else {
                    taskWriter.write("false\n" + taskList.get(i).toStringFile() + "\n");
                }
            }

            taskWriter.close();
        }
        catch(IOException e){
            System.out.println("File error occured. Could not save");
        }

        System.out.println("task list has been saved.");

    }//end save file

    public void loadFile(String fileName){
        String taskName;
        String dueDate;
        String description;
        String isCompleted;
        boolean taskDone;
        int listSize = 0;

        File listFile = new File(fileName);
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(listFile);
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
        }

        listSize = fileReader.nextInt();
        fileReader.nextLine();

        for(int i = 0; i < listSize; i++){
            isCompleted = fileReader.nextLine();
            dueDate = fileReader.nextLine();
            taskName = fileReader.nextLine();
            description = fileReader.nextLine();

            if(isCompleted.contains("true")){
                taskDone = true;
            }
            else{
                taskDone = false;
            }

            try{
                TaskItem newItem = new TaskItem(taskName, dueDate, description, taskDone);
                this.add(newItem);
            }
            catch(InputMismatchException err) {
                System.out.println("An invalid task name was found in the file. List not loaded");
                return;
            }

        }
    }//end load file
}//end task list
