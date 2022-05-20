package Commands;

import com.company.EventsException;
import com.company.Hall;
import com.company.HallsDay;
import com.company.Schedule;

import java.time.LocalDate;
import java.util.*;

public class Worst implements com.company.Worst {
    //клас съответстващ на процента на какупени билети спрямо общия брой места
    private class BoughtPercent{
        private int bought;
        private int total;

        private BoughtPercent(int bought, int total) {
            this.bought = bought;
            this.total = total;
        }

        public int getBought() {
            return bought;
        }

        public int getTotal() {
            return total;
        }

        public double getPercent(){
            return bought*100.0/total;
        }
    }

    private Set<String> worstShows;//имената на най-лошо предсватящите се представления
    private double threshold;

    public Worst(double threshold){
        this.threshold = threshold;
    }

    //намиране на представленията с най-малко закупени билети
    @Override
    public Map<String, Double> getWorst(Schedule schedule, LocalDate from, LocalDate to) {
        //имената на представленията
        worstShows = new HashSet<>();
        //статистика за преставленията
        Map<String, BoughtPercent> statistics = new HashMap<>();
        Map<LocalDate, HallsDay> perDate = schedule.getHalls();
        for (Map.Entry<LocalDate, HallsDay> i: perDate.entrySet()) {
            //определяме дали дадена дата се намира в търсения диапазон
            if(i.getKey().isEqual(from) || i.getKey().isEqual(to) || (i.getKey().isAfter(from) && i.getKey().isBefore(to))){
                //итерираме през залите за дадената дата
                for (Hall h: i.getValue().getHalls()) {
                    if(h == null || h.getShowName() == null)continue;
                    //ако представлението е вече срещано актуализираме статистиката за него
                    if(statistics.containsKey(h.getShowName())){

                        BoughtPercent previous = statistics.get(h.getShowName());
                        statistics.put(h.getShowName(),
                                new BoughtPercent(previous.getBought()+h.getNumberBought(),
                                        previous.getTotal()+h.getMaxRows()*h.getMaxSeats()));

                    }else {
                        //ако не е срещано намираме съотношението закупени места-общ брой места
                        statistics.put(h.getShowName(), new BoughtPercent(h.getNumberBought(), h.getMaxRows()*h.getMaxSeats()));
                    }
                }
            }
        }

        Map<String, Double> result = new HashMap<>();
        for (Map.Entry<String, BoughtPercent> i: statistics.entrySet()) {
            //намираме отношенито закупени места-общ брой места
            double p = i.getValue().getPercent();
            //ако по-малко от минимално допустимото го прибавяме към множеството worstShows
            if(p<threshold){
                result.put(i.getKey(), p);
                worstShows.add(i.getKey());
            }
        }

        return result;
    }

    //премахване на представленията
    @Override
    public void removeWorst(Schedule schedule,  LocalDate from, LocalDate to) {
        if(worstShows == null)
            throw new RuntimeException("Set worstShows not initialised!");
        //итерираме през всички зали за дадения диапазаон от време и ако представлението съответсва на вече
        // намерените (worstShows) се премахва
        for (Map.Entry<LocalDate, HallsDay> i: schedule.getHalls().entrySet()) {
            if(i.getKey().isEqual(from) || i.getKey().isEqual(to) || (i.getKey().isAfter(from) && i.getKey().isBefore(to))){

                HallsDay hallsDay = i.getValue();
                Hall[] halls = hallsDay.getHalls();
                for (int j = 0; j < halls.length; j++) {

                    if(halls[j] != null && worstShows.contains(halls[j].getShowName())) {
                        try {
                            hallsDay.remove(j);
                        } catch (EventsException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
        //премахваме празните дати
        schedule.removeEmpty();
    }
}
