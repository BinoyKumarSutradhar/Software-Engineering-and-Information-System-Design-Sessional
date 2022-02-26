import java.util.ArrayList;
import java.util.List;

public class builder {
    private List<shake> drinks = new ArrayList<shake>();

    public void add(shake y){
        drinks.add(y);
    }

    public int total(){
        int  t = 0;

        for (shake y : drinks) {
            t += y.getrate();
        }
        return t;
    }

    public void show(){

        for (shake y : drinks) {
            y.base_ingr();

            System.out.println("\nExtra ingredients added:");
            for (String a : y.x) {

                if(a.equalsIgnoreCase("Almond milk"))
                {
                    System.out.println("Almond milk-60 Tk");
                }
                if(a.equalsIgnoreCase("Candy"))
                {
                    System.out.println("Candy-50 Tk");
                }
                if(a.equalsIgnoreCase("Cookie"))
                {
                    System.out.println("Cookie-40 Tk");
                }
            }
            System.out.println("\n");

            System.out.println("Shake price :"+ y.getrate()+"\n");
        }
        System.out.println("\nTotal price :"+ total());
    }

}
