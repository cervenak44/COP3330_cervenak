public class Triangle extends Shape2D{
    public Triangle(double i, double b) {

        this.height = i;
        this.base = b;


    }
    @Override
    protected String getName() {
        return "triangle";
    }

    @Override
    protected double getArea() {
        return this.area = (this.height * this.base)/2;
    }

    @Override
    protected double getVolume() {
        return 0;
    }
}
