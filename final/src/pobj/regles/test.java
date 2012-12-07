package pobj.regles;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import agent.laby.interf.CaseButtonBis;

public class test extends JFrame {

	public test(){
		int[] order = {7,0,1,6,2,5,4,3,8};
		int j =0;
		String s = ".?# ?#.#";
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3,3));
		for(int i = 0 ; i < 9 ; i++){
			JButton b = new JButton();
			if(i == 4){
				p.add(b);
				b.setText(" ");
			}else{
			b.setText(""+s.charAt(order[j]));
			j++;
			p.add(b);
			}
		}
		add(p);
		this.setVisible(true);
		this.pack();		
	}
	
	public static void main(String[]args){
		new test();
	}
}
