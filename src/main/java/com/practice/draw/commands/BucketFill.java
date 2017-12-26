package com.practice.draw.commands;

import com.practice.draw.args.CanvasArgs;
import com.practice.draw.common.Point;

import java.util.List;

public interface BucketFill {
   void provideCurrentState(CanvasArgs canvasArgs, List<List<Point>> childShapes, List<List<Point>> filledCoordinates);
}
