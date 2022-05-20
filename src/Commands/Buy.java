package Commands;

import Commands.TicketCodes.CodeGenerator;
import com.company.*;

import java.time.LocalDate;

public class Buy implements com.company.Buy {
    //закупуване на билет
    //връща кода на билета
    @Override
    public String buy(Schedule schedule, int row, int seat, LocalDate date, String name) throws CommandException {
        //определяме валидността на данните и намираме мястото
        HallsDay hallsDay = schedule.getHallsForDay(date);
        if(hallsDay == null)throw new CommandException("No events on that date");
        Hall hall = null;
        int hallNum=-1;
        Hall[] halls = hallsDay.getHalls();

        for (int i = 0; i < halls.length; i++) {
            if(halls[i] != null && halls[i].getShowName() != null && halls[i].getShowName().equals(name)){
                hall = halls[i];
                hallNum = i;
            }
        }

        if(hall == null)throw new CommandException("show \""+name+"\" not found!");
        Seat seat1;
        try {
            seat1 = hall.getSeat(row, seat);
        } catch (SeatException e) {
            throw new CommandException("This seat doesn't exit!");
        }
        if(seat1.getSeatState() == SeatState.Bought)
            throw new CommandException("Seat's already bought!");
        //премахваме бележката
        seat1.setNote(null);
        hall.setNumberBought(hall.getNumberBought() + 1);
        seat1.setSeatState(SeatState.Bought);
        //определяме кода
        GenerateCode generateCode = new CodeGenerator();
        return generateCode.getCode(seat1, hallNum, date);
    }
}
