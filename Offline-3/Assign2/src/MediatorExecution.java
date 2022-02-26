import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

abstract class mediator{
    //abstract void sendMessage(String msg, Member user);
    abstract void addStudent(Student user);
}

class ExamController extends mediator{

    private List<Student> users = new ArrayList<Student>();
    private List<Integer> marks = new ArrayList<Integer>();
    private Examiner ex;
    int c;

    public ExamController(){
        this.c = 0;
    }

    public List get_list()
    {
        return marks;
    }

    public void set_ex(Examiner y)
    {
        ex=y;
    }

    @Override
    public void addStudent(Student user){
        this.users.add(user);
        c++;
    }

    public void set_marks()
    {
        Random rand = new Random();
        for(int i=0;i<c;i++){
            marks.add(rand.nextInt(100)+1);
        }
        System.out.println("Before Scrutinizing:");
        System.out.println("Id----->Marks");
        for(int i=0;i<c;i++){
            System.out.println((users.get(i).getRoll()+1)+"----->"+marks.get(i));
        }


        System.out.println("\nAfter Scrutinizing:");
        System.out.println("Id----->Previous Mark----->Current Mark");
        /*for (int i=0; i<c; i++) {
            int t=rand.nextInt(c);
            if(t)
        }*/
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<c; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i=0; i<c; i++) {
            int j=list.get(i);
            if(j%2!=0)
            {
                int f= marks.get(j);
                int e=0;
                e=f+5;
                if(e<=100)
                {
                    marks.set(j,e);
                    System.out.println((users.get(j).getRoll()+1)+"----->"+(marks.get(j)-5)+"----->"+marks.get(j));
                }
                else
                {
                    e=e-7;
                    marks.set(j,e);
                    System.out.println((users.get(j).getRoll()+1)+"----->"+f+"----->"+marks.get(j));
                }
                //System.out.println((users.get(j).getRoll()+1)+"----->"+marks.get(j));
            }
        }
        /*System.out.println("Student :");
        for(int i=0;i<c;i++){
            users.get(i).setMark(marks.get(i));
            System.out.print("\nId:"+(i+1));
            users.get(i).print();
        }*/
        /*Collections.shuffle(marks);
        for(int i=0;i<((c/2)+1);i++){

            System.out.println((users.get(i).getRoll()+1)+"----->"+marks.get(i));
        }*/
    }

    public void send_student() throws IOException {
        BufferedReader b2 = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Student :");
        for(int i=0;i<c;i++){
            users.get(i).setMark(marks.get(i));
            System.out.print("\nId:"+(i+1));
            users.get(i).print();
        }

        //ex.set_list(marks);
        //for(int i=0;i<c;i++){
        while (true) {
            System.out.println("\nDo you want to re-examine?? Y or N");
            String g = b2.readLine();
            if (g.equalsIgnoreCase("y")) {
                System.out.println("Enter Roll:");
                String h = b2.readLine();
                int k = Integer.parseInt(h);
                if (ex.recheck(k - 1, marks.get(k - 1)) == -1) {
                    System.out.println("No change!!");
                } else {
                    System.out.println("\nAfter Rechecking:");
                    marks.set(k - 1, ex.recheck(k - 1, marks.get(k - 1)));
                    users.get(k - 1).update(marks.get(k - 1));
                }
            }
            else
            {
                break;
            }
            //}
        }

    }

}

abstract class Member {
    protected mediator m;
    protected int roll;

    public Member(mediator med, int r){
        this.m=med;
        this.roll=r;
    }

    /*public abstract void send(String msg);

    public abstract void receive(String msg);*/
}

class Student extends Member
{
    int mark;
    public Student(mediator med, int r) {
        super(med, r);
    }

    public int getRoll()
    {
        return roll;
    }

    public void setMark(int r)
    {
        mark=r;
    }

    public void update(int r)
    {
        mark=r;
        System.out.println("Updated mark:"+mark);
    }


    public void print()
    {
        System.out.print("-------Mark:"+mark);
    }

    /*public void send(String msg){
        System.out.println(this.name+": Sending Message="+msg);
        m.sendMessage(msg, this);
    }

    public void receive(String msg) {
        System.out.println(this.name+": Received Message:"+msg);
    }*/
}

class Examiner extends Member
{
    /*protected mediator m;
    protected int roll;*/
    //private List<Integer> rec = null;
    public Examiner(mediator med, int r) {
        super(med, r);
    }
    /*public List get_child()
    {
        return child;
    }*/
    /*public void sendlist(String msg){
        System.out.println(this.name+": Sending Message="+msg);
        m.sendMessage(msg, this);
    }

    public void receivelist(String msg) {
        System.out.println(this.name+": Received Message:"+msg);
    }*/
    /*public void set_list(List w)
    {
        rec=w;
    }*/
    public int recheck(int q,int num)
    {
        //int c=0;
        //int m=0;
        //c=rec.size();
            if(q%2!=0)
            {
                int f= num;
                int e=0;
                e=f+5;
                if(e<=100 && e>=0)
                {
                    //rec.set(q,e);
                    //System.out.println((users.get(j).getRoll()+1)+"----->"+(marks.get(j)-5)+"----->"+marks.get(j));
                    return e;
                }
                else if(e>100)
                {
                    e=e-7;
                    return e;
                    //rec.set(q,e);
                    //System.out.println((users.get(j).getRoll()+1)+"----->"+f+"----->"+marks.get(j));
                }
                //System.out.println((users.get(j).getRoll()+1)+"----->"+marks.get(j));
            }
            else
            {
                return -1;
            }
        //}
        return 0;
    }
}

public class MediatorExecution {

    public static void main(String[] args) throws IOException {
        BufferedReader b1 = new BufferedReader(new InputStreamReader(System.in));

        ExamController x = new ExamController();
        Examiner y = new Examiner(x,1);
        x.set_ex(y);
        System.out.println("Enter no of Students:");
        String a = b1.readLine();
        int b = Integer.parseInt(a);
        for(int j=1;j<=b;j++)
        {
            Student s = new Student(x,j-1);
            x.addStudent(s);
        }
        x.set_marks();
        x.send_student();
        /*for(int j=1;j<=b;j++)
        {
            Student s = new Student(x,j-1);
            System.out.print("\nId:"+j);
            s.print();
        }*/


    }
}
