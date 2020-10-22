package com.vulfcorp.abstracts;

import com.vulfcorp.interfaces.IMatrix;
import com.vulfcorp.interfaces.IMatrixDrawer;
import com.vulfcorp.interfaces.IVector;

import java.util.ArrayList;

public abstract class AbstractMatrix implements IMatrix {
    private IVector[] matrix;
    private int lineCount;
    private int columnCount;

    protected void fillField(IVector[] matrix, int lineCount, int columnCount){
        this.matrix = matrix;
        this.columnCount = columnCount;
        this.lineCount = lineCount;
    }

    @Override
    public void draw(IMatrixDrawer drawer) {
        drawer.drawMatrix(this);
    }

    @Override
    public void writeRecord(int line, int column, int number) {
        if(checkingCorrectnessOfPosition(line,column)){
            (matrix[line]).writeRecord(column,number);
        } else{
            throw new IllegalArgumentException("line = [0," +lineCount+']'
                    +"|| column = [0,"+columnCount+"] "
                    + "current: line = "+ line +" column = "+column);
        }
    }

    @Override
    public int readRecord(int line, int column) {
        if(checkingCorrectnessOfPosition(line,column)){
            return (matrix[line]).readRecord(column);
        }else{
            throw new IllegalArgumentException("line = [0," +lineCount+']'
                    +"|| column = [0,"+columnCount+"] "
                    + "current: line = "+ line +" column = "+column);
        }
    }

    @Override
    public int getLineCount() {
        return lineCount;
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    private boolean checkingCorrectnessOfPosition(int line, int column){
        boolean s1 = 0<=line && line< lineCount;
        boolean s2 = 0<=column && column <= columnCount;
        return s1 && s2;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < lineCount; i++){
            builder.append(matrix[i].toString());
            builder.append("\n");
        }
        return builder.toString();
    }
}
