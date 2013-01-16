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
	
	public ProgressPanel()
	{
		setLayout(new GridLayout(1,0));
		progressBar = new JProgressBar(0,100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		add(progressBar);
		setVisible(true);
		}
	
	public void setValue(int i)
	{
		progressBar.setValue(i);
	}
	
	public int getValue(){
		return progressBar.getValue();
	}
	
}
