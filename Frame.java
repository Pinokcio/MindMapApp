import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Frame extends JFrame{

	private Container contentPane;
	public static JTextArea textField = new JTextArea();
	public static JPanel drawField = new JPanel();
	public static JPanel AttributePane = new JPanel();
	
	private JSplitPane rootSplitPane = new JSplitPane();
	private static JSplitPane subRootRight = new JSplitPane();
	private JSplitPane subRootLeft = new JSplitPane();
	private JScrollPane textScroll = new JScrollPane(textField);
	private static JScrollPane mindScroll = new JScrollPane(drawField);
	private JSplitPane sub_subRootRight = new JSplitPane();
	private JScrollPane attributeScroll = new JScrollPane(AttributePane);

	public Frame() {
		setTitle("������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
		Menu();
		ToolBar();
		splitPane();
		setSize(900,600);
		setVisible(true);
	}
	public void Menu() {
		JMenuBar mb = new JMenuBar();
		
		JMenu newMenu = new JMenu("���� �����");
		JMenu openMenu = new JMenu("����");
		JMenu saveMenu = new JMenu("����");
		JMenu saveAsMenu = new JMenu("�ٸ� �̸����� ����");
		JMenu exitMenu = new JMenu("�ݱ�");
		JMenu applyMenu = new JMenu("����");
		JMenu changeMenu = new JMenu("����");

				
		mb.add(newMenu);
		mb.add(openMenu);
		mb.add(saveMenu);
		mb.add(saveAsMenu);
		mb.add(exitMenu);
		mb.add(applyMenu);
		mb.add(changeMenu);
		
		setJMenuBar(mb);
		setVisible(true);
	}
	public void ToolBar() {
		JToolBar toolBar = new JToolBar("Tool Bar");
		toolBar.setFloatable(true);
		
		toolBar.add(new JButton("���� �����"));
		toolBar.add(new JButton("����"));
		toolBar.add(new JButton("����"));
		toolBar.add(new JButton("�ٸ� �̸����� ����"));
		toolBar.add(new JButton("�ݱ�"));
		toolBar.add(new JButton("����"));
		toolBar.add(new JButton("����"));

		toolBar.addSeparator();
		
		JComboBox<String> combo = new JComboBox<String>();
		combo.addItem("Java");
		combo.addItem("C#");
		combo.addItem("C");
		combo.addItem("C++");
		toolBar.add(combo);
		
		contentPane.add(toolBar, BorderLayout.NORTH);
	}
	public void splitPane() {
		//����
		subRootLeft.setTopComponent(textScroll);

		JButton apply = new JButton("����");
		apply.setBackground(Color.PINK);
		apply.addMouseListener(new Apply());
		subRootLeft.setBottomComponent(apply);
		
		//������
		sub_subRootRight.setOrientation(JSplitPane.VERTICAL_SPLIT);
		sub_subRootRight.setDividerLocation(450);
		sub_subRootRight.setDividerSize(0);
		AttributePane.setBackground(Color.WHITE);;
		sub_subRootRight.setTopComponent(attributeScroll);
		JButton change = new JButton("����");
		change.setBackground(Color.PINK);
		sub_subRootRight.setBottomComponent(change);
		
		subRootRight.setRightComponent(sub_subRootRight);
		//���
		drawMind();
		
		rootSplitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		subRootRight.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		rootSplitPane.setRightComponent(subRootRight);
		subRootLeft.setOrientation(JSplitPane.VERTICAL_SPLIT);
		rootSplitPane.setLeftComponent(subRootLeft);
		
		rootSplitPane.setDividerLocation(270);
		subRootLeft.setDividerLocation(450);
		subRootLeft.setDividerSize(0);
		
		this.contentPane.add(rootSplitPane);
	}
	public static void drawMind() {
		drawField.setLayout(null);
		drawField.setBackground(Color.WHITE);
		subRootRight.setLeftComponent(mindScroll);
		subRootRight.setDividerLocation(300);
	}
}