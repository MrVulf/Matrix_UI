package com.vulfcorp.abstracts;

import com.vulfcorp.interfaces.ICommand;
import com.vulfcorp.managers.CommandManager;

public abstract class AbstractCommand implements ICommand {

    public AbstractCommand(){
        CommandManager.getInstance().register(this);
    }

    @Override
    public abstract void execute();
}
