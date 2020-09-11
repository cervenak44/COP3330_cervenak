public class Decrypter {
    private String numIn;
    private int numToInt;

    public Decrypter(String numIn) {
        this.numToInt = Integer.parseInt(numIn); //takes string input and changes to int
    }


    public String decrypt(){

        int tempNum = numToInt;
        int digit1 = tempNum/1000, digit2 = tempNum/100 % 10, digit3 = tempNum/10 % 10, digit4 = tempNum %10;

        if(digit1 <= 6 && digit1 >= 0)
            digit1 = digit1 + 10;
        if(digit2 <= 6 && digit2 >= 0)
            digit2 = digit2 + 10;
        if(digit3 <= 6 && digit3 >= 0)
            digit3 = digit3 + 10;
        if(digit4 <= 6 && digit4 >= 0)
            digit4 = digit4 + 10;

        digit1 = digit1 - 7;
        digit2 = digit2 - 7;
        digit3 = digit3 - 7;
        digit4 = digit4 - 7;

        tempNum = digit1;
        digit1 = digit3 * 1000;
        digit3 = tempNum * 10;

        tempNum = digit2;
        digit2 = digit4 * 100;
        digit4 = tempNum;

        numToInt = digit1 + digit2 + digit3 + digit4;

        numIn = Integer.toString(numToInt); //converts int back to string

        return numIn;
    } //end encrypt
}
