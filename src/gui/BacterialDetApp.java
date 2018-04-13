package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;

public class BacterialDetApp implements ActionListener{

	private JFrame frame;
	private ConnectionWindow connectionWindow;
	private JTextField textFieldGenotype,textFieldClass,textFieldGamma,textFieldBeta,textFieldAplha;
	private JTable tableHistory;
	private JButton btnConnectToDatabase, btnSaveToDatabase, btnAddBacteria, btnSaveToxml, btnShowExaminedHistory;
	private JPanel panelReasearchHistory,panelBacteries ;
	private JList listBacteries;

	/**
	 * Create the application.
	 */
	public BacterialDetApp() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 583, 503);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle("Bacterial detector");
		frame.getContentPane().setLayout(null);
		connectionWindow = new ConnectionWindow();
		
		/*
		 * 			JPanel
		 */
		
		panelBacteries = new JPanel();
		panelBacteries.setBounds(12, 66, 258, 391);
		panelBacteries.setBorder(BorderFactory.createTitledBorder("Bacteries"));
		frame.getContentPane().add(panelBacteries);
		panelBacteries.setLayout(null);
		
		panelReasearchHistory = new JPanel();
		panelReasearchHistory.setBounds(307, 13, 258, 444);
		panelReasearchHistory.setBorder(BorderFactory.createTitledBorder("History of research"));
		frame.getContentPane().add(panelReasearchHistory);
		panelReasearchHistory.setLayout(null);
				
		/*
		 * 			JButtons
		 */
		
		btnSaveToDatabase = new JButton("Save to database and .xml");
		btnSaveToDatabase.setBounds(12, 347, 234, 31);
		btnSaveToDatabase.addActionListener(this);
		panelBacteries.add(btnSaveToDatabase);
		
		btnAddBacteria = new JButton("Add bacteria");
		btnAddBacteria.setBounds(109, 170, 137, 31);
		btnAddBacteria.addActionListener(this);
		panelBacteries.add(btnAddBacteria);
		
		btnConnectToDatabase = new JButton("Connect to database");
		btnConnectToDatabase.setBounds(12, 13, 258, 40);
		btnConnectToDatabase.addActionListener(this);
		frame.getContentPane().add(btnConnectToDatabase);
		
		btnSaveToxml = new JButton("Save to .xml file");
		btnSaveToxml.setBounds(12, 394, 234, 37);
		btnSaveToxml.addActionListener(this);
		panelReasearchHistory.add(btnSaveToxml);
		
		btnShowExaminedHistory = new JButton("Show examined history");
		btnShowExaminedHistory.setBounds(12, 29, 234, 37);
		btnShowExaminedHistory.addActionListener(this);
		panelReasearchHistory.add(btnShowExaminedHistory);
		
		/*
		 * 			JTextField
		 */
		
		textFieldGenotype = new JTextField();
		textFieldGenotype.setBounds(109, 25, 137, 22);
		textFieldGenotype.getDocument().addDocumentListener( new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				updateTextFields();	
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateTextFields();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				updateTextFields();

			}
		});
		panelBacteries.add(textFieldGenotype);
		textFieldGenotype.setColumns(10);
		
		textFieldAplha = new JTextField();
		textFieldAplha.setEditable(false);
		textFieldAplha.setBounds(109, 54, 137, 22);
		panelBacteries.add(textFieldAplha);
		textFieldAplha.setColumns(10);
		
		textFieldBeta = new JTextField();
		textFieldBeta.setEditable(false);
		textFieldBeta.setBounds(109, 83, 137, 22);
		panelBacteries.add(textFieldBeta);
		textFieldBeta.setColumns(10);
		
		textFieldGamma = new JTextField();
		textFieldGamma.setEditable(false);
		textFieldGamma.setBounds(109, 112, 137, 22);
		panelBacteries.add(textFieldGamma);
		textFieldGamma.setColumns(10);
		
		textFieldClass = new JTextField();
		textFieldClass.setEditable(false);
		textFieldClass.setBounds(109, 141, 137, 22);
		panelBacteries.add(textFieldClass);
		textFieldClass.setColumns(10);
		
		
		/*
		 * 			JLabel
		 */
		
		JLabel lblGenotype = new JLabel("Genotype:");
		lblGenotype.setBounds(12, 28, 85, 16);
		panelBacteries.add(lblGenotype);
		
		JLabel lblAlphaGene = new JLabel("Alpha gene:");
		lblAlphaGene.setBounds(12, 57, 85, 16);
		panelBacteries.add(lblAlphaGene);
		
		JLabel lblBetaGene = new JLabel("Beta gene:");
		lblBetaGene.setBounds(12, 86, 85, 16);
		panelBacteries.add(lblBetaGene);
		
		JLabel lblGammaGene = new JLabel("Gamma  gene:");
		lblGammaGene.setBounds(12, 115, 85, 16);
		panelBacteries.add(lblGammaGene);
		
		JLabel lblClassification = new JLabel("Classification:");
		lblClassification.setBounds(12, 144, 85, 16);
		panelBacteries.add(lblClassification);
		
		/*
		 * 			JList
		 */
		
		listBacteries = new JList();
		listBacteries.setBorder(new LineBorder(new Color(0, 0, 0)));
		listBacteries.setBackground(SystemColor.menu);
		listBacteries.setBounds(12, 211, 234, 123);
		panelBacteries.add(listBacteries);
		
		/*
		 * 			JTable
		 */
		
		tableHistory = new JTable();
		tableHistory.setBounds(12, 79, 234, 302);
		panelReasearchHistory.add(tableHistory);		
		
		
		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setBounds(289, 13, 2, 444);
		frame.getContentPane().add(separator);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object z = e.getSource();
		
		if(z == btnAddBacteria)
		{
			
		}
		else if (z == btnConnectToDatabase)
		{
			connectionWindow.setVisible(true);
		}
		else if(z == btnSaveToDatabase)
		{
			
		}
		else if(z == btnSaveToxml)
		{
			
		}
		else if(z == btnShowExaminedHistory)
		{
			
		}
		
	}
	
	public void updateTextFields()
	{
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BacterialDetApp window = new BacterialDetApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
