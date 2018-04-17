package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import module.*;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ConnectionWindow extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textFieldUsername, textFieldURL;
	private JButton btnConnect;
	private ConnectionManager connectionManager;

	/**
	 * Create the frame.
	 */
	public ConnectionWindow() {
		setBounds(100, 100, 289, 179);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Connection");
		
		JLabel lblUrl = new JLabel("URL:");
		lblUrl.setBounds(12, 13, 69, 16);
		contentPane.add(lblUrl);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(12, 42, 69, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(12, 71, 69, 16);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(93, 68, 178, 19);
		contentPane.add(passwordField);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(93, 39, 178, 22);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldURL = new JTextField();
		textFieldURL.setBounds(93, 10, 178, 22);
		contentPane.add(textFieldURL);
		textFieldURL.setColumns(10);
		
		connectionManager = new ConnectionManager();
		textFieldURL.setText(connectionManager.getURL());
		btnConnect = new JButton("Connect");
		btnConnect.setBounds(65, 100, 157, 33);
		btnConnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				connectionManager.setURL(textFieldURL.getText());
				connectionManager.setUsername(textFieldUsername.getText());
				connectionManager.setPassword(passwordField.getPassword());
				connectionManager.createConnection();
				setVisible(false);
			}
		});
		contentPane.add(btnConnect);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectionWindow frame = new ConnectionWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
