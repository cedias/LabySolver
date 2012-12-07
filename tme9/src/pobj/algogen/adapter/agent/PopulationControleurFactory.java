package pobj.algogen.adapter.agent;

import agent.control.Controleur;
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
	public static Population<Controleur> createRandomPopulation(int size, int nbRules) {
		Population<Controleur> pop = new Population<Controleur>();
		for (int i = 0; i<size; i++) {
		    pop.add(new ControleurIndividuAdapter(nbRules));
		}
		return pop;
    }

	public static Environnement<Controleur> createEnvironnement(Labyrinthe laby, int nbPas) {
		return new LabyEnvironnementAdapter(laby, nbPas);
	} 
   
}
