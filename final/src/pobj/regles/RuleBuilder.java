package pobj.regles;


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

public class RuleBuilder extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JComboBox<String> optionList;
	private CaseButtonBis[] cases;
	private JButton mur ;
	private JButton point ; 
	private JButton vide ;
	private JButton any ;
	private JButton add ;
	private JButton remove;
	
	private char swap = '#';
	
	private ArrayList<String>listCond =  new ArrayList<String>();
	private ArrayList<String>listCondDir  = new ArrayList<String>();
	private ArrayList<Regle>listR;
	
	private  final int[] order = {7,0,1,6,2,5,4,3,8};

	JPanel listPanel , sidePanel , buttonPanel , secPanel;
	private int currentRegleNb = 0;


	public RuleBuilder(ArrayList<Regle> r){
		super();
		this.setLayout(new GridLayout(1,2));
		listR = r;	
		int i = 0;
		
		while(i < listR.size()){
			listCond.add(properString(listR.get(i).toString()));
			listCondDir.add(i +") " +properString(listR.get(i).toString()));
			i++;
		}
		createButtons();
		sidePanel();
		updateGraphics(listR.get(0));
		this.setVisible(true);
		this.pack();		
	}
	
	// SIDE PANEL 
	
	public void sidePanel(){
		sidePanel = new JPanel();
		sidePanel.setLayout(new GridLayout(3,1));
		ButtonPanel();
		secPanel();
		listPanel();
		add(sidePanel);
	}
	
	// BUTTON PANEL CREATOR 
	
	private void ButtonPanel(){
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,4));
		mur = new JButton(new ImageIcon("wall.jpg"));
		mur.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				swap = '#';	
			}
		});
		any = new JButton();
		any.setText("ANY");
		any.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				swap = '?';	
			}
		});
		point = new JButton(new ImageIcon("dot.jpg"));
		point.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				swap = '.';	
			}
		});
		vide = new JButton();
		vide.setText("vide");
		vide.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				swap = ' ';	
			}
		});	
		buttonPanel.add(mur);
		buttonPanel.add(any);
		buttonPanel.add(point);
		buttonPanel.add(vide);
		sidePanel.add(buttonPanel);
	}
	
	// SECOND PANEL ADD AND REMOVE BUTTON
	
	private void secPanel(){
		secPanel = new JPanel();
		secPanel.setLayout(new GridLayout(1,2));
		add = new JButton();
		add.setText("Add");
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addRegle();
				add.setSelected(false);
			}
		});
		remove = new JButton();
		remove.setText("Remove");
		remove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				remove();
			}
		});	
		secPanel.add(add);
		secPanel.add(remove);
		sidePanel.add(secPanel);
		
	}
	
	// LIST PANEL 
	
	private void listPanel(){
		listPanel = new JPanel();
		optionList = new JComboBox<String>();
		for(int i = 0 ; i < listCondDir.size() ; i++ ){
			optionList.addItem(listCondDir.get(i));
		}
		optionList.setPreferredSize(new Dimension(200,30));
		listPanel.add(optionList);
		optionList.addActionListener(new ActionListener()
		    {
		    	  public void actionPerformed(ActionEvent e) {
		    	        JComboBox<String> cb = (JComboBox<String>)e.getSource();
		    	        int ruleNb = cb.getSelectedIndex();
		    	        currentRegleNb=ruleNb;
		    	        updateGraphics(listR.get(ruleNb));
		    }});
		sidePanel.add(listPanel);	
	}
	
	
	
	private void createButtons(){
		JPanel p = new JPanel();
		cases = new CaseButtonBis[9];
		p.setLayout(new GridLayout(3,3));
		int i;
		int j = 0;
		
		for(i = 0 ; i < 9 ; i++){
			if(i==4){
				CaseButtonBis b = new CaseButtonBis(9);
				
				b.addActionListener(new ActionListener(){
					
					public void actionPerformed(ActionEvent e) {					
						buttonDir((CaseButtonBis)e.getSource());
					}
				});
				
				cases[i]=b;
				p.add(b);
				
			}else{
				
			CaseButtonBis b = new CaseButtonBis(order[j]);
			j++;
			b.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					buttonAction((CaseButtonBis)e.getSource());
				}
			});
			cases[i]=b;
			p.add(b);
			}
		}	
		add(p);
		
	}	
	
	public void buttonDir(CaseButtonBis source) {
		Direction dir = listR.get(currentRegleNb).getAction();
		switch(dir){
			case BAS : 		
				dir = Direction.GAUCHE; 	
				break;
				
			case HAUT :		
				dir = Direction.DROITE;	
				break;	
			case GAUCHE : 	
				dir = Direction.HAUT;	
				break;
			case DROITE : 	
				dir = Direction.BAS;	
				break;
		default:
			break;
		}
		
		String description = listCond.get(currentRegleNb);
		Regle r = new Regle(new Observation(description),dir);
		
		listCond.set(currentRegleNb,properString(r.toString()));
		listCondDir.set(currentRegleNb,currentRegleNb +") " + description);
		listR.set(currentRegleNb,r);
		optionList.insertItemAt(listCondDir.get(currentRegleNb), currentRegleNb);
		//updateGraphics(r);
		}
	
	public void buttonAction(CaseButtonBis c){
		Direction d = listR.get(currentRegleNb).getAction();
		if(c.getNb()==4){	
		}
		String s = listCond.get(currentRegleNb);
		if(swap == s.charAt(c.getNb())){
			return;
		}
		s = s.substring(0, c.getNb()) + swap + s.substring(c.getNb()+1);
		Regle r = new Regle(new Observation(s),d);
		listCond.set(currentRegleNb,properString(r.toString()));
		listCondDir.set(currentRegleNb,currentRegleNb +") " + s);
		listR.set(currentRegleNb,r);
		updateGraphics(r);
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
	
	// ADD AND REMOVE
	
	public void remove(){
		if(listR.size()==1){
			System.out.println("you need to keep atleast one rule");
			return;
		}
		listR.remove(currentRegleNb);
		listCond.remove(currentRegleNb);
		listCondDir.remove(currentRegleNb);
		if(currentRegleNb == listR.size())
			currentRegleNb--;
		if(currentRegleNb == -1){
			currentRegleNb = 0;
		}
		updateStringList();
		for(int i = 0 ; i < listCondDir.size() ; i++){
			optionList.removeItemAt(i);
			optionList.insertItemAt(listCondDir.get(i), i);
		}
		optionList.removeItemAt(listCondDir.size());
		optionList.setSelectedIndex(currentRegleNb);
		updateGraphics(listR.get(currentRegleNb));
	}
	
	public void addRegle(){
		Regle nouv = new Regle(new Observation("........"), Direction.BAS);
		listR.add(nouv);
		listCond.add(properString(listR.get(listR.size()-1).toString()));
		listCondDir.add(listR.size()-1 +") " +properString(listR.get(listR.size()-1).toString()));
		currentRegleNb = listR.size()-1;
		optionList.addItem(listCondDir.get(currentRegleNb));
		optionList.setSelectedIndex(currentRegleNb);
		updateGraphics(nouv);
	}
	
	
	
	// UPDATE FUNCTIONS

	private void updateGraphics(Regle r) {
		int j =0;
		String s = properString(r.toString());
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3,3));
		for(int i = 0 ; i < 9 ; i++){
			if(i == 4){
				Direction dir = r.getAction();
				switch(dir){
					case BAS : 		cases[i].setIcon(new ImageIcon("down.png")); 	break;
					case HAUT :		cases[i].setIcon(new ImageIcon("up.png"));		break;	
					case GAUCHE : 	cases[i].setIcon(new ImageIcon("left.png"));	break;
					case DROITE : 	cases[i].setIcon(new ImageIcon("right.png"));	break;
				default:
					break;
					}
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
	
	private void updateStringList(){
		listCond = new ArrayList<String>();
		listCondDir =new ArrayList<String>();
		int i = 0;
		while(i < listR.size()){
		listCond.add(properString(listR.get(i).toString()));
		listCondDir.add(i +") " +properString(listR.get(i).toString()));
		i++;
		}
	}
	
	public static void main(String[]args){
		Regle r = new Regle(new Observation(".?# ?#.#"), Direction.HAUT);
		
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
		new RuleBuilder(list);
		
	}
}
