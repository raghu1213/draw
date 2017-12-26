package com.practice.draw.output;

import com.practice.draw.args.CanvasArgs;
import com.practice.draw.commands.Canvas;
import com.practice.draw.commands.ResultProvider;
import com.practice.draw.common.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OutputWriter {


    private final Printer<String> printer;

    public OutputWriter() {
        printer = null;
    }

    //@Autowired
    public OutputWriter(Printer<String> printer) {

        this.printer = printer;
    }

    public void print(List<ResultProvider> executedCommands) {

        List<Point> pointsToPrint = new ArrayList<>();
        CanvasArgs canvasArgs = null;
        for (ResultProvider command : executedCommands) {
            if (command instanceof Canvas) {
                canvasArgs = (CanvasArgs) ((Canvas) command).getArgs();
            }
            pointsToPrint.addAll(command.getResult());
        }

        String[][] finalResult = getEmtpy2DArray(canvasArgs);
        for (Point point : pointsToPrint) {
            finalResult[point.getY()][point.getX()] = point.getColor();
        }

        Print(canvasArgs, finalResult);
    }



    private String[][] getEmtpy2DArray(CanvasArgs canvasArgs) {
        String[][] holder = new String[canvasArgs.getHeight()+1][canvasArgs.getWidth()+1];
        for (int i = 0; i < canvasArgs.getHeight(); i++) {
            Arrays.fill(holder[i]," ");
        }
        return holder;
    }

    private void Print(CanvasArgs canvasArgs, String[][] finalResult) {
        for (int i = 0; i <= canvasArgs.getHeight(); i++) {
            for (int j = 0; j <= canvasArgs.getWidth(); j++) {
                String value = finalResult[i][j];
                this.printer.print(value);
            }
            this.printer.printLine("");
        }
    }
}
