public class Encrypter {
    private String numIn, numOut;
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

         String digitToString1, digitToString2, digitToString3, digitToString4;

         digitToString1 = Integer.toString(digit1);
         digitToString2 = Integer.toString(digit2);
         digitToString3 = Integer.toString(digit3);
         digitToString4 = Integer.toString(digit4);

         numOut = digitToString3 + digitToString4 + digitToString1 +digitToString2;

        return numOut;
    } //end encrypt
} //end encrypter
