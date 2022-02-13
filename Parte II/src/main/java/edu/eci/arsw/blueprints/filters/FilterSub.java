package edu.eci.arsw.blueprints.filters;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
@Qualifier("Sub")
public class FilterSub implements Filter {
    @Override
    /**
     * Metodo encargado de filtrar la lista
     * @return la lista filtrada
     */
    public Blueprint filter(Blueprint blueprint) {
        List<Point> pointList = blueprint.getPoints();
        List<Point> newList = new ArrayList<Point>();
        for(int i =0; i< pointList.size() ; i++){
            if(i% 2 == 0){
                newList.add(pointList.get(i));

            }
        }
        blueprint.setPoints(newList);
        return blueprint;
    }
}
