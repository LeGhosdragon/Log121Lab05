/*
 * @Author: Yvon Meunier, Aymerik Blais, Simon Boudreau
 * @Date:   2025-07-21 20:00:00
 * @Last Modified by: Yvon Meunier
 * @Last Modified time: 2025-08-02 20:00:00
 */

package com.example.log121Lab05.Models.Commands;

import com.example.log121Lab05.Models.State;

/**
 * ICommand is an interface which dictates what methods its children must implement to be able to behave like a command
 */
public interface ICommand {

    void execute();

    void setState(State state);
}
