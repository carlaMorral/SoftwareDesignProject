package edu.ub.ubflix.model.llistarTopSeries;

import edu.ub.ubflix.model.Valoracio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class llistarTopValoracions implements llistarTop<Valoracio>{
    @Override
    public List<String> getTop(List<Valoracio> valoracions){
        List<String> res = new ArrayList<>();
        valoracions.sort(new valoracioSorter());
        //no es necessari comrpovar si ja és l'element perque només tenim una única valoracio per serie.
        for(Valoracio v : valoracions){
            if(res.size()<10)res.add(v.getIdSerie());

        }
        if(res.isEmpty()) res.add("No hi ha cap serie");
        return res;
    }
}
