package com.practice.draw.commands;

import com.practice.draw.TestHelper.TestHelperBase;
import com.practice.draw.args.CanvasArgs;
import com.practice.draw.args.CommandArgs;
import com.practice.draw.args.FillArgs;
import com.practice.draw.common.Constants;
import com.practice.draw.common.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ObjectArrayArguments;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


class FillCommandTest extends TestHelperBase {
    @Test
    void getResultsReturnsEmptyReadyOnlyCollectionWhenCalledWithoutExecute()
    {
        Command fillCommand = (Command) this.getContext().getBean(Constants.COMMAND_FILL); //new FillCommand();
        List<Point> coordinates = fillCommand.getResult();

        Assertions.assertEquals(0,coordinates.size());
        Assertions.assertThrows(UnsupportedOperationException.class,()->coordinates.add(new Point(1,2)));
    }

    @ParameterizedTest(name = "{index}=>{0}=>{5}")
    @MethodSource(names = "createTestData")
    void executeFillsTheCanvas(String[] testArgsForCanvas, String[] testArgsForFill, List<List<Point>> childShapes, List<List<Point>> filledCoordinates, int expectedCount){
        Command fillCommand = (Command) this.getContext().getBean(Constants.COMMAND_FILL);;//new FillCommand();
        CanvasArgs canvasArgs = (CanvasArgs) executeCommandParser(testArgsForCanvas);
        FillArgs fillArgs = (FillArgs) executeCommandParser(testArgsForFill);

        fillCommand.setArgs(fillArgs);
        ((BucketFill)fillCommand).provideCurrentState(canvasArgs,childShapes,filledCoordinates);

        fillCommand.execute();

        List<Point> result = fillCommand.getResult();
        Assertions.assertEquals(expectedCount,result.size());
    }

    @Test
    void executeOverwritesTheColorIfCalledAgainWithNewColor(){
        String[] testArgsForCanvas = new String[]{"C","2","2"};
        String[] firstFillArgs = new String[]{"B","1","1","o"};
        String[] secondFillArgs = new String[]{"B","1","1","x"};
        Command fillCommand = getFillCommand(testArgsForCanvas, firstFillArgs, new ArrayList<>());

        fillCommand.execute();
        List<Point> result = fillCommand.getResult();

        Assertions.assertEquals(4,result.size());
        for (Point point: result) {
            Assertions.assertEquals("o",point.getColor());
        }

        //now change the color
        List<Point> finalResult = result;
        fillCommand = getFillCommand(testArgsForCanvas, secondFillArgs,new ArrayList<List<Point>>(){{add(finalResult);}});
        fillCommand.execute();
        result = fillCommand.getResult();

        Assertions.assertEquals(4,result.size());
        for (Point point: result) {
            Assertions.assertEquals("x",point.getColor());
        }


    }

    private Command getFillCommand(String[] testArgsForCanvas, String[] firstFillArgs, List<List<Point>> existingFill) {
        //setup a canvas boundary
        List<Point> canvasBoundry = new ArrayList<Point>()
        {{
            add(new Point(0,0)); add(new Point(1,0));add(new Point(2,0));add(new Point(3,0));
            add(new Point(0,1)); add(new Point(3,1));
            add(new Point(0,2)); add(new Point(3,2));
            add(new Point(0,3)); add(new Point(1,3));add(new Point(2,3));add(new Point(3,3));
        }};


        CanvasArgs canvasArgs = (CanvasArgs) executeCommandParser(testArgsForCanvas);
        FillArgs fillArgs = (FillArgs) executeCommandParser(firstFillArgs);

        //execute the command with first fill color
        Command fillCommand = (Command) this.getContext().getBean(Constants.COMMAND_FILL);
        fillCommand.setArgs(fillArgs);
        ((BucketFill)fillCommand).provideCurrentState(canvasArgs,new ArrayList<List<Point>>(){{add(canvasBoundry);}},existingFill);
        return fillCommand;
    }

    private static Stream<Arguments> createTestData() {
        List<Point> rectangle = new ArrayList<Point>()
        {{
            add(new Point(0,0)); add(new Point(1,0));add(new Point(2,0));
            add(new Point(0,1)); add(new Point(2,1));
            add(new Point(0,2)); add(new Point(1,2));add(new Point(2,2));
        }};

        List<Point> line = new ArrayList<Point>()
        {{
            add(new Point(0,3)); add(new Point(1,3));add(new Point(2,3));add(new Point(3,3));add(new Point(4,3));
        }};

        return Stream.of(
                ObjectArrayArguments.create(new String[]{"C","3","3"},new String[] {"B","2","2","o"},new ArrayList<>(),new ArrayList<>(),9,"FillEntireAreaWhenCanvasIsEmpty"),
                ObjectArrayArguments.create(new String[]{"C","3","3"},new String[] {"B","4","3","o"},new ArrayList<>(),new ArrayList<>(),0,"FillPointFallsOnCanvasObject(BorderPoint)"),
                ObjectArrayArguments.create(new String[]{"C","4","3"},new String[] {"B","1","1","%"},new ArrayList<List<Point>>(){{add(rectangle);}},new ArrayList<>(),1,"FillRectangleInsideCanvas"),
                ObjectArrayArguments.create(new String[]{"C","4","5"},new String[] {"B","1","4","%"},new ArrayList<List<Point>>(){{add(rectangle);add(line);}},new ArrayList<>(),8,"TwoShapesInsideCanvas")
        );

    }
}