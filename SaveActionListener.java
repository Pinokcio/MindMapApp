import java.awt.event.*;

public class SaveActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		if(OpenActionListener.gfp!=null) {
			String[] revisedGfp = OpenActionListener.gfp.returnFilePath().split(".xml");
			if(revisedGfp[0]!=null) {
				new WriteXml(revisedGfp[0]);
				Frame.pathLabel.setPathLabel(revisedGfp[0]+".xml");
				if(OpenActionListener.gfp!=null)
					Frame.pathLabel.setBounds(0, 0, (revisedGfp[0].length()+4)*10, 50);
			}
		}
		else {
			createFileAndSaveXml();
		}
	}
	public void createFileAndSaveXml() {
		OpenActionListener.gfp = new GetFilePath("save");
		if(OpenActionListener.gfp.returnFilePath()!=null)
		{
			new WriteXml(OpenActionListener.gfp.returnFilePath());
			Frame.pathLabel.setPathLabel(OpenActionListener.gfp.returnFilePath()+".xml");
			if(OpenActionListener.gfp!=null)
				Frame.pathLabel.setBounds(0, 0, (OpenActionListener.gfp.returnFilePath().length()+4)*10, 50);
		}
		else
			OpenActionListener.gfp = null;
	}
}
