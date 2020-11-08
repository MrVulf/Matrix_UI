package com.vulfcorp.abstracts;

import com.vulfcorp.interfaces.ICommand;
import com.vulfcorp.managers.CommandManager;

public abstract class AbstractCommand implements ICommand {
    @Override
    public void execute() {
        CommandManager.getInstance().register(this);
        doExecute();
    }

    protected abstract void doExecute();
}
