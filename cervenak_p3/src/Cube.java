public class Cube extends Shape3D {
    public Cube(double i) {
        this.height = i;

    }
    @Override
    protected String getName() {
        return "cube";
    }

    @Override
    protected double getArea() {
        return this.area = (this.height * this.height) * 6;
    }

    @Override
    protected double getVolume() {
        return this.volume = this.height * this.height * this.height;
    }
}
