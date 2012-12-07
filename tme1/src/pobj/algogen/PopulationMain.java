package pobj.algogen;
/**
 * Classe Main
 * @param size taille de la population
 */
public class PopulationMain {
    public static void main(String[] args) {
	Population pop = PopulationFactory.createRandomPopulation(Integer.parseInt(args[0]));
	System.out.println(pop.toString());
    } 
}