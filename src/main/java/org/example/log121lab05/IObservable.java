package org.example.log121lab05;

import java.util.ArrayList;

public interface IObservable
{
    ArrayList<Observator> Observators =  new ArrayList<>();
    void addObservator(Observator obs);
    void removeObservator(Observator obs);
    void notifyObservators();
}
