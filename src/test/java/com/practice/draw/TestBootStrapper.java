package com.practice.draw;

import com.practice.draw.commands.Command;
import com.practice.draw.commands.CommandController;
import com.practice.draw.common.Constants;
import com.practice.draw.output.OutputWriter;
import com.practice.draw.output.Printer;
import com.practice.draw.parsers.Parser;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestBootStrapper {

    Parser commandLineParser;
    CommandController commandController;
    Printer<String> printer;
    OutputWriter outputWriter;
    Command command ;

    public TestBootStrapper(AnnotationConfigApplicationContext context){
        commandLineParser = (Parser)context.getBean(Constants.COMMAND_LINE_PARSER);
        commandController = context.getBean(CommandController.class);
        printer = (Printer<String>) context.getBean(Constants.PRINTER_STRING);
        outputWriter =  new OutputWriter(printer);

    }

    public Parser getCommandLineParser() {
        return commandLineParser;
    }

    public CommandController getCommandController() {
        return commandController;
    }

    public Command getCommand() {
        return command;
    }

    public OutputWriter getOutputWriter() {
        return outputWriter;
    }


}
