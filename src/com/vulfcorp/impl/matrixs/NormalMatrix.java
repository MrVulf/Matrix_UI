package com.vulfcorp.impl.matrixs;

import com.vulfcorp.abstracts.AbstractMatrix;
import com.vulfcorp.impl.vectors.NormalVector;
import com.vulfcorp.interfaces.IVector;

public class NormalMatrix extends AbstractMatrix {
    public NormalMatrix(int lineCount, int columnCount) {
        IVector[] matrix;
        if(lineCount > 0 && columnCount > 0) {
            matrix = new IVector[lineCount];
            for (int i = 0; i < lineCount; i++) {
                matrix[i] = new NormalVector(columnCount);
            }
        } else {
            throw new IllegalArgumentException("lineCount and columnCount should be positive" +
                    " || current: " + lineCount + " " + columnCount);
        }
        fillField(matrix, lineCount, columnCount);
    }

}
