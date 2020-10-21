package com.vulfcorp;

import com.vulfcorp.impl.ConsoleMatrixDrawer;
import com.vulfcorp.impl.NormalMatrix;
import com.vulfcorp.impl.SpareMatrix;
import com.vulfcorp.tools.InitiatorMatrix;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("resources/home.fxml"));
        primaryStage.setTitle("Matrix Generator");
        primaryStage.setScene(new Scene(root, 1060, 720));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        //testDrawMatrixInConsole();
    }

    private static void testDrawMatrixInConsole(){
        NormalMatrix normalMatrix = new NormalMatrix(5, 5);
        SpareMatrix  spareMatrix  = new SpareMatrix(5,5);

        InitiatorMatrix.FillMatrix(normalMatrix, 15, 99);
        InitiatorMatrix.FillMatrix(spareMatrix, 10, 99);


        normalMatrix.Draw(ConsoleMatrixDrawer.getDrawerWithBorder());
        spareMatrix.Draw(ConsoleMatrixDrawer.getDrawerWithBorder());
        System.out.println("\n");
        normalMatrix.Draw(ConsoleMatrixDrawer.getDrawerWithoutBorder());
        spareMatrix.Draw(ConsoleMatrixDrawer.getDrawerWithoutBorder());
    }
}
