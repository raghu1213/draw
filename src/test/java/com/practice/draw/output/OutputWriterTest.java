package com.practice.draw.output;

import com.practice.draw.TestHelper.TestHelperBase;
import com.practice.draw.args.CommandArgs;
import com.practice.draw.commands.CommandController;
import com.practice.draw.parsers.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OutputWriterTest extends TestHelperBase {
    @Test
    void printPrintsTheShapesUsingGivenPrinter() {
        StringPrinter printer = new StringPrinter();
        OutputWriter outputWriter = new OutputWriter(printer);
        Parser parser = this.getBootStrapper().getCommandLineParser();
        CommandController controller = this.getBootStrapper().getCommandController();

        CommandArgs parsedArgs = parser.parse("C 20 4".split(" "));
        controller.executeCommand(parsedArgs);
        outputWriter.print(controller.getExecutedCommands());
        String result = printer.getOutput();
        String expected = "----------------------" +System.lineSeparator()+
                "|                    |" +System.lineSeparator()+
                "|                    |" +System.lineSeparator()+
                "|                    |" +System.lineSeparator()+
                "|                    |" +System.lineSeparator()+
                "----------------------" +System.lineSeparator();
        assertEquals(expected,result);
    }
}