package com.vulfcorp.interfaces;

public interface IVector {
    void writeRecord (int position, int number);
    int readRecord (int position);
    int getSize();
}
