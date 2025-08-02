/*
 * @Author: mikey.zhaopeng
 * @Email:  admin@example.com
 * @Date:   2016-07-29 15:57:29
 * @Last Modified by: Noscere
 * @Last Modified time: 2022-11-15 06:25:53.546
 * @Description: description
 */
package com.example.log121Lab05.Models;

public class Memento {

    private State state;

    public Memento(State state) {
        this.state = new State(state);
    }

    public State getState() {
        return state;
    }

}
