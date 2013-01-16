package agent.laby.interf;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


import pobj.algogen.adapter.agent.ControleurIndividuAdapter;
import pobj.regles.RuleBuilder;
import pobj.util.Configuration;

import agent.Simulation;
import agent.control.Controleur;
import agent.control.IControleur;
import agent.laby.AlgoGenParameter;
import agent.laby.Labyrinthe;
import agent.laby.ProgressPanel;

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
	private JButton ruleEdit;
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
	private JPanel cardPanel;
	private ProgressPanel progressPanel;
	private CardLayout cardLayout;
	
	private CalculThread calculThread;
	private ControleurIndividuAdapter controleur;
	private Labyrinthe laby;
	private int nbSteps;
	private int nbIndividus;
	private int nbGen;
	private int nbRules;


	
	
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
	
	private void setParameters(){
		CalculThread t = new CalculThread(stepSlider.getValue(),nbRulesSlider.getValue(),generationSlider.getValue(),individuSlider.getValue(),progressPanel,laby);
		t.run();
		controleur =  t.getControleur();
		reachedPointsValue.setText(t.getReachedPointsValue() + " Points");
		efficacityValue.setText(t.getEfficacityValue() + "%");
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
				
				cardLayout.show(cardPanel, "progressPanel");
				calculThread  = new CalculThread(stepSlider.getValue(),nbRulesSlider.getValue(),generationSlider.getValue(),individuSlider.getValue(),progressPanel,laby);
				
				Thread r = new Thread(new Runnable(){
					public void run() {
						
						while(calculThread.isAlive()){};
						
						controleur =  calculThread.getControleur();
						reachedPointsValue.setText(calculThread.getReachedPointsValue() + " Points");
						efficacityValue.setText(calculThread.getEfficacityValue() + "%");
						cardLayout.show(cardPanel, "rightPanel");
					}
				});
				
				calculThread.start();
				r.start();
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
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		add(cardPanel);
		

		
		rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.X_AXIS));		
		
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
		
		
		cardPanel.add(rightPanel,"rightPanel");
		cardLayout.show(cardPanel, "rightPanel");
		progressPanel = new ProgressPanel(nbGen);
		cardPanel.add(progressPanel,"progressPanel");
		
		
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
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
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
	
	ruleEdit = new JButton("ruleEdit");
	buttonsPanel.add(ruleEdit);
	ruleEdit.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			Controleur c = (Controleur) controleur.getValeurPropre();
			JFrame rb = new RuleBuilder(c.getRuleset());
			rb.addWindowListener(new WindowListener(){

				
				public void windowOpened(WindowEvent e) {				}

				@Override
				public void windowClosing(WindowEvent e) {
					Simulation sim = new Simulation(laby,(IControleur) controleur.getValeurPropre());
					int x = sim.mesurePerf(nbSteps);
					System.out.println(x);
					reachedPointsValue.setText(x + " Points");
					efficacityValue.setText(x/laby.getNbPoints() * 100  + "%");
				}

				@Override
				public void windowClosed(WindowEvent e) {			
				}

				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
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
		
		JFileChooser fc = new JFileChooser("./data/configs");
		int returnVal = fc.showSaveDialog(null);

	    if (returnVal == JFileChooser.APPROVE_OPTION) {
	    	Configuration.exportConfigurationData(new FileOutputStream(fc.getSelectedFile()));
	    }
	}
	
	private void loadConfig() throws IOException, ParserConfigurationException, SAXException
	{
		
		JFileChooser fc = new JFileChooser("./data/configs");
		int returnVal = fc.showOpenDialog(null);

	    if (returnVal == JFileChooser.APPROVE_OPTION) {
	       Configuration.importConfigurationData(fc.getSelectedFile());
	       resetConfig();
	    }

		
	}
}
