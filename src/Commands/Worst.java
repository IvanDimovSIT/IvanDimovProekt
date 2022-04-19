package Commands;

import com.company.Hall;
import com.company.HallsDay;
import com.company.Schedule;

import java.time.LocalDate;
import java.util.*;

public class Worst implements com.company.Worst {
    private class BoughtPercent{
        private int bought;
        private int total;

        public BoughtPercent(int bought, int total) {
            this.bought = bought;
            this.total = total;
        }

        public int getBought() {
            return bought;
        }

        public void setBought(int bought) {
            this.bought = bought;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public double getPercent(){
            return bought*100.0/total;
        }
    }

    private Set<String> worstShows;
    private double threshold;

    public Worst(double threshold){
        this.threshold = threshold;
    }

    @Override
    public Map<String, Double> getWorst(Schedule schedule, LocalDate from, LocalDate to) {
        worstShows = new HashSet<>();
        Map<String, BoughtPercent> statistics = new HashMap<>();
        Map<LocalDate, HallsDay> perDate = schedule.getHalls();
        for (Map.Entry<LocalDate, HallsDay> i: perDate.entrySet()) {
            if(i.getKey().isEqual(from) || i.getKey().isEqual(to) || (i.getKey().isAfter(from) && i.getKey().isBefore(to))){
                for (Hall h: i.getValue().getHalls()) {
                    if(h.getShowName() == null)continue;
                    if(statistics.containsKey(h.getShowName())){
                        BoughtPercent previous = statistics.get(h.getShowName());
                        statistics.put(h.getShowName(),
                                new BoughtPercent(previous.getBought()+h.getNumberBought(),
                                        previous.getTotal()+h.getMaxRows()*h.getMaxSeats()));

                    }else {
                        statistics.put(h.getShowName(), new BoughtPercent(h.getNumberBought(), h.getMaxRows()*h.getMaxSeats()));
                    }
                }
            }
        }

        Map<String, Double> result = new HashMap<>();
        for (Map.Entry<String, BoughtPercent> i: statistics.entrySet()) {
            double p = i.getValue().getPercent();
            if(p<threshold){
                result.put(i.getKey(), p);
                worstShows.add(i.getKey());
            }
        }

        return result;
    }

    @Override
    public void removeWorst(Schedule schedule,  LocalDate from, LocalDate to) {
        if(worstShows == null)
            throw new RuntimeException("Set worstShows not initialised!");
        for (Map.Entry<LocalDate, HallsDay> i: schedule.getHalls().entrySet()) {
            if(i.getKey().isEqual(from) || i.getKey().isEqual(to) || (i.getKey().isAfter(from) && i.getKey().isBefore(to))){
                for (Hall h: i.getValue().getHalls()) {
                    if(worstShows.contains(h.getShowName()))
                        h.setShowName(null);
                }
            }
        }
        schedule.removeEmpty();
    }
}
