package com.vulfcorp.abstracts;

import com.vulfcorp.interfaces.ICommand;
import com.vulfcorp.managers.CommandManager;

public abstract class AbstractCommand implements ICommand {
    @Override
    public final void execute(){
        if(!CommandManager.getInstance().geUndoState()) {
            CommandManager.getInstance().register(this);
        }
        doExecute();
    }
    protected abstract void doExecute();
}
