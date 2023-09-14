package gtav;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class App extends JFrame {
	public App() {
		StartupMeta startup_meta = new StartupMeta();
		Process process = new Process();

		setLayout(new FlowLayout(FlowLayout.LEFT));
		setSize(250, 200);
		setLocation(100, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JTextField session_unique = new JTextField(null, "", ABORT);
		session_unique.setSize(250, 100);
		session_unique.setLocation(0, 0);

		JButton deleteButton = new JButton("Delete Private Session");
		deleteButton.setSize(125, 100);
		deleteButton.setLocation(0, 100);
		JButton applyButton = new JButton("Apply Private Session");
		applyButton.setSize(125, 100);
		applyButton.setLocation(125, 100);

		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String sess_unique = session_unique.getText();
				try {
					process.kill("GTA5.exe");
					process.kill("PlayGTAV.exe");
					startup_meta.apply(sess_unique);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try {
					process.kill("GTA5.exe");
					process.kill("PlayGTAV.exe");
					startup_meta.delete();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		add(session_unique);
		add(applyButton);
		add(deleteButton);

		setVisible(true);
	}

	public static void main(String[] args) throws IOException {
		// TODO TEST THIS ON WINDOWS
		// TODO Add GUI with javax.
		new App();
	}
}
