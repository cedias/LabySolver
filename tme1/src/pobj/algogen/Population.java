package pobj.algogen;
import java.util.Arrays;
/**
 * Classe de représentation d'une population d'individus
 */
public class Population {
    /** taille max d'une population */
    private static final int POP_SIZE = 20;
    /** tableau des individus, ensemble de la population*/
    private Individu[] individus;
    /** taille de la population */
    private int size = 0 ;
     /**
     * Construit une population vide
     */
    public Population() {
	individus = new Individu[POP_SIZE];
    }
     /**
     * Retourne la taille de la population
     */
    public int size() {
	return size;
    }
     /**
     * ajoute un individu dans la population
     * @param individu un individu
     */
    public void add (Individu individu) {
	if (size < individus.length) {
	    individus[size]= individu;
	    size++;
	} else {
	    throw new ArrayIndexOutOfBoundsException("Plus de place !");
	}
    }
     /**
     * transforme une population en chaine de caractere
     */
    public String toString(){
	return Arrays.toString(individus);
    }
}
