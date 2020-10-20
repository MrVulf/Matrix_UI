package com.vulfcorp.impl;

import com.vulfcorp.interfaces.IVector;

import java.util.Arrays;

public class NormalVector implements IVector {
    private int[] vector;

    public NormalVector(int size){
        if(size > 0) {
            vector = new int[size];
        } else {
            throw new IllegalArgumentException("size should be >0 => size="+size);
        }
    }

    @Override
    public void writeRecord(int position, int number) {
        if(0 <= position && position < vector.length){
            vector[position]=number;
        } else {
            throw new IllegalArgumentException("position should be [0,"+
                    (vector.length-1)+"]. position="+position);
        }
    }

    @Override
    public int readRecord(int position) {
        if (0 <= position && position < vector.length) {
            return vector[position];
        } else {
            throw new IllegalArgumentException("position should be [0," +
                    (vector.length - 1) + "]. position=" + position);
        }
    }

    @Override
    public int getSize() {
        return vector.length;
    }

    @Override
    public String toString() {
        StringBuilder builder= new StringBuilder("{");
        for(int i : vector) {
            builder.append(" " + i + " ");
        }
        builder.append("}");
        return builder.toString();
    }
}
