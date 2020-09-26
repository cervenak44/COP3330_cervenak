
//import java.text.DecimalFormat; //used for formatting method

public class BodyMassIndex {
    public String bodyMassCategory;
    public double bmiCalculated;

    //private static DecimalFormat df2 = new DecimalFormat("#.##"); //formatting to .00 decimals

    public BodyMassIndex(double height, double weight) {


        //703 * weight / inches^2
        this.bmiCalculated = 0.0;
        bmiCalculated = (weight * 703) / (height * height);


        bodyMassCategory = BodyMassCategory(bmiCalculated);

        //System.out.println(df2.format(bmiCalculated)); //checking my output here
        //System.out.println(bodyMassCategory);

    }


   public String BodyMassCategory(double bmiCalculated){
        this.bodyMassCategory = "none";
       if(bmiCalculated < 18.5){
           bodyMassCategory =  "underweight";
           return bodyMassCategory;

       }else if(bmiCalculated >= 18.5 && bmiCalculated < 24.9){
           bodyMassCategory =  "normal weight";
           return bodyMassCategory;

       }else if(bmiCalculated >= 25 && bmiCalculated < 29.9){
           bodyMassCategory =  "Overweight";
           return bodyMassCategory;

       }else if(bmiCalculated >= 30){
           bodyMassCategory =  "Obese";
           return bodyMassCategory;
       }
       return bodyMassCategory;
   }



}
