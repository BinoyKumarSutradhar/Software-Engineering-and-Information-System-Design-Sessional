import java.io.*;

public class Demo {
    public static void main(String[] args)throws IOException {

        System.out.println("\n");
        System.out.println("TEST CASE #1:\n");

        AbstractFactory c_factory = FactoryCreator.getfactory("computer");
        computer c = c_factory.getcomputer("computerA");

        AbstractFactory s_factory = FactoryCreator.getfactory("shape");
        shape s = s_factory.getshape("circle");

        c.details();
        s.show(10,0 ,0);

        System.out.println("\n");

        System.out.println("TEST CASE #2:\n");

        computer c1 = c_factory.getcomputer("computerB");

        shape s1 = s_factory.getshape("square");

        c1.details();
        s1.show(15, 0, 0);

        System.out.println("\n");

        System.out.println("TEST CASE #3:\n");

        computer c2 = c_factory.getcomputer("computerC");

        shape s2 = s_factory.getshape("rectangle");

        c2.details();
        s2.show(20,25, 0);

        System.out.println("\n");

        System.out.println("TEST CASE #4:\n");

        computer c3 = c_factory.getcomputer("computerA");

        shape s3 = s_factory.getshape("triangle");

        c3.details();
        s3.show(30,35,40);

        System.out.println("\n");
    }

}
