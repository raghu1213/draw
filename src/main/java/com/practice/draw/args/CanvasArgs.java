package com.practice.draw.args;

import com.practice.draw.common.Constants;

import java.security.InvalidParameterException;

public class CanvasArgs extends CommandLineArgsValidator implements CommandArgs{
    private final String horizontalLineColor;
    private final String verticalLineColor;
    private  int width;
    private int height;

    public CanvasArgs(String horizontalLineColor, String verticalLineColor){

        this.horizontalLineColor = horizontalLineColor;
        this.verticalLineColor = verticalLineColor;
    }

    public int getWidth() {
        return width +1;
    }

    public int getHeight() {
        return height+1;
    }

    public String getHorizontalLineColor() {
        return horizontalLineColor;
    }

    public String getVerticalLineColor() {
        return verticalLineColor;
    }

    @Override
    public void parse(String[] args) {
        if (this.validate(args,3)){
            this.width =  Integer.parseInt(args[1]);
            this.height = Integer.parseInt(args[2]);
        }
        else {
            throw new InvalidParameterException(Constants.EXCEPTION_INVALID_PARAMTER);
        }
    }

}
