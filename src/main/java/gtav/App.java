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
	public static void main(String[] args) throws IOException {
		// TODO Add GUI with javax.
		App app = new App();
		app.repaint();
		StartupMeta startup_meta = new StartupMeta();
		Process process = new Process();

		app.setLayout(new FlowLayout(FlowLayout.LEFT));
		app.setSize(430, 200);
		app.setLocation(100, 100);
		app.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JLabel passcode_title_label = new JLabel("Passcode");

		JTextField session_unique = new JTextField(null, "", ABORT);
		session_unique.setSize(250, 100);
		session_unique.setLocation(0, 0);

		JButton killButton = new JButton("Kill");
		killButton.setPreferredSize(new Dimension(100, 60));
		JButton deleteButton = new JButton("Delete");
		deleteButton.setPreferredSize(new Dimension(100, 60));
		JButton applyButton = new JButton("Apply");
		applyButton.setPreferredSize(new Dimension(100, 60));
		JButton randomButton = new JButton("Random");
		randomButton.setPreferredSize(new Dimension(100, 60));

		JLabel status_label = new JLabel();

		killButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				String[] process_name_list = {"GTA5.exe","PlayGTAV.exe"};
				for(String process_name:process_name_list){
					status_label.setText("Killing process " + process_name);
					try {
						process.kill(process_name);
						status_label.setText("Killed process " + process_name);
					} catch(Exception e){
						e.printStackTrace();
						status_label.setText(e.getMessage());
					}
				}
			}
		});
		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				String sess_unique = session_unique.getText();
				String[] process_name_list = {"GTA5.exe","PlayGTAV.exe"};
				try {
					status_label.setText("Applying Private Session");
					startup_meta.apply(sess_unique);
				} catch (Exception e) {
					e.printStackTrace();
					status_label.setText(e.getMessage());
				}
				for(String process_name:process_name_list){
					status_label.setText("Killing process " + process_name);
					try {
						process.kill(process_name);
						status_label.setText("Killed process " + process_name);
					} catch(Exception e){
						e.printStackTrace();
						status_label.setText(e.getMessage());
					}
				}
				status_label.setText("Applied Private Session. Now you can start GTAV");
			}
		});

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				String sess_unique = session_unique.getText();
				String[] process_name_list = {"GTA5.exe","PlayGTAV.exe"};
				try {
					status_label.setText("Deleting Private Session");
					startup_meta.delete();
				} catch (Exception e) {
					e.printStackTrace();
					status_label.setText(e.getMessage());
				}
				for(String process_name:process_name_list){
					status_label.setText("Killing process " + process_name);
					try {
						process.kill(process_name);
						status_label.setText("Killed process " + process_name);
					} catch(Exception e){
						e.printStackTrace();
						status_label.setText(e.getMessage());
					}
				}
				status_label.setText("Deleted Private Session. Now you can start GTAV");
			}
		});

		randomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				try {
					session_unique.setText(Random.randomString());
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
					status_label.setText(e.getMessage());
				}
			}
		});

		app.add(passcode_title_label);
		app.add(session_unique);
		app.add(killButton);
		app.add(applyButton);
		app.add(deleteButton);
		app.add(randomButton);
		app.add(status_label);

		app.setTitle("GTA V Public Solo Friend Session");
		app.setVisible(true);
	}
}
