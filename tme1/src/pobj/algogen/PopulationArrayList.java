package pobj.algogen;
import java.util.ArrayList;
/**
 * Classe de représentation d'une population d'individus
 */
public class PopulationArrayList {

    /** ArrayList des individus, ensemble de la population*/
    private ArrayList<Individu> individus;
     /**
     * Construit une population vide
     */
    public PopulationArrayList() {
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
    public String toString(){
	return individus.toString();
    }
}
