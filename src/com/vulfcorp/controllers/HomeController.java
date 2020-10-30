package com.vulfcorp.controllers;

import com.vulfcorp.decorators.MatrixDecorator;
import com.vulfcorp.impl.ConsoleMatrixDrawer;
import com.vulfcorp.impl.NormalMatrix;
import com.vulfcorp.impl.SparseMatrix;
import com.vulfcorp.impl.UIMatrixDrawer;
import com.vulfcorp.interfaces.IMatrix;
import com.vulfcorp.interfaces.IMatrixViewer;
import com.vulfcorp.tools.InitiatorMatrix;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private Button renumberRowsAndColumnsButton;

    @FXML
    private Button redecorateToDefaultButton;


    private HomeController thisController = this;

    private MatrixDecorator matrix;

    @FXML
    void initialize(){
        normalMatrixButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                IMatrix nMatrix = InitiatorMatrix.FillMatrix(new NormalMatrix(5,5),15,10);
                matrix = new MatrixDecorator(nMatrix);
                setViewInUI();
            }

        });

        sparseMatrixButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                IMatrix nMatrix = InitiatorMatrix.FillMatrix(new SparseMatrix(5,5),10,10);
                matrix = new MatrixDecorator(nMatrix);
                setViewInUI();
            }

        });

        renumberRowsAndColumnsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if (matrix != null) {
                    int columnCount = matrix.getColumnCount();
                    int lineCount = matrix.getLineCount();

                    int column1 = (int) (Math.random() * (columnCount));
                    int column2 = (int) (Math.random() * (columnCount));
                    int line1 = (int) (Math.random() * (lineCount));
                    int line2 = (int) (Math.random() * (lineCount));

                    matrix.swapLines(line1, line2);
                    matrix.swapColumn(column1, column2);

                    ShowAlert("RENUMBER EVENT", "swap columns (" + column1 + "," + column2 +
                            "), rows (" + line1 + "," + line2 + ")");

                    setViewInUI();
                } else{
                    ShowAlert("MATRIX EVENT", "MATRIX WASN'T GENERATED");
                }
            }
        });

        redecorateToDefaultButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                matrix.decorateByDefault();
                setViewInUI();
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

    private void ShowAlert(String header, String info){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(header);
        alert.setContentText(info);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println(info);
            }
        });
    }

    @Override
    public void viewMatrix(String matrixView) {
        consoleTextArea.clear();
        consoleTextArea.appendText(matrixView);
    }
}

