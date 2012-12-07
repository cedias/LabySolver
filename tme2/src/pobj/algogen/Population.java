package pobj.algogen;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Classe de représentation d'une population d'individus
 */
public class Population {

    /** ArrayList des individus, ensemble de la population*/
    private ArrayList<Individu> individus;
     /**
     * Construit une population vide
     */
    public Population() {
    	individus = new ArrayList<Individu>();
    }
    
     /**
     * Retourne la taille de la population
     */
    public int size() {
    	return individus.size();
    }
    
     /**
     * ajoute un individu dans la population
     * @param individu un individu
     */
    public void add (Individu individu) {
    	individus.add(individu);
    }
    
     /**
     * transforme une population en chaine de caractere
     */
    public String toString() {
    	return individus.toString();
    }
    /**
     * @param cible
     */
    public void evaluer(Environnement cible) {
    	for (Individu i : individus) {
    		i.setFitness(cible.eval(i));
    	}
    	Collections.sort(individus);
    	Collections.reverse(individus);
    }
    
    public void reproduire(){
    	boolean mutation = true ;
    	int originalSize = individus.size();
    	int size = (int) (individus.size() * 0.2);
    	while (individus.size() > size){
    		individus.remove(individus.size()-1);
    	}
 
    	for (int i = size ; i < originalSize ; i++) {
    		individus.add(new Individu());
    	}
    	for (int i = 0 ; i < size ; i++) {
    		double taux = Math.random();
    		if (taux < 0.05) {
    			if(!mutation){
    				individus.set(i,individus.get(i).croiser(individus.get(i+1)));
    			} else {
    				individus.set(i,individus.get(i).cloneMutant());
    			}
    		}
    	}
    }
}
