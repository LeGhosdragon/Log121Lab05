/*
 * @Author: mikey.zhaopeng
 * @Email:  admin@example.com
 * @Date:   2016-07-29 15:57:29
 * @Last Modified by: Noscere
 * @Last Modified time: 2022-11-15 06:25:53.546
 * @Description: description
 */
package com.example.log121Lab05.Models.Commands;

import com.example.log121Lab05.Controller;
import com.example.log121Lab05.Models.State;

public class Translate implements ICommand {
    private double deltaX;
    private double deltaY;
    private State state;

    public Translate(State state, double deltaX, double deltaY) {
        this.state = state;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    @Override
    public void execute() {
        Controller controller = Controller.getInstance();
        state.translatePerspective(controller.getSelectedPerspective(), deltaX, deltaY);
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }
}
