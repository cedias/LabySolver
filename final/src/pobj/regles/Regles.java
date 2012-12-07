package pobj.regles;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import agent.control.Direction;
import agent.control.Observation;
import agent.control.Regle;
import agent.laby.ContenuCase;
import agent.laby.interf.CaseButton;
import agent.laby.interf.CaseButtonBis;

public class Regles extends JFrame implements ActionListener {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CaseButtonBis[] cases;
	private String[] listCond;
	private Regle[] listR;
	private int[] order = {7,0,1,6,2,5,4,3,8};
	private JComboBox optionList;
	JPanel p;

	private int currentRegleNb;


	public Regles(Regle[] r){
		super();
		this.setLayout(new GridLayout(2,1));
		listR = r;
		listCond = new String[listR.length];
		int i = 0;
		while(listR[i] != null){
			listCond[i] = setup(listR[i].getConditions().toString());
			i++;
		}
		JPanel p = new JPanel();
		cases = new CaseButtonBis[9];
		p.setLayout(new GridLayout(3,3));
		int j =0 ;
		for(i = 0 ; i < 9 ; i++){
			if(i==4){
				CaseButtonBis b = new CaseButtonBis(9);
				cases[i]=b;
				p.add(b);
			}else{
			CaseButtonBis b = new CaseButtonBis(order[j]);
			j++;
			b.addActionListener(this);
			cases[i]=b;
			p.add(b);
			}
		}
		
		getContentPane().add(p, BorderLayout.NORTH);
		listPanel();
		updateGraphics(listR[0]);
		currentRegleNb = 0;
		this.setVisible(true);
		this.pack();		
	}
	
	public void updateGraphics(Regle r) {
		int j =0;
		String s = setup(r.getConditions().toString());
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3,3));
		for(int i = 0 ; i < 9 ; i++){
			if(i == 4){
				cases[i].setIcon(new ImageIcon("arrow.jpg"));
			}else{
				char c = s.charAt(order[j]);
				if(c==('#'))
				cases[i].setAspect(ContenuCase.MUR);
				if(c==('?'))
					cases[i].setAspect(ContenuCase.ANY);
				if(c==('.'))
					cases[i].setAspect(ContenuCase.POINT);
				if(c==(' '))
					cases[i].setAspect(ContenuCase.VIDE);
				if(c==('A'))
					cases[i].setAspect(ContenuCase.AGENT);

				j++;
			}
		}
		repaint();
	}
	
	public void listPanel(){
		p = new JPanel();
		optionList = new JComboBox(listCond);
		optionList.setPreferredSize(new Dimension(200,30));
		p.add(optionList);
		getContentPane().add(p, BorderLayout.SOUTH);
		optionList.addActionListener(new ActionListener()
		    {
		    	  public void actionPerformed(ActionEvent e) {
		    	        JComboBox cb = (JComboBox)e.getSource();
		    	        int ruleNb = cb.getSelectedIndex();
		    	        currentRegleNb=ruleNb;
		    	        updateGraphics(listR[ruleNb]);
		    }});
		
	}
	
	public void updateList(){
		this.remove(p);
		p = new JPanel();
		optionList = new JComboBox(listCond);
		optionList.setPreferredSize(new Dimension(200,30));
		optionList.setSelectedIndex(currentRegleNb);
		p.add(optionList);
		getContentPane().add(p, BorderLayout.SOUTH);
		optionList.addActionListener(new ActionListener()
		    {
		    	  public void actionPerformed(ActionEvent e) {
		    	        JComboBox cb = (JComboBox)e.getSource();
		    	        int ruleNb = cb.getSelectedIndex();
		    	        currentRegleNb=ruleNb;
		    	        updateGraphics(listR[ruleNb]);
		    }});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		CaseButtonBis c = (CaseButtonBis) e.getSource();
		String s = listCond[currentRegleNb];
		s = s.substring(0, c.getNb()) + '#' + s.substring(c.getNb()+1);
		Regle r = new Regle(new Observation(s),Direction.BAS);
		listCond[currentRegleNb] = setup(r.getConditions().toString());
		listR[currentRegleNb] = r;
		updateGraphics(r);
		updateList();
		
	}
	
	public static  String setup(String s){
		String x = s.replace("\n", "");
		String res = "";
		res = res + x.charAt(1);
		res = res + x.charAt(2);
		res = res + x.charAt(5);
		res = res + x.charAt(8);
		res = res + x.charAt(7);
		res = res + x.charAt(6);
		res = res + x.charAt(3);
		res = res + x.charAt(0);
		return res;
	}
	
	public static void main(String[]args){
		Regle r = new Regle(new Observation(".?# ?#.#"), Direction.BAS);
		
		Regle r1 = new Regle(new Observation("....... "), Direction.BAS);
		Regle r2 = new Regle(new Observation("........"), Direction.BAS);
		Regle r3 = new Regle(new Observation(".......?"), Direction.BAS);
		Regle r4 = new Regle(new Observation(".....#.#"), Direction.BAS);
		Regle r5 = new Regle(new Observation("....??.#"), Direction.BAS);
		Regle[] list = new Regle[10];
		list[0] = r;
		list[1] = r1;
		list[2] = r2;
		list[3] = r3;
		list[4] = r4;
		list[5] = r5;
		System.out.println(r.toString());
		new Regles(list);
		
	}
}
