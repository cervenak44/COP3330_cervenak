public class Encrypter {
    private String numIn;
    private int numToInt;

    public Encrypter(String numIn) {
        this.numToInt = Integer.parseInt(numIn); //takes string input and changes to int
    }


     public String encrypt(){

         int tempNum = numToInt;
         int digit4 = (tempNum + 7) % 10;
         tempNum = tempNum / 10;
         int digit3 = (tempNum + 7) % 10;
         tempNum = tempNum / 10;
         int digit2 = (tempNum + 7) % 10;
         tempNum = tempNum / 10;
         int digit1 = (tempNum + 7) % 10;
         numToInt = (digit3 * 1000 + digit4 * 100 + digit1 * 10 + digit2);

         numIn = Integer.toString(numToInt); //converts int back to string

        return numIn;
    } //end encrypt

} //end encrypter
