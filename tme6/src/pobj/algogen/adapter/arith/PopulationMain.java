package pobj.algogen.adapter.arith;

import pobj.algogen.Environnement;
import pobj.algogen.Population;


/**
 * Classe Main
 * @param size taille de la population
 */
public class PopulationMain {

    public static void main(String[] args) {
    	
    	Population pop = PopulationExpressionFactory.createRandomPopulation(Integer.parseInt(args[0]));
		Environnement env = PopulationExpressionFactory.createEnvironnement();
		pop.evaluer(env);
		System.out.println("-------------original gen----------");
		System.out.println(pop.toString());
		for (int i = 1 ; i < 10; i++){
			System.out.println("------------- gen " + i + "----------");
			pop.evoluer(env);
			System.out.println(pop.toString());
			}
	    }
    
}