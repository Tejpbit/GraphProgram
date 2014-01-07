package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.Graph;
import model.Node;



public class GraphView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<NodeView> nodes = new ArrayList<NodeView>();
	private List<EdgeView> edges = new ArrayList<EdgeView>();
	
	private Graph graph;
	
	private MouseListener activeTool;

	public GraphView(Graph graph) {
		this.graph = graph;
		setBackground(Color.ORANGE);
	}
	
	public void setActiveTool(MouseListener tool) {
		System.out.println("Setting tool: " + tool.getClass());
		removeMouseListener(activeTool);
		activeTool = tool;
		addMouseListener(tool);
		
		System.out.println("Nr of mouseListeners: " + getMouseListeners().length);
	}
	
	public boolean addNode(Rect rect) { //TODO is this method implemented correctly? try placing nodes ontop of eachother.
		Node newNode = graph.addNode(rect.centerX(), rect.centerY());
		if (newNode == null)
			return false;
		NodeView nView = new NodeView(this, newNode, rect);
		nodes.add(nView);
		repaint();
		return true;
	}

	public void addEdge(NodeView n1, NodeView n2) {
		edges.add(new EdgeView(n1, n2));
		graph.addEdge(n1.getNodeModel(), n2.getNodeModel());
		repaint();
	}
	
	/**
	 * 
	 * @return the node which n is intersecting. if n isn't intersecting any previously added node, then return null
	 */
	public NodeView getIntersectingNodeView(Rect rect) {
		for (NodeView node : nodes)
			if(rect.intersect(node.getRect()))
				return node;
		return null;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println("repint!");
		for(NodeView n : nodes)
			n.paint(g);
		
		for(EdgeView e : edges) {
			e.paint(g);
		}
	}
	
	/**
	 * this makeMatrix method does not support multiGraphs... so don't do multiGraphs
	 */
	public String makeMatrix() {
		StringBuilder matrixString = new StringBuilder();
		
		int[][] matrix = new int[nodes.size()][nodes.size()];
		for (int i = 0 ; i < nodes.size() ; i++){
			for (int j = 0 ; j < nodes.size() ; j++){
				if(i != j && edges.contains(new EdgeView(nodes.get(i), nodes.get(j))))
					++matrix[i][j];
			}
		}
		
		for (int i = 0 ; i < nodes.size() ; i++){
			matrixString.append("\n");
			for (int j = 0 ; j < nodes.size() ; j++){
				matrixString.append(matrix[i][j]);
			}
		}
		
		return matrixString.toString().trim();
	}
	
	public List<EdgeView> getConnectedEdges(NodeView nView) {
		List<EdgeView> connectedEdges = new ArrayList<EdgeView>();
		
		for (EdgeView e : edges){
			if (e.getNode1().equals(nView) || e.getNode2().equals(nView))
				connectedEdges.add(e);
		}
		return connectedEdges;
	}

	public void syncWithModel() {
		nodes.clear();
		edges.clear();
		
		for (Node node : graph.getNodes()) { //TODO FIXA .du får strukturera upp detta på någon annat vis. Just nu lägger du till noder i listan som itereras över. djävla smäck
			Rect rect = new Rect(node.getX(), node.getY(), node.getX(), node.getY());
			rect.inset( - NodeView.radius, - NodeView.radius);
			addNode(rect);
		}
	}
	
	@Override
	public String toString() {
		return makeMatrix();
	}
}
