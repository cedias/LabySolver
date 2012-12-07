package pobj.algogen.adapter.arith;

import pobj.algogen.Environnement;
import pobj.algogen.Population;
import pobj.util.Chrono;


/**
 * Classe Main
 * @param size taille de la population
 */
public class PopulationMain {

public static void main(String[] args) {
    	
    	Chrono chr = new Chrono();
    	Population pop = PopulationExpressionFactory.createRandomPopulation(10);
		Environnement env = PopulationExpressionFactory.createEnvironnement();
		pop.evaluer(env);
		for (int i = 1 ; i < 1000; i	++){
			pop.evoluer(env);
		
		}
		chr.stop();
    }
/*
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
    */
}