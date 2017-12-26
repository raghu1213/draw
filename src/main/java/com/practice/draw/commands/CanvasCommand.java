package com.practice.draw.commands;

import com.practice.draw.args.CanvasArgs;
import com.practice.draw.args.CommandArgs;
import com.practice.draw.common.CoordinateGenerator;
import com.practice.draw.common.Point;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public  class CanvasCommand implements Command,Canvas {


    private CanvasArgs canvasArgs;
    private List<Point> coordinates;
    private CoordinateGenerator coordinateGenerator;

    public CanvasCommand(CoordinateGenerator coordinateGenerator) {
        this.coordinateGenerator = coordinateGenerator;
        coordinates = new ArrayList<>();
    }

    @Override
    public void execute() {
        Point topLeft = new Point(0, 0);
        Point topRight = new Point(topLeft.getX() + this.canvasArgs.getWidth(), topLeft.getY());
        Point bottomLeft = new Point(topLeft.getX(), topLeft.getY() + this.canvasArgs.getHeight());
        Point bottomRight = new Point(bottomLeft.getX() + this.canvasArgs.getWidth(), bottomLeft.getY());

        this.coordinates = this.coordinateGenerator.getRectangle(
                topLeft,
                topRight,
                bottomLeft,
                bottomRight,
                this.canvasArgs.getHorizontalLineColor(),
                this.canvasArgs.getVerticalLineColor()
        );
    }

    @Override
    public void setArgs(CommandArgs args) {
        this.canvasArgs = (CanvasArgs) args;
    }


    @Override
    public List<Point> getResult() {
        return Collections.unmodifiableList(coordinates);
    }

    @Override
    public CommandArgs getArgs() {
        return this.canvasArgs;
    }
}
