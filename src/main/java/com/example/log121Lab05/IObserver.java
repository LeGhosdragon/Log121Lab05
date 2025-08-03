/*
 * @Author: Yvon Meunier, Aymerik Blais, Simon Boudreau
 * @Date:   2025-07-21 20:00:00
 * @Last Modified by: Yvon Meunier
 * @Last Modified time: 2025-08-02 20:00:00
 */
package com.example.log121Lab05;

/**
 * IObserver is an interface that dictates what methods its child should implement to be able to receive notifications from other objects
 */
public interface IObserver
{
    /**
     * @param obs
     * @param arg
     */
    void update(Observable obs, Object arg);
}
