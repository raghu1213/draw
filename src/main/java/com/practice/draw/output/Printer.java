package com.practice.draw.output;

public interface Printer<T> {
    void print(T value);
    void printLine(T value);
}
