package com.practice.draw.common;

public interface Validator<T> {
    boolean validate(T param);
}
