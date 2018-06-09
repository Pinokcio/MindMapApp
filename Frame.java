import javax.swing.*;

import java.awt.*;

public class Frame extends JFrame{

	private static Container contentPane;
	public static JTextArea textField = new JTextArea();
	public static JPanel drawField = new JPanel();
	public static JPanel AttributePane = new JPanel();
	
	private static JSplitPane rootSplitPane = new JSplitPane();
	private static JSplitPane subRootRight = new JSplitPane();
	private static JSplitPane subRootLeft = new JSplitPane();
	private static JScrollPane textScroll = new JScrollPane(textField);
	private static JScrollPane mindScroll = new JScrollPane(drawField);
	private static JSplitPane sub_subRootRight = new JSplitPane();
	private static JScrollPane attributeScroll = new JScrollPane(AttributePane);
	public static PathLabel pathLabel = new PathLabel();
	
	//Dimension size = this.getBounds().getSize();
	public static int frameWidth = 1200;
	public static int frameHeight = 800;
	
	//AttributePane의 속성 값
	public static JTextField textTF = new JTextField(20); 
	public static JTextField xTF = new JTextField(20);
	public static JTextField yTF = new JTextField(20);
	public static JTextField wTF = new JTextField(20);
	public static JTextField hTF = new JTextField(20);
	public static JTextField colorTF = new JTextField(20);
	
	private Font font = new Font("Arial", Font.PLAIN, 20);
	
	public Frame() {
		setTitle("MindMap");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(frameWidth, frameHeight);
		contentPane = getContentPane();
		Menu();
		ToolBar();
		splitPane();
		this.addComponentListener(new ResizeListener());
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void Menu() {
		JMenuBar mb = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenu saveAndChangeMenu = new JMenu("Save & Change");

		JMenuItem newItem = new JMenuItem("New");
		JMenuItem openItem = new JMenuItem("Open");
		JMenuItem saveItem = new JMenuItem("Save");
		JMenuItem saveAsItem = new JMenuItem("SaveAs");
		JMenuItem closeItem = new JMenuItem("Close");
		JMenuItem applyItem = new JMenuItem("Apply");
		JMenuItem changeItem = new JMenuItem("Change");
		
		fileMenu.setFont(font);
		saveAndChangeMenu.setFont(font);
		
		newItem.addActionListener(new NewTextField());
		openItem.addActionListener(new OpenActionListener());
		saveItem.addActionListener(new SaveActionListener());
		saveAsItem.addActionListener(new SaveAsActionListener());
		applyItem.addActionListener(new ApplyActionListener());
		closeItem.addActionListener(new CloseActionListener());
		
		fileMenu.add(newItem);
		fileMenu.add(openItem);
		fileMenu.add(closeItem);
		saveAndChangeMenu.add(saveItem);
		saveAndChangeMenu.add(saveAsItem);
		saveAndChangeMenu.add(applyItem);
		saveAndChangeMenu.add(changeItem);
		
		mb.add(fileMenu);
		mb.add(saveAndChangeMenu);

		setJMenuBar(mb);
		setVisible(true);
	}
	public void ToolBar() {
		JToolBar toolBar = new JToolBar("Tool Bar");
		toolBar.setFloatable(true);
		
		JButton newButton = new JButton("New");
		JButton openButton = new JButton("Open");
		JButton saveButton = new JButton("Save");
		JButton saveAsButton = new JButton("SaveAs");
		JButton closeButton = new JButton("Close");
		JButton applyButton = new JButton("Apply");
		JButton changeButton = new JButton("Change");
		JButton searchButton = new JButton("Search");
		
		newButton.setFont(font);
		openButton.setFont(font);
		saveButton.setFont(font);
		saveAsButton.setFont(font);
		closeButton.setFont(font);
		applyButton.setFont(font);
		changeButton.setFont(font);
		searchButton.setFont(font);
		
		toolBar.add(newButton);
		toolBar.addSeparator();
		toolBar.add(openButton);
		toolBar.addSeparator();
		toolBar.add(saveButton);
		toolBar.addSeparator();
		toolBar.add(saveAsButton);
		toolBar.addSeparator();
		toolBar.add(closeButton);
		toolBar.addSeparator();
		toolBar.add(applyButton);
		toolBar.addSeparator();
		toolBar.add(changeButton);
		toolBar.addSeparator();
		toolBar.add(searchButton);
		newButton.addActionListener(new NewTextField());
		openButton.addActionListener(new OpenActionListener());
		saveButton.addActionListener(new SaveActionListener());
		saveAsButton.addActionListener(new SaveAsActionListener());
		applyButton.addActionListener(new ApplyActionListener());
		closeButton.addActionListener(new CloseActionListener());
		searchButton.addActionListener(new NodeSearchActionListener());
		
		toolBar.addSeparator();
		
		contentPane.add(toolBar, BorderLayout.NORTH);
	}
	public void splitPane() {
		//왼쪽
		textField.setFont(new Font("",Font.PLAIN, 30));
		textField.setTabSize(3);
		subRootLeft.setTopComponent(textScroll);

		JButton apply = new JButton("APPLY");
		apply.setFont(new Font("Arial", Font.BOLD, 20));
		apply.setBackground(Color.ORANGE);
		apply.addActionListener(new ApplyActionListener());
		subRootLeft.setBottomComponent(apply);
		
		//오른쪽
		sub_subRootRight.setOrientation(JSplitPane.VERTICAL_SPLIT);

		AttributePane.setBackground(Color.WHITE);;
		sub_subRootRight.setTopComponent(attributeScroll);
		JButton change = new JButton("CHANGE");
		change.setFont(new Font("Arial", Font.BOLD, 20));
		change.setBackground(Color.ORANGE);
		sub_subRootRight.setBottomComponent(change);
				
		subRootRight.setRightComponent(sub_subRootRight);
		sub_subRootRight.setDividerSize(0);
		
		rootSplitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		subRootRight.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		rootSplitPane.setRightComponent(subRootRight);
		subRootLeft.setOrientation(JSplitPane.VERTICAL_SPLIT);
		rootSplitPane.setLeftComponent(subRootLeft);
		

		//가운데
		drawMind();
		//locateDivider();
		drawAttribute();

		subRootLeft.setDividerSize(0);
		
		contentPane.add(rootSplitPane);
	}
	public static void drawMind() {
		drawField.setLayout(null);
		drawField.setBackground(Color.WHITE);
		subRootRight.setLeftComponent(mindScroll);
		drawField.add(pathLabel);
		locateDivider();
	}
	public static void locateDivider() {
		subRootRight.setDividerLocation((int)(frameWidth*(2/4.0)));
		sub_subRootRight.setDividerLocation(frameHeight - 180);		//버튼크기는 항상 같게
		subRootLeft.setDividerLocation(frameHeight - 180);
		rootSplitPane.setDividerLocation((int)(frameWidth*(1/4.0)));
	}
	public void drawAttribute() {
		Font font = new Font("", Font.PLAIN, 20);
		int attriPaneHeight = (frameHeight - 180);	//디바이더의 위치까지가 속성페인 높이
		int vGap = (int)(attriPaneHeight / 6.5);				//레이블 사이의 수직 갭

		AttributePane.setLayout(null);
		JLabel textLB = new JLabel("TEXT : ");
		JLabel xLB = new JLabel("X : ");
		JLabel yLB = new JLabel("Y : ");
		JLabel wLB = new JLabel("W : ");
		JLabel hLB = new JLabel("H : ");
		JLabel colorLB = new JLabel("Color : ");
		
		textLB.setFont(font);
		textLB.setBounds(40, attriPaneHeight-6*vGap, 200, 50);
		textTF.setFont(font);
		textTF.setBounds(150, attriPaneHeight-6*vGap, 150, 50);
		
		xLB.setFont(font);
		xLB.setBounds(40, attriPaneHeight-5*vGap, 200, 50);
		xTF.setFont(font);
		xTF.setBounds(150, attriPaneHeight-5*vGap, 150, 50);

		yLB.setFont(font);
		yLB.setBounds(40, attriPaneHeight-4*vGap, 200, 50);
		yTF.setFont(font);
		yTF.setBounds(150, attriPaneHeight-4*vGap, 150, 50);

		wLB.setFont(font);
		wLB.setBounds(40, attriPaneHeight-3*vGap, 200, 50);
		wTF.setFont(font);
		wTF.setBounds(150, attriPaneHeight-3*vGap, 150, 50);
		
		hLB.setFont(font);
		hLB.setBounds(40, attriPaneHeight-2*vGap, 200, 50);
		hTF.setFont(font);
		hTF.setBounds(150, attriPaneHeight-2*vGap, 150, 50);
		
		colorLB.setFont(font);
		colorLB.setBounds(40, attriPaneHeight-1*vGap, 200, 50);
		colorTF.setFont(font);
		colorTF.setBounds(150, attriPaneHeight-1*vGap, 150, 50);
		
		AttributePane.add(textLB);
		textTF.setEditable(false);
		AttributePane.add(textTF);
		AttributePane.add(xLB);
		AttributePane.add(xTF);
		AttributePane.add(yLB);
		AttributePane.add(yTF);
		AttributePane.add(wLB);
		AttributePane.add(wTF);
		AttributePane.add(hLB);
		AttributePane.add(hTF);
		AttributePane.add(colorLB);
		AttributePane.add(colorTF);

	}
}
