package com.practice.draw.commands;

import com.practice.draw.TestHelper.TestHelperBase;
import com.practice.draw.args.CommandArgs;
import com.practice.draw.parsers.Parser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandControllerTest extends TestHelperBase {

    @Test
    void getExecutedCommandsCalledWithoutExecutingAnything()
    {
        CommandController controller = new CommandController();
        assertEquals(0,controller.getExecutedCommands().size());
    }

    @ParameterizedTest(name = "{index}=>{0}}")
    @ValueSource(strings ={"L 1 2 6 2","R 14 1 18 3","B 10 3 o"})
    void executeCalledWithoutCanvasThrowsException(String args){
        CommandArgs parsedArgs = this.getBootStrapper().getCommandLineParser().parse(args.split(" "));
        CommandController controller =this.getBootStrapper().getCommandController();

        assertThrows( UnsupportedOperationException.class,()->controller.executeCommand(parsedArgs ));
    }

    @Test
    void executeRunsCommandAndMaintainsState(){
        Parser parser = this.getBootStrapper().getCommandLineParser();
        CommandController controller =this.getBootStrapper().getCommandController();

        CommandArgs parsedArgs = parser.parse("C 20 4".split(" "));
        controller.executeCommand(parsedArgs);
        parsedArgs = parser.parse("L 1 2 6 2".split(" "));
        controller.executeCommand(parsedArgs);
        parsedArgs = parser.parse("L 6 3 6 4".split(" "));
        controller.executeCommand(parsedArgs);
        parsedArgs = parser.parse("R 14 1 18 3".split(" "));
        controller.executeCommand(parsedArgs);
        parsedArgs = parser.parse("B 10 3 o".split(" "));
        controller.executeCommand(parsedArgs);

        assertEquals(5,controller.getExecutedCommands().size());
    }

}