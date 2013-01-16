package pobj.algogen.test;

import java.io.IOException;

import pobj.algogen.Individu;
import pobj.algogen.Population;
import pobj.algogen.adapter.agent.LabyEnvironnementAdapter;
import pobj.algogen.adapter.agent.PopulationControleurFactory;
import agent.control.Controleur;
import agent.laby.ChargeurLabyrinthe;
import agent.laby.Labyrinthe;
import junit.framework.TestCase;

public class PopulationTest extends TestCase {
	private LabyEnvironnementAdapter env; 
	private Labyrinthe labyTest;
	private	Population<Controleur> pop;
	
	public PopulationTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		try {
			labyTest = ChargeurLabyrinthe.chargerLabyrinthe("mazes/all_in.mze");
		} catch (IOException e) {
			fail("Could not find test maze !");
		}
		env = new LabyEnvironnementAdapter(labyTest, 10);
		pop = (Population<Controleur>) PopulationControleurFactory.createRandomPopulation(100,20);
		}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSize() {
		assertTrue(pop.size()==100);
	}
/*
 * On vérifie que la taille augmente de 1
 */
	public void testAdd() {
		int size = pop.size();
		Individu<Controleur> individu= (pop.get(0)).clone();
		pop.add(individu);
		assertTrue(pop.size()==size+1);
	}
/*
 * on vérifie que la nouvelle population est différente de la précédente
 */
	public void testEvoluer() {
		Population<Controleur> pop2 = (Population<Controleur>) pop.evoluer(env);
		assertTrue(!pop2.equals(pop));
	}
/*
 * On vérifie que les individus sont bien rangés dans l'ordre
 */
	public void testEvaluer() {
		pop.evaluer(env);
		int max = pop.size();
		for (int i = 0; i < max-1; i++) {
			assertTrue(pop.get(i).getFitness()>=pop.get(i+1).getFitness());
		}
	}

}
