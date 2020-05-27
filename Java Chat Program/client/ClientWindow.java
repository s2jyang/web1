package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ClientWindow {

	private JFrame frame;
	private JTextField messageField;
	private static JTextArea textArea = new JTextArea();
	
	private Client client;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					ClientWindow window = new ClientWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public ClientWindow() {
		initialize();
		
		String name = JOptionPane.showInputDialog("Enter Name");
		client = new Client(name, "localhost", 52864);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Chat Program");
		frame.setBounds(100, 100, 539, 335);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		

		textArea.setEditable(false);

		
		JScrollPane scrollPane = new JScrollPane(textArea);
		frame.getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		messageField = new JTextField();
		panel.add(messageField);
		messageField.setColumns(30);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(e -> {
			if (!messageField.getText().equals("")) {
			
			client.send(messageField.getText());
			messageField.setText("");
			}
		});
		panel.add(btnSend);
		
		frame.setLocationRelativeTo(null);
		
	}

	
	public static void printToConsole(String message) {
		textArea.setText(textArea.getText()+message+"\n");
	}
	
}
