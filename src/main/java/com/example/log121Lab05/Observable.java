/*
 * @Author: mikey.zhaopeng
 * @Email:  admin@example.com
 * @Date:   2016-07-29 15:57:29
 * @Last Modified by: Noscere
 * @Last Modified time: 2022-11-15 06:25:53.546
 * @Description: description
 */
package com.example.log121Lab05;

import java.util.ArrayList;

public abstract class Observable
{
    private ArrayList<IObserver> observers =  new ArrayList<>();

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
