package com.vulfcorp.impl;

import com.vulfcorp.abstracts.AbstractMatrix;
import com.vulfcorp.interfaces.IVector;

public class SpareMatrix extends AbstractMatrix {
    public SpareMatrix(int lineCount, int columnCount) {
        IVector[] matrix;
        if(lineCount > 0 && columnCount > 0) {
            matrix = new IVector[lineCount];
            for (int i = 0; i < lineCount; i++) {
                matrix[i] = new SpareVector(columnCount);
            }
        } else {
            throw new IllegalArgumentException("lineCount and columnCount should be positive" +
                    " || current: " + lineCount + " " + columnCount);
        }
        fillField(matrix, lineCount, columnCount);
    }

    public String getElementForDrawing(int i, int j) {
        int value = readRecord(i,j);
        return (value!=0 ? String.valueOf(value) : " ");
    }
}
