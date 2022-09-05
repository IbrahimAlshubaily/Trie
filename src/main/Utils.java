package main;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {
    static HashMap<String, Integer> selectTopK(HashMap<String, Integer> map, int k) {
        return (HashMap<String, Integer>)
                map.entrySet().stream().
                //Sort by wordCount in descending order
                sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
                limit(k).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
