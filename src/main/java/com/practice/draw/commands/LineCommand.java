package com.practice.draw.commands;

import com.practice.draw.args.CommandArgs;
import com.practice.draw.args.LineArgs;
import com.practice.draw.common.CoordinateGenerator;
import com.practice.draw.common.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LineCommand implements Command {
    private List<Point> coordinates;
    private LineArgs lineArgs;
    private CoordinateGenerator coordinateGenerator;

    public LineCommand(CoordinateGenerator coordinateGenerator) {
        this.coordinateGenerator = coordinateGenerator;
        coordinates =  new ArrayList<>();
    }

    @Override
    public void execute() {
        this.coordinates = this.coordinateGenerator.getCooridnatesBetweenTwoPoints(
                lineArgs.getX1(),
                lineArgs.getY1(),
                lineArgs.getX2(),
                lineArgs.getY2(),
                lineArgs.getColor()
                );
    }

    @Override
    public void setArgs(CommandArgs args) {
        this.lineArgs = (LineArgs)args;
    }

    @Override
    public List<Point> getResult() {
        return Collections.unmodifiableList(coordinates);
    }


}
