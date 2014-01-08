package com.tejpbit.graph.view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.tejpbit.graph.controller.Tool;
import com.tejpbit.graph.model.Edge;
import com.tejpbit.graph.model.Graph;
import com.tejpbit.graph.model.IViewCallBack;
import com.tejpbit.graph.model.Node;



public class GraphView extends JPanel implements MouseListener, MouseMotionListener, IViewCallBack{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<NodeView> nodes = new ArrayList<NodeView>();
	private List<EdgeView> edges = new ArrayList<EdgeView>();
	private IExtraPaintObject extraPaintObject; //if more is needed this needs to be changed to an array
	
	private Graph graph;
	
	private Tool activeTool;

	public GraphView(Graph graph) {
		this.graph = graph;
		setBackground(Color.ORANGE);
	}
	
	public void setActiveTool(Tool tool) {
		System.out.println("Setting tool: " + tool.getClass());
		removeMouseListener(this);
		activeTool = tool;
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println("repint!");
		System.out.println("number of nodes: " + nodes.size());
		for (NodeView n : nodes)
			n.paint(g);
		
		for (EdgeView e : edges) {
			e.paint(g);
		}
		if (extraPaintObject != null) {
			extraPaintObject.paint(g);
			extraPaintObject = null;
		}
	}
	

	public void addExtraPaintObject(IExtraPaintObject extraPaintObject) {
		this.extraPaintObject = extraPaintObject;
	}
	
//	/**
//	 * this makeMatrix method does not support multiGraphs... so don't do multiGraphs
//	 */
//	//TODO method in wrong class. move it to Graph
//	public String makeMatrix() {
//		StringBuilder matrixString = new StringBuilder();
//		
//		int[][] matrix = new int[nodes.size()][nodes.size()];
//		for (int i = 0 ; i < nodes.size() ; i++){
//			for (int j = 0 ; j < nodes.size() ; j++){
//				if(i != j && edges.contains(new EdgeView(nodes.get(i), nodes.get(j))))
//					++matrix[i][j];
//			}
//		}
//		
//		for (int i = 0 ; i < nodes.size() ; i++){
//			matrixString.append("\n");
//			for (int j = 0 ; j < nodes.size() ; j++){
//				matrixString.append(matrix[i][j]);
//			}
//		}
//		
//		return matrixString.toString().trim();
//	}
	
	public Graph getGraph() {
		return graph;
	}

	public void syncWithModel() {
		nodes.clear();
		edges.clear();
		//TODO fixa
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		activeTool.mouseDragged(this, e.getX(), e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		activeTool.mouseMoved(this, e.getX(), e.getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		activeTool.mouseClicked(this, e.getX(), e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		activeTool.mouseEntered(this, e.getX(), e.getY());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		activeTool.mouseExited(this, e.getX(), e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		activeTool.mousePressed(this, e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		activeTool.mouseReleased(this, e.getX(), e.getY());
	}

	@Override
	public void addNode(Node node) {
		nodes.add(new NodeView(this, node));
		repaint();
	}

	@Override
	public void removeNode(Node node) {
		nodes.remove(new NodeView(this, node));
		repaint();
	}

	@Override
	public void addEdge(Edge edge) {
		edges.add(new EdgeView(edge));
		repaint();
	}

	@Override
	public void removeEdge(Edge edge) {
		edges.remove(new EdgeView(edge));
		repaint();
	}
}
