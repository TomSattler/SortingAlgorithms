package hsos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class AlgoResultScreen {
	
	static boolean[] selected;
	static String filePath;
	static JFrame resultScreen;
	private static int[] arr;
	
	public AlgoResultScreen(boolean[] arr, String filePathFromOrigin) {
		selected=arr;
		filePath=filePathFromOrigin;
		
		buildScreen();
		buildExitButton();
		buildReturnButton();
		readNumbersFromFile(filePath);
		
		erstelleErgebnisse();
	
	}
	
	static int[] getArray() {
		return arr;
	}
	
    public static void readNumbersFromFile(String fileName) {
		if(!(fileName.equals("data_20") || fileName.equals("data_1000") || fileName.equals("data_100k"))) {
			//Path path;
			Scanner scanner;
			try {
				scanner = new Scanner(new File(filePath));


				int elemAnzahl = scanner.nextInt();
				System.out.println("Anzahl an Elementen: " + elemAnzahl);
				arr = new int[elemAnzahl];

				int index = 0;
				while (scanner.hasNextInt()) {

					arr[index] = scanner.nextInt();
					++index;
				}

				scanner.close();


			//	for (int i = 0; i < arr.length; i++) {
			//		System.out.print(arr[i] + ",");
			//	}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{

			Scanner scanner;
			try {
				Path path;
				path = Paths.get(Objects.requireNonNull(Application.class.getClassLoader().getResource(filePath)).toURI());
				scanner = new Scanner(path, StandardCharsets.UTF_8);
				//scanner = new Scanner(new File(filePath));

				int elemAnzahl = scanner.nextInt();
				System.out.println("Anzahl an Elementen: " + elemAnzahl);
				arr = new int[elemAnzahl];

				int index = 0;
				while (scanner.hasNextInt()) {
					// System.out.println(index + ". Zahl: " + scanner.nextInt());
					arr[index] = scanner.nextInt();
					++index;
				}

				scanner.close();

				//for (int i = 0; i < arr.length; i++) {
				//	System.out.print(arr[i] + ",");
				//}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
	
	static void buildScreen() {
		resultScreen=new JFrame();
		
		//remove fussy Manager (<- Garbage)
		resultScreen.setLayout(null);
		
		resultScreen.setBounds(0, 0, 560, 280);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		resultScreen.setLocation(dim.width/2-resultScreen.getSize().width/2,
														dim.height/2-resultScreen.getSize().height/2); 
		
		resultScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		resultScreen.setUndecorated(true);
		resultScreen.setBackground(new Color(0.1f,0.1f,0.1f,0.9f));
		resultScreen.setVisible(true);
	}
	
	static void buildReturnButton() {
		JLabel algoReturnButton = new JLabel();
	
		algoReturnButton.setBounds(200,200, 350, 70);
		algoReturnButton.setOpaque(true);
		
		algoReturnButton.setBackground(new Color(0.0f,1f,0.0f,0.3f));
		
		algoReturnButton.setVisible(true);
		algoReturnButton.addMouseListener(new AlgoMouseListener(50,algoReturnButton));
		
		Font font = new Font("Impact", Font.PLAIN, 35);
		
		JLabel returnTitle = new JLabel("Return");
		returnTitle.setFont(font);
		returnTitle.setBounds((350-returnTitle.getPreferredSize().width)/2+200, 200,returnTitle.getPreferredSize().width, 70);
		returnTitle.setVisible(true);
		
		resultScreen.add(returnTitle);
		resultScreen.add(algoReturnButton);
	}
	
	static void buildExitButton() {
		JLabel exitButton = new JLabel();
		
		exitButton.setBounds(10,200, 180, 70);
		exitButton.setOpaque(true);
		
		exitButton.setBackground(new Color(1f,0.0f,0.0f,0.3f));
		
		exitButton.setVisible(true);
		exitButton.addMouseListener(new AlgoMouseListener(12,exitButton));
		
		Font font = new Font("Impact", Font.PLAIN, 35);
		JLabel closeTitle = new JLabel("Close");
		closeTitle.setFont(font);
		//closeTitle.setForeground(Color.BLACK);
		closeTitle.setBounds((180-closeTitle.getPreferredSize().width)/2+10, 200,closeTitle.getPreferredSize().width, 70);
		closeTitle.setVisible(true);
		
		resultScreen.add(closeTitle);
		
		resultScreen.add(exitButton);
	}
	
	static void erstelleErgebnisse() {
		int yJump=-20;
		for(int i=0;i<selected.length;i++) {
			if(selected[i]==true) {
				Font font = new Font("Impact", Font.PLAIN, 35);
				switch (i) {
				case 0:{
					//Generiere JLabel Titel und JLabel Zeit = "loading..."
					JLabel insertionSortLabel = new JLabel("Insertion Sort");
					insertionSortLabel.setBounds(10, yJump, 1000, 80);
					insertionSortLabel.setFont(font);
					insertionSortLabel.setForeground(Color.WHITE);
					resultScreen.add(insertionSortLabel);
					
					//System.out.println("Jump: "+yJump + " i: "+i);
					
					JLabel insertionSortTime = new JLabel("Loading...");
					insertionSortTime.setBounds(250, yJump, 1000, 80);
					insertionSortTime.setFont(font);
					insertionSortTime.setForeground(Color.WHITE);
					resultScreen.add(insertionSortTime);
					yJump+=40;
					
					
					Thread threadForPerformance0 = new Thread() {
						public void run() {
							insertionSortTime.setText(Long.toString(SortierAlgorithmen.insertionSort(AlgoResultScreen.getArray()))+"ns");
							return;
						}
					};
					threadForPerformance0.start();
		
//insertionSortTime.setText(insertionSort(filePath));
					break;
				}
				case 1:{
					JLabel selectionSortLabel = new JLabel("Selection Sort");
					selectionSortLabel.setBounds(10, yJump, 1000, 80);
					selectionSortLabel.setFont(font);
					selectionSortLabel.setForeground(Color.WHITE);
					resultScreen.add(selectionSortLabel);
					
					//System.out.println("Jump: "+yJump + " i: "+i);
					
					JLabel selectionSortTime = new JLabel("Loading...");
					selectionSortTime.setBounds(250, yJump, 1000, 80);
					selectionSortTime.setFont(font);
					selectionSortTime.setForeground(Color.WHITE);
					resultScreen.add(selectionSortTime);
					yJump+=40;
		
					Thread threadForPerformance1 = new Thread() {
						public void run() {
							selectionSortTime.setText("selectionSort(arr);");
							return;
						}
					};
					threadForPerformance1.start();
//selectionSortTime.setText(selectionSort(filePath));
					break;
				}
				case 2:{
					JLabel heapSortLabel = new JLabel("Heap Sort");
					heapSortLabel.setBounds(10, yJump, 1000, 80);
					heapSortLabel.setFont(font);
					heapSortLabel.setForeground(Color.WHITE);
					resultScreen.add(heapSortLabel);
					
					//System.out.println("Jump: "+yJump + " i: "+i);
					
					JLabel heapSortTime = new JLabel("Loading...");
					heapSortTime.setBounds(250, yJump, 1000, 80);
					heapSortTime.setFont(font);
					heapSortTime.setForeground(Color.WHITE);
					resultScreen.add(heapSortTime);
					yJump+=40;
		
					
					
					Thread threadForPerformance2 = new Thread() {
						public void run() {
							heapSortTime.setText(Long.toString(SortierAlgorithmen.heapSort(AlgoResultScreen.getArray()))+"ns");
							return;
						}
					};
					threadForPerformance2.start();
//heapSortTime.setText(heapSort(filePath));
					break;
				}
				case 3:{
					JLabel mergeSortLabel = new JLabel("Merge Sort");
					mergeSortLabel.setBounds(10, yJump, 1000, 80);
					mergeSortLabel.setFont(font);
					mergeSortLabel.setForeground(Color.WHITE);
					resultScreen.add(mergeSortLabel);
					
					//System.out.println("Jump: "+yJump + " i: "+i);
					
					JLabel mergeSortTime = new JLabel("Loading...");
					mergeSortTime.setBounds(250, yJump, 1000, 80);
					mergeSortTime.setFont(font);
					mergeSortTime.setForeground(Color.WHITE);
					resultScreen.add(mergeSortTime);
					yJump+=40;
					
					
					
					Thread threadForPerformance3 = new Thread() {
						public void run() {
							mergeSortTime.setText("mergeSort(arr);");
							return;
						}
					};
					threadForPerformance3.start();
//mergeSortTime.setText(mergeSort(filePath));
					break;
				}
				case 4:{
					JLabel quickSortLabel = new JLabel("Quick Sort");
					quickSortLabel.setBounds(10, yJump, 1000, 80);
					quickSortLabel.setFont(font);
					quickSortLabel.setForeground(Color.WHITE);
					resultScreen.add(quickSortLabel);
					
					//System.out.println("Jump: "+yJump + " i: "+i);
					
					JLabel quickSortTime = new JLabel("Loading...");
					quickSortTime.setBounds(250, yJump, 1000, 80);
					quickSortTime.setFont(font);
					quickSortTime.setForeground(Color.WHITE);
					resultScreen.add(quickSortTime);
					yJump+=40;
					
					
					
					Thread threadForPerformance4 = new Thread() {
						public void run() {
							quickSortTime.setText("quickSort(arr);");
							return;
						}
					};
					threadForPerformance4.start();
//quickSortTime.setText(quickSort(filePath));
					break;
				}
				default:
					System.out.println("I dont know how but you somehow managed to break something");
					break;
				}
				
			}
		}
	}
}
