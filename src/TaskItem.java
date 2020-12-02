import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskItem {

    private String name;
    private String dueDate;
    private String description;
    private boolean isCompleted = false;

    public TaskItem(String inputName, String inputDueDate, String inputDescription, boolean completion) throws IllegalArgumentException{
        IllegalArgumentException e = null;

        boolean checker = dateNameChecker(inputName, inputDueDate);

        if(!checker){
            throw e;
        }

        this.name = inputName;
        this.dueDate = inputDueDate;
        this.description = inputDescription;
        this.isCompleted = completion;

    }

    public boolean dateNameChecker(String inputName, String inputDueDate){
        if(inputName.length() < 1){System.out.println("Title must be at 1 character long; Task not created."); return false;}

        if (!(inputDueDate.matches("....-..-.."))) {System.out.println("Date is in improper format; Use YYYY-MM-DD"); return false;}

        if((inputDueDate.charAt(5) == '1' && inputDueDate.charAt(6) == '3') || (inputDueDate.charAt(5) == '1' && inputDueDate.charAt(6) == '4') || (inputDueDate.charAt(5) == '1' && inputDueDate.charAt(6) == '5') || (inputDueDate.charAt(5) == '1' && inputDueDate.charAt(6) == '6') || (inputDueDate.charAt(5) == '1' && inputDueDate.charAt(6) == '7') || (inputDueDate.charAt(5) == '1' && inputDueDate.charAt(6) == '8') || (inputDueDate.charAt(5) == '1' && inputDueDate.charAt(6) == '9')){
            System.out.println("Date invalid.");
            return false;
        }

        Date currentDate = new Date();
        Date inputDate = null;
        try {inputDate = new SimpleDateFormat("yyyy-MM-dd").parse(inputDueDate);} catch (ParseException er) {er.getMessage();}

        if(currentDate.after(inputDate)){System.out.println("Date is in the past. Task not created."); return false;}

        return true;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void unsetCompleted(){
        this.isCompleted = false;
    }

    public void setCompleted() {
        this.isCompleted = true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getDescription() {
        return description;
    }

    public String toStringFile() {
        return "[" + dueDate + "]" + "\n" + name + "\n" + description;
    }

    @Override
    public String toString() {
        return "[" + dueDate + "] " + name + ": " + description;
    }

}