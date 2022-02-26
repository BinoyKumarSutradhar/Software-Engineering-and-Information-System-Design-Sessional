import java.io.*;
import java.util.ArrayList;
import java.util.List;
import static java.lang.System.exit;
import static java.lang.System.out;

abstract class shake{
    protected int rate;
    List<String> x = new ArrayList<String>(1000);
    public void setrate(int r)
    {
        rate=rate+r;
    }
    public int getrate()

    {
        return rate;
    }

    abstract void base_ingr();

    public void added_ingr(String s)
    {
        /*if(s==null)
        {
            return null;
        }*/
        if(s.equalsIgnoreCase("Almond milk"))
        {
            //System.out.println("Almond milk-60 Tk");
            x.add("Almond milk");
            setrate(60);
        }
        if(s.equalsIgnoreCase("Candy"))
        {
            //System.out.println("Candy-50 Tk");
            x.add("Candy");
            setrate(50);
        }
        if(s.equalsIgnoreCase("Cookie"))
        {
            //System.out.println("Cookie-40 Tk");
            x.add("Cookie");
            setrate(40);
        }
    }
}


class Chocolate_shake extends shake{

    public void base_ingr()
    {
        System.out.println("Shake name: Chocolate shake");
        System.out.println("Base ingredients: Milk, Sugar, Chocolate syrup, Chocolate icecream.");
        System.out.println("Base price: 230 Tk");
    }

}

 class Coffee_shake extends shake{

    public void base_ingr()
    {
        System.out.println("Shake name: Coffee shake");
        System.out.println("Base ingredients: Milk, Sugar, Coffee, Jello.");
        System.out.println("Base price: 230 Tk");
    }

}

 class Strawberry_shake extends shake{

    public void base_ingr()
    {
        System.out.println("Shake name: Strawberry shake");
        System.out.println("Base ingredients: Milk, Sugar, Strawberry syrup, Strawberry icecream.");
        System.out.println("Base price: 200 Tk");
    }

}

 class Vanilla_shake extends shake{

    public void base_ingr()
    {
        System.out.println("Shake name: Vanilla shake");
        System.out.println("Base ingredients: Milk, Sugar, Vanilla flavouring, Jello.");
        System.out.println("Base price: 190 Tk");
    }

}

 class Zero_shake extends shake{

    public void base_ingr()
    {
        System.out.println("Shake name: Zero shake");
        System.out.println("Base ingredients: Milk, Sweetener, Vanilla flavouring, Sugar free jello.");
        System.out.println("Base price: 240 Tk");
    }

}



 class Shakefactory{

    BufferedReader b1 = new BufferedReader(new InputStreamReader(System.in));
    public shake getshake() throws IOException {

        shake S = null;

        System.out.println("Available Shakes: ");
        System.out.println(" 1. Chocolate - 230 Tk");
        System.out.println(" 2. Coffee - 230 Tk");
        System.out.println(" 3. Strawberry - 200 Tk");
        System.out.println(" 4. Vanilla - 190 Tk ");
        System.out.println(" 5. Zero - 240 Tk");
        System.out.println("Enter your preferred shake name:");

        BufferedReader b1 = new BufferedReader(new InputStreamReader(System.in));
        String type = b1.readLine();

        if(type==null)
        {
            return null;
        }
        if(type.equalsIgnoreCase("Chocolate"))
        {
             S = new Chocolate_shake();
             S.setrate(230);
        }
        else if(type.equalsIgnoreCase("Coffee"))
        {
             S = new Coffee_shake();
             S.setrate(230);
        }
         else if(type.equalsIgnoreCase("Strawberry"))
        {
             S = new Strawberry_shake();
             S.setrate(200);
        }
        else if(type.equalsIgnoreCase("Vanilla"))
        {
             S = new Vanilla_shake();
             S.setrate(190);
        }
        else if(type.equalsIgnoreCase("Zero"))
        {
             S = new Zero_shake();
             S.setrate(240);
        }

        else
        {
            return null;
        }

        System.out.println("Any extra ingredient needed?? ");
        System.out.println("Press Y or N");

        String a = b1.readLine();

        if (a.equalsIgnoreCase("Y")) {
            System.out.println("Extra ingredients available : ");
            System.out.println(" 1. Almond milk - 60 Tk ");
            System.out.println(" 2. Candy - 50 Tk");
            System.out.println(" 3. Cookie - 40 Tk");
            System.out.println("Enter no of ingredients:");

            int n = Integer.parseInt(b1.readLine());

            for (int i = 0; i < n; i++) {

                System.out.println("Enter your preferred ingredients name : ");
                String c = b1.readLine();
                S.added_ingr(c);
            }

        }
        else
        {
            return S;
        }

        return S;

    }

 }



public class shakeshack {
    public static void main(String[] args)throws IOException {
        Shakefactory factory = new Shakefactory();
        builder d = new builder();

        while (true) {
            System.out.println("Press O for new order.");
            System.out.println("Press P for print.");
            System.out.println("Press E for exit.");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String s1 = br.readLine();
            if (s1.equalsIgnoreCase("O")) {

                shake shack = factory.getshake();
                d.add(shack);
            }

            else if(s1.equalsIgnoreCase("P"))
            {
                d.show();
            }

            else if (s1.equalsIgnoreCase("E"))
            {
                d.show();
                exit(0);
            }

        }
    }


}
