package com.example.labo5carry;

import java.util.ArrayList;

public abstract class Observable
{
    ArrayList<IObserver> observers =  new ArrayList<>();

    protected void addObserver(IObserver obs) {
        observers.add(obs);
    }

    protected void removeObserver(IObserver obs) {
        observers.remove(obs);
    }

    protected void notifyObservers(Object arg) {
        for (IObserver obs : observers) {
            obs.update(this, arg);
        }
    }

}
