package com.vulfcorp.abstracts;

import com.vulfcorp.impl.ConsoleMatrixDrawer;
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
}
