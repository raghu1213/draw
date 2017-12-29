package com.practice.draw.commands;

import com.practice.draw.args.CanvasArgs;
import com.practice.draw.args.CommandArgs;
import com.practice.draw.common.Constants;
import com.practice.draw.common.Point;
import com.practice.draw.common.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller(Constants.COMMAND_CONTROLLER)
public class CommandController implements Validator<Command> {

    /**
     * Maintains a list of commands
     * Can be used to iterate the commands
     */
    private List<Command> commands ;

    /**
     * Maintains state of the canvas. There can be only one canvas
     */
    private Canvas canvas;

    private CommandFactory commandFactory;

    public CommandController(){
        commands = new ArrayList<>();
    }

    @Autowired
    public void setCommandFactory(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    /**
     * Executes the commands for the given arguments
     * */
    public void executeCommand(CommandArgs args){
        Command command  = commandFactory.createCommand(args.getClass().getSimpleName());
        if (this.validate(command)) {
            command.setArgs(args);
            this.setupState(command);
            commands.add(command);
            command.execute();
        }
    }

    /**
     * Gets list of all the commands executed so far
     */
    public List<ResultProvider> getExecutedCommands() {
        return Collections.unmodifiableList(this.commands);
    }

    @Override
    public boolean validate(Command param) {
        if (this.commands.size()==0 && !(param instanceof Canvas)){
            throw new UnsupportedOperationException("Canvas is not provided. You must provide a canvas before any other command");
        }
        return true;
    }

    private void setupState(Command command) {
        if (command instanceof Canvas) {
            this.canvas= (Canvas) command;
        }
        else if (command instanceof BucketFill) {
            ((BucketFill) command).provideCurrentState(
                    (CanvasArgs) this.canvas.getArgs(),
                    this.getGeometricShape(),
                    this.getFillCoordinates());
        }
    }



    private List<List<Point>> getGeometricShape(){
        return this.getFilteredShapes(true);
    }

    private List<List<Point>> getFillCoordinates(){
        return this.getFilteredShapes(false);
    }

    private List<List<Point>> getFilteredShapes(boolean geometicShapes) {
        List<List<Point>> result = new ArrayList<>();
        for (Command command: this.commands) {
            if ((command instanceof BucketFill)== geometicShapes) continue;
            List<Point> coordinates = command.getResult();
            result.add(coordinates);
        }
        return result;
    }


}
