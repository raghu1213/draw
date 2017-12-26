package com.practice.draw.commands;

import com.practice.draw.args.CommandArgs;
import com.practice.draw.common.Point;

import java.util.List;

public class QuitCommand implements Command {
    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public void setArgs(CommandArgs args) {

    }

    @Override
    public List<Point> getResult() {
        return null;
    }
}
