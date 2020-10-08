public class Pyramid extends Shape3D {
    public Pyramid(double l, double b, double h) { //5 10 15

        this.length = l; //5
        this.base = b; //10
        this.height = h; //15

        //5 * 10 + 5root((10/2)*(10/2) + h*h

        this.area = this.length * this.base + this.length * Math.sqrt((((this.base * this.base)/4)) + (this.height * this.height))
                + this.base * Math.sqrt(((this.length/2) * (this.length/2)) + (this.height * this.height));

        this.volume = (this.length * this.height * this.base)/3;

    }
    @Override
    protected String getName() {
        return "pyramid";
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
