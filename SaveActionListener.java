import java.awt.event.*;

public class SaveActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		if(OpenActionListener.gfp!=null) {
			String[] revisedGfp = OpenActionListener.gfp.returnFilePath().split(".xml");
			if(revisedGfp[0]!=null) {
				new WriteXml(revisedGfp[0]);
				Frame.pathLabel.setPathLabel(revisedGfp[0]+".xml");
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
		}
		else
			OpenActionListener.gfp = null;
	}
}
