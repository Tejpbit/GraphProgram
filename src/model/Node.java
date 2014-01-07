package model;

import java.io.Serializable;

import view.Rect;

public class Node implements Serializable, Comparable<Node>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_NODE_SIZE = 10;

	private int id;
	private Rect rect;
	
	/**
	 * the graph that this node is part of
	 */
	private Graph graph;
	
	public Node(Graph graph, int id, int x, int y) {
		this.graph = graph;
		this.id = id;
		this.rect = new Rect(x, y, x + DEFAULT_NODE_SIZE, x + DEFAULT_NODE_SIZE);
	}

	public boolean intersects(Node n) {
		System.out.println("this" + this.rect);
		System.out.println("other" + n.rect);
		return Rect.intersects(rect, n.rect);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean hasEdgeTo(Node n) {
		return graph.edgeExistBetween(this, n);
	}
	
	@Override
	public String toString() {
		return id+"";
	}
	
	public int getX() {
		return rect.left;
	}
	
	public int getY() {
		return rect.top;
	}
	
	public void placeAt(int x, int y) {
		rect = new Rect(x, y, DEFAULT_NODE_SIZE, DEFAULT_NODE_SIZE);
	}
	
	public void move(int dx, int dy) {
		rect.offset(dx, dy);
	}

	@Override
	public int compareTo(Node n) {
		
		int dx = getX() - n.getX();
		int dy = getY() - n.getY();
		
		if (dy == 0)
			return dx;
		return dy;
	}
}