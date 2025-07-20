package org.example.log121lab05;


public class FXMLHandler
{
    private static FXMLHandler h = null;

    private FXMLHandler()
    {
        h = this;
    }

    public static synchronized FXMLHandler getInstance()
    {
        if (h == null)
            h = new FXMLHandler();
        return h;
    }

    public void update(IObservable obs)
    {
        if(obs.getClass() == Perspective.class)
        {
            update((Perspective)obs);
        }
        if(obs.getClass() == Image.class)
        {
            update((Image)obs);
        }
    }
    public void update(Perspective obs)
    {
        //Perspective specific update
    }
    public void update(Image obs)
    {
        //Image specific update
    }
}
