import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Graph;
import view.GraphView;
import controller.CreateTool;
import controller.InspectTool;


public class MainFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu options = new JMenu("Options");
	private JMenuItem save = new JMenuItem("Save");
	private JMenuItem load = new JMenuItem("Load");
	private JMenuItem reIndentify = new JMenuItem("Re-Indentify");
	private JMenuItem matrix = new  JMenuItem("Matrix");
	
	private JButton createButton = new JButton("1.Create");
	private JButton destroyButton = new JButton("2.Destroy");
	private JButton inspectButton = new JButton("3.Inspect");
	
	private GraphView graphView;
	private Graph graph;
	
	private CreateTool createTool;
	private InspectTool checkNodeTool;
	
	public  MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(800, 800));
		
		// ***** MENU CREATION *****
		options.setMnemonic(KeyEvent.VK_O);
		menuBar.add(options);
		save.setMnemonic(KeyEvent.VK_S);
		load.setMnemonic(KeyEvent.VK_L);
		reIndentify.setMnemonic(KeyEvent.VK_R);
		matrix.setMnemonic(KeyEvent.VK_M);
		
		options.add(save);
		options.add(load);
		options.add(reIndentify);
		options.add(matrix);
		
		setJMenuBar(menuBar);
		//***************************
		
		createButton.setMnemonic(KeyEvent.VK_1);
		destroyButton.setMnemonic(KeyEvent.VK_2);
		inspectButton.setMnemonic(KeyEvent.VK_3);
		
		// **** NODE INFO DISPLAY *****
		JPanel displayNodeInfo = new JPanel(new GridLayout(2, 1));
		JTextField nodeId = new JTextField();
		JTextField neighbours = new JTextField();
		displayNodeInfo.add(nodeId);
		displayNodeInfo.add(neighbours);
		// ****************************
		
		graph = new Graph();
		graphView = new GraphView(graph);
		createTool = new CreateTool(graphView);
		checkNodeTool = new InspectTool(graphView, nodeId, neighbours);
		
		initializeActionListeners();
		
		
		
		// ********* EAST PANEL *************
		JPanel northPanel = new JPanel(new GridLayout(1, 0));
		northPanel.add(createButton);
		northPanel.add(destroyButton);
		northPanel.add(inspectButton);
		northPanel.add(displayNodeInfo);
		//***********************************
		
		add(northPanel, BorderLayout.NORTH);
		
		add(graphView, BorderLayout.CENTER);
		setVisible(true);
		pack();
		
		//Standard start tool is NodeTool
		graphView.setActiveTool(createTool);
		
	}

	private void saveGraph() {
		ObjectOutputStream oos; 
		try {
			oos = new ObjectOutputStream(new FileOutputStream("ThereCanBeOnlyOneSavedGraph.txt"));
			
			oos.writeObject(graph);
			
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadGraph() {
		ObjectInputStream ois;
		
		try {
			ois = new ObjectInputStream(new FileInputStream("ThereCanBeOnlyOneSavedGraph.txt"));
			
			graph = (Graph) ois.readObject();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		graphView = new GraphView(graph);
		graphView.syncWithModel();
	}
	
	private void initializeActionListeners() {
		
		
		createButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				graphView.setActiveTool(createTool);
			}
		});

		destroyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO add destroytool-switch actionlistener here
			}
		});
		
		inspectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				graphView.setActiveTool(checkNodeTool);
			}
		});
		
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveGraph();
			}
		});
		
		load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadGraph();
			}
		});
		
		reIndentify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				graph.reIndentifyNodes();
			}
		});
		
		matrix.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(MainFrame.this, new JTextArea(graphView.toString()), "Matrix", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
}
