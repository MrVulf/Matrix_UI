package com.vulfcorp.abstracts;

import com.vulfcorp.interfaces.IMatrix;
import com.vulfcorp.interfaces.IMatrixDrawer;
import com.vulfcorp.interfaces.IMatrixInternalIterator;

import java.util.Iterator;

public abstract class AbstractMatrixDrawer implements IMatrixDrawer {

    @Override
    public void drawMatrix(IMatrix matrix) {
        if(isNeedBorder())
            drawWithBorder(matrix);
        else
            drawWithoutBorder(matrix);
    }

    protected abstract void drawWithBorder(IMatrix matrix);

    protected abstract void drawWithoutBorder(IMatrix matrix);

    protected abstract boolean isNeedBorder();

    // use Internal Iterator
    final protected String drawDefaultMatrixViewWithBorder(IMatrix matrix){
        int column = matrix.getColumnCount();
        final Integer[] currentLine = {-1}; // use for control \n in iterate
        final StringBuilder result = new StringBuilder();

        makeDefaultBorderHeader(result, column);
        matrix.each(new IMatrixInternalIterator() {
            @Override
            public void iterate(IMatrix m, int row, int col) {
                if(row != currentLine[0]) {
                    result.append("\n|");
                    currentLine[0] = row;
                }
                Integer value = m.readRecord(row,col);
                if(value == null)
                    result.append(String.format("%5s|",""));
                else
                    result.append(String.format("%5s|",value));
            }
        });
        /*
        for(int i = 0; i < lines; i++){
            result.append("\n|");
            for(int j = 0; j < column; j++){
                Integer value = iterator.next();
                if(value == null)
                    result.append(String.format("%5s|",""));
                else
                    result.append(String.format("%5s|",value));
            }
        }*/
        makeDefaultBorderHeader(result, column);

        return result.toString();
    }

    // use External Iterator
    final protected String drawDefaultMatrixViewWithoutBorder(IMatrix matrix){
        int column = matrix.getColumnCount();
        int lines = matrix.getLineCount();
        Iterator<Integer> iterator = matrix.getIterator();

        StringBuilder result = new StringBuilder();
        for(int i = 0; i < lines; i++){
            result.append("\n");
            for(int j = 0; j < column; j++){
                Integer value = iterator.next();
                if(value == null)
                    result.append(String.format("%5s|",""));
                else
                    result.append(String.format("%5s|",value));
            }
        }

        return result.toString();
    }

    private void makeDefaultBorderHeader(StringBuilder builder, int column){
        builder.append("\n+");
        for(int i = 0; i < column-1; i++){
            for(int j = 0; j < 6; j++)
                builder.append("-");
        }
        for(int j = 0; j < 5; j++)
            builder.append("-");
        builder.append('+');
    }
}
