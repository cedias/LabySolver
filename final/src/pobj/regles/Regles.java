package pobj.regles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import agent.control.Direction;
import agent.control.Observation;
import agent.control.Regle;
import agent.laby.ContenuCase;
import agent.laby.interf.CaseButtonBis;

public class Regles extends JFrame implements ActionListener {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CaseButtonBis[] cases;
	private JButton mur , point , vide , any ;
	private char swap;
	private ArrayList<String>listCond;
	private ArrayList<String>listCondDir;
	private ArrayList<Regle>listR;
	private int[] order = {7,0,1,6,2,5,4,3,8};
	private JComboBox optionList;
	JPanel listPanel , sidePanel;
	private int currentRegleNb;


	public Regles(ArrayList<Regle> r){
		super();
		this.setLayout(new GridLayout(1,2));
		listR = r;
		listCond = new ArrayList<String>();
		listCondDir =new ArrayList<String>();
		int i = 0;
		while(i < listR.size()){
		listCond.add(properString(listR.get(i).toString()));
		listCondDir.add(i +") " +properString(listR.get(i).toString())+ " Direction :" + listR.get(i).getAction().toString());
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
		add(p);
		sidePanel();
		updateGraphics(listR.get(0));
		currentRegleNb = 0;
		swap = '#';
		this.setVisible(true);
		this.pack();		
	}
	
	public void updateGraphics(Regle r) {
		int j =0;
		String s = properString(r.toString());
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
	
	public void sidePanel(){
		sidePanel = new JPanel();
		sidePanel.setLayout(new GridLayout(2,1));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,4));
		mur = new JButton(new ImageIcon("wall.jpg"));
		mur.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				swap = '#';	
			}
		});
		any = new JButton();
		any.setText("ANY");
		any.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				swap = '?';	
			}
		});
		point = new JButton(new ImageIcon("dot.jpg"));
		point.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				swap = '.';	
			}
		});
		vide = new JButton();
		vide.setText("vide");
		vide.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				swap = ' ';	
			}
		});
		buttonPanel.add(mur);
		buttonPanel.add(any);
		buttonPanel.add(point);
		buttonPanel.add(vide);
		sidePanel.add(buttonPanel);
		listPanel();
		sidePanel.add(listPanel);
		add(sidePanel);
	}
	
	public void listPanel(){
		listPanel = new JPanel();
		optionList = new JComboBox(listCondDir.toArray());
		optionList.setPreferredSize(new Dimension(200,30));
		listPanel.add(optionList);
		optionList.addActionListener(new ActionListener()
		    {
		    	  public void actionPerformed(ActionEvent e) {
		    	        JComboBox cb = (JComboBox)e.getSource();
		    	        int ruleNb = cb.getSelectedIndex();
		    	        currentRegleNb=ruleNb;
		    	        updateGraphics(listR.get(ruleNb));
		    }});
		
	}
	
	private void updateList(){
		sidePanel.remove(listPanel);
		listPanel = new JPanel();
		optionList = new JComboBox(listCondDir.toArray());
		optionList.setPreferredSize(new Dimension(200,30));
		optionList.setSelectedIndex(currentRegleNb);
		listPanel.add(optionList);
		sidePanel.add(listPanel);
		optionList.addActionListener(new ActionListener()
		    {
		    	  public void actionPerformed(ActionEvent e) {
		    	        JComboBox cb = (JComboBox)e.getSource();
		    	        int ruleNb = cb.getSelectedIndex();
		    	        currentRegleNb=ruleNb;
		    	        updateGraphics(listR.get(ruleNb));
		    }});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		CaseButtonBis c = (CaseButtonBis) e.getSource();
		String s = listCond.get(currentRegleNb);
		s = s.substring(0, c.getNb()) + swap + s.substring(c.getNb()+1);
		Regle r = new Regle(new Observation(s),Direction.BAS);
		listCond.set(currentRegleNb,properString(r.toString()));
		listCondDir.set(currentRegleNb,currentRegleNb +") " +properString(listR.get(currentRegleNb).toString())+ " Direction :" + listR.get(currentRegleNb).getAction().toString());
		listR.set(currentRegleNb,r);
		updateGraphics(r);
		updateList();
		
	}
	
	public static  String properString(String s){
		String x = s.substring(1, 12);
		x = x.replace("\n", "");
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
		ArrayList<Regle> list = new ArrayList<Regle>();
		list.add(r);
		list.add(r1);
		list.add(r2);
		list.add(r3);
		list.add(r4);
		list.add(r5);
		new Regles(list);
		
	}
}
