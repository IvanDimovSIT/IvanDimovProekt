package com.company;

public interface ProcessCommand {
    void process(String[] command)throws CommandException;
}
