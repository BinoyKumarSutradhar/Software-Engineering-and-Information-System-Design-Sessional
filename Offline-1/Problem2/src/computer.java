import java.io.*;

abstract class computer {

    abstract void details();

}

class computerA extends computer{
    public void details()
    {
        System.out.println("Computer name : Computer A");
        System.out.println("Resolution : 200 x 200");
    }
}

class computerB extends computer{
    public void details()
    {
        System.out.println("Computer name : Computer B");
        System.out.println("Resolution : 350 x 250");
    }
}

class computerC extends computer{
    public void details()
    {
        System.out.println("Computer name : Computer C");
        System.out.println("Resolution : 550 x 430");
    }
}
