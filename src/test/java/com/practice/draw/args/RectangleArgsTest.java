package com.practice.draw.args;


import com.practice.draw.TestHelper.TestHelperBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ObjectArrayArguments;

import java.security.InvalidParameterException;
import java.util.stream.Stream;

public class RectangleArgsTest extends TestHelperBase {

    @Test
    void parseWhenCalledWithValidArgsInitializesTheValues(){
        String testArgs[] = new String[]{"R","14","1","18","3"};
        RectangleArgs canvasArgs = (RectangleArgs) executeCommandParser(testArgs);
        Assertions.assertEquals(14,canvasArgs.getX1());
        Assertions.assertEquals(1,canvasArgs.getY1());
        Assertions.assertEquals(18,canvasArgs.getX2());
        Assertions.assertEquals(3,canvasArgs.getY2());
    }

    @ParameterizedTest(name = "{index}=>{1}")
    @MethodSource(names = "createInvalidParams")
    void parseWhenCalledWithInvalidParamsThrowsException(String[] testArgs){
        Assertions.assertThrows(InvalidParameterException.class, ()-> executeCommandParser(testArgs));
    }

    private static Stream<Arguments> createInvalidParams() {
        return Stream.of(
                ObjectArrayArguments.create(new String[]{"R","14","1","18","xxxx"},"InvalidValuesAtEnd"),//Invalid value at the end of array
                ObjectArrayArguments.create(new String[]{"R","xxxx","1","18","3"},"InvalidValuesAtStart"), //Invalid value at beginning of array
                ObjectArrayArguments.create(new String[]{"R","14","1","-18","3"},"NegativeValues"), //Negative values passed
                ObjectArrayArguments.create(new String[]{"R","","","",""},"AllEmptyParams"), //Length okay but values empty
                ObjectArrayArguments.create(new String[]{"R",""},"InvalidAndInsufficientParams"), //Invalid command
                ObjectArrayArguments.create(new String[]{"R","14","1","18","3","10"},"LengthGreaterThanExpected"),//Valid values but length does not match
                ObjectArrayArguments.create(new String[]{"R"},"NoParametersProvided") //No parameters passed
        );
    }

}
