package pobj.algogen.adapter.arith;
import pobj.algogen.AbstractIndividu;
import pobj.algogen.Individu;
import pobj.arith.Expression;
import pobj.arith.ExpressionFactory;
import pobj.arith.OperateurBinaire;
import pobj.arith.Operator;
/**
 * Classe de représentation d'un individu
 */
public class IndividuExpression extends AbstractIndividu<Expression>{
    /** valeur propre d'un individu */ 
    private final Expression valeurPropre;
    private int tailleExpression = 3 ;
    /**
     * cree un nouvel individu au hasard
     */
    public IndividuExpression() {
    	super();
		valeurPropre = ExpressionFactory.createRandomExpression(tailleExpression);
    }
     /**
     * cree un nouvel individu avec de l'expression exp
     * @param exp expression de l'individu
     */
    public IndividuExpression(Expression exp) {
    	super();
    	valeurPropre = exp;
    }
     /* (non-Javadoc)
	 * @see pobj.algogen.I_Individu#toString()
	 */
    @Override
	public String toString() {
    	return "Valeur propre : " + valeurPropre + "\n Fitness :" + getFitness();
    }
	/* (non-Javadoc)
	 * @see pobj.algogen.I_Individu#getValeurPropre()
	 */
	public Expression getValeurPropre() {
		return valeurPropre;
	}

	/* (non-Javadoc)
	 * @see pobj.algogen.I_Individu#muter()
	 */
	@Override
	public IndividuExpression muter() {
		return new IndividuExpression(ExpressionFactory.createRandomExpression(tailleExpression));
	}
	
	/* (non-Javadoc)
	 * @see pobj.algogen.I_Individu#croiser(pobj.algogen.Individu)
	 */
	@Override
	public IndividuExpression croiser(Individu<Expression> autre){
		if(autre instanceof IndividuExpression){
			IndividuExpression pote = (IndividuExpression) autre;
			if(!(valeurPropre instanceof OperateurBinaire) && !valeurPropre.equals(pote.valeurPropre))
				return new IndividuExpression(ExpressionFactory.createOperateurBinaire(Operator.DIV, ExpressionFactory.createOperateurBinaire(Operator.PLUS, valeurPropre, pote.valeurPropre), ExpressionFactory.createConstante(2)));
			else
				return new IndividuExpression(valeurPropre);
		} else {
			System.out.println("incroisable (exception?!)");
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see pobj.algogen.I_Individu#cloner()
	 */
	@Override
	public IndividuExpression clone(){
		IndividuExpression ind = new IndividuExpression(valeurPropre);
		ind.setFitness(getFitness());
		return ind;
	}
}
