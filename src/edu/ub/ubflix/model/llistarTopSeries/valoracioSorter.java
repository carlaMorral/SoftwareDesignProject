package edu.ub.ubflix.model.llistarTopSeries;

import edu.ub.ubflix.model.Valoracio;

import java.util.Comparator;

/*
Per tal de poder llistar el top correctament, ens es necessari poder comparar cada un dels tipus d'element
segons el qual estem usant el top. Per això fem servir el metode Collections.sort, el cual per
"personalitzar-lo" i organitzar segons un mèotde propi, ens obliga a crear classes que implementin l'interficie
comparator. Així doncs, tenim valoracioSorter  i visualitzacioSorter.
Encara que en principi pugui semblar que l'acoblament del programa augmenta, en cas de voler afegir una nova
funció només caldria extendre el programa i no modificar quelcom ja existent.
*/

public class valoracioSorter implements Comparator<Valoracio> {
    public int compare (Valoracio a, Valoracio b){

        int val_a = a.getEstrelles();
        int val_b = b.getEstrelles();

        if (a.getThumb().equals("yes")) val_a = a.getEstrelles()+1;

        if (b.getThumb().equals("yes")) val_b = b.getEstrelles()+1;

        return val_a-val_b;
    }
}
