package com.vulfcorp.impl;

import com.vulfcorp.abstracts.AbstractMatrixDrawer;
import com.vulfcorp.interfaces.IMatrix;

public class UIMatrixDrawer extends AbstractMatrixDrawer {



    @Override
    protected void drawWithBorder(IMatrix matrix) {

    }

    @Override
    protected void drawWithoutBorder(IMatrix matrix) {

    }

    @Override
    protected boolean isNeedBorder() {
        return false;
    }
}
