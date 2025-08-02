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
import com.example.log121Lab05.Models.Perspective;
import com.example.log121Lab05.Models.State;

import java.awt.*;

public class Zoom implements ICommand {

    private double factor;
    private State state;
    private Point mouseCoords;
    public Zoom(State state, double factor, Point location)
    {
        this.state = state;
        this.factor = factor;
        this.mouseCoords = location;
    }


    /**
     *
     */
    @Override
    public void execute() {
        Perspective perspective = Controller.getInstance().getSelectedPerspective();
        state.zoomPerspective(perspective, factor, mouseCoords);
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

}
