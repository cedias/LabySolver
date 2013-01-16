
package agent.laby.interf;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import pobj.algogen.Environnement;
import pobj.algogen.Population;
import pobj.algogen.adapter.agent.ControleurIndividuAdapter;
import pobj.algogen.adapter.agent.PopulationControleurFactory;

import agent.Simulation;
import agent.control.Controleur;
import agent.laby.Labyrinthe;

public class LabyViewer extends JFrame {

	private static final long serialVersionUID = 1L;
	private LabyActivePanel centerPanel;
	private JPanel southPanel;
	private ConfigGenPanel northPanel;
	private Labyrinthe laby;

	private ControleurIndividuAdapter controler;
	private int nbSteps;
	private int nbIndividus;
	private int nbGen;
	private int nbRules;

	
	
	
	public LabyViewer(Labyrinthe laby)
	{
		super("LabyViewer");
		
		this.laby = laby;

		
		createCenterPanel();
		createNorthMenu();
		createSidePanel();
		
		setSize(800, 800);
		setResizable(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void createNorthMenu() {
		northPanel = new ConfigGenPanel(laby.clone());
		getContentPane().add(northPanel, BorderLayout.NORTH);
	}

	private void createCenterPanel()
	{	
		centerPanel = new LabyActivePanel(laby);
		getContentPane().add(centerPanel, BorderLayout.CENTER);
	}
	
	
	private void createSidePanel()
	{
		southPanel = new JPanel();
		JButton play = new JButton("play");
		play.setPreferredSize(new Dimension(700,100));
		southPanel.add(play);
		
		
		
		play.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				updateAttributes(); //get new attributes
				runSimulation(); //plays
			}
		});
		
		
		getContentPane().add(southPanel, BorderLayout.SOUTH);
	}
	
	private void updateAttributes(){
		controler = northPanel.getControler();
		nbSteps = northPanel.getNbSteps();
	}
	
	//Depreciated ?
	@SuppressWarnings("unused")
	private void computeNewControler() {
		Population<Controleur> pop = PopulationControleurFactory.createRandomPopulation(nbIndividus,nbRules);
		Environnement<Controleur> env = PopulationControleurFactory.createEnvironnement(laby,nbSteps);
		pop.evaluer(env);
		for (int i = 1 ; i < nbGen; i++) {
			pop = pop.evoluer(env);
		}
		controler =  (ControleurIndividuAdapter)pop.get(0);
	}
	
	private void runSimulation() {
		 new Thread(new Runnable(){
			@Override
			public void run() {
				Labyrinthe labyClone = laby.clone(); 
				final Simulation simu = new Simulation(labyClone, controler.getValeurPropre());
				simu.addObserver(centerPanel);
				centerPanel.setLaby(labyClone);
				simu.mesurePerf(nbSteps);
				
			}
			
		}).start();
	}

	
}
