package com.vulfcorp.interfaces;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public interface IMatrix {
    void writeRecord (int line, int column, int number);
    Integer readRecord (int line, int column);
    int getLineCount();
    int getColumnCount();
    void draw(IMatrixDrawer drawer);
    default Iterator getIterator(){
        return new Iterator<Integer>(){
            int row = 0;
            int column = 0;

            @Override
            public boolean hasNext() {
                boolean s1 = 0<=row && row< getLineCount();
                boolean s2 = 0<=column && column < getColumnCount();
                return s1 && s2;
            }

            @Override
            public Integer next() {
                if(row == getLineCount() && column == getColumnCount())
                    throw new NoSuchElementException();
                Integer value = readRecord(row, column);
                correctRowAndColumn();
                return value;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void forEachRemaining(Consumer<? super Integer> action) {
                throw new UnsupportedOperationException();
            }

            private void correctRowAndColumn(){
                if(column == getColumnCount()-1){
                    if(row != getLineCount()-1){
                        column = 0;
                        row++;
                    }
                    return;
                }
                column++;
            }
        };
    }
    default void each(IMatrixInternalIterator iterator){
        for(int row = 0; row < getLineCount(); row++)
            for(int column = 0; column < getColumnCount(); column++){
                iterator.iterate(this, row, column);
            }
    }
    IMatrix getCopy();
}
