package com.vulfcorp.impl;

import com.vulfcorp.interfaces.IVector;

import java.util.HashMap;
import java.util.Map;

public class SpareVector implements IVector {

    private Map<Integer,Integer> vector;
    private int size;

    public SpareVector(int size) {
        if(size > 0) {
            this.size=size;
            vector = new HashMap<>();
        } else {
            throw new IllegalArgumentException("size should be >0 => size="+size);
        }
    }

    @Override
    public void writeRecord(int position, int number) {
        if(number == 0 && 0 <= position && position < size)
            vector.remove(position);
        else {
            if (checkElementCanBeAdd()) {
                if (0 <= position && position < size) {
                    vector.put(position, number);
                } else {
                    throw new IllegalArgumentException("position should be [0," +
                            (size - 1) + "]. position=" + position);
                }
            } else {
                throw new IllegalArgumentException("SpareVector has max count not null elements for saving it's feature || position = " + position + ", number = " + number);
            }
        }
    }

    @Override
    public int readRecord(int position) {
        if(0 <= position && position < size){
            if(vector.containsKey(position))
                return vector.get(position);
            else
                return 0;
        } else {
            throw new IllegalArgumentException("position should be in "+
                    vector.keySet()+" || position="+position);
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder builder= new StringBuilder("{");
        for(int i = 0; i < size; i++) {
            if (vector.containsKey(i))
                builder.append(" " + vector.get(i) + " ");
            else
                builder.append(" 0 ");
        }
        builder.append("}");
        return builder.toString();
    }

    private boolean checkElementCanBeAdd(){
        Double avg = ((double)vector.size()+1)/size;
        if(avg.compareTo(0.5) <= 0)
            return true;
        else
            return false;
    }




}
