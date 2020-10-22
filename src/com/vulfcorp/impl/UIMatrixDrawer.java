package com.vulfcorp.impl;

import com.vulfcorp.abstracts.AbstractMatrixDrawer;
import com.vulfcorp.controllers.HomeController;
import com.vulfcorp.interfaces.IMatrix;
import com.vulfcorp.interfaces.IMatrixViewer;

public class UIMatrixDrawer extends AbstractMatrixDrawer {

    private boolean isNeedBorder;
    private IMatrixViewer viewer;

    public UIMatrixDrawer(boolean isNeedBorder, IMatrixViewer viewer) {
        this.isNeedBorder = isNeedBorder;
        this.viewer = viewer;
    }

    @Override
    protected void drawWithBorder(IMatrix matrix) {
        viewer.viewMatrix(drawDefaultMatrixViewWithBorder(matrix));
    }

    @Override
    protected void drawWithoutBorder(IMatrix matrix) {
        viewer.viewMatrix(drawDefaultMatrixViewWithoutBorder(matrix));
    }

    @Override
    protected boolean isNeedBorder() {
        return isNeedBorder;
    }

}
