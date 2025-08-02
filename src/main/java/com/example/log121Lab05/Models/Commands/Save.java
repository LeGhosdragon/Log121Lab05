/*
 * @Author: mikey.zhaopeng
 * @Email:  admin@example.com
 * @Date:   2016-07-29 15:57:29
 * @Last Modified by: Noscere
 * @Last Modified time: 2022-11-15 06:25:53.546
 * @Description: description
 */
package com.example.log121Lab05.Models.Commands;

import com.example.log121Lab05.Models.Services.FileService;
import com.example.log121Lab05.Models.State;

public class Save implements ICommand {

    private State state;

    public Save(State s) {
        this.state = s;
    }

    @Override
    public void execute() {
        FileService.writeSave(state);
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }
}
