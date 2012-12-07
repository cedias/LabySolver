package agent.laby.interf;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import pobj.algogen.Environnement;
import pobj.algogen.Population;
import pobj.algogen.adapter.agent.ControleurIndividuAdapter;
import pobj.algogen.adapter.agent.PopulationControleurFactory;

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
	private JButton getResultButton = new JButton("Générer");
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
	
	private ControleurIndividuAdapter controleur;
	private Labyrinthe laby;

	
	
	public ConfigGenPanel(Labyrinthe laby) {
		super();
		this.laby = laby;
		this.setLayout(new GridLayout(1,3));
		this.setSize(800, 250);
		createSlidersPanel();
		createButtonPanel();
		createStatsPanel();
		setParameters();
	}
	
	private void createButtonPanel() {
		getResultButton.setSize(100, 100);
		add(getResultButton);
		
		getResultButton.addActionListener(new ActionListener(){		
			public void actionPerformed(ActionEvent e) {
				setParameters();
			}
		});
	}
	
	public ControleurIndividuAdapter getControler() {
		return controleur;
		
	}
	
	public int getNbSteps(){
		return stepSlider.getValue();
	}

	protected void setParameters() {
		int nbSteps = stepSlider.getValue();
		int nbRules = nbRulesSlider.getValue();
		int nbGen = generationSlider.getValue();
		Population pop = PopulationControleurFactory.createRandomPopulation(individuSlider.getValue(),nbRules);
		Environnement env = PopulationControleurFactory.createEnvironnement(laby,nbSteps);
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
		
		leftPanel.add(generationLabel);
		generationSlider = new JSlider(JSlider.HORIZONTAL,GENERATIONS_MIN,GENERATIONS_MAX,GENERATIONS_DEFAULT);
		leftPanel.add(generationSlider);
		
		leftPanel.add(individusLabel);
		individuSlider = new JSlider(JSlider.HORIZONTAL,INDIVIDUS_MIN,INDIVIDUS_MAX,INDIVIDUS_DEFAULT);
		leftPanel.add(individuSlider);
		
		leftPanel.add(nbRulesLabel);
		nbRulesSlider = new JSlider(JSlider.HORIZONTAL, NBRULES_MIN,NBRULES_MAX,NBRULES_DEFAULT );
		leftPanel.add(nbRulesSlider);
		
		leftPanel.add(nbstepLabel);
		stepSlider = new JSlider(JSlider.HORIZONTAL,STEP_MIN,STEP_MAX,STEP_DEFAULT);
		leftPanel.add(stepSlider);
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
	
	
	
}
