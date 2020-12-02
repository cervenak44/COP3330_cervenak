public class ContactItem {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;

    public ContactItem(String inputFirstName, String inputLastName, String inputPhoneNumber, String inputAddress) throws IllegalArgumentException {

        boolean contactChecker = nameChecker(inputFirstName, inputLastName, inputPhoneNumber, inputAddress);

        if(!contactChecker){
            throw new IllegalArgumentException();
        }

        this.firstName = inputFirstName;
        this.lastName = inputLastName;
        this.phoneNumber = inputPhoneNumber;
        this.emailAddress = inputAddress;
    }

    public boolean nameChecker(String one, String two, String three, String four){

        if(one.length() == 0 && two.length() == 0 && three.length() == 0 && four.length() == 0){
            System.out.println("Contact must contain at least one of the fields prompted.");
            return false;
        }

        if(four.length() != 0) {
            int counter = 0;

            for (int j = 0; j < four.length(); j++) {
                if (four.charAt(j) == '@') {
                    counter++;
                }
            }

            if (counter != 1) {
                System.out.println("Email invalid.\nPlease include an @ and any domain below:\n.com\n.net\n.gov\n.edu\n.org");
                return false;
            }
        }

        if(three.length() != 0) {
            if (!three.matches("...-...-....")) {
                System.out.println("Phone number invalid.\nPlease include dashes in the format XXX-XXX-XXXX.");
                return false;
            }

            for (int i = 0; i < three.length(); i++) {
                if ((three.charAt(i) >= 'a' && three.charAt(i) <= 'z') || (three.charAt(i) >= 'A' && three.charAt(i) <= 'Z')) {
                    System.out.println("Phone number invalid.\nPlease include only numbers.");
                    return false;
                }
            }
        }

        return true;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {

        if(this.phoneNumber.equals("") && this.lastName.equals("") && this.emailAddress.equals("")){
            System.out.println("This would mean all your fields will be blank.\nThat is not allowed. Nothing changed.");
            throw new IllegalArgumentException();
        }else {
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(this.firstName.equals("") && this.phoneNumber.equals("") && this.emailAddress.equals("")){
            System.out.println("This would mean all your fields will be blank.\nThat is not allowed. Nothing changed.");
            throw new IllegalArgumentException();
        }else {
            this.lastName = lastName;
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {

        if(this.firstName.equals("") && this.lastName.equals("") && this.emailAddress.equals("")){
            System.out.println("This would mean all your fields will be blank.\nThat is not allowed. Nothing changed.");
            throw new IllegalArgumentException();
        }else {
            this.phoneNumber = phoneNumber;
        }
    }

    public String getEmailAddress() throws IllegalArgumentException{return emailAddress;}

    public void setEmailAddress(String emailAddress) {
        if(this.firstName.equals("") && this.lastName.equals("") && this.phoneNumber.equals("")){
            System.out.println("This would mean all your fields will be blank.\nThat is not allowed. Nothing changed.");
            throw new IllegalArgumentException();
        }else {
            this.emailAddress = emailAddress;
        }
    }

    public String toStringFile(){return firstName + "\n" + lastName + "\n" + phoneNumber + "\n" + emailAddress;}

    @Override
    public String toString(){return "Name: " + firstName + " " + lastName + "\n" + "Phone: " + phoneNumber + "\n" + "Email: " + emailAddress;}
}