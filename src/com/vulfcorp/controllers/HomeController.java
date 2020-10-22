package com.vulfcorp.controllers;

import com.vulfcorp.impl.ConsoleMatrixDrawer;
import com.vulfcorp.impl.NormalMatrix;
import com.vulfcorp.impl.SpareMatrix;
import com.vulfcorp.impl.UIMatrixDrawer;
import com.vulfcorp.interfaces.IMatrix;
import com.vulfcorp.interfaces.IMatrixViewer;
import com.vulfcorp.tools.InitiatorMatrix;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class HomeController implements IMatrixViewer {
    @FXML
    private Button normalMatrixButton;

    @FXML
    private Button sparseMatrixButton;

    @FXML
    private CheckBox borderCheckBox;

    @FXML
    private TextArea consoleTextArea;

    @FXML
    private SubScene uiSubScene;

    private HomeController thisController = this;

    @FXML
    void initialize(){
        normalMatrixButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
               IMatrix matrix = InitiatorMatrix.FillMatrix(new NormalMatrix(5,5),15,10);
               if(borderCheckBox.isSelected()) {
                   matrix.draw(new UIMatrixDrawer(true, thisController));
                   matrix.draw(ConsoleMatrixDrawer.getDrawerWithBorder());
               }
                else {
                   matrix.draw(new UIMatrixDrawer(false, thisController));
                   matrix.draw(ConsoleMatrixDrawer.getDrawerWithoutBorder());
               }
            }

        });

        sparseMatrixButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                IMatrix matrix = InitiatorMatrix.FillMatrix(new SpareMatrix(5,5),10,10);
                if(borderCheckBox.isSelected()) {
                    matrix.draw(new UIMatrixDrawer(true, thisController));
                    matrix.draw(ConsoleMatrixDrawer.getDrawerWithBorder());
                }
                else {
                    matrix.draw(new UIMatrixDrawer(false, thisController));
                    matrix.draw(ConsoleMatrixDrawer.getDrawerWithoutBorder());
                }
            }

        });

        borderCheckBox.fire();

    }

    @Override
    public void viewMatrix(String matrixView) {
        consoleTextArea.clear();
        consoleTextArea.appendText(matrixView);
    }
}

