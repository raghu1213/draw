package com.practice.draw.args;

import com.practice.draw.TestHelper.TestHelperBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ObjectArrayArguments;
import org.junit.jupiter.params.provider.ValueSource;

import java.security.InvalidParameterException;
import java.util.stream.Stream;

public class FillArgsTest extends TestHelperBase {

    @Test
    void parseWhenCalledWithValidArgsInitializesTheValues(){
        String testArgs[] = new String[]{"B","10","3","o"};
        FillArgs canvasArgs = (FillArgs) executeCommandParser(testArgs);
        Assertions.assertEquals(10,canvasArgs.getX());
        Assertions.assertEquals(3,canvasArgs.getY());
        Assertions.assertEquals("o",canvasArgs.getColor());
    }

    @ParameterizedTest(name = "color char is \"{0}\"")
    @ValueSource(strings= {"a","x","","-",".","6","z","%"})
    void ColorArgumentCanTakeAnyValue(String color){
        String testArgs[] = new String[]{"B","10","3",color};
        FillArgs canvasArgs = (FillArgs) executeCommandParser(testArgs);

        Assertions.assertEquals(color,canvasArgs.getColor());
    }

    @ParameterizedTest(name = "{index}=>{1}")
    @MethodSource(names = "createInvalidParams")
    void parseWhenCalledWithInvalidParamsThrowsException(String[] testArgs){
        Assertions.assertThrows(InvalidParameterException.class, ()-> executeCommandParser(testArgs));
    }

    private static Stream<Arguments> createInvalidParams() {
        return Stream.of(
                ObjectArrayArguments.create(new String[]{"B","10","a","o"},"InvalidValueForY"),//Invalid value at the end of array
                ObjectArrayArguments.create(new String[]{"B","xx","2"},"InvalidValueForX"), //Invalid value at beginning of array
                ObjectArrayArguments.create(new String[]{"B","","",""},"AllEmptyParams"), //Length okay but values empty
                ObjectArrayArguments.create(new String[]{"B",""},"InvalidAndInsufficientParams"), //Invalid command
                ObjectArrayArguments.create(new String[]{"B","10","3","x","2"},"LengthGreaterThanExpected"),//Valid values but length does not match
                ObjectArrayArguments.create(new String[]{"B","10","3"},"ColorNotProvided"),//Color is not provided
                ObjectArrayArguments.create(new String[]{"B"},"NoParametersProvided") //No parameters passed
        );
    }
}
