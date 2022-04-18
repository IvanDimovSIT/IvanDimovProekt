package com.company;

import java.util.List;
import java.util.Map;

public interface Top {
    List<Map.Entry<String, Integer>> topShows(Schedule schedule, Integer topNumber);

}
