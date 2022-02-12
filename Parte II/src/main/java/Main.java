import edu.eci.arsw.blueprints.model.*;
import edu.eci.arsw.blueprints.persistence.*;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Main {
    @Autowired
    static BlueprintsServices blueprintsServices;

    public static void main(String[] args) throws BlueprintPersistenceException, BlueprintNotFoundException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        blueprintsServices = ac.getBean(BlueprintsServices.class);

        Point[] pts=new Point[]{new Point(7, 10),new Point(3, 4),new Point(5, 6),new Point(7, 10),new Point(7, 10)};
        Blueprint a = new Blueprint("David","Extreme",pts);
        blueprintsServices.addNewBlueprint(a);
        
        blueprintsServices.addNewBlueprint(new Blueprint("David", "Monalisa",pts));
        System.out.println(blueprintsServices.getBlueprint("David","Monalisa"));
        System.out.println(blueprintsServices.getBlueprintsByAuthor("David"));







    }
}
