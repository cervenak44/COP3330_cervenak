import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.text.DecimalFormat;

public class App {
//Cannot Change Main
    public static void main(String[] args) {
        ArrayList<BodyMassIndex> bmiData = new ArrayList<BodyMassIndex>();

        while (moreInput()) {
            double height = getUserHeight();
            double weight = getUserWeight();

            BodyMassIndex bmi = new BodyMassIndex(height, weight);
            bmiData.add(bmi);

            displayBmiInfo(bmi);
        }

        displayBmiStatistics(bmiData);
    } //end Main

    public static boolean moreInput(){
        boolean moreInput;

        System.out.println("Enter Info? Y or N");
        Scanner scan = new Scanner(System.in);
        char s = scan.next().charAt(0);

        if(s == 'y' || s == 'Y'){
            moreInput = true;
        }
        else if(s == 'n' || s == 'N') {
            moreInput = false;
        }
        else {
            moreInput = false;
        }

            return moreInput;


    } //end moreInput

    public static double getUserHeight(){
        //only accepts positive values
        //inches
        Scanner scan = new Scanner(System.in);
        double height = 0;

        do {
            System.out.println("Enter Height in Inches");
            if(scan.hasNextDouble()){
                height = scan.nextDouble();
                if(height < 0){
                    System.out.println("Enter a Positive number");
                    scan.nextLine();
                }
            }
            else{
                System.out.println("Enter a number");
                scan.nextLine();
            }

        }while(height <= 0);

        return height;
    } //end getUserHeight

    public static double getUserWeight(){
        //only accepts positive values
        //pounds
        Scanner scan = new Scanner(System.in);
        double weight = 0;

        do {
            System.out.println("Enter Weight in Pounds");
            if(scan.hasNextDouble()){
                weight = scan.nextDouble();
                if(weight < 0){
                    System.out.println("Enter a positive number");
                    scan.nextLine();
                }
            }
            else{
                System.out.println("Enter a number");
                scan.nextLine();
            }

        }while(weight <= 0);

        return weight;
    }//end getUserWeight

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public static void displayBmiInfo(BodyMassIndex bmi){
        //prints user's bmi score and category

        //System.out.println("Testing below");
        System.out.println(df2.format(bmi.bmiCalculated));
        System.out.println(bmi.bodyMassCategory);

    }//end displayBmi

    public static void displayBmiStatistics(ArrayList<BodyMassIndex> bmiData){
        //prints average BMI score of data

        System.out.println("The average BMI is" + " " + df2.format(calculateAverage(bmiData)));

        /*System.out.println("__________________");

        System.out.println(bmiData.get(0).bmiCalculated);
        System.out.println("__________________");
        for(BodyMassIndex bmiD: bmiData){
            System.out.println(bmiD.bmiCalculated);
        }

         *///end test printing

    } //end displayBmiStat

    private static double calculateAverage(ArrayList<BodyMassIndex> bmiData) {
        double total = 0.0;
        double avg;

        for(int i =0; i < bmiData.size(); i++){
            total += bmiData.get(i).bmiCalculated;
        }
        avg = total/bmiData.size();

        return avg;

    }

}//end App

