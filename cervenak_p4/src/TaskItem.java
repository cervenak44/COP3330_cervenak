public class TaskItem {

    private String name;
    private String dueDate;
    private String description;
    private boolean isCompleted = false;

    public TaskItem(String inputName, String inputDueDate, String inputDescription, boolean completion) {
        this.name = inputName;
        this.dueDate = inputDueDate;
        this.description = inputDescription;
        this.isCompleted = completion;

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
        return "[" + dueDate + "]\n" + name + "\n" + description;
    }

    @Override
    public String toString() {
        return "[" + dueDate + "] " + name + ": " + description;
    }

} //end task item