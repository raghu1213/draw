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


public class LineArgsTest  extends TestHelperBase {

    @Test
    void parseWhenCalledWithValidArgsInitializesTheValues(){
        String testArgs[] = new String[]{"L","1","2","6","2"};
        LineArgs lineArgs = (LineArgs) executeCommandParser(testArgs);
        Assertions.assertEquals(1,lineArgs.getX1());
        Assertions.assertEquals(2,lineArgs.getY1());
        Assertions.assertEquals(6,lineArgs.getX2());
        Assertions.assertEquals(2,lineArgs.getY2());
    }

    @ParameterizedTest(name = "{index}=>{1}")
    @MethodSource(names = "createInvalidParams")
    void parseWhenCalledWithInvalidParamsThrowsException(String[] testArgs){
        Assertions.assertThrows(InvalidParameterException.class, ()-> executeCommandParser(testArgs));
    }

    private static Stream<Arguments> createInvalidParams() {
        return Stream.of(
                ObjectArrayArguments.create(new String[]{"L","1","2","6","a"},"InvalidValuesAtEnd"),//Invalid value at the end of array
                ObjectArrayArguments.create(new String[]{"L","xx","2","6","2"},"InvalidValuesAtStart"), //Invalid value at beginning of array
                ObjectArrayArguments.create(new String[]{"L","","","",""},"AllEmptyParams"), //Length okay but values empty
                ObjectArrayArguments.create(new String[]{"L",""},"InvalidAndInsufficientParams"), //Invalid command
                ObjectArrayArguments.create(new String[]{"L","1","2","6","2","7","8"},"LengthGreaterThanExpected"),//Valid values but length does not match
                ObjectArrayArguments.create(new String[]{"L"},"NoParametersProvided") //No parameters passed
        );
    }


}
