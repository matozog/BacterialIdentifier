package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import module.Bacteria;
import module.ConnectionManager;
import module.DatabaseManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

@XmlRootElement(name = "Bacteries")
@XmlSeeAlso(ArrayList.class)
public class BacterialDetApp implements ActionListener{

	private JFrame frame;
	private ConnectionWindow connectionWindow;
	private JTextField textFieldGenotype,textFieldClass,textFieldGamma,textFieldBeta,textFieldAplha;
	private JTable tableHistory;
	private JButton btnConnectToDatabase, btnSaveToDatabase, btnAddBacteria, btnSaveToxml, btnShowExaminedHistory,btnRemove, btnExit;
	private JPanel panelReasearchHistory,panelBacteries ;
	private JList listBacteries;
	private DefaultListModel modelBacteriesList;
	private ConnectionManager connectionManager;
	private JScrollPane scrollHistoryTable,scrollList;
	private DatabaseManager databaseManager;
	private int columnCount=0;
	private Bacteria bacteria;
	private ArrayList<Bacteria> listBacteriesToAdd = new ArrayList<Bacteria>();
	private List<Bacteria> listExaminedBacteries = new ArrayList<Bacteria>();

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
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle("Bacterial detector");
		frame.getContentPane().setLayout(null);
		connectionWindow = new ConnectionWindow();
		connectionManager = new ConnectionManager();
		bacteria = new Bacteria();
		databaseManager = new DatabaseManager();
		
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
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(188, 13, 82, 40);
		btnExit.addActionListener(this);
		frame.getContentPane().add(btnExit);
		
		btnRemove = new JButton("Remove");
		btnRemove.setBounds(134, 173, 112, 31);
		btnRemove.addActionListener(this);
		panelBacteries.add(btnRemove);
		
		btnSaveToDatabase = new JButton("Save to database and .xml");
		btnSaveToDatabase.setBounds(12, 347, 234, 31);
		btnSaveToDatabase.setEnabled(false);
		btnSaveToDatabase.addActionListener(this);
		panelBacteries.add(btnSaveToDatabase);
		
		btnAddBacteria = new JButton("Add bacteria");
		btnAddBacteria.setBounds(12, 173, 117, 31);
		btnAddBacteria.addActionListener(this);
		panelBacteries.add(btnAddBacteria);
		
		btnConnectToDatabase = new JButton("Connect to database");
		btnConnectToDatabase.setBounds(12, 13, 169, 40);
		btnConnectToDatabase.addActionListener(this);
		frame.getContentPane().add(btnConnectToDatabase);
		
		btnSaveToxml = new JButton("Save to .xml file");
		btnSaveToxml.setBounds(12, 394, 234, 37);
		btnSaveToxml.setEnabled(false);
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
				if(textFieldGenotype.getText().length()==6)
					updateTextFields();	
				else clearTextFields();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(textFieldGenotype.getText().length()==6)
					updateTextFields();
				else clearTextFields();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(textFieldGenotype.getText().length()==6)
					updateTextFields();
				else clearTextFields();

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
		
		modelBacteriesList = new DefaultListModel();
		listBacteries = new JList(modelBacteriesList);
		listBacteries.setFont(new Font("Tahoma", Font.PLAIN, 13));
		listBacteries.setBorder(new LineBorder(new Color(0, 0, 0)));
		listBacteries.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listBacteries.setLayoutOrientation(JList.VERTICAL);
		listBacteries.setVisibleRowCount(-1);
		listBacteries.setBackground(SystemColor.menu);
		scrollList = new JScrollPane(listBacteries);
		scrollList.setBounds(12, 228, 234, 106);
		panelBacteries.add(scrollList);
		
		JLabel lblGenotypeList = new JLabel("Genotype - ");
		lblGenotypeList.setBounds(12, 209, 73, 16);
		panelBacteries.add(lblGenotypeList);
		
		JLabel lblClassList = new JLabel("Class");
		lblClassList.setBounds(76, 209, 56, 16);
		panelBacteries.add(lblClassList);
		
		/*
		 * 			JTable
		 */
		
		tableHistory = new JTable();
		tableHistory.setBounds(12, 79, 234, 302);
		scrollHistoryTable = new JScrollPane(tableHistory);
		scrollHistoryTable.setBounds(12, 79, 234, 301);
		panelReasearchHistory.add(scrollHistoryTable, BorderLayout.CENTER);	
		
		
		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setBounds(289, 13, 2, 444);
		frame.getContentPane().add(separator);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object z = e.getSource();
		
		if(z == btnAddBacteria)
		{
			if(textFieldGenotype.getText().length() != 6 )
			{
				JOptionPane.showMessageDialog(null, "Wrong data!", "Warning", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				Bacteria bacteria = new Bacteria(textFieldGenotype.getText());
				bacteria.setClassification(textFieldClass.getText());
				bacteria.setAlphaGene(textFieldAplha.getText());
				bacteria.setBetaGene(textFieldBeta.getText());
				bacteria.setGammeGene(textFieldGamma.getText());
				modelBacteriesList.addElement(bacteria.getGenotyp() +  " - " + bacteria.getClassification());
				listBacteriesToAdd.add(bacteria);
				btnSaveToDatabase.setEnabled(true);
			}
		}
		else if (z == btnConnectToDatabase)
		{
			connectionWindow.setVisible(true);
		}
		else if(z == btnSaveToDatabase)
		{
			if(databaseManager.insertNewBacterie(listBacteriesToAdd))
			{
				saveDataToXML();
				modelBacteriesList.clear();
				listExaminedBacteries.addAll(listBacteriesToAdd);
				btnSaveToDatabase.setEnabled(false);
			}
		}
		else if(z == btnSaveToxml)
		{
			saveDataToXML();
		}
		else if(z == btnShowExaminedHistory)
		{
			showExaminedHistory();
			btnSaveToxml.setEnabled(true);
		}
		else if(z == btnRemove)
		{
			if(modelBacteriesList.size()!=0 && listBacteries.getSelectedIndex()!=-1)
			{
				listBacteriesToAdd.remove(listBacteries.getSelectedIndex());
				modelBacteriesList.removeElementAt(listBacteries.getSelectedIndex());
			}
			else JOptionPane.showMessageDialog(null,"Empty list or do not choose bacteria!", "Warning", JOptionPane.WARNING_MESSAGE);
			if(modelBacteriesList.size()==0) btnSaveToDatabase.setEnabled(false);
		}
		else if (z == btnExit)
		{
			if(JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Question", JOptionPane.YES_NO_OPTION) == 0)
			{
				databaseManager.exitProgram();
				System.exit(0);
			}
		}
	}
	
	public void clearTextFields()
	{
		textFieldAplha.setText("");
		textFieldBeta.setText("");
		textFieldGamma.setText("");
		textFieldClass.setText("");
	}
	
	public void saveDataToXML()
	{
		 try {
		    	JAXBContext jaxbContext = JAXBContext.newInstance(BacterialDetApp.class);
			    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.marshal(this, new File("files/ExaminedHistory.xml"));
			} catch (JAXBException e) {
				e.printStackTrace();
			}
	}
	
	public void updateTextFields()
	{
			bacteria.setGenotyp(textFieldGenotype.getText());
			textFieldAplha.setText(bacteria.calculateAlpha(textFieldGenotype.getText()));
			textFieldBeta.setText(bacteria.calculateBeta(textFieldGenotype.getText()));
			textFieldGamma.setText(bacteria.calculateGamma(textFieldGenotype.getText()));
			textFieldClass.setText(databaseManager.calculateClassification(bacteria));
	}
	
	public void showExaminedHistory() 
	{
		databaseManager.readFlagellaTable();
		databaseManager.readToughnessTable();
	 	Vector data = new Vector();
		Vector column = new Vector();
	 	Vector row = null;
		try {

			 databaseManager.setStatement((Statement) connectionManager.getConnection().createStatement());
			 databaseManager.setResultSet(databaseManager.getStatement().executeQuery("call showHistoryResearch();"));
			 ResultSetMetaData rsmd = (ResultSetMetaData) databaseManager.getResultSet().getMetaData();
			 columnCount = rsmd.getColumnCount();
			 for(int i=1; i<=columnCount; i++)
			 {
			 	column.add(rsmd.getColumnName(i));
			 }
			 listExaminedBacteries.clear();
			 while (databaseManager.getResultSet().next())
			 {
				Bacteria bacteria = new Bacteria();
			 	row = new Vector();
			 	for(int i=1; i<=columnCount; i++)
			 	{
			 		row.add(databaseManager.getResultSet().getString(i));	
			 	}
				data.add(row);
				bacteria.setGenotyp((String)row.get(0));
				bacteria.setClassification((String)row.get(1));
				listExaminedBacteries.add(bacteria);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No connection to datebase!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		((DefaultTableModel) tableHistory.getModel()).setDataVector(data,column);
	}
	
	@XmlElement (name = "Bacteria")
	public List<Bacteria> getListExaminedBacteries() {
		return listExaminedBacteries;
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
