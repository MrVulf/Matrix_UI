package com.vulfcorp.controllers;

import com.vulfcorp.abstracts.AbstractCommand;
import com.vulfcorp.decorators.MatrixDecorator;
import com.vulfcorp.impl.drawers.ConsoleMatrixDrawer;
import com.vulfcorp.impl.matrixs.NormalMatrix;
import com.vulfcorp.impl.matrixs.SparseMatrix;
import com.vulfcorp.impl.drawers.UIMatrixDrawer;
import com.vulfcorp.interfaces.IMatrix;
import com.vulfcorp.interfaces.IMatrixViewer;
import com.vulfcorp.managers.CommandManager;
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

    @FXML
    private Button undoButton;

    private final HomeController thisController = this;

    private MatrixDecorator matrix;

    @FXML
    void initialize(){
        normalMatrixButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                IMatrix nMatrix = InitiatorMatrix.FillMatrix(new NormalMatrix(5,5),15,10);
                MyCommand command = new MyCommand(thisController, new MatrixDecorator(nMatrix), borderCheckBox.isSelected()){
                    @Override
                    public void doExecute() {
                        setDataInController();
                    }
                };
                command.execute();
            }

        });

        sparseMatrixButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                IMatrix nMatrix = InitiatorMatrix.FillMatrix(new SparseMatrix(5,5),10,10);
                MyCommand command = new MyCommand(thisController, new MatrixDecorator(nMatrix), borderCheckBox.isSelected()){
                    @Override
                    public void doExecute() {
                        setDataInController();
                    }
                };
                command.execute();
            }

        });

        renumberRowsAndColumnsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                MyCommand command = new MyCommand(thisController,matrix, borderCheckBox.isSelected()){
                    @Override
                    public void doExecute() {
                        if (getInternalDecorator() != null) {
                            int columnCount = getInternalDecorator().getColumnCount();
                            int lineCount = getInternalDecorator().getLineCount();

                            int column1 = (int) (Math.random() * (columnCount));
                            int column2 = (int) (Math.random() * (columnCount));
                            int line1 = (int) (Math.random() * (lineCount));
                            int line2 = (int) (Math.random() * (lineCount));

                            getInternalDecorator().swapLines(line1, line2);
                            getInternalDecorator().swapColumn(column1, column2);

                            setDataInController();
                        }

                    }
                };
                command.execute();
            }
        });

        redecorateToDefaultButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                MyCommand command = new MyCommand(thisController, matrix, borderCheckBox.isSelected()){
                    @Override
                    public void doExecute() {
                        getInternalDecorator().decorateByDefault();
                        setDataInController();
                    }
                };
                command.execute();
            }
        });

        borderCheckBox.fire();

        borderCheckBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MyCommand command = new MyCommand(thisController, matrix, borderCheckBox.isSelected()){
                    @Override
                    public void doExecute() {
                        setDataInController();
                    }
                };
                command.execute();
            }
        });

        undoButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                CommandManager.getInstance().undo();
            }
        });

        // remember first state of the controller
        new MyCommand(thisController, matrix, borderCheckBox.isSelected()){
            @Override
            public void doExecute() {
                setDataInController();
            }
        }.execute();


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

    private abstract static class MyCommand extends AbstractCommand {
        private final HomeController controller;
        private final MatrixDecorator internalDecorator;
        private final boolean isBorderNeed;

        public MyCommand(HomeController controller, MatrixDecorator decorator, boolean isBorderNeed) {
            this.controller = controller;
            this.isBorderNeed = isBorderNeed;
            if(decorator != null)
                this.internalDecorator = (MatrixDecorator)decorator.getCopy(); // return IMatrix (MatrixDecorator inside)
            else
                this.internalDecorator = null;
        }

        public void setDataInController(){
            controller.matrix=internalDecorator;
            controller.borderCheckBox.setSelected(isBorderNeed);
            controller.setViewInUI();
        }

        protected MatrixDecorator getInternalDecorator(){
            return internalDecorator;
        }
    }
}

