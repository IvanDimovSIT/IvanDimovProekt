package Commands;

import com.company.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Report implements com.company.Report {
    //намиране продажбите за представленията по начална дата, крайна дата и номер на зала
    @Override
    public Map<String, Integer> reportSales(Schedule schedule, LocalDate from, LocalDate to, Integer hallNumber) throws CommandException {
        Map<String, Integer> sales = new HashMap<>();

        //итерираме през всички дати
        for (Map.Entry<LocalDate, HallsDay> i: schedule.getHalls().entrySet()) {
            LocalDate date = i.getKey();
            //определяме дали дадена дата се намира в търсения диапазон
            if(date.isEqual(from) || date.isEqual(to) || (date.isAfter(from) && date.isBefore(to))){
                Hall[] halls = i.getValue().getHalls();
                if(hallNumber != null && halls.length<=hallNumber)
                    throw new CommandException("Hall #"+(hallNumber+1)+" doesn't exist!");
                //итерираме през залите
                for (int hall=0; hall<halls.length; hall++) {

                    if(halls[hall] == null || halls[hall].getShowName() == null) continue;
                    else if(hallNumber == null || hallNumber == hall){
                        //ако вече е срещано даденото представление, прибавяме към старата стойност броя закупени места
                        if(sales.containsKey(halls[hall].getShowName()))
                            sales.put(halls[hall].getShowName(),sales.get(halls[hall].getShowName())+ halls[hall].getNumberBought());
                        else
                            sales.put(halls[hall].getShowName(), halls[hall].getNumberBought());
                    }
                }
            }
        }
        return sales;
    }
}
