package com.vulfcorp.interfaces;

public interface IMatrix {
    void writeRecord (int line, int column, int number);
    int readRecord (int line, int column);
    int getLineCount();
    int getColumnCount();
    void Draw(IMatrixDrawer drawer);
    String getElementForDrawing(int i, int j);
}
