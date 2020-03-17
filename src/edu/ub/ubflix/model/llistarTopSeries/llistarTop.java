package edu.ub.ubflix.model.llistarTopSeries;

import java.util.Comparator;
import java.util.List;
/*Per llistar el top de series usem aquest patro strategy, determinat per aquesta interficie. Usem un template
* per tal que si llistem per valoracions, es passi la llista de valoracions de l'usuari, i anàlogament per
* visualitzacions. getTop simplement es el metode que ens retorna el top*/
public interface llistarTop <T> {
    public List<String> getTop(List<T> things);
}
