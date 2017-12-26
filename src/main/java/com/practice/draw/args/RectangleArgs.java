package com.practice.draw.args;

import com.practice.draw.common.Constants;

import java.security.InvalidParameterException;

public class RectangleArgs extends CommandLineArgsValidator implements CommandArgs {

    private final String horizontalLineColor;
    private final String verticalLineColor;
    int x1;
    int y1;
    int x2;
    int y2;

    public RectangleArgs(String horizontalLineColor, String verticalLineColor){
        this.horizontalLineColor = horizontalLineColor;
        this.verticalLineColor = verticalLineColor;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public String getHorizontalLineColor() {
        return horizontalLineColor;
    }

    public String getVerticalLineColor() {
        return verticalLineColor;
    }

    @Override
    public void parse(String[] args) {
        if (this.validate(args,5)){
            this.x1 = Integer.parseInt(args[1]);
            this.y1 = Integer.parseInt(args[2]);
            this.x2 = Integer.parseInt(args[3]);
            this.y2 = Integer.parseInt(args[4]);
        }
        else{
            throw new InvalidParameterException(Constants.EXCEPTION_INVALID_PARAMTER);
        }

    }


}
