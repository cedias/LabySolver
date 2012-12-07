package pobj.algogen;
import pobj.arith.Expression;
import pobj.arith.ExpressionFactory;
import pobj.arith.OperateurBinaire;
import pobj.arith.Operator;
/**
 * Classe de représentation d'un individu
 */
public class Individu implements Comparable<Individu>{
    /** valeur propre d'un individu */ 
    private final Expression valeurPropre;
    private double fitness;
    private int tailleExpression = 5 ;
    /**
     * cree un nouvel individu au hasard
     */
    public Individu() {
		valeurPropre = ExpressionFactory.createRandomExpression(tailleExpression);
		fitness = 0;
    }
     /**
     * cree un nouvel individu avec de l'expression exp
     * @param exp expression de l'individu
     */
    public Individu(Expression exp) {
    	valeurPropre = exp;
    }
     /**
     * transforme un individu en chaine de caractere
     */
    public String toString() {
    	return "Valeur propre : " + valeurPropre + "\n Fitness :" + fitness;
    }
    /**
     * changer la fitness 
     * @param i nouvelle fitness
     */
    public void setFitness(double i) {
    	fitness = i;
    }
	public Expression getValeurPropre() {
		return valeurPropre;
	}
    
	public int compareTo(Individu o) {
		return Double.compare(fitness, o.fitness);
	}

	public Individu muter() {
		return new Individu(ExpressionFactory.createRandomExpression(tailleExpression));
	}
	
	public Individu croiser(Individu autre){
		if(!(valeurPropre instanceof OperateurBinaire) && !valeurPropre.equals(autre.valeurPropre))
			return new Individu(ExpressionFactory.createOperateurBinaire(Operator.DIV, ExpressionFactory.createOperateurBinaire(Operator.PLUS, valeurPropre, autre.valeurPropre), ExpressionFactory.createConstante(2)));
		else
			return new Individu(valeurPropre);
	}
	
	public Individu cloner(){
		Individu ind = new Individu(valeurPropre);
		ind.setFitness(fitness);
		return ind;
	}
}
