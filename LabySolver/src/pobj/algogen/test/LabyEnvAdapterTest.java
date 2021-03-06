/**
 * 
 */
package pobj.algogen.test;

import java.io.IOException;

import agent.control.Controleur;
import agent.laby.ChargeurLabyrinthe;
import agent.laby.Labyrinthe;
import pobj.algogen.Population;
import pobj.algogen.adapter.agent.PopulationControleurFactory;
import pobj.algogen.adapter.agent.ControleurIndividuAdapter;
import pobj.algogen.adapter.agent.LabyEnvironnementAdapter;
import junit.framework.TestCase;

/**
 * @author sigaud
 *
 */
public class LabyEnvAdapterTest extends TestCase {
	private LabyEnvironnementAdapter env; 
	private Labyrinthe labyTest;
	private	Population<Controleur> pop;
	/**
	 * @param arg0
	 */
	public LabyEnvAdapterTest(String arg0) {
		super(arg0);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		try {
			labyTest = ChargeurLabyrinthe.chargerLabyrinthe("mazes/all_in.mze");
		} catch (IOException e) {
			e.printStackTrace();
			fail("Could not find test maze !");
		}
		env = new LabyEnvironnementAdapter(labyTest, 10);
		pop = PopulationControleurFactory.createRandomPopulation(100,20);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link pobj.algogen.adapter.agent.LabyEnvironnementAdapter#eval(pobj.algogen.Individu)}.
	 */
	public void testEval() {
		ControleurIndividuAdapter indiv = (ControleurIndividuAdapter) pop.get(0);
		double retour = env.eval(indiv);
		assert(retour >0);
	}

}
