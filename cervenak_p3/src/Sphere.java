public class Sphere extends Shape3D {
    public Sphere(double i) {

        this.radius = i;

        this.area = (this.radius * this.radius) * Math.PI * 4;

        this.volume = ((this.radius * this.radius * this.radius) * Math.PI * 4)/3;

    }
    @Override
    protected String getName() {
        return "sphere";
    }

    @Override
    protected double getArea() {
        return this.area;
    }

    @Override
    protected double getVolume() {
        return this.volume;
    }
}
