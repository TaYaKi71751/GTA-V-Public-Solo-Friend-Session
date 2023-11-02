package gtav;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class App extends JFrame {
	public App() {
		StartupMeta startup_meta = new StartupMeta();
		Process process = new Process();

		setLayout(new FlowLayout(FlowLayout.LEFT));
		setSize(250, 200);
		setLocation(100, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JLabel passcode_title_label = new JLabel("Passcode");

		JTextField session_unique = new JTextField(null, "", ABORT);
		session_unique.setSize(250, 100);
		session_unique.setLocation(0, 0);

		JButton deleteButton = new JButton("Delete");
		deleteButton.setPreferredSize(new Dimension(100, 60));
		JButton applyButton = new JButton("Apply");
		applyButton.setPreferredSize(new Dimension(100, 60));
		JButton randomButton = new JButton("Random");
		randomButton.setPreferredSize(new Dimension(100, 60));

		JLabel status_label = new JLabel();

		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String sess_unique = session_unique.getText();
				try {
					String[] process_name_list = {"GTA5.exe","PlayGTAV.exe"};
					for(String process_name:process_name_list){
						status_label.setText("Killing process " + process_name);
						try {
							process.kill(process_name);
							status_label.setText("Killed process " + process_name);
						} catch(IOException e2){
							e2.printStackTrace();
							status_label.setText(e2.getMessage());
						}
					}
					status_label.setText("Applying Private Session");
					startup_meta.apply(sess_unique);
					status_label.setText("Applied Private Session. Now you can start GTAV");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					status_label.setText(e1.getMessage());
				}
			}
		});

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String sess_unique = session_unique.getText();
				try {
					String[] process_name_list = {"GTA5.exe","PlayGTAV.exe"};
					for(String process_name:process_name_list){
						status_label.setText("Killing process " + process_name);
						try {
							process.kill(process_name);
							status_label.setText("Killed process " + process_name);
						} catch(IOException e2){
							e2.printStackTrace();
							status_label.setText(e2.getMessage());
						}
					}
					status_label.setText("Deleting Private Session");
					startup_meta.delete();
					status_label.setText("Deleted Private Session. Now you can start GTAV");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					status_label.setText(e1.getMessage());
				}
			}
		});

		randomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try {
					session_unique.setText(Random.randomString());
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					status_label.setText(e1.getMessage());
				}
			}
		});

		add(passcode_title_label);
		add(session_unique);
		add(applyButton);
		add(deleteButton);
		add(randomButton);
		add(status_label);

		setTitle("GTA V Public Solo Friend Session");
		setVisible(true);
	}

	public static void main(String[] args) throws IOException {
		// TODO TEST THIS ON WINDOWS
		// TODO Add GUI with javax.
		new App();
	}
}
