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

    @Override
    public List<Map.Entry<String, Integer>> topShows(Schedule schedule, Integer topNumber) {
        Map<String, Integer> statistics = new HashMap<>();

        for (HallsDay hd: schedule.getHalls().values()) {
            for (Hall h: hd.getHalls()) {
                if(h.getShowName() == null)continue;
                if(statistics.containsKey(h.getShowName())){
                    statistics.put(h.getShowName(), statistics.get(h.getShowName()) + h.getNumberBought());
                }else{
                    statistics.put(h.getShowName(), h.getNumberBought());
                }
            }
        }

        List<Map.Entry<String, Integer>> result = new ArrayList<>(statistics.entrySet());
        Collections.sort(result, new EntryComparator());
        if(topNumber == null || result.size() <= topNumber)
            return result;
        else {
            List<Map.Entry<String, Integer>> finalResult = new ArrayList<>();
            for (int i = 0; i < topNumber; i++)
                finalResult.add(result.get(i));
            return finalResult;
        }
    }
}
