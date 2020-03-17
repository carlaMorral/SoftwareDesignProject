package edu.ub.ubflix.model.llistarTopSeries;

import edu.ub.ubflix.model.Visualitzacio;

import java.util.*;

public class llistarTopVisualitzacions implements llistarTop<Visualitzacio> {

    public List<String> getTop(List<Visualitzacio> visualitzacions){
        List<String> res = new ArrayList<>();
        HashMap<String,Integer> numVis = new HashMap<>();
        for(Visualitzacio v : visualitzacions){
            //com a molt llistarem el top 10
            //com d'una serie podem tenir diverses visualitzacions, si ja es present, no la afegirem
            if(v.getEstat().equals("Watched")){
                if(!(numVis.containsKey(v.getIdSerie()))) numVis.put(v.getIdSerie(),1);
                else {
                    int newValue = numVis.get(v.getIdSerie());
                    newValue++;
                    numVis.replace(v.getIdSerie(),newValue);
                }

            }
        }
        //retornem les entrades (parells String-integer) del HashMap en una llista d'entrades String-Integer
        LinkedList<Map.Entry<String,Integer>> entrades = new LinkedList<Map.Entry<String, Integer>>(numVis.entrySet());
        //els ordenem a partir dels seus valors
        entrades.sort(new visualitzacioSorter());
        for (Map.Entry<String, Integer> entry : entrades) {
            //i afegim els valors a res
            if (res.size() < 10) res.add(entry.getKey());
        }
        if(res.isEmpty()) res.add("No hi ha cap serie");
        return res;
    }
}
