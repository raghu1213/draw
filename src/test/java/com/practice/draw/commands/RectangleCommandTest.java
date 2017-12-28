package com.practice.draw.commands;

import com.practice.draw.TestHelper.TestHelperBase;
import com.practice.draw.args.CommandArgs;
import com.practice.draw.args.RectangleArgs;
import com.practice.draw.common.Constants;
import com.practice.draw.common.CoordinateGenerator;
import com.practice.draw.common.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class RectangleCommandTest extends TestHelperBase {
    @Test
    void getResultsReturnsEmptyReadyOnlyCollectionWhenCalledWithoutExecute()
    {
        Command rectangleCommand = (Command) this.getContext().getBean(Constants.COMMAND_RECTANGLE);// new RectangleCommand(new CoordinateGenerator());
        List<Point> coordinates = rectangleCommand.getResult();

        Assertions.assertEquals(0,coordinates.size());
        Assertions.assertThrows(UnsupportedOperationException.class,()->coordinates.add(new Point(1,2)));
    }

    @Test
        //void GeneratesValidCoordinatesForHorizontalLine(String[] inputArgs, List<Point> someExpectedValues, int expectedSize)
    void executeGeneratesCanvasCoordinates()
    {
        String[] testArgs = new String[]{"R","0","0","3","2"};
        CommandArgs rectangleArgs = (CommandArgs) this.getContext().getBean(Constants.COMMAND_RECTANGLE_KEYWORD); //new RectangleArgs("-","|");
        rectangleArgs.parse(testArgs);

        List<Point> expectedCoors = new ArrayList<Point>()
        {{
            add(new Point(0,0)); add(new Point(1,0));add(new Point(2,0));add(new Point(3,0));
            add(new Point(0,1)); add(new Point(3,1));
            add(new Point(0,2)); add(new Point(1,2));add(new Point(2,2));add(new Point(3,2));
        }};

        Command canvasCommand = (Command) this.getContext().getBean(Constants.COMMAND_RECTANGLE); //new RectangleCommand(new CoordinateGenerator());
        canvasCommand.setArgs(rectangleArgs);
        canvasCommand.execute();
        List<Point> coordinates = canvasCommand.getResult();

        Assertions.assertEquals(10,coordinates.size());
        Assertions.assertTrue(coordinates.containsAll(expectedCoors));
    }

}