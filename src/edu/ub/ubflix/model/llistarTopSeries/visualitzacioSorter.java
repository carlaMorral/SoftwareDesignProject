package edu.ub.ubflix.model.llistarTopSeries;

import edu.ub.ubflix.model.Visualitzacio;

import java.util.Comparator;
import java.util.Map;

public class visualitzacioSorter implements Comparator<Map.Entry<String, Integer>> {
    public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2){
        //com compararem entrades d'un HashMap, necessitem fer les conversiosn pertinents dels objectes
        //a continuacio, simplement compararem els seus valors
        return o1.getValue().compareTo(o2.getValue());
    }
}
