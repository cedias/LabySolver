package agent.test;

import agent.Simulation;
import agent.control.ControlFactory;
import agent.control.Controleur;
import agent.laby.ChargeurLabyrinthe;
import agent.laby.Labyrinthe;
import junit.framework.TestCase;

public class SimulationTest extends TestCase {
	/*
	 * set up prepares the testing
	 * @param simuTest
	 */
	Simulation simuTest;
	protected void setUp() throws Exception {
		super.setUp();
		Labyrinthe laby = ChargeurLabyrinthe.chargerLabyrinthe("goal.mze");
		Controleur c = (Controleur) ControlFactory.createControleur(20);
		simuTest = new Simulation(laby,c);
	}

	public void testSimulation() {
		System.out.println(simuTest.getScore());
		fail("Not yet implemented");
	}

	public void testMesurePerf() {
		fail("Not yet implemented");
	}

}
