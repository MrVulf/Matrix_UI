package com.vulfcorp.interfaces;

public interface IVector {
    void writeRecord (int position, int number);
    Integer readRecord (int position);
    int getSize();
}
