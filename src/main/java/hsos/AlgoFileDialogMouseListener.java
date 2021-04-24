package hsos;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class AlgoFileDialogMouseListener implements MouseListener{

	JLabel button;
	//Kinda unnecessary, but its here now, so...
	static String filePath;
	
	public AlgoFileDialogMouseListener(JLabel button, String filePathFromOrigin) {
		this.button=button;
		filePath=filePathFromOrigin;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("FilePath clicked");
		JFrame fileDialogFrame= new JFrame();
		
	//Start file dialog
		FileDialog fd = new FileDialog(fileDialogFrame, "W�hlen Sie die zu sortierende Datei aus", FileDialog.LOAD);
		fd.setDirectory("C:\\");
		fd.setFile("*.txt");
		fd.setVisible(true);
		if (fd.getFile() != null) {
		
	//Set variables from file dialog
		filePath=fd.getDirectory()+fd.getFile();
		System.out.println("Datei ausgew�hlt: "+filePath);
		SortAlgoInterface.setFilePath(filePath);
		
		SortAlgoInterface.resetFileSetButtons();
		this.button.setBorder(new LineBorder(Color.GREEN, 3));
		}
		else {
			System.err.println("Keine Datei ausgew�hlt");
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.button.setBounds(this.button.getX()-5, this.button.getY()-5, 
				this.button.getWidth()+10, this.button.getHeight()+10);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		this.button.setBounds(this.button.getX()+5, this.button.getY()+5,
				this.button.getWidth()-10, this.button.getHeight()-10);
	}
	
	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
