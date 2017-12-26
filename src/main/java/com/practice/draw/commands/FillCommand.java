package com.practice.draw.commands;

import com.practice.draw.args.CanvasArgs;
import com.practice.draw.args.CommandArgs;
import com.practice.draw.args.FillArgs;
import com.practice.draw.common.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FillCommand implements Command, BucketFill {

    private List<Point> coordinates;
    private CanvasArgs canvasArgs;
    private FillArgs fillArgs;
    private List<Point> childShapes;
    private List<Point> filledCoordinates;

    public FillCommand() {
       this.coordinates = new ArrayList<>();
       this.childShapes = new ArrayList<>();
       this.filledCoordinates = new ArrayList<>();
    }

    @Override
    public void execute() {
        this.fill(this.fillArgs.getX(),this.fillArgs.getY(),this.fillArgs.getColor());
    }

    @Override
    public void setArgs(CommandArgs args) {
        this.fillArgs = (FillArgs) args;
    }

    @Override
    public List<Point> getResult() {
        return Collections.unmodifiableList(this.coordinates);
    }

    @Override
    public void provideCurrentState(CanvasArgs canvasArgs, List<List<Point>> childShapes, List<List<Point>> filledCoordinates) {
        this.canvasArgs = canvasArgs;

        for (List<Point> child : childShapes) {
            this.childShapes.addAll(child);
        }
        for (List<Point> point : filledCoordinates) {
            this.filledCoordinates.addAll(point);
        }
    }

    private void fill(int x, int y, String color) {
        if (x<=0 || x >= this.canvasArgs.getWidth()) return;
        if (y<=0 || y >= this.canvasArgs.getHeight()) return;

        Point p =new Point(x,y,color);
        if (this.childShapes.contains(p)){
            return;
        }

        if (filledCoordinates.contains(p)){
            Point existing = this.filledCoordinates.get(this.filledCoordinates.indexOf(p));
            if (existing.getColor().equals(color)) return;
            filledCoordinates.remove(p);//clear the old filled point
        }

        this.coordinates.add(p);
        this.filledCoordinates.add(p);
        this.fill(x-1,y,color);
        this.fill(x+1,y,color);
        this.fill(x,y-1,color);
        this.fill(x,y+1,color);
    }
}
