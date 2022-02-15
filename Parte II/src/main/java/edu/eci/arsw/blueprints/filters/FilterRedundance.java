package edu.eci.arsw.blueprints.filters;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import edu.eci.arsw.blueprints.filters.Filter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

@Service
@Qualifier("Redundace")

    /**
     * Metodo encargado de filtrar la lista de forma redundante
     * @return la lista filtrada redudantemente
     */
public class FilterRedundance implements Filter {

    public Blueprint filter(Blueprint bp) {

        List<Point> list= bp.getPoints();
        List<Point> newList = new ArrayList<>();
        for(int i=0;i<list.size()-1;i++) {

            Point p1 = list.get(i);
            int cont=0;
            for(int j=i+1;j<list.size();j++) {
                Point p2 = list.get(j);
                boolean  cond1= p1.getX()==p2.getX();
                boolean  cond2= p1.getY()==p2.getY();

                if(!(cond1 && cond2)) {
                    if(cont==0) {
                        newList.add(p1);
                    }else {
                        i=j-1;
                    }

                    if(j==list.size()-1) {
                        newList.add(p2);
                    }

                    j=list.size();

                }else {
                    cont+=1;
                }

            }

        }
        bp.setPoints(newList);
        return bp;
    }
}
