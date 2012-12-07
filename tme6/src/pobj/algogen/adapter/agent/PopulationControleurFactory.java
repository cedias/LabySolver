package pobj.algogen.adapter.agent;

import agent.laby.Labyrinthe;
import pobj.algogen.Environnement;
import pobj.algogen.Population;

/**
 * Classe créatrice de population au hasard
 */
public class PopulationControleurFactory {
	

	/**
     * cree une nouvelle population de size size
     * @param size taille le la population crée
     */
	public static Population createRandomPopulation(int size, int nbRules) {
		Population pop = new Population();
		for (int i = 0; i<size; i++) {
		    pop.add(new ControleurIndividuAdapter(nbRules));
		}
		return pop;
    }

	public static Environnement createEnvironnement(Labyrinthe laby, int nbPas) {
		return new LabyEnvironnementAdapter(laby, nbPas);
	} 
   
}
