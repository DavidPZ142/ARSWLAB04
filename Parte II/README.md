## Escuela Colombiana de Ingeniería

## Arquitecturas de Software

# Componentes y conectores - Parte I.

El ejercicio se debe traer terminado para el siguiente laboratorio (Parte II).

#### Middleware- gestión de planos.


## Antes de hacer este ejercicio, realice [el ejercicio introductorio al manejo de Spring y la configuración basada en anotaciones](https://github.com/ARSW-ECI/Spring_LightweightCont_Annotation-DI_Example).

En este ejercicio se va a construír un modelo de clases para la capa lógica de una aplicación que permita gestionar planos arquitectónicos de una prestigiosa compañia de diseño. 

![](img/ClassDiagram1.png)

1. Configure la aplicación para que funcione bajo un esquema de inyección de dependencias, tal como se muestra en el diagrama anterior.


	Lo anterior requiere:

	* Agregar las dependencias de Spring.
	
	Se agregan dependencias en pom.xml
	* Agregar la configuración de Spring.

	Se agregan los archivos applicationContext.xml y nb-configuration.xml
	
	
	* Configurar la aplicación -mediante anotaciones- para que el esquema de persistencia sea inyectado al momento de ser creado el bean 'BlueprintServices'.


![](https://github.com/DavidPZ666/ARSWLAB04/blob/main/Parte%20II/img/Punto1Parte3.png)
![](https://github.com/DavidPZ666/ARSWLAB04/blob/main/Parte%20II/img/Punto1Parte3.png)


2. Complete los operaciones getBluePrint() y getBlueprintsByAuthor(). Implemente todo lo requerido de las capas inferiores (por ahora, el esquema de persistencia disponible 'InMemoryBlueprintPersistence') agregando las pruebas correspondientes en 'InMemoryPersistenceTest'.

![](https://github.com/DavidPZ666/ARSWLAB04/blob/main/Parte%20II/img/Punto2%201.png)
![](https://github.com/DavidPZ666/ARSWLAB04/blob/main/Parte%20II/img/Punto2%202.png)

3. Haga un programa en el que cree (mediante Spring) una instancia de BlueprintServices, y rectifique la funcionalidad del mismo: registrar planos, consultar planos, registrar planos específicos, etc.

![](https://github.com/DavidPZ666/ARSWLAB04/blob/main/Parte%20II/img/Parte%203.png)
![](https://github.com/DavidPZ666/ARSWLAB04/blob/main/Parte%20II/img/Parte%2032.png)

4. Se quiere que las operaciones de consulta de planos realicen un proceso de filtrado, antes de retornar los planos consultados. Dichos filtros lo que buscan es reducir el tamaño de los planos, removiendo datos redundantes o simplemente submuestrando, antes de retornarlos. Ajuste la aplicación (agregando las abstracciones e implementaciones que considere) para que a la clase BlueprintServices se le inyecte uno de dos posibles 'filtros' (o eventuales futuros filtros). No se contempla el uso de más de uno a la vez:

```
public interface Filter  {

    Blueprint filter (Blueprint blueprint);
} 
```

	* (A) Filtrado de redundancias: suprime del plano los puntos consecutivos que sean repetidos.
	
	
	```
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
	```
	
	
	* (B) Filtrado de submuestreo: suprime 1 de cada 2 puntos del plano, de manera intercalada.
	
	```
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
	
	```

5. Agrege las pruebas correspondientes a cada uno de estos filtros, y pruebe su funcionamiento en el programa de prueba, comprobando que sólo cambiando la posición de las anotaciones -sin cambiar nada más-, el programa retorne los planos filtrados de la manera (A) o de la manera (B). 
