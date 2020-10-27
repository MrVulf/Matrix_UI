package com.vulfcorp.decorators;

import com.vulfcorp.interfaces.IMatrix;
import com.vulfcorp.interfaces.IMatrixDrawer;

import java.util.HashMap;
import java.util.Map;

public class MatrixDecorator implements IMatrix {
    private final IMatrix component;
    private final Map<Integer,Integer> linesMap;
    private final Map<Integer,Integer> columnsMap;

    public MatrixDecorator(IMatrix component) {
        this.component = component;
        linesMap = new HashMap<>();
        columnsMap = new HashMap<>();
        decorateByDefault();
    }

    public void decorateByDefault(){
        for(int i = 0; i < component.getLineCount(); i++){
            linesMap.put(i,i);
        }
        for(int i = 0; i < component.getColumnCount(); i++){
            columnsMap.put(i,i);
        }
    }

    public void swapColumn(int firstColumn, int secondColumn){
        if(isColumnNumberCorrect(firstColumn) && isColumnNumberCorrect(secondColumn)) {
            columnsMap.put(firstColumn, secondColumn);
            columnsMap.put(secondColumn, firstColumn);
        } else {
            throw new IllegalArgumentException("FirstColumn = "+firstColumn+" SecondColumn = "+secondColumn+" are not correct");
        }
    }

    public void swapLines(int firstLine, int secondLine){
        if(isLineNumberCorrect(firstLine) && isLineNumberCorrect(secondLine)) {
            linesMap.put(firstLine, secondLine);
            linesMap.put(secondLine, firstLine);
        } else {
            throw new IllegalArgumentException("FirstLine = "+firstLine+" SecondLine = "+secondLine+" are not correct");
        }
    }

    private boolean isColumnNumberCorrect(int column){
        return 0 <= column && column < columnsMap.size();
    }

    private boolean isLineNumberCorrect(int line){
        return 0 <= line && line < linesMap.size();
    }

    @Override
    public void writeRecord(int line, int column, int number) {
        int newLine = linesMap.get(line);
        int newColumn = columnsMap.get(column);

        component.writeRecord(newLine, newColumn, number);
    }

    @Override
    public int readRecord(int line, int column) {
        int newLine = linesMap.get(line);
        int newColumn = columnsMap.get(column);

        return component.readRecord(newLine, newColumn);
    }

    @Override
    public int getLineCount() {
        return component.getLineCount();
    }

    @Override
    public int getColumnCount() {
        return component.getColumnCount();
    }

    @Override
    public void draw(IMatrixDrawer drawer) {
        drawer.drawMatrix(this);
    }

    @Override
    public String getElementForDrawing(int i, int j) {
        int newLine = linesMap.get(i);
        int newColumn = columnsMap.get(j);

        return component.getElementForDrawing(newLine, newColumn);
    }
}
