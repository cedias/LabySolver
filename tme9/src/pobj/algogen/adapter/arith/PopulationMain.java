package pobj.algogen.adapter.arith;

import pobj.algogen.Environnement;
import pobj.algogen.Population;
import pobj.arith.Expression;
import pobj.util.Chrono;


/**
 * Classe Main
 * @param size taille de la population
 */
public class PopulationMain {

    public static void main(String[] args) {
    	
    	Chrono chr = new Chrono();
    	Population<Expression> pop = PopulationExpressionFactory.createRandomPopulation(10);
		Environnement<Expression> env = PopulationExpressionFactory.createEnvironnement();
		pop.evaluer(env);
		for (int i = 1 ; i < 1000; i++){
			pop.evoluer(env);
		
		}
		chr.stop();
    }
}