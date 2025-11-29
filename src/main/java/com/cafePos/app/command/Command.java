package com.cafePos.app.command;


public interface Command {
    void execute();
    default void undo() { /* optional */ }
}
