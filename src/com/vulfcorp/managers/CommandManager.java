package com.vulfcorp.managers;

import com.vulfcorp.interfaces.ICommand;

import java.util.LinkedList;
import java.util.List;

public class CommandManager {
    private static CommandManager instance;
    public static CommandManager getInstance(){
        if(instance==null)
            instance = new CommandManager();
        return instance;
    }

    private final List<ICommand> commandList = new LinkedList<>();
    private boolean isInUndoState = false;

    private CommandManager() {}

    public boolean geUndoState(){
        return isInUndoState;
    }

    public void register(ICommand command){
        commandList.add(command);
    }

    public void undo(){
        isInUndoState = true;
        if(commandList.size()!=0) {
            commandList.remove(commandList.size() - 1);
            System.out.println("BEGIN UNDO MECHANISM");
            for (ICommand command : commandList) {
                command.execute();
            }
            System.out.println("END UNDO MECHANISM");
        }
        isInUndoState = false;
    }
}
