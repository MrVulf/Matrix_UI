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

    private List<ICommand> commandList = new LinkedList<ICommand>();

    private CommandManager() {}

    public void register(ICommand command){
        commandList.add(command);
    }

    public void undo(){
        commandList.remove(commandList.size()-1);
        for(ICommand command : commandList){
            command.execute();
        }
    }
}
