package com.vulfcorp.impl.drawers;

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
        System.out.println(drawDefaultMatrixViewWithBorder(matrix));
    }


    @Override
    protected void drawWithoutBorder(IMatrix matrix) {
        System.out.println(drawDefaultMatrixViewWithoutBorder(matrix));
    }


}
