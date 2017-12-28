package com.practice.draw.output;


//@Service
public class StringPrinter implements Printer<String> {

    private String output="";
    @Override
    public void print(String value) {
        output+= value;
    }

    @Override
    public void printLine(String value) {
        output =  output + System.lineSeparator()+ value;
    }

    public String getOutput(){
        return this.output;
    }
}
