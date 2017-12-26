package com.practice.draw.common;

import java.util.Objects;

public class Point implements Comparable<Point> {
    public Point(int x, int y, String color) {
        this(x,y);
        this.color = color;
    }

    public Point (int x, int y){
        this.x = x;
        this.y = y;
    }

    int x;
    int y;
    String color;

    public String getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        Point other = (Point)obj;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s, %s)",this.x,this.y,this.color);
    }

    @Override
    public int compareTo(Point other) {
       int result;
       if (this.y < other.y){
           result = -1;
       }
       else if (this.y> other.y){
           result = 1;
       }
       else{
           if (this.x< other.x)
               result = -1;
           else if(this.x > other.x){
               result = 1;
           }
           else{
               result = 0;
           }
       }

       return  result;
    }

    @Override
    public int hashCode() {
        int newHash = Objects.hash(this.x,this.y);
        return newHash;
    }
}
