import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class NodeSearchActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		String nodeToSearch = JOptionPane.showInputDialog("ã��"); 
		int cnt = 0;
		String path = null;
		String informationText = null;
		ArrayList<String> searchedNode = new ArrayList<String>();
		MNode tmpNode;
		for(int i = 0; i < MNode.nodeArray.size(); i++) {
			if(MNode.nodeArray.get(i).getText().equals(nodeToSearch)) {
				tmpNode = MNode.nodeArray.get(i);
				path = tmpNode.getText();
				cnt++;
				while(tmpNode.getParent()!=null) {
					path = tmpNode.getParent().getText() + " - " + path;
					tmpNode  = tmpNode.getParent();
				}	
				searchedNode.add(path);
			}
		}
		informationText = cnt + "���� ��尡 �߰ߵǾ����ϴ�.";
		for(int i = 0 ; i < searchedNode.size(); i++) {
			informationText = informationText + "\n" +searchedNode.get(i);
		}
		if(searchedNode.size()!=0)
			JOptionPane.showMessageDialog(null,informationText,"ã��",JOptionPane.INFORMATION_MESSAGE);
		else {
			JOptionPane.showMessageDialog(null, "�Է��Ͻ� �ؽ�Ʈ�� ��尡 �߰ߵ��� �ʾҽ��ϴ�.", "ã��", JOptionPane.INFORMATION_MESSAGE);
		}
		searchedNode.clear();
	}
}
