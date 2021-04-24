package hsos;
/* General Info
 _____            _     _____                      _                 _____      _             __
/  ___|          | |   |  _  |                    (_)               |_   _|    | |           / _|              
\ `--.  ___  _ __| |_  | | | |_   _____ _ ____   ___  _____      __   | | _ __ | |_ ___ _ __| |_ __ _  ___ ___ 
 `--. \/ _ \| '__| __| | | | \ \ / / _ \ '__\ \ / / |/ _ \ \ /\ / /   | || '_ \| __/ _ \ '__|  _/ _` |/ __/ _ \
/\__/ / (_) | |  | |_  \ \_/ /\ V /  __/ |   \ V /| |  __/\ V  V /   _| || | | | ||  __/ |  | || (_| | (_|  __/
\____/ \___/|_|   \__|  \___/  \_/ \___|_|    \_/ |_|\___| \_/\_/    \___/_| |_|\__\___|_|  |_| \__,_|\___\___|
                                                                                                               
                                                                                                               
 _               ___                   _     
| |             |_  |                 (_)    
| |__  _   _      | | __ _ _ __  _ __  _ ___ 
| '_ \| | | |     | |/ _` | '_ \| '_ \| / __|
| |_) | |_| | /\__/ / (_| | | | | | | | \__ \
|_.__/ \__, | \____/ \__,_|_| |_|_| |_|_|___/
        __/ |                                
       |___/                                 
*/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;

public class SortAlgoInterface {
	static JFrame screen;
	
	JLayeredPane layer;
	private final static int HEIGHT=280;
	private final static int EXECUTE_BUTTONS_Y=200;
	static JLabel fileSelect20;
	static JLabel fileSelect1000;
	static JLabel fileSelect100k;
	static JLabel fileSelectCustom;
	static String filePath="unknown file";
	
	public SortAlgoInterface() {
		screen = new JFrame();
		
		//LoadingScreen because slow download of images
		JFrame loading = loadingScreen();
		
		screenSetup();
		makeButtons();
		makeButtonTitles();
		makeCloseButton();
		makeRunButton();
		makeRunAndCloseButtonTitles();
		makeFileDialogButton();
		makePresetFileButtons();
		makeFileSelectionTitles();
		
		loading.setVisible(false);
		screen.setVisible(true);
	}
	
	private static JFrame loadingScreen() {
		JFrame internalLoadingScreen = new JFrame();
		
		internalLoadingScreen.setBounds(0, 0, 560, HEIGHT);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		internalLoadingScreen.setLocation(dim.width/2-internalLoadingScreen.getSize().width/2,
														dim.height/2-internalLoadingScreen.getSize().height/2); 
		
		internalLoadingScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		internalLoadingScreen.setUndecorated(true);
		internalLoadingScreen.setBackground(new Color(0.1f,0.1f,0.1f,0.9f));
		internalLoadingScreen.setVisible(true);
		
		JLabel loadMessage = new JLabel("Loading...");
		Font font = new Font("Impact", Font.PLAIN, 55);
		loadMessage.setFont(font);
		loadMessage.setBounds((560-loadMessage.getPreferredSize().width)/2, 85,loadMessage.getPreferredSize().width, 100);
		loadMessage.setVisible(true);
		
		internalLoadingScreen.add(loadMessage);
		internalLoadingScreen.repaint();
		
		return internalLoadingScreen;
	}
	
	public static void setFilePath(String filePathInput) {
		filePath=filePathInput;
		System.out.println("Set");
	}
	
	public static JFrame getScreen() {
		return screen;
	}
	
	public static String getFilePath() {
		return filePath;
	}
	
	private void screenSetup() {
		layer = new JLayeredPane();
		screen.add(layer);

		screen.setBounds(0, 0, 560, HEIGHT);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		screen.setLocation(dim.width/2-screen.getSize().width/2, dim.height/2-screen.getSize().height/2); 
		
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		screen.setUndecorated(true);
		screen.setBackground(new Color(0.1f,0.1f,0.1f,0.9f));
		
		//this.screen.setVisible(true);
	}
	
	public void add(JLabel l) {
		this.layer.add(l);
	}
	
	private void makeButtons() {
		for(int i=0;i<5;i++) {
			JLabel algoSelectButton = new JLabel();
			try {
				URL url;
				switch (i) {
				case 0:{
					url=new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/InsertionSort.jpg/400px-InsertionSort.jpg");
					break;
				}
				case 1:{
					url=new URL("https://res.cloudinary.com/nlogn/images/f_auto,q_auto/v1588592390/selection-sort/selection-sort.png");
					break;
				}
				case 2:{
					url=new URL("https://www.inf.hs-flensburg.de/lang/algorithmen/sortieren/heap/heap.gif");
					break;
				}
				case 3:{
					url=new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/e/e6/Merge_sort_algorithm_diagram.svg/300px-Merge_sort_algorithm_diagram.svg.png");
					break;
				}
				case 4:{
					url=new URL("https://qnaplus.com/wp-content/uploads/2017/05/quick_sort.png");
					break;
				}
				default:
					url=new URL("https://www.inf.hs-flensburg.de/lang/algorithmen/sortieren/heap/heap.gif");
					break;
				}
				Image image = ImageIO.read(url);
				Image dimg = image.getScaledInstance(100, 100,Image.SCALE_SMOOTH);
				algoSelectButton.setIcon(new ImageIcon(dimg));
			}catch (Exception e) {
				System.out.println("Fehler beim Laden der Textur");
			}
				algoSelectButton.setBackground(Color.WHITE);
				algoSelectButton.setOpaque(true);
				this.layer.add(algoSelectButton,10);
				algoSelectButton.setBounds(10+(110*i),10, 100, 100);
				algoSelectButton.setVisible(true);
				algoSelectButton.addMouseListener(new AlgoMouseListener(i,algoSelectButton));
		}
	}
	
	private void makeButtonTitles() {
		Font font = new Font("Impact", Font.PLAIN, 16);
		
		JLabel insertionSortTitle = new JLabel("Insertion Sort");
		insertionSortTitle.setFont(font);
		this.layer.add(insertionSortTitle,0);
		insertionSortTitle.setBounds((100-insertionSortTitle.getPreferredSize().width)/2+10, 35,insertionSortTitle.getPreferredSize().width, 50);
		insertionSortTitle.setVisible(true);
		
		
		JLabel selectionSortTitle = new JLabel("Selection Sort");
		selectionSortTitle.setFont(font);
		this.layer.add(selectionSortTitle,0);
		selectionSortTitle.setBounds((100-selectionSortTitle.getPreferredSize().width)/2+120, 35,selectionSortTitle.getPreferredSize().width, 50);
		selectionSortTitle.setVisible(true);
		
		JLabel heapSortTitle = new JLabel("Heap Sort");
		heapSortTitle.setFont(font);
		this.layer.add(heapSortTitle,0);
		heapSortTitle.setBounds((100-heapSortTitle.getPreferredSize().width)/2+230, 35,heapSortTitle.getPreferredSize().width, 50);
		heapSortTitle.setVisible(true);
		
		JLabel mergeSortTitle = new JLabel("Merge Sort");
		mergeSortTitle.setFont(font);
		this.layer.add(mergeSortTitle,0);
		mergeSortTitle.setBounds((100-mergeSortTitle.getPreferredSize().width)/2+340, 35,mergeSortTitle.getPreferredSize().width, 50);
		mergeSortTitle.setVisible(true);
		
		JLabel quickSortTitle = new JLabel("Quick Sort");
		quickSortTitle.setFont(font);
		this.layer.add(quickSortTitle,0);
		quickSortTitle.setBounds((100-quickSortTitle.getPreferredSize().width)/2+450, 35,quickSortTitle.getPreferredSize().width, 50);
		quickSortTitle.setVisible(true);
	}
	
	private void makeRunButton() {
		JLabel algoRunButton = new JLabel();
		this.layer.add(algoRunButton,10);
		algoRunButton.setBounds(200,EXECUTE_BUTTONS_Y, 350, 70);
		algoRunButton.setOpaque(true);
		//algoRunButton.setBackground(Color.GREEN);
		
		algoRunButton.setBackground(new Color(0.0f,1f,0.0f,0.3f));
		
		algoRunButton.setVisible(true);
		algoRunButton.addMouseListener(new AlgoMouseListener(11,algoRunButton));
	}
	
	private void makeCloseButton() {
		JLabel algoCloseButton = new JLabel();
		this.layer.add(algoCloseButton,10);
		algoCloseButton.setBounds(10,EXECUTE_BUTTONS_Y, 180, 70);
		algoCloseButton.setOpaque(true);
		//algoCloseButton.setBackground(Color.RED);
		
		algoCloseButton.setBackground(new Color(1f,0.0f,0.0f,0.3f));
		
		algoCloseButton.setVisible(true);
		algoCloseButton.addMouseListener(new AlgoMouseListener(12,algoCloseButton));
	}
	
	private void makeRunAndCloseButtonTitles() {
		Font font = new Font("Impact", Font.PLAIN, 35);
		
		JLabel runTitle = new JLabel("RUN");
		runTitle.setFont(font);
		this.layer.add(runTitle,0);
		runTitle.setBounds((350-runTitle.getPreferredSize().width)/2+200, EXECUTE_BUTTONS_Y,runTitle.getPreferredSize().width, 70);
		runTitle.setVisible(true);
		
		JLabel closeTitle = new JLabel("Close");
		closeTitle.setFont(font);
		this.layer.add(closeTitle,0);
		closeTitle.setBounds((180-closeTitle.getPreferredSize().width)/2+10, EXECUTE_BUTTONS_Y,closeTitle.getPreferredSize().width, 70);
		closeTitle.setVisible(true);
	}
	
	private void makeFileDialogButton() {
		//Font font = new Font("Impact", Font.PLAIN, 20);
		JLabel fileDialogButton = new JLabel();
		//fileDialogButton.setBorder(new EmptyBorder(0,20,0,0));
		//fileDialogButton.setFont(font);
		/*
		try {
		URL url = new URL("https://p1.hiclipart.com/preview/43/972/60/windows-7-clean-gray-folders-gray-file-folder-icon-png-clipart.jpg");
		Image image = ImageIO.read(url);
		Image dimg = image.getScaledInstance(180, 70,Image.SCALE_SMOOTH);
		
		fileDialogButton.setIcon(new ImageIcon(dimg));
		}catch (Exception e) {
			System.out.println("ERROR");
		}
		*/
		this.layer.add(fileDialogButton,10);
		fileDialogButton.setBounds(370,120, 180, 70);
		fileDialogButton.setOpaque(true);
		fileDialogButton.setBackground(Color.LIGHT_GRAY);
		
		fileDialogButton.setVisible(true);
		fileDialogButton.addMouseListener(new AlgoFileDialogMouseListener(fileDialogButton, filePath));
		fileSelectCustom=fileDialogButton;
	}
	
	private void makePresetFileButtons() {
		
		//data_20
		JLabel data20 = new JLabel();
		data20.setBounds(10,120, 110, 70);
		data20.setOpaque(true);
		data20.setBackground(Color.LIGHT_GRAY);
		this.layer.add(data20,10);
		fileSelect20=data20;
		data20.addMouseListener(new AlgoMouseListener(20, data20));
		
		
		//data_1000
		JLabel data1000 = new JLabel();
		data1000.setBounds(20+110,120, 110, 70);
		data1000.setOpaque(true);
		data1000.setBackground(Color.LIGHT_GRAY);
		this.layer.add(data1000,10);
		fileSelect1000=data1000;
		data1000.addMouseListener(new AlgoMouseListener(21, data1000));
		
		//data_100k
		JLabel data100k = new JLabel();
		data100k.setBounds(30+220,120, 110, 70);
		data100k.setOpaque(true);
		data100k.setBackground(Color.LIGHT_GRAY);
		this.layer.add(data100k,10);
		fileSelect100k=data100k;
		data100k.addMouseListener(new AlgoMouseListener(22, data100k));
	}
	
	public static void resetFileSetButtons() {
		fileSelectCustom.setBorder(new LineBorder(Color.WHITE, 0));
		fileSelect20.setBorder(new LineBorder(Color.WHITE, 0));
		fileSelect1000.setBorder(new LineBorder(Color.WHITE, 0));
		fileSelect100k.setBorder(new LineBorder(Color.WHITE, 0));
	}
	
	public void makeFileSelectionTitles() {
			Font font = new Font("Impact", Font.PLAIN, 25);
			
			JLabel data20Title = new JLabel("data20");
			data20Title.setFont(font);
			this.layer.add(data20Title,0);
			data20Title.setBounds(30, 120,data20Title.getPreferredSize().width, 70);
			data20Title.setVisible(true);
			
			JLabel data100Title = new JLabel("data1000");
			data100Title.setFont(font);
			this.layer.add(data100Title,0);
			data100Title.setBounds(140, 120,data100Title.getPreferredSize().width, 70);
			data100Title.setVisible(true);
			
			
			
			JLabel data100kTitle = new JLabel("data100k");
			data100kTitle.setFont(font);
			this.layer.add(data100kTitle,0);
			data100kTitle.setBounds(260, 120,data100kTitle.getPreferredSize().width, 70);
			data100kTitle.setVisible(true);
			
			JLabel dataCustomTitle = new JLabel("dataCustom");
			dataCustomTitle.setFont(font);
			this.layer.add(dataCustomTitle,0);
			dataCustomTitle.setBounds(400, 120,dataCustomTitle.getPreferredSize().width, 70);
			dataCustomTitle.setVisible(true);
	}
	
	public static void repaint() {
		screen.repaint();
	}
	
	public static void main(String[] args) {
		
		@SuppressWarnings("unused")
		SortAlgoInterface screen = new SortAlgoInterface();
	}


}

