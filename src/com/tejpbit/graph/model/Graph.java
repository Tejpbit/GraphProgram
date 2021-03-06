package com.tejpbit.graph.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Graph implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IViewCallBack viewCallBack;
	
	private List<Node> nodes = new ArrayList<Node>();
	private List<Edge> edges = new ArrayList<Edge>();
	
	private int currentId;

	
	public void setViewCallBack(IViewCallBack viewCallBack) {
		this.viewCallBack = viewCallBack;
	}
	/**
	 * tries to add a node at (x,y) <br/>
	 * @return true if node was successfully created, false otherwise.
	 * <p/>
	 * a node made not be created within 5 pixel radius of another
	 */
	public boolean addNode(int x, int y) {
		Node newNode = new Node(this, currentId++, x, y);
		
		for (Node n : nodes) {
			if (newNode.intersects(n))
				return false;
		}
		
		if (nodes.add(newNode)) {
			viewCallBack.addNode(newNode);
			return true;
		}
		return false;
	}	
	
	public boolean addEdge(Node n1, Node n2) {
		Edge newEdge = new Edge(n1, n2);
		if (edges.add(newEdge)) {
			viewCallBack.addEdge(newEdge);
			return true;
		}
		return false;
	}
	
	public boolean removeNode(Node n) {
		if (nodes.remove(n)) {
			viewCallBack.removeNode(n);
			removeEdgesConnectingTo(n);
			return true;
		}
		return false;
	}
	
	public void removeEdgesConnectingTo(Node n) {
		for (Edge e : new LinkedList<Edge>(edges)) {
			if (e.connectsTo(n))
				removeEdge(e);
		}
			
	}
	
	public boolean removeEdge(Edge e) { 
		if (edges.remove(e)) {
			viewCallBack.removeEdge(e);
			return true;
		}
		return false;
	}
	
	public List<Edge> getEdgesTo(Node n) {
		List<Edge> nodesEdges = new ArrayList<Edge>();
		
		for (Edge e : edges){
			if (e.getN1().equals(n) || e.getN2().equals(n))
				nodesEdges.add(e);
		}
		return nodesEdges;
	}
	
	public boolean edgeExistBetween(Node n1, Node n2) {
		return edges.contains(new Edge(n1, n2)) ||
				edges.contains(new Edge(n2, n1)); // can this be done diffrently? Yes it can. Make sure the nodes in the edge is saved right
	}
	
	public Node getNodeAt(int x, int y) {
		Node tempNode = new Node(this, Integer.MAX_VALUE, x, y);
		
		for (Node n : nodes) {
			if (n.intersects(tempNode))
				return n;
		}
		return null;
	}
	
	public List<Node> getNodes() {
		return new ArrayList<Node>(nodes);
	}
	
	public List<Edge> getEdges() {
		return new ArrayList<Edge>(edges);
	}
	
	@Override
	public String toString() {
		return getMatrix();
	}

	/**
	 * this makeMatrix method does not support multiGraphs... so don't do multiGraphs
	 */
	public String getMatrix() {
		StringBuilder matrixString = new StringBuilder();
		
		int[][] matrix = new int[nodes.size()][nodes.size()];
		for (int i = 0 ; i < nodes.size() ; i++){
			for (int j = 0 ; j < nodes.size() ; j++){
				if(i != j && edgeExistBetween(nodes.get(i), nodes.get(j)) )
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