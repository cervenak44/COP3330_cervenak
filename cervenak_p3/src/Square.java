public class Square extends Shape2D {
    public Square(double i) {


        this.height = i;

        this.area = this.height * this.height;

    }

    @Override
    protected String getName() {
        return "square";
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
