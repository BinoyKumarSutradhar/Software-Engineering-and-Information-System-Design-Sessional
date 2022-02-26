
abstract class AbstractFactory {
    public abstract computer getcomputer(String c);
    public abstract shape getshape(String s);

}

class computerfactory extends AbstractFactory{

    public computer getcomputer(String c)
    {
        if(c==null)
        {
            return null;
        }
        if(c.equalsIgnoreCase("COMPUTERA"))
        {
            return new computerA();
        }
        else if(c.equalsIgnoreCase("COMPUTERB"))
        {
            return new computerB();
        }
        else if(c.equalsIgnoreCase("COMPUTERC"))
        {
            return new computerC();
        }
        return null;
    }

    public shape getshape(String s)
    {
        return null;
    }

}

class shapefactory extends AbstractFactory{

    public shape getshape(String s)
    {
        if(s==null)
        {
            return null;
        }
        if(s.equalsIgnoreCase("CIRCLE"))
        {
            return new circle();
        }
        else if(s.equalsIgnoreCase("SQUARE"))
        {
            return new square();
        }
        else if(s.equalsIgnoreCase("RECTANGLE"))
        {
            return new rectangle();
        }
        else if(s.equalsIgnoreCase("TRIANGLE"))
        {
            return new triangle();
        }
        return null;
    }

    public computer getcomputer(String c)
    {
        return null;
    }

}

class FactoryCreator{
    public static AbstractFactory getfactory(String a)
    {
        if(a.equalsIgnoreCase("SHAPE"))
        {
            return new shapefactory();
        }
        else if(a.equalsIgnoreCase("COMPUTER"))
        {
            return new computerfactory();
        }
        return null;
    }

}