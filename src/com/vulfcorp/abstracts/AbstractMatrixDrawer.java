package com.vulfcorp.abstracts;

import com.vulfcorp.interfaces.IMatrix;
import com.vulfcorp.interfaces.IMatrixDrawer;

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

    final protected String drawDefaultMatrixViewWithBorder(IMatrix matrix){
        int column = matrix.getColumnCount();
        int lines = matrix.getLineCount();
        StringBuilder result = new StringBuilder();

        makeDefaultBorderHeader(result, column);
        for(int i = 0; i < lines; i++){
            result.append("\n|");
            for(int j = 0; j < column; j++){
                result.append(String.format("%5s|",matrix.getElementForDrawing(i,j)));
            }
        }
        makeDefaultBorderHeader(result, column);

        return result.toString();
    }

    final protected String drawDefaultMatrixViewWithoutBorder(IMatrix matrix){
        int column = matrix.getColumnCount();
        int lines = matrix.getLineCount();

        StringBuilder result = new StringBuilder();
        for(int i = 0; i < lines; i++){
            result.append("\n");
            for(int j = 0; j < column; j++){
                /*
                result.append(String.format("%5s|",matrix.getElementForDrawing(i,j)));
                                        ^--- TextArea incorrect view it
                */
                String cNumber = matrix.getElementForDrawing(i,j);
                for(int space = 0; space < 5-cNumber.length(); space++)
                    result.append(" ");
                result.append(cNumber+"|");

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
