package Commands;

import com.company.Hall;
import com.company.HallsDay;
import com.company.Schedule;

import java.util.*;

public class Top implements com.company.Top {

    private class EntryComparator implements Comparator<Map.Entry<String, Integer>>{
        @Override
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            return o2.getValue()-o1.getValue();
        }
    }

    //намиране на представленията сортирани по брой закупени билети
    @Override
    public List<Map.Entry<String, Integer>> topShows(Schedule schedule, Integer topNumber) {
        //Map съответстващ на име на представлението и брой закупени места
        Map<String, Integer> statistics = new HashMap<>();

        //итерираме през всички зали за всички дати
        for (HallsDay hd: schedule.getHalls().values()) {
            for (Hall h: hd.getHalls()) {
                if(h == null || h.getShowName() == null)continue;
                //намираме броя на закупените места
                if(statistics.containsKey(h.getShowName())){
                    statistics.put(h.getShowName(), statistics.get(h.getShowName()) + h.getNumberBought());
                }else{
                    statistics.put(h.getShowName(), h.getNumberBought());
                }
            }
        }

        //преобразуваме Map в List за да можем да сортираме
        List<Map.Entry<String, Integer>> result = new ArrayList<>(statistics.entrySet());
        Collections.sort(result, new EntryComparator());
        if(topNumber == null || result.size() <= topNumber)
            return result;
        else {
            //отделяме определен брой от най-гледаните представления
            List<Map.Entry<String, Integer>> finalResult = new ArrayList<>();
            for (int i = 0; i < topNumber; i++)
                finalResult.add(result.get(i));
            return finalResult;
        }
    }
}
