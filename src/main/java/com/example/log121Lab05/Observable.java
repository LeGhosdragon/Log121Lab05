/*
 * @Author: Yvon Meunier, Aymerik Blais, Simon Boudreau
 * @Date:   2025-07-21 20:00:00
 * @Last Modified by: Yvon Meunier
 * @Last Modified time: 2025-08-02 20:00:00
 */
package com.example.log121Lab05;

import java.util.ArrayList;

/**
 * Observable is an abstract class that dictates what properties and methods its children should have to be able to notify the Observers
 */
public abstract class Observable
{
    private ArrayList<IObserver> observers =  new ArrayList<>();

    /**
     * Adds an Observer to the list
     * @param obs
     */
    protected void addObserver(IObserver obs) {
        observers.add(obs);
    }

    /**
     * Removes an Observer from the list
     * @param obs
     */
    protected void removeObserver(IObserver obs) {
        observers.remove(obs);
    }

    /**
     * Notifies all the Observers that the object changed
     * @param arg the changed parameter
     */
    protected void notifyObservers(Object arg) {
        for (IObserver obs : observers) {
            obs.update(this, arg);
        }
    }

}
