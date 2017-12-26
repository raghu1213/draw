package com.practice.draw.commands;

import com.practice.draw.args.CommandArgs;
import com.practice.draw.common.Point;

import java.util.List;

public interface Command extends ResultProvider {
    void execute();
    void setArgs(CommandArgs args);

}
