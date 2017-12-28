package com.practice.draw.commands;

import com.practice.draw.TestHelper.TestHelperBase;
import com.practice.draw.args.*;
import com.practice.draw.common.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ObjectArrayArguments;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class LineCommandTest extends TestHelperBase {

    @Test
    void getResultsReturnsEmptyReadyOnlyCollectionWhenCalledWithoutExecute()
    {
        Command lineCommand = (Command) this.getContext().getBean(Constants.COMMAND_LINE);//new LineCommand(null);
        List<Point> coordinates = lineCommand.getResult();

        Assertions.assertEquals(0,coordinates.size()); // empty collection
        Assertions.assertThrows(UnsupportedOperationException.class,()->coordinates.add(new Point(1,2))); // read only collection
    }

    @ParameterizedTest(name = "{index}=>{0}=>{3}")
    @MethodSource(names = "createTestData")
    void executeGeneratesCoordinates(String[] inputArgs, List<Point> someExpectedValues, int expectedSize)
    {
        CommandArgs lineArgs = (CommandArgs)this.getContext().getBean(Constants.COMMAND_LINE_KEYWORD); //new LineArgs("");
        lineArgs.parse(inputArgs);

        Command lineCommand = (Command) this.getContext().getBean(Constants.COMMAND_LINE); //new LineCommand(new CoordinateGenerator());
        lineCommand.setArgs(lineArgs);
        lineCommand.execute();
        List<Point> coordinates = lineCommand.getResult();

        Assertions.assertEquals(expectedSize,coordinates.size());
        Assertions.assertTrue(coordinates.containsAll(someExpectedValues));
    }


    private static Stream<Arguments> createTestData() {
        return Stream.of(
                ObjectArrayArguments.create(new String[]{"L","1","2", "6", "2"},new ArrayList<Point>(){
                    {
                        add(new Point(1,2));
                        add(new Point(6,2));
                        add(new Point(4,2));
                    }
                },6,"DrawHorizontalLine"),
                ObjectArrayArguments.create(new String[]{"L","1","2", "1", "10"},new ArrayList<Point>(){
                    {
                        add(new Point(1,2));
                        add(new Point(1,5));
                        add(new Point(1,10));
                    }
                },9,"DrawVerticalLine"));
    }


}
