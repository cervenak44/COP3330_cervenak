public class TaskItem {
    private String title;
    private String description;
    private String dueDate;

    public TaskItem(String title, String description, String dueDate) {

        if(isTitleValid(title)) {
            this.title = title;
        } else {
            throw new InvalidTitleException("title not valid;");
        }

        if(isDescriptionValid(description)){
            this.description = description;
        } else {
            throw new InvalidDescriptionException("description not valid;");
        }

        if(isDueDateValid(dueDate)){
            this.dueDate = dueDate;
        }else {
            throw new InvalidDueDateException("due date not valid");
        }
    }

    private boolean isTitleValid(String name) {
        return name.length() > 1;
    }

    private boolean isDescriptionValid(String description) {
        return description.length() >= 0;
    }

    private boolean isDueDateValid(String dueDate){
        return dueDate.length() >= 0;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDueDate(){
        return this.dueDate;
    }
}

class InvalidTitleException extends IllegalArgumentException {
    public InvalidTitleException(String msg) {
        super(msg);
    }
}

class InvalidDescriptionException extends IllegalArgumentException {
    public InvalidDescriptionException(String msg) {
        super(msg);
    }
}

class InvalidDueDateException extends IllegalArgumentException{
    public InvalidDueDateException(String msg){
        super(msg);
    }
}

