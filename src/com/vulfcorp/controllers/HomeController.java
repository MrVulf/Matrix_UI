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

    private HomeController thisController = this;

    private IMatrix matrix;

    @FXML
    void initialize(){
        normalMatrixButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                matrix = InitiatorMatrix.FillMatrix(new NormalMatrix(5,5),15,10);
                setViewInUI();
            }

        });

        sparseMatrixButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                matrix = InitiatorMatrix.FillMatrix(new SpareMatrix(5,5),10,10);
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

        borderCheckBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setViewInUI();
            }
        });

    }

    private void setViewInUI(){
        if(matrix == null){
            consoleTextArea.clear();
            consoleTextArea.appendText("matrix wasn't generate yet");
            return;
        }
        if(borderCheckBox.isSelected()) {
            matrix.draw(new UIMatrixDrawer(true, thisController));
            matrix.draw(ConsoleMatrixDrawer.getDrawerWithBorder());
        }
        else {
            matrix.draw(new UIMatrixDrawer(false, thisController));
            matrix.draw(ConsoleMatrixDrawer.getDrawerWithoutBorder());
        }
    }

    @Override
    public void viewMatrix(String matrixView) {
        consoleTextArea.clear();
        consoleTextArea.appendText(matrixView);
    }
}

