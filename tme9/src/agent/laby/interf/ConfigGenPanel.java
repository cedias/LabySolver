package agent.laby.interf;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;


import pobj.algogen.Environnement;
import pobj.algogen.Population;
import pobj.algogen.adapter.agent.ControleurIndividuAdapter;
import pobj.algogen.adapter.agent.PopulationControleurFactory;
import pobj.util.Configuration;

import agent.control.Controleur;
import agent.laby.AlgoGenParameter;
import agent.laby.Labyrinthe;

public class ConfigGenPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static final int INDIVIDUS_MAX = 60;
	private static final int INDIVIDUS_MIN = 1;
	private static final int INDIVIDUS_DEFAULT = 30;

	private static final int STEP_MAX = 1000;
	private static final int STEP_MIN = 1;
	private static final int STEP_DEFAULT = 500;
	
	private static final int GENERATIONS_MAX = 1000;
	private static final int GENERATIONS_MIN = 0;
	private static final int GENERATIONS_DEFAULT = 500;
	
	private static final int NBRULES_MAX = 11;
	private static final int NBRULES_MIN = 1;
	private static final int NBRULES_DEFAULT = 6;

	private JSlider generationSlider;
	private JSlider nbRulesSlider;
	private JSlider individuSlider;
	private JSlider stepSlider;
	private JButton saveConfigButton;
	private JButton loadConfigButton;
	private JButton resetConfigButton;
	private JLabel totalPointsLabel = new JLabel("Total: ");
	private JLabel totalPointsValue;
	private JLabel reachedPointsLabel = new JLabel("Obtenus: ");
	private JLabel reachedPointsValue = new JLabel();
	private JLabel efficacityLabel = new JLabel("Efficacité: ");
	private JLabel efficacityValue = new JLabel();
	private JLabel generationLabel = new JLabel("Nombre De Générations");
	private JLabel individusLabel = new JLabel("Nombre d'Individus");
	private JLabel nbRulesLabel = new JLabel("Nombre de Regles");
	private JLabel nbstepLabel = new JLabel("Nombre De Pas");
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel rightPanelLabel;
	private JPanel rightPanelValue;
	private JPanel buttonsPanel;
	
	private ControleurIndividuAdapter controleur;
	private Labyrinthe laby;
	private int nbSteps;
	private int nbIndividus;
	private int nbGen;
	private int nbRules;
	private Thread calculThread;

	
	
	public ConfigGenPanel(Labyrinthe laby) {
		super();
		
		Configuration config = Configuration.getInstance();
		try {
		nbSteps = Integer.parseInt(config.getParameterValue(AlgoGenParameter.NB_PAS));
		nbGen = Integer.parseInt(config.getParameterValue(AlgoGenParameter.NB_GEN));
		nbRules = Integer.parseInt(config.getParameterValue(AlgoGenParameter.NB_RULES));
		nbIndividus = Integer.parseInt(config.getParameterValue(AlgoGenParameter.TAILLE_POP));
		}
		catch(NumberFormatException e)
		{
			nbSteps = STEP_DEFAULT;
			nbIndividus = INDIVIDUS_DEFAULT;
			nbGen = GENERATIONS_DEFAULT;
			nbRules = NBRULES_DEFAULT;
		}
		this.laby = laby;
		this.setLayout(new GridLayout(1,3));
		this.setSize(800, 250);
		createButtonsPanel();
		createSlidersPanel();
		createStatsPanel();
		setParameters();
	}
	
	public ControleurIndividuAdapter getControler() {
		return controleur;
		
	}
	
	public int getNbSteps(){
		return stepSlider.getValue();
	}
	
	
	private void setParameters() {
		int nbSteps = stepSlider.getValue();
		int nbRules = nbRulesSlider.getValue();
		int nbGen = generationSlider.getValue();
		Population<Controleur> pop = PopulationControleurFactory.createRandomPopulation(individuSlider.getValue(),nbRules);
		Environnement<Controleur> env = PopulationControleurFactory.createEnvironnement(laby,nbSteps);
		pop.evaluer(env);
		for (int i = 1 ; i < nbGen; i++) {
			pop = pop.evoluer(env);
		}
		
		controleur =  (ControleurIndividuAdapter)pop.get(0);
		reachedPointsValue.setText((int)pop.get(0).getFitness() + " Points");
		efficacityValue.setText((int)(pop.get(0).getFitness()/laby.getNbPoints()*100) + "%");
	}

	private void createSlidersPanel(){
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));
		leftPanel.setSize(150, 150);
		add(leftPanel);
		
		MouseListener onChange = new MouseAdapter(){
			@Override
			public void mouseReleased(MouseEvent arg0)
			{
				
				if (calculThread != null)
					calculThread.interrupt();

				calculThread = new Thread(new Runnable(){
					@Override
					public void run() {
						setParameters();
						calculThread = null; 
					}
				});
				calculThread.start();
			}

		};
		
		leftPanel.add(generationLabel);
		generationSlider = new JSlider(JSlider.HORIZONTAL,GENERATIONS_MIN,GENERATIONS_MAX,nbGen);
		leftPanel.add(generationSlider);
		generationSlider.addMouseListener(onChange);
		
		leftPanel.add(individusLabel);
		individuSlider = new JSlider(JSlider.HORIZONTAL,INDIVIDUS_MIN,INDIVIDUS_MAX,nbIndividus);
		leftPanel.add(individuSlider);
		individuSlider.addMouseListener(onChange);
		
		leftPanel.add(nbRulesLabel);
		nbRulesSlider = new JSlider(JSlider.HORIZONTAL, NBRULES_MIN,NBRULES_MAX,nbRules );
		leftPanel.add(nbRulesSlider);
		nbRulesSlider.addMouseListener(onChange);
		
		leftPanel.add(nbstepLabel);
		stepSlider = new JSlider(JSlider.HORIZONTAL,STEP_MIN,STEP_MAX,nbSteps);
		leftPanel.add(stepSlider);
		stepSlider.addMouseListener(onChange);
	}
	
	private void createStatsPanel(){
		rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.X_AXIS));
		add(rightPanel);
		
		rightPanelLabel = new JPanel();
		rightPanelLabel.setLayout(new BoxLayout(rightPanelLabel, BoxLayout.Y_AXIS));
		rightPanel.add(rightPanelLabel);
		
		rightPanelValue = new JPanel();
		rightPanelValue.setLayout(new BoxLayout(rightPanelValue, BoxLayout.Y_AXIS));
		rightPanel.add(rightPanelValue);
		
		totalPointsValue = new JLabel(laby.getNbPoints() + " Points");
		
		rightPanelLabel.add(totalPointsLabel);
		rightPanelValue.add(totalPointsValue);
		
		rightPanelLabel.add(reachedPointsLabel);
		rightPanelValue.add(reachedPointsValue);
		
		rightPanelLabel.add(efficacityLabel);
		rightPanelValue.add(efficacityValue);
	}
	
	private void createButtonsPanel()
	{
	buttonsPanel = new JPanel();
	buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.Y_AXIS));
	add(buttonsPanel);
	
	saveConfigButton = new JButton("Sauvegarder");
	buttonsPanel.add(saveConfigButton);
	saveConfigButton.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				saveConfig();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	});
	
	loadConfigButton = new JButton("Charger");
	buttonsPanel.add(loadConfigButton);
	loadConfigButton.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				loadConfig();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	});
	
	resetConfigButton = new JButton("Réinitialiser");
	buttonsPanel.add(resetConfigButton);
	resetConfigButton.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			resetConfig();
		}
		
	});
	
	
	}
	private void resetConfig()
	{
		Configuration config = Configuration.getInstance();
		try {
			nbSteps = Integer.parseInt(config.getParameterValue(AlgoGenParameter.NB_PAS));
			nbGen = Integer.parseInt(config.getParameterValue(AlgoGenParameter.NB_GEN));
			nbRules = Integer.parseInt(config.getParameterValue(AlgoGenParameter.NB_RULES));
			nbIndividus = Integer.parseInt(config.getParameterValue(AlgoGenParameter.TAILLE_POP));
			}
			catch(NumberFormatException e)
			{
				nbSteps = STEP_DEFAULT;
				nbIndividus = INDIVIDUS_DEFAULT;
				nbGen = GENERATIONS_DEFAULT;
				nbRules = NBRULES_DEFAULT;
			}
		generationSlider.setValue(nbGen);
		nbRulesSlider.setValue(nbRules);
		individuSlider.setValue(nbIndividus);
		stepSlider.setValue(nbSteps);
		setParameters();
		
	}
	
	private void saveConfig() throws FileNotFoundException, IOException
	{
		Configuration config = Configuration.getInstance();
		config.setParameterValue(AlgoGenParameter.NB_GEN, generationSlider.getValue() + "");
		config.setParameterValue(AlgoGenParameter.NB_PAS, stepSlider.getValue() + "");
		config.setParameterValue(AlgoGenParameter.NB_RULES, nbRulesSlider.getValue() + "");
		config.setParameterValue(AlgoGenParameter.TAILLE_POP, individuSlider.getValue() + "");
		
		JFileChooser fc = new JFileChooser("./configs");

		int returnVal = fc.showSaveDialog(null);

	    if (returnVal == JFileChooser.APPROVE_OPTION) {
	       Configuration.sauverConfiguration(new FileOutputStream(fc.getSelectedFile()));
	       setParameters();
	    }
	}
	
	private void loadConfig() throws IOException
	{
		
		JFileChooser fc = new JFileChooser("./configs");
		int returnVal = fc.showOpenDialog(null);

	    if (returnVal == JFileChooser.APPROVE_OPTION) {
	       Configuration.chargerConfiguration(fc.getSelectedFile());
	       resetConfig();
	       setParameters();
	    }

		
	}
}
