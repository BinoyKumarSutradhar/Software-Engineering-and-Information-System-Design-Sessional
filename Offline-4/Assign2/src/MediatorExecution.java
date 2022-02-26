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

    //public void receive()

    public List get_list()
    {
        return marks;
    }

    public void set_ex(Examiner y,int x)
    {
        ex=y;
        ex.send(x);
        System.out.print("\nExam controller prompt:");
        System.out.print(" Scripts and marks of student id 1");
        for(int k=2;k<=x;k++)
        {
            System.out.print(","+k);
        }
        System.out.print(" have been received.\n");
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
        //System.out.println("Before Scrutinizing:");
        //System.out.println("Id----->Marks");
        for(int i=0;i<c;i++){
            System.out.println("Student id: "+(users.get(i).getRoll()+1)+" , Marks:"+marks.get(i));
        }


        System.out.println("\nScrutinization has been done.");
        //System.out.println("Id----->Previous Mark----->Current Mark");
        /*for (int i=0; i<c; i++) {
            int t=rand.nextInt(c);
            if(t)
        }*/
       /* ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<c; i++) {
            list.add(i);
        }
        Collections.shuffle(list);*/
        Random rand1 = new Random();
        for (int i=0; i<c; i++) {

            int j=rand.nextInt(1000);
            if(j%2!=0)
            {
                int f= marks.get(i);
                int e=0;
                e=f+5;
                if(e<=100)
                {
                    marks.set(i,e);
                    System.out.println("Student id: "+(users.get(i).getRoll()+1)+", Previous marks: "+(marks.get(i)-5)+", Corrected marks: "+marks.get(i));
                }
                else
                {
                    e=e-7;
                    marks.set(i,e);
                    System.out.println("Student id: "+(users.get(i).getRoll()+1)+", Previous marks: "+f+", Corrected marks: "+marks.get(i));
                }
                //System.out.println((users.get(j).getRoll()+1)+"----->"+marks.get(j));
            }
        }
        System.out.println("\nResult has been published to the students.");

    }

    public void send_student() throws IOException {

        BufferedReader b2 = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<Integer>();
        List<String> l1 = new ArrayList<String>();
        List<String> l2 = new ArrayList<String>();
        List<String> l3 = new ArrayList<String>();
        //***********
        System.out.print("\n");
        for(int i=0;i<c;i++){
            System.out.print("Student "+(i+1)+" prompt :");
            System.out.print(" Result published.");
            users.get(i).setMark(marks.get(i));
            System.out.print(" Got marks ");
            users.get(i).print();
            System.out.println("\nDo you want to apply for re-examine??");
            System.out.println("1. yes");
            System.out.println("2. no");
            String g = b2.readLine();
            int t = Integer.parseInt(g);
            if(t==1)
            {
                list.add(i);
            }
        }


        System.out.println("Re-examine request sent.\n");
        System.out.println("Exam controller prompt: Re-examine request got from student id 5");
        System.out.println("Exam script of student id 5 sent to the examiner");
        System.out.println("Examiner prompt:");
        System.out.print("Exam script of student id 5 received for re-examine.\n");
        for (int num : list)
        {
            int n=marks.get(num);
            int o=ex.recheck(num, marks.get(num));
            if (o == -1) {
                System.out.println("Marks of student id "+(num+1)+" unchanged.");
                l1.add("Marks of student id "+(num+1)+" unchanged.");
                l2.add("Student id "+(num+1)+" has been informed about no change in marks.");
                l3.add("Student "+(num+1)+" prompt:Marks unchanged.");
            } else {
                System.out.print("Marks of student id "+(num+1)+" changed.");
                marks.set(num, o);
                System.out.println("Previous marks were "+n+" now the corrected marks are "+marks.get(num));
                l1.add("Marks of student id "+(num+1)+" updated from "+n+" to "+marks.get(num));
                l2.add(" Updated marks have been sent to student id "+(num+1));
                l3.add("Student "+(num+1)+" prompt:Marks were previously "+n+". Now the corrected marks are "+marks.get(num));
                users.get(num).update(marks.get(num));
            }
        }

        System.out.println("Exam controller prompt:");
        for(String z:l1)
        {
            System.out.print(z);
            System.out.print("\n");
        }
        for(String b:l2)
        {
            System.out.print(b);
            System.out.print("\n");
        }

        for(String v:l3)
        {
            System.out.println(v);
            System.out.print("\n");

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
        System.out.print(mark);
    }

}

class Examiner extends Member
{
    /*protected mediator m;
    protected int roll;*/
    //private List<Integer> rec = null;
    public Examiner(mediator med, int r)
    {
        super(med, r);
    }

    public void send(int x)
    {
        System.out.print("Examiner prompt:");
        System.out.print(" Scripts and marks of student id 1");
        for(int k=2;k<=x;k++)
        {
            System.out.print(","+k);
        }
        System.out.print(" have been sent to exam controller office.\n");
    }

    public int recheck(int q,int num)
    {
        //int c=0;
        //int m=0;
        //c=rec.size();
        Random rand2=new Random();
        int j=rand2.nextInt(1000);
            if(j%2!=0)
            {
                int f= num;
                int e=0;
                e=f+5;
                if(e<=100 && e>=0)
                {

                    return e;
                }
                else if(e>100)
                {
                    e=e-7;
                    return e;

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
        x.set_ex(y,5);
        //y.send(5);
        /*System.out.println("Enter no of Students:");
        String a = b1.readLine();
        int b = Integer.parseInt(a);*/
        for(int j=1;j<=5;j++)
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
