import static org.junit.jupiter.api.Assertions.*;

class BodyMassIndexTest {

    @org.junit.jupiter.api.Test
    void BodyMassIndexTest() {
        BodyMassIndex bmi1 = new BodyMassIndex(72, 100); //underweight test
        BodyMassIndex bmi2 = new BodyMassIndex(72, 180); //normal bmi test
        BodyMassIndex bmi3 = new BodyMassIndex(72, 200); //overweight test
        BodyMassIndex bmi4 = new BodyMassIndex(72, 300); //obese test

        assertEquals(13.560956790123457, bmi1.bmiCalculated);
        assertEquals("underweight", bmi1.bodyMassCategory);

        assertEquals(24.40972222222222, bmi2.bmiCalculated);
        assertEquals("normal weight", bmi2.bodyMassCategory);

        assertEquals(27.121913580246915, bmi3.bmiCalculated);
        assertEquals("Overweight", bmi3.bodyMassCategory);

        assertEquals(40.682870370370374, bmi4.bmiCalculated);
        assertEquals("Obese", bmi4.bodyMassCategory);

    }
}