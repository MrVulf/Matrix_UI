package com.vulfcorp.impl;

import com.vulfcorp.interfaces.IVector;

public class NormalVector implements IVector {
    private int[] vector;

    public NormalVector(int size){
        if(size > 0) {
            vector = new int[size];
        } else {
            throw new IllegalArgumentException("size should be >0 => size="+size);
        }
    }
    // for getCopy()
    private NormalVector(int[] extVector){
        this.vector = new int[extVector.length];
        for(int i = 0; i < extVector.length; i++){
            this.vector[i]=extVector[i];
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
    public Integer readRecord(int position) {
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
    public IVector getCopy() {
        return new NormalVector(vector);
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
