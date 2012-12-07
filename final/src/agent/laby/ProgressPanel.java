package agent.laby;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class ProgressPanel extends JPanel {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JProgressBar progressBar;
	private int nbMax;	
	public ProgressPanel(int nbMax){
		this.nbMax = nbMax;
		setLayout(new GridLayout(1,0));
		progressBar = new JProgressBar(0,nbMax);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		add(progressBar);
		setVisible(true);
		}
	
	public void setValue(int i){
		progressBar.setValue(i);
		if(i < nbMax/2)
			progressBar.setString("Evaluating hold on");
		else if(i == nbMax/2)
			progressBar.setString("Half way throught");
		else if(i > nbMax * 0.75 && i < nbMax -1)
			progressBar.setString("Almost there");
		else if(i == nbMax-1)
			progressBar.setString("Finished");
	}
	
}
