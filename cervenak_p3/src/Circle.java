public class Circle extends Shape2D {
    public Circle(double i){

        this.radius = i;

        this.area = (this.radius * this.radius) * Math.PI;
    }
    @Override
    protected String getName() {
        return "circle";
    }

    @Override
    protected double getArea() {
        return this.area;
    }

    @Override
    protected double getVolume() {
        return 0;
    }
}
