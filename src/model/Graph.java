package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Node> nodes = new ArrayList<Node>();
	private List<Edge> edges = new ArrayList<Edge>();
	
	private int currentId;
	
	public Node addNode(int x, int y) {
		Node newNode = new Node(this, currentId++, x, y);
		
		for (Node n : nodes) {
			if (newNode.intersects(n))
				return null;
		}
		nodes.add(newNode);
		return newNode;
	}	
	
	public void addEdge(Node n1, Node n2) {
		edges.add(new Edge(n1, n2));
	}
	
	public boolean edgeExistBetween(Node n1, Node n2) {
		return edges.contains(new Edge(n1, n2)) ||
				edges.contains(new Edge(n2, n1)); // can this be done diffrently? Yes it can. Make sure the nodes in the edge is saved right
	}
	
	public List<Node> getNodes() {
		return nodes;
	}
	
	public List<Edge> getEdges() {
		return edges;
	}
	
	@Override
	public String toString() {
		return String.format("Graph Nodes=%d, Edges=%d", nodes.size(), edges.size());
	}

	public void reIndentifyNodes() {
		Collections.sort(nodes);
		
		for (int i = 0 ; i < nodes.size() ; i++) {
			nodes.get(i).setId(i);
		}
		
		System.out.println("currentIdPRE: " + currentId);
		
		currentId = nodes.size();
		System.out.println("currentIdPOST: " + currentId);
	}
}
