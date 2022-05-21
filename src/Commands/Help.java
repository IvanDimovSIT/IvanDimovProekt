package Commands;

public class Help implements com.company.Help {
    private static final String[] commands = {
            "open <file>\tOpens <file>",
            "close\tCloses currently opened file",
            "save\tSaves the currently open file",
            "saveas <file>\tSaves the currently opened file in <file>",
            "help\tPrints this information",
            "exit\tExits the program",
            "addevent <date> <hall> <name>\tAdds a new event on <date> with name <name> in <hall>",
            "freeseats <date> <name>\tLists all the free seats(not booked or bought) for the event <name> on <date>",
            "book <row> <seat> <date> <name> <note>\tBooks a ticket for the show <name> on <date> in seat <row> <seat> and adds a note <note>",
            "unbook <row> <seat> <date> <name>\tUnbooks the seat for the show <name> on <date> in row <row> in seat <seat>",
            "buy <row> <seat> <date> <name>\tBuys the free or booked seat for the show <name> on <date> in row <row> in seat <seat>",
            "bookings [<date>] [<name>]\tShows all the booked days for the show <name> on <date>, if name is skipped then lists for all shows on that day, if both are skipped then lists all booked seats",
            "check <code>\tChecks if thicket code <code> is valid and displays the seat number",
            "report <from> <to> [<hall>]\tShows all the bought tickets form date <from> to date <to> in hall <hall>, if <hall> is skipped then the sales for all halls are shown",
            "top [<number>]\tShow the top <number> most watched shows, if <number> is skipped then shows all shows>",
            "worst <from> <to>\tShows the events with less than 10% sales in the period between <from> and <to>, then asks if they should be removed"
    };
    //връшаме имената на командите, техните параметри и функцията им
    @Override
    public String[] allCommands() {
        return commands;
    }

}

