package Commands;

public class Help implements com.company.Help {
    private static final String[] commands = {
            "open <file>\t\t\t\t\t\t\t\topens <file>",
            "close\t\t\t\t\t\t\t\t\tcloses currently opened file",
            "save\t\t\t\t\t\t\t\t\tsaves the currently open file",
            "saveas <file>\t\t\t\t\t\t\tsaves the currently opened file in <file>",
            "help\t\t\t\t\t\t\t\t\tprints this information",
            "exit\t\t\t\t\t\t\t\t\texits the program",
            "addevent <date> <hall> <name>\t\t\tAdds a new event on <date> with name <name> in <hall>",
            "freeseats <date> <name>\t\t\t\t\tLists all the free seats(not booked or bought) for the event <name> on <date>",
            "book <row> <seat> <date> <name> <note>\tBooks a ticket for the show <name> on <date> in seat <row> <seat> and adds a note <note>",
            "unbook <row> <seat> <date> <name>\t\tUnbooks the seat for the show <name> on <date> in row <row> in seat <seat>",
            "buy <row> <seat> <date> <name>\t\t\tBuys the free or booked seat for the show <name> on <date> in row <row> in seat <seat>",
            "bookings [<date>] [<name>]\t\t\t\tShows all the booked days for the show <name> on <date>, if name is skipped then lists for all shows on that day, if both are skipped then lists all booked seats"
    };

    @Override
    public String[] allCommands() {
        return commands;
    }

}
