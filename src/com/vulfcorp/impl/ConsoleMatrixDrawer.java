package com.vulfcorp.impl;

import com.vulfcorp.abstracts.AbstractMatrixDrawer;
import com.vulfcorp.interfaces.IMatrix;
import com.vulfcorp.interfaces.IMatrixDrawer;

public class ConsoleMatrixDrawer extends AbstractMatrixDrawer {
    private static IMatrixDrawer drawerWithBorder = null;
    private static IMatrixDrawer drawerWithoutBorder = null;

    public static IMatrixDrawer getDrawerWithBorder(){
        if(drawerWithBorder==null){
            drawerWithBorder = new ConsoleMatrixDrawer(true);
        }
        return drawerWithBorder;
    }
    public static IMatrixDrawer getDrawerWithoutBorder(){
        if(drawerWithoutBorder==null){
            drawerWithoutBorder = new ConsoleMatrixDrawer(false);
        }
        return drawerWithoutBorder;
    }

    private boolean isNeedBorder;

    private ConsoleMatrixDrawer(boolean drawBorder) {
        this.isNeedBorder = drawBorder;
    }

    @Override
    protected boolean isNeedBorder() {
        return this.isNeedBorder;
    }

    @Override
    protected void drawWithBorder(IMatrix matrix) {
        int column = matrix.getColumnCount();
        int lines = matrix.getLineCount();
        StringBuilder result = new StringBuilder();

        makeBorderHeader(result, column);
        for(int i = 0; i < lines; i++){
            result.append("\n|");
            for(int j = 0; j < column; j++){
                result.append(String.format("%5s|",matrix.getElementForDrawing(i,j)));
            }
        }
        makeBorderHeader(result, column);

        System.out.println("Your matrix:"+result.toString());
    }

    private void makeBorderHeader(StringBuilder builder, int column){
        builder.append("\n+");
        for(int i = 0; i < column-1; i++){
            for(int j = 0; j < 6; j++)
                builder.append("-");
        }
        for(int j = 0; j < 5; j++)
            builder.append("-");
        builder.append('+');
    }

    @Override
    protected void drawWithoutBorder(IMatrix matrix) {
        int column = matrix.getColumnCount();
        int lines = matrix.getLineCount();

        StringBuilder result = new StringBuilder();
        for(int i = 0; i < lines; i++){
            result.append("\n");
            for(int j = 0; j < column; j++){
                result.append(String.format("%6s|",matrix.getElementForDrawing(i,j)));
            }
        }
        System.out.println("Your matrix:"+result.toString());
    }


}
