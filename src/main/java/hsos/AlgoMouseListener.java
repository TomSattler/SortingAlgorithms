package hsos;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class AlgoMouseListener implements MouseListener{
	
	static boolean[] clicked= new boolean[5];
	int bNr;
	JLabel thisButton;

	public AlgoMouseListener(int bNr, JLabel button) {
		if(bNr<10) {
			for(int i=0;i<clicked.length;i++) {
				clicked[i]=false;
			}
		}
		this.bNr=bNr;
		this.thisButton=button;
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(this.bNr<10) {
			//System.out.println("Clicked");
			if(clicked[this.bNr]==true) {
				clicked[this.bNr]=false;
				this.thisButton.setBorder(new LineBorder(Color.WHITE, 0));
				SortAlgoInterface.getScreen().repaint();
			}
			else {
				clicked[this.bNr]=true;
				this.thisButton.setBorder(new LineBorder(Color.GREEN, 3));
				SortAlgoInterface.getScreen().repaint();
			}
			/*
			for(int i=0;i<clicked.length;i++) {
				System.out.println(clicked[i]);
			}
			*/
		}
		
//RUN BUTTON EVENT/////////////////////////////////////////////////////////////
		if(this.bNr==11) {
			System.out.println("RUN BUTTON");
			System.out.println("Chosen File: "+SortAlgoInterface.getFilePath());
			if(SortAlgoInterface.getFilePath().equals("unknown file")) {
				System.err.println("Keine Datei ausgew�hlt");
			}
			else {
				SortAlgoInterface.getScreen().setVisible(false);
				@SuppressWarnings("unused")
				AlgoResultScreen resultScreen = new AlgoResultScreen(clicked,SortAlgoInterface.getFilePath());
			}
		}
//RUN BUTTON EVENT/////////////////////////////////////////////////////////////
		
		
		//CLOSE BUTTON EVENT
		if(this.bNr==12) {
			System.out.println("[Closing...]");
			System.exit(0); 
		}
		
		
		
		if(this.bNr==20) {
			SortAlgoInterface.resetFileSetButtons();
			this.thisButton.setBorder(new LineBorder(Color.GREEN, 3));
			SortAlgoInterface.setFilePath("data_20");
			System.out.println("Datei ausgew�hlt: "+SortAlgoInterface.getFilePath());
			SortAlgoInterface.repaint();
		}
		if(this.bNr==21) {
			SortAlgoInterface.resetFileSetButtons();
			this.thisButton.setBorder(new LineBorder(Color.GREEN, 3));
			SortAlgoInterface.setFilePath("data_1000");
			System.out.println("Datei ausgew�hlt: "+SortAlgoInterface.getFilePath());
			SortAlgoInterface.repaint();
		}
		if(this.bNr==22) {
			SortAlgoInterface.resetFileSetButtons();
			this.thisButton.setBorder(new LineBorder(Color.GREEN, 3));
			SortAlgoInterface.setFilePath("data_100k");
			System.out.println("Datei ausgew�hlt: "+SortAlgoInterface.getFilePath());
			SortAlgoInterface.repaint();
		}
		
		if(this.bNr==50) {
			AlgoResultScreen.resultScreen.setVisible(false);
			@SuppressWarnings("unused")
			SortAlgoInterface x = new SortAlgoInterface(); 
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
		this.thisButton.setBounds(this.thisButton.getX()-5, this.thisButton.getY()-5, 
				this.thisButton.getWidth()+10, this.thisButton.getHeight()+10);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
		this.thisButton.setBounds(this.thisButton.getX()+5, this.thisButton.getY()+5,
				this.thisButton.getWidth()-10, this.thisButton.getHeight()-10);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
