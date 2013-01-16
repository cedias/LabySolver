package agent.laby.interf;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import agent.laby.ContenuCase;

public class CaseButtonBis extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// L'aspect de ce bouton
	private ContenuCase aspect = ContenuCase.VIDE;
	private int nb;

	public CaseButtonBis(int nb){
		super();
		this.nb = nb;
	}
	
	public void setAspect(ContenuCase content) {
		this.aspect = content;
		updateGraphics();
	}
	private void updateGraphics() {

		switch (aspect) {
		case POINT:
			setBackground(Color.yellow);
			setIcon(new ImageIcon("dot.jpg"));
			break;
		case VIDE:
			setBackground(Color.white);
			setIcon(null);
			break;
		case MUR:
			setBackground(Color.black);
			setIcon(new ImageIcon("wall.jpg"));
			break;
		case ANY:
		case AGENT:
			setBackground(Color.blue);
			setIcon(new ImageIcon("agent.jpg"));
			setIcon(null);
			break;

		}
		// inciter à repeindre cet objet à la prochaine occasion
		repaint();
	}
	public int getNb(){
		return nb;
	}
		

}
