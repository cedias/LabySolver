package pobj.algogen;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import pobj.algogen.adapter.agent.ControleurIndividuAdapter;
/**
 * Classe de représentation d'une population d'individus
 */
public class Population implements Iterable<Individu> {

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
     * change un individu dans la population
     * @param i l'individu a changer
     */
    private void set(int i, Individu ind) {
    	individus.set(i,ind);
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
    
    
    public Population reproduire() {
    	boolean mutation = true ;
    	int nbExs = (int) (individus.size() * 0.2);
    	Random rand = new Random();
    	Population newPop = new Population();
    	
    	for (int i=0; i<nbExs; i++) {
    		Individu chosenOne = individus.get(i);
    		newPop.add(chosenOne.clone());
    	}
    		
    	for (int i = nbExs ; i < individus.size() ; i++) {
    		Individu alea1 = individus.get(rand.nextInt((int)nbExs));
    		Individu alea2 = individus.get(rand.nextInt((int)nbExs));
    		newPop.add(alea1.croiser(alea2));
    		
    	}
    	
    	for (int i = nbExs ; i < individus.size() ; i++) {
    		double taux = Math.random();
    		if (taux < 0.05) {
    			if(!mutation){
    				try{
    					newPop.set(i,individus.get(i).croiser(individus.get(i+1)));
    				}catch(IndexOutOfBoundsException e){
    					newPop.set(i,individus.get(i).croiser(individus.get(i-1)));
    				}
    				mutation = true;
    			} else {
    				newPop.set(i,individus.get(i).muter());
    				mutation = false;
    			}
    		}
    	}
    	return newPop;
    }

	public Population evoluer(Environnement cible) {
		Population newGen = reproduire();
    	newGen.evaluer(cible);
    	return newGen;
    }

	public Iterator<Individu> iterator() {
		return individus.iterator();
	}

	public Individu get(int i) {
		return individus.get(i);
	}
}
