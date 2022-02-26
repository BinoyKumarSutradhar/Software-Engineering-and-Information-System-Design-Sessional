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

    List<Observer> observerList = new ArrayList<Observer>();

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

        state=s;
        for (Iterator<Observer> it = observerList.iterator(); it.hasNext();)
        {
            Observer o = it.next();
            o.update(state);
        }
    }

}

interface Observer
{
    public void update(int x) throws IOException;
    public void init(int y,Company in);

}

class reg_user implements Observer
{
    int u;
    int service;
    Company x = new Company();

    @Override
    public void init(int y,Company in) {
        u=y;
        service=1;
        x=in;

    }

    public void update(int r) throws IOException {

        BufferedReader b1 = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\nRegular user prompt:");
        display(r);
        if(u==0)
        {
            if(r==1)
            {
                //System.out.println("Regular Choice:");
                System.out.println("Do you want to continue using the limited functionality or pay $20 per hour to enjoy the full functionality taking service from server of DEF company?");
                System.out.println("In the 2nd case, all your data will be copied to the server of DEF company.");
                System.out.println("Please choose:");
                System.out.println(" 1. limited functionality");
                System.out.println(" 2. pay for full functionality");
                //System.out.println("Enter choice:");
                String c = b1.readLine();
                service = Integer.parseInt(c);
                u = r;
            }
            else if(r==2)
            {
                //System.out.println("Regular Choice:");
                System.out.println("Do you want to pay $20 per hour to take service from the server of DEF company?");
                System.out.println("Note that, it will copy all your data to the server of DEF company.");
                System.out.println("Please choose:");
                System.out.println(" 1. yes, pay $20 per hour");
                System.out.println(" 2. no");
                //System.out.println(" 3. Both Server");
                //System.out.println("Enter choice:");
                String c = b1.readLine();
                int g;
                g = Integer.parseInt(c);
                //service = Integer.parseInt(c);
                if(g==1)
                {
                    service=2;
                }
                else
                {
                    service=1;
                }
                u = r;
            }
        }
        else if(u==1)
        {
            if(r==0)
            {
               if(service==2)
               {

                   System.out.println("Your total Bill: x taka");
               }
               u=r;
            }
            if(r==2)
            {
                u=r;
            }
        }
        else if(u==2)
        {
            if(r==0)
            {
                if(service==2)
                {
                    //System.out.println("Total cost: 20x.");
                    System.out.println("Your total Bill: x taka");
                }
                u=r;
            }
            if(r==1)
            {
                u=r;
            }
        }

        //init(r);
        //display(r);
    }

    public void display(int y)
    {
        //System.out.println("Regular Server State :"+y);
        if(y==0)
        {
            System.out.println("Now the server is operational again.");
        }
        else if(y==1)
        {
            System.out.println("Server is partially down now.");
        }
        else if(y==2)
        {
            System.out.println("Server is fully down now..");
        }
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

        System.out.println("\nPremium user prompt:");
        display(r);
        if(u==0)
        {
            if(r==1)
            {

                //System.out.println(" 1. Current Server");
               /* System.out.println(" 2. New Server");
                System.out.println(" 3. Both Server");*/
                System.out.println("Do you want to use service from two servers (partially from the server of our company and partially from the server of DEF company) or from one server (server of DEF company)?");
                System.out.println("Please choose:");
                System.out.println(" 1. One Server");
                System.out.println(" 2. Two Servers");
                //System.out.println("Enter choice:");
                String c = b1.readLine();
                int k;
                k = Integer.parseInt(c);
                service=k+1;
                u = r;
            }
            else if(r==2)
            {
                service = 2;
                System.out.println("Your service is now being provided by DEF company!!");
                u = r;
            }
        }
        else if(u==1)
        {
            if(r==2)
            {
                if(service==3)
                {
                    service = 2;
                    System.out.println("All of your services has been shifted to the server of DEF company!!");
                }
                //System.out.println("Your service is now being provided by DEF company!!");
                u=r;
            }
            if(r==0)
            {
                u=r;
            }
        }
        else if(u==2)
        {
            if(r==0)
            {
                u=r;
            }
            if(r==1)
            {
                u=r;
            }
        }

        //init(r);
        //display(r);
    }

    public void display(int y)
    {
        //System.out.println("Current Server State :"+y+"\n");
        if(y==0)
        {
            System.out.println("Now the server is operational again.");
        }
        else if(y==1)
        {
            System.out.println("Server is partially down now.");
        }
        else if(y==2)
        {
            System.out.println("Server is fully down now..");
        }
    }
}

public class UserDemo {
    public static void main(String args[]) throws IOException {
        BufferedReader c1 = new BufferedReader(new InputStreamReader(System.in));

        reg_user regular = new reg_user();
        prem_user premium = new prem_user();

        Company ABC = new Company();
        Company DEF = new Company();


        ABC.registerObserver(premium);
        ABC.registerObserver(regular);

        ABC.set_state(0);           //*********

        //*******Server state =0 ----Operational
        //*******Server state =1 ----Partially down
        //*******Server state =2 ----Fully down



        while (true)
        {
            /*System.out.println("Options:");
            System.out.println("1. Change state.");
            System.out.println("2. Exit.");
            System.out.println("Enter option:");*/

            //String v=c1.readLine();
            //int i = Integer.parseInt(v);

            //if(i==1)
            //{
                //System.out.println("Enter state:");
                String w = c1.readLine();
                int j = Integer.parseInt(w);
                //j++;       //***************critical change
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
            //}

            /*else
            {
                exit(0);
            }*/

        }

    }
}
