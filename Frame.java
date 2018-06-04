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
	
	//Dimension size = this.getBounds().getSize();
	public static int frameWidth = 1600;
	public static int frameHeight = 900;
	
	//AttributePane의 속성 값
	public static JTextField textTF = new JTextField(20); 
	public static JTextField xTF = new JTextField(20);
	public static JTextField yTF = new JTextField(20);
	public static JTextField wTF = new JTextField(20);
	public static JTextField hTF = new JTextField(20);
	public static JTextField colorTF = new JTextField(20);

	
	public Frame() {
		setTitle("프레임");
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
		
		toolBar.add(newButton);
		toolBar.add(openButton);
		toolBar.add(saveButton);
		toolBar.add(saveAsButton);
		toolBar.add(closeButton);
		toolBar.add(applyButton);
		toolBar.add(changeButton);
		
		openButton.addActionListener(new OpenActionListener());
		saveButton.addActionListener(new SaveActionListener());
		saveAsButton.addActionListener(new SaveAsActionListener());
		applyButton.addActionListener(new ApplyActionListener());
		closeButton.addActionListener(new CloseActionListener());
		
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
		
		this.contentPane.add(rootSplitPane);
	}
	public static void drawMind() {
		drawField.setLayout(null);
		drawField.setBackground(Color.WHITE);
		subRootRight.setLeftComponent(mindScroll);
		
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
		AttributePane.setLayout(new GridLayout(0, 2));
		JLabel textLB = new JLabel("TEXT : ");
		JLabel xLB = new JLabel("X : ");
		JLabel yLB = new JLabel("Y : ");
		JLabel wLB = new JLabel("W : ");
		JLabel hLB = new JLabel("H : ");
		JLabel colorLB = new JLabel("Color : ");
		textLB.setFont(font);
		xLB.setFont(font);;
		yLB.setFont(font);
		wLB.setFont(font);
		hLB.setFont(font);
		colorLB.setFont(font);
		
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
