import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.System.exit;

class Company
{
    int state;
    int cost;
    //ArrayList<Observer> observerList;
    List<Observer> observerList = new ArrayList<Observer>();

    /*public Company() {
        observerList = new ArrayList<Observer>();

    }*/

    void set_state(int x)
    {
        state = x;
        for (Iterator<Observer> it = observerList.iterator(); it.hasNext();)
        {
            Observer o = it.next();
            o.init(x,this);
        }
        cost=0;
    }

    int set_cost()
    {
        cost=200;
        return cost;
    }

    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    public void unregisterObserver(Observer o) {
        observerList.remove(o);
    }

    public void notifyObservers(int s) throws IOException {
       /* if(s.equalsIgnoreCase("Operational"))
        {
           state = 1;
        }
        else if(s.equalsIgnoreCase("Partially Down"))
        {
            state = 2;
        }
        else if(s.equalsIgnoreCase("Fully Down"))
        {
            state = 3;
        }*/
        state=s;
        for (Iterator<Observer> it = observerList.iterator(); it.hasNext();)
        {
            Observer o = it.next();
            o.update(state);
        }
    }

    /*public void stateChanged(String a) throws IOException {
        notifyObservers(a);
    }*/
}

interface Observer
{
    public void update(int x) throws IOException;
    public void init(int y,Company in);
    //public void cost();
}

class reg_user implements Observer
{
    int u;
    int service;
    Company x = new Company();
    //Company DEF = new Company();
   // int t;
    @Override
    public void init(int y,Company in) {
        u=y;
        service=1;
        x=in;
        //t=0;
    }

   /* public void cost()
    {
        System.out.println("Usage cost: x");
    }*/

    public void update(int r) throws IOException {

        BufferedReader b1 = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\n*****REGULAR*******");
        if(u==1)
        {
            if(r==2)
            {
                System.out.println("Regular Choice:");
                System.out.println(" 1. Current Server");
                System.out.println(" 2. New Server");
                //System.out.println(" 3. Both Server");
                System.out.println("Enter choice:");
                String c = b1.readLine();
                service = Integer.parseInt(c);
                u = r;
            }
            else if(r==3)
            {
                System.out.println("Regular Choice:");
                System.out.println(" 1. Current Server");
                System.out.println(" 2. New Server");
                //System.out.println(" 3. Both Server");
                System.out.println("Enter choice:");
                String c = b1.readLine();
                service = Integer.parseInt(c);
                u = r;
            }
        }
        else if(u==2)
        {
            if(r==1)
            {
               if(service==2)
               {
                   //System.out.println("Total cost: 20x.");
                   //x.set_cost();
                   System.out.println("Total Bill:"+x.set_cost());
               }
               u=r;
            }
            if(r==3)
            {
                u=r;
            }
        }
        else if(u==3)
        {
            if(r==1)
            {
                if(service==2)
                {
                    //System.out.println("Total cost: 20x.");
                    System.out.println("Total Bill:"+x.set_cost());
                }
                u=r;
            }
            if(r==2)
            {
                u=r;
            }
        }

        //init(r);
        display(r);
    }

    public void display(int y)
    {
        System.out.println("Regular Server State :"+y);
    }
}

class prem_user implements Observer
{
    int u;
    int service;
    Company x = null;
    // int t;
    @Override
    public void init(int y,Company in) {
        u=y;
        service=1;
        x=in;
        //t=0;
    }

    public void update(int r) throws IOException {

        BufferedReader b1 = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\n*****PREMIUM*****");
        if(u==1)
        {
            if(r==2)
            {
                System.out.println("Premium Choice:");
                //System.out.println(" 1. Current Server");
                System.out.println(" 2. New Server");
                System.out.println(" 3. Both Server");
                System.out.println("Enter choice:");
                String c = b1.readLine();
                service = Integer.parseInt(c);
                u = r;
            }
            else if(r==3)
            {
                service = 2;
                System.out.println("Your service is now being provided by DEF company!!");
                u = r;
            }
        }
        else if(u==2)
        {
            if(r==3)
            {
                if(service==3)
                {
                    service = 2;
                    System.out.println("Your service is now being provided by DEF company!!");
                }
                //System.out.println("Your service is now being provided by DEF company!!");
                u=r;
            }
            if(r==1)
            {
                u=r;
            }
        }
        else if(u==3)
        {
            if(r==1)
            {
                u=r;
            }
            if(r==2)
            {
                u=r;
            }
        }

        //init(r);
        display(r);
    }

    public void display(int y)
    {
        System.out.println("Current Server State :"+y+"\n");
    }
}

public class UserDemo {
    public static void main(String args[]) throws IOException {
        BufferedReader c1 = new BufferedReader(new InputStreamReader(System.in));

        reg_user regular = new reg_user();
        prem_user premium = new prem_user();

        Company ABC = new Company();
        Company DEF = new Company();

        ABC.registerObserver(regular);
        ABC.registerObserver(premium);

        ABC.set_state(1);

        while (true)
        {
            System.out.println("Options:");
            System.out.println("1. Change state.");
            System.out.println("2. Exit.");
            System.out.println("Enter option:");

            String v=c1.readLine();
            int i = Integer.parseInt(v);

            if(i==1)
            {
                System.out.println("Enter state:");
                String w = c1.readLine();
                int j = Integer.parseInt(w);
                j++;
                ABC.notifyObservers(j);

                if (regular.service == 2) {
                    DEF.registerObserver(regular);
                    //ABC.unregisterObserver(regular);
                }
                if (premium.service == 2) {
                    DEF.registerObserver(premium);
                   // ABC.unregisterObserver(premium);
                }
                if (regular.service == 3) {
                    DEF.registerObserver(regular);
                    //ABC.unregisterObserver(regular);
                }
                if (premium.service == 3) {
                    DEF.registerObserver(premium);
                    //ABC.unregisterObserver(premium);
                }
            }

            else
            {
                exit(0);
            }

        }

    }
}
