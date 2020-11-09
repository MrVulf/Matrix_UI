package com.vulfcorp;

import com.vulfcorp.decorators.MatrixDecorator;
import com.vulfcorp.impl.drawers.ConsoleMatrixDrawer;
import com.vulfcorp.impl.matrixs.NormalMatrix;
import com.vulfcorp.impl.matrixs.SparseMatrix;
import com.vulfcorp.interfaces.IMatrixInternalIterator;
import com.vulfcorp.interfaces.IMatrix;
import com.vulfcorp.tools.InitiatorMatrix;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Iterator;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("resources/home.fxml"));
        primaryStage.setTitle("Matrix Generator");
        primaryStage.setScene(new Scene(root, 1020, 720));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        //testDrawMatrixInConsole();
        //testMatrixDecorator();
        //testMatrixIterator();
        //testInternalIterator();
    }

    private static void testDrawMatrixInConsole(){
        NormalMatrix normalMatrix = new NormalMatrix(5, 5);
        SparseMatrix sparseMatrix = new SparseMatrix(5,5);

        InitiatorMatrix.FillMatrix(normalMatrix, 15, 99);
        InitiatorMatrix.FillMatrix(sparseMatrix, 10, 99);


        normalMatrix.draw(ConsoleMatrixDrawer.getDrawerWithBorder());
        sparseMatrix.draw(ConsoleMatrixDrawer.getDrawerWithBorder());
        System.out.println("\n");
        normalMatrix.draw(ConsoleMatrixDrawer.getDrawerWithoutBorder());
        sparseMatrix.draw(ConsoleMatrixDrawer.getDrawerWithoutBorder());
    }

    private static void testMatrixDecorator(){
        NormalMatrix normalMatrix = new NormalMatrix(5, 5);

        InitiatorMatrix.FillMatrix(normalMatrix, 15, 99);

        MatrixDecorator normalDecorator = new MatrixDecorator(normalMatrix);
        normalDecorator.swapColumn(2,3);
        normalDecorator.swapLines(0,2);

        normalMatrix.draw(ConsoleMatrixDrawer.getDrawerWithBorder());
        normalDecorator.draw(ConsoleMatrixDrawer.getDrawerWithBorder());
    }

    private static void testMatrixIterator(){
        NormalMatrix normalMatrix = new NormalMatrix(5, 5);
        InitiatorMatrix.FillMatrix(normalMatrix, 15, 99);
        MatrixDecorator normalDecorator = new MatrixDecorator(normalMatrix);

        normalDecorator.swapColumn(2,3);
        normalDecorator.swapLines(0,2);

        Iterator iterator1 = normalMatrix.getIterator();
        Iterator iterator2 = normalDecorator.getIterator();

        System.out.println(iterator1.hasNext() + " || " + iterator1.next() +"\n");
        System.out.println(iterator2.hasNext() + " || " + iterator2.next() +"\n");
        System.out.println("finish test");
    }

    private static void testInternalIterator(){
        NormalMatrix normalMatrix = new NormalMatrix(5, 5);
        InitiatorMatrix.FillMatrix(normalMatrix, 15, 99);
        System.out.println("Normal:");
        normalMatrix.draw(ConsoleMatrixDrawer.getDrawerWithBorder());

        normalMatrix.each(new IMatrixInternalIterator() {
            @Override
            public void iterate(IMatrix m, int row, int col) {
                m.writeRecord(row,col,0);
            }
        });
        normalMatrix.draw(ConsoleMatrixDrawer.getDrawerWithBorder());

        System.out.println("Decorator");
        SparseMatrix sparseMatrix = new SparseMatrix(5,5);
        InitiatorMatrix.FillMatrix(sparseMatrix, 10, 99);
        MatrixDecorator sparseDecorator = new MatrixDecorator(sparseMatrix);
        sparseDecorator.draw(ConsoleMatrixDrawer.getDrawerWithoutBorder());
        sparseDecorator.each(new IMatrixInternalIterator() {
            @Override
            public void iterate(IMatrix m, int row, int col) {
                m.writeRecord(row,col,0);
            }
        });
        sparseDecorator.draw(ConsoleMatrixDrawer.getDrawerWithoutBorder());


    }
}
