package pobj.algogen;
/**
 * Classe Main
 * @param size taille de la population
 */
public class PopulationMain {
    public static void main(String[] args) {
	Population pop = PopulationFactory.createRandomPopulation(Integer.parseInt(args[0]));
	System.out.println("-------------original gen----------");
	System.out.println(pop.toString());
	ValeurCible env = new ValeurCible(0.2);
	pop.evaluer(env);
	for (int i = 0 ; i < 15 ; i++){
		System.out.println("------------- gen " + i + "----------");
		pop.reproduire();
		System.out.println(pop.toString());
		}
    }
    
}