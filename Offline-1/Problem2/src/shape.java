import java.io.*;

abstract class shape {

    abstract void show(double a, double b, double c);

}


class circle extends shape{
    @Override
    void show(double a,double b,double c) {
        double A;
        double P;
        A = 3.1416*a*a;
        P = 2*3.1416*a;

        System.out.println("Shape name : Circle");
        System.out.println("Surface area :"+A );
        System.out.println("Perimeter : "+P );
    }
}

class square extends shape{
    @Override
    void show(double a, double b, double c) {
        double A;
        double P;
        A = a*a;
        P = 4*a;

        System.out.println("Shape name : Square");
        System.out.println("Surface area :"+A );
        System.out.println("Perimeter : "+P );
    }
}

class rectangle extends shape{
    @Override
    void show(double a, double b, double c) {
        double A;
        double P;
        A = a*b;
        P = 2*(a+b);

        System.out.println("Shape name : Rectangle");
        System.out.println("Surface area :"+A );
        System.out.println("Perimeter : "+P );
    }
}

class triangle extends shape{
    @Override
    void show(double a, double b, double c) {
        double A;
        double s;
        double P;

        s = (a+b+c)/2;
        A = Math.sqrt(s*(s-a)*(s-b)*(s-c));

        P = a + b + c ;

        System.out.println("Shape name : Triangle");
        System.out.println("Surface area : "+A );
        System.out.println("Perimeter : "+P );
    }
}