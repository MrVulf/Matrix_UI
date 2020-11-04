package com.vulfcorp.impl;

import com.vulfcorp.abstracts.AbstractMatrix;
import com.vulfcorp.interfaces.IVector;

public class SparseMatrix extends AbstractMatrix {
    public SparseMatrix(int lineCount, int columnCount) {
        IVector[] matrix;
        if(lineCount > 0 && columnCount > 0) {
            matrix = new IVector[lineCount];
            for (int i = 0; i < lineCount; i++) {
                matrix[i] = new SparseVector(columnCount);
            }
        } else {
            throw new IllegalArgumentException("lineCount and columnCount should be positive" +
                    " || current: " + lineCount + " " + columnCount);
        }
        fillField(matrix, lineCount, columnCount);
    }

}
