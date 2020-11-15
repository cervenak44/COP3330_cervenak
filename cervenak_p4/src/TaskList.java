import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class TaskList {
    List<TaskItem> list;

    // Student List is an ordered sequence of StudentData indexed by an integer value from 0 to n
    public TaskList() {
        list = new ArrayList<>();
    }

    public void add(TaskItem data) {
        list.add(data);
    }

    public void remove(TaskItem data){
        list.remove(data);
    }

    public void write(String filename) {
        try(Formatter output = new Formatter(filename)) {
            for(int i = 0; i < list.size(); i++) {
                TaskItem data = list.get(i);
                output.format("%s;%s;%s%n", data.getTitle(), data.getDescription(), data.getDueDate());
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to find the file...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
