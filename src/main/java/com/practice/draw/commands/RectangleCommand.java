package com.practice.draw.commands;

import com.practice.draw.args.CommandArgs;
import com.practice.draw.args.RectangleArgs;
import com.practice.draw.common.CoordinateGenerator;
import com.practice.draw.common.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RectangleCommand implements Command {

    private RectangleArgs rectangleArgs;
    private List<Point> coordinates;
    private CoordinateGenerator coordinateGenerator;

    public RectangleCommand(CoordinateGenerator coordinateGenerator) {
        this.coordinateGenerator = coordinateGenerator;
        coordinates = new ArrayList<>();
    }

    @Override
    public void execute() {
        Point topLeft = new Point(this.rectangleArgs.getX1(), this.rectangleArgs.getY1());
        Point bottomRight = new Point(this.rectangleArgs.getX2(), this.rectangleArgs.getY2());
        int width = this.rectangleArgs.getX2() - this.rectangleArgs.getX1();
        int height = this.rectangleArgs.getY2() - this.rectangleArgs.getY1();
        Point topRight = new Point(topLeft.getX()+ width, topLeft.getY() );
        Point bottomLeft = new Point(topLeft.getX(),topLeft.getY()+height);


        this.coordinates = this.coordinateGenerator.getRectangle(
                topLeft,
                topRight,
                bottomLeft,
                bottomRight,
                this.rectangleArgs.getHorizontalLineColor(),
                this.rectangleArgs.getVerticalLineColor()
        );
    }

    @Override
    public void setArgs(CommandArgs args) {
        this.rectangleArgs = (RectangleArgs) args;
    }

    @Override
    public List<Point> getResult() {
        return  Collections.unmodifiableList(this.coordinates);
    }
}
