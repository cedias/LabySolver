package pobj.algogen;
/**
 * Classe créatrice de population au hasard
 */
public class PopulationFactory {
     /**
     * cree une nouvelle population de size size
     * @param size taille le la population crée
     */
    public static Population createRandomPopulation(int size) {
		Population pop = new Population();
		for (int i = 0; i<size; i++) {
		    pop.add(new Individu());
		}
		return pop;
    } 
}
