package com.company;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CommandProcessor implements ProcessCommand{
    private Schedule schedule;
    private int numberOfHalls;
    private int[] rows;
    private int[] seats;
    private String lastFile;

    public CommandProcessor(Schedule schedule, int numberOfHalls, int[] rows, int[] seats) {
        this.schedule = schedule;
        this.numberOfHalls = numberOfHalls;
        this.rows = rows;
        this.seats = seats;
        lastFile = null;
    }

    private void saveAs(String[] params)throws CommandException{
        if(params.length!=2)
            throw new CommandException("Incorrect parameters");
        SaveAs saveAs = new Commands.SaveAs();
        String fileName = params[1].replace("\"", "");
        lastFile = fileName;
        saveAs.saveAs(schedule, fileName);
        System.out.println("file saved");
    }

    private void save(){
        SaveAs saveAs = new Commands.SaveAs();
        if(lastFile != null) {
            saveAs.saveAs(schedule, lastFile);
            System.out.println("file saved");
        }
        else
            System.out.println("No file found!");
    }

    private void close(){
        CloseFile closeFile = new Commands.CloseFile();
        closeFile.closeFile(schedule, numberOfHalls, rows, seats);
        System.out.println("file closed");
    }

    private void open(String[] params)throws CommandException{
        if(params.length!=2)
            throw new CommandException("Incorrect parameters");
        OpenFile openFile = new Commands.OpenFile();
        String fileName = params[1].replace("\"", "");
        lastFile = fileName;
        try {
            openFile.open(schedule, fileName);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        System.out.println("file opened");
    }

    private void addEvent(String[] params)throws CommandException{
        if(params.length!=2)
            throw new CommandException("Incorrect parameters");
        AddEvent addEvent = new Commands.AddEvent();
        String[] params1 = params[1].split(" ", 3);
        if(params1.length!=3)
            throw new CommandException("Incorrect parameters");
        LocalDate date = LocalDate.parse(params1[0]);
        int hall = Integer.parseInt(params1[1]);
        String name = params1[2].replace("\"","");
        try {
            addEvent.addEvent(schedule, date, hall-1 , name);
        } catch (EventsException e) {
            throw new CommandException(e.getMessage());
        }
        System.out.println("event added");
    }

    public void help(){
        System.out.println("The following commands are supported:");
        Help help = new Commands.Help();
        for (String i: help.allCommands()) {
            System.out.println(i);
        }
    }

    @Override
    public void process(String[] command)throws CommandException {
        switch (command[0]){
            case "saveas":
                saveAs(command);
                break;
            case "save":
                save();
                break;
            case "close":
                close();
                break;
            case "open":
                open(command);
                break;
            case "help":
                help();
                break;
            case "addevent":
                try {
                    addEvent(command);
                }catch (DateTimeParseException e){
                    System.out.println("Incorrect date formatting!");
                }
                break;
            default:
                throw new CommandException("Command not recognised!");
        }
    }
}
