package com.vulfcorp;

import com.vulfcorp.decorators.MatrixDecorator;
import com.vulfcorp.impl.ConsoleMatrixDrawer;
import com.vulfcorp.impl.NormalMatrix;
import com.vulfcorp.impl.SparseMatrix;
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
}
