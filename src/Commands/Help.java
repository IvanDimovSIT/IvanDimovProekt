package Commands;

public class Help implements com.company.Help {
    private static final String[] commands = {
            "open <file>\t\t\t\t\t\topens <file>",
            "close\t\t\t\t\t\t\tcloses currently opened file",
            "save\t\t\t\t\t\t\tsaves the currently open file",
            "saveas <file>\t\t\t\t\tsaves the currently opened file in <file>",
            "help\t\t\t\t\t\t\tprints this information",
            "exit\t\t\t\t\t\t\texits the program",
            "addevent <date> <hall> <name>\tAdds a new event on <date> with name <name> in <hall>",
            "freeseats <date> <name>\t\t\tLists all the free seats(not booked or bought) for the event <name> on <date>"
    };

    @Override
    public String[] allCommands() {
        return commands;
    }

}
