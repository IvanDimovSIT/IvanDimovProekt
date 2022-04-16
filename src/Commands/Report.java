package Commands;

import com.company.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Report implements com.company.Report {

    private int countBought(Hall hall){
        int count = 0;
        for (int row=0; row<hall.getMaxRows(); row++){
            for (int seat=0; seat<hall.getMaxSeats(); seat++) {
                try {
                    if(hall.getSeat(row, seat).getSeatState() == SeatState.Bought)
                        count++;
                } catch (SeatException e) {
                    e.printStackTrace();
                }
            }
        }
        return count;
    }

    @Override
    public Map<String, Integer> reportSales(Schedule schedule, LocalDate from, LocalDate to, Integer hallNumber) throws CommandException {
        Map<String, Integer> sales = new HashMap<>();
        for (Map.Entry<LocalDate, HallsDay> i: schedule.getHalls().entrySet()) {
            LocalDate date = i.getKey();

            if(date.isEqual(from) || date.isEqual(to) || (date.isAfter(from) && date.isBefore(to))){
                Hall[] halls = i.getValue().getHalls();
                if(hallNumber != null && halls.length<=hallNumber)
                    throw new CommandException("Hall #"+(hallNumber+1)+" doesn't exist!");

                for (int hall=0; hall<halls.length; hall++) {

                    if(halls[hall].getShowName() == null) continue;
                    else if(hallNumber == null || hallNumber == hall){

                        if(sales.containsKey(halls[hall].getShowName()))
                            sales.put(halls[hall].getShowName(),sales.get(halls[hall].getShowName())+countBought(halls[hall]));
                        else
                            sales.put(halls[hall].getShowName(), countBought(halls[hall]));
                    }
                }
            }
        }
        return sales;
    }
}
