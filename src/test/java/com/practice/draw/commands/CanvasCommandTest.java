package com.practice.draw.commands;

import com.practice.draw.TestHelper.TestHelperBase;
import com.practice.draw.args.CanvasArgs;
import com.practice.draw.args.CommandArgs;
import com.practice.draw.common.Constants;
import com.practice.draw.common.CoordinateGenerator;
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

public class CanvasCommandTest extends TestHelperBase {

    @Test
    void getResultsReturnsEmptyReadyOnlyCollectionWhenCalledWithoutExecute()
    {
        Command canvasCommand = (Command) this.getContext().getBean(Constants.COMMAND_CANVAS_TEST);
        List<Point> coordinates = canvasCommand.getResult();

        Assertions.assertEquals(0,coordinates.size());
        Assertions.assertThrows(UnsupportedOperationException.class,()->coordinates.add(new Point(1,2)));
    }

   @Test
   void executeGeneratesCanvasCoordinates()
    {
        String[] testArgs = new String[]{"C","2","2"};
        CommandArgs canvasArgs = (CommandArgs) this.getContext().getBean(Constants.COMMAND_CANVAS_KEYWORD); // new CanvasArgs("-","|");
        canvasArgs.parse(testArgs);

        List<Point> expectedCoors = new ArrayList<Point>()
        {{
                add(new Point(0,0)); add(new Point(1,0));add(new Point(2,0));add(new Point(3,0));
                add(new Point(0,1)); add(new Point(3,1));
                add(new Point(0,2)); add(new Point(3,2));
                add(new Point(0,3)); add(new Point(1,3));add(new Point(2,3));add(new Point(3,3));
        }};

        Command canvasCommand = (Command) this.getContext().getBean(Constants.COMMAND_CANVAS); //new CanvasCommand(new CoordinateGenerator());
        canvasCommand.setArgs(canvasArgs);
        canvasCommand.execute();
        List<Point> coordinates = canvasCommand.getResult();

        Assertions.assertEquals( 12,coordinates.size());
        Assertions.assertTrue(coordinates.containsAll(expectedCoors));
    }


}
