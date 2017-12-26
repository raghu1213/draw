package com.practice.draw.args;

import com.practice.draw.common.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

public class FillArgs  extends CommandLineArgsValidator implements CommandArgs {

    int x;
    int y;
    String color;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getColor() {
        return color;
    }

    @Override
    public void parse(String[] args) {
        if (validate(args)){
            this.x = Integer.parseInt(args[1]);
            this.y= Integer.parseInt(args[2]);
            this.color = args[3];
        }
        else{
            throw new InvalidParameterException(Constants.EXCEPTION_INVALID_PARAMTER);
        }
    }

    @Override
    public boolean validate(String[] param) {
        return param.length ==4 && isInteger(param[1]) && isInteger(param[2]);
    }
}
