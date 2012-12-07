package pobj.algogen;
/**
 * Classe créatrice de population au hasard
 */
public class PopulationFactoryTableau {
     /**
     * cree une nouvelle population de size size
     * @param size taille le la population crée
     */
    public static PopulationTableau createRandomPopulation(int size) {
		PopulationTableau pop = new PopulationTableau();
		for (int i = 0; i<size; i++) {
		    pop.add(new Individu());
		}
		return pop;
    } 
}
