package com.practice.draw.output;

import org.springframework.stereotype.Service;

@Service
public class ConsolePrinter implements Printer<String> {

    @Override
    public void print(String value) {
        System.out.print(value);
    }

    @Override
    public void printLine(String value) {
        System.out.println(value);
    }
}
