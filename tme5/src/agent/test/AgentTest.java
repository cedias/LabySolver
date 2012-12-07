package agent.test;

import java.io.IOException;

import agent.Agent;
import agent.Simulation;
import agent.control.ControlFactory;
import agent.control.Controleur;
import agent.laby.ChargeurLabyrinthe;
import agent.laby.Labyrinthe;
import junit.framework.TestCase;

public class AgentTest extends TestCase {
	
	Simulation simuTest;
	
	protected void setUp() throws Exception
	{
		super.setUp();
		Labyrinthe laby = ChargeurLabyrinthe.chargerLabyrinthe("testright3pts.mze");
		Controleur c = (Controleur) ControlFactory.createControleurDroitier();
		simuTest = new Simulation(laby,c);
	}

	public void testAgent()
	{
		System.out.println("the score is " + simuTest.getScore());
	}
	
	public void testMesurePerf()
	{
		assertTrue(simuTest.mesurePerf(20)==3);
	}
	
	public void testEightTest() throws IOException
	{
		simuTest = new Simulation(ChargeurLabyrinthe.chargerLabyrinthe("14test.mze"),ControlFactory.createControleurSmart());
		simuTest.mesurePerf(20);
		System.out.println("the score is " + simuTest.getScore());
	}

}
