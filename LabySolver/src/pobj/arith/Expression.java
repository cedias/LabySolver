package pobj.arith;
/**
 * @author Charles-Emmanuel Dias
 * @author Marwan Ghanem
 */
public interface Expression {
	public double eval(EnvEval envEval);
	public Expression simplifier();
}
