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


public class CanvasArgsTest extends TestHelperBase {

    @Test
    void parseWhenCalledWithValidArgsInitializesTheValues(){
        String testArgs[] = new String[]{"C","20","4"};
        CanvasArgs canvasArgs = (CanvasArgs) executeCommandParser(testArgs);
        Assertions.assertEquals(20+1,canvasArgs.getWidth());
        Assertions.assertEquals(4+1,canvasArgs.getHeight());
    }

    @ParameterizedTest(name = "{index}=>{1}")
    @MethodSource(names = "createInvalidParams")
    void parseWhenCalledWithInvalidParamsThrowsException(String[] testArgs){
        Assertions.assertThrows(InvalidParameterException.class, ()-> executeCommandParser(testArgs));
    }

    private static Stream<Arguments> createInvalidParams() {
        return Stream.of(
                ObjectArrayArguments.create(new String[]{"C","20","xx"},"InvalidValuesAtEnd"),//Invalid value at the end of array
                ObjectArrayArguments.create(new String[]{"C","xx","2"},"InvalidValuesAtStart"), //Invalid value at beginning of array
                ObjectArrayArguments.create(new String[]{"C","",""},"AllEmptyParams"), //Length okay but values empty
                ObjectArrayArguments.create(new String[]{"C",""},"InvalidAndInsufficientParams"), //Invalid command
                ObjectArrayArguments.create(new String[]{"C","20","4","6","2"},"LengthGreaterThanExpected"),//Valid values but length does not match
                ObjectArrayArguments.create(new String[]{"C"},"NoParametersProvided") //No parameters passed
        );
    }

}
