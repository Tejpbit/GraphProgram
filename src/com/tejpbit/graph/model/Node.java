package com.tejpbit.graph.model;

import java.io.Serializable;
import java.util.List;

public class Node implements Serializable, Comparable<Node>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_NODE_RADIUS = 15;

	private int id;
	private Rect rect;
	
	/**
	 * the graph that this node is part of
	 */
	private Graph graph;
	
	public Node(Graph graph, int id, int x, int y) {
		this.graph = graph;
		this.id = id;
		this.rect = new Rect(x, y, x, y);
		this.rect.inset(- DEFAULT_NODE_RADIUS, - DEFAULT_NODE_RADIUS);
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
	
	public Rect getRect() {
		return new Rect(rect);
	}
	
	public int getX() {
		return rect.left;
	}
	
	public int getY() {
		return rect.top;
	}
	
	public int centerX() {
		return rect.centerX();
	}
	
	public int centerY() {
		return rect.centerY();
	}
	
	public boolean hasEdgeTo(Node n) {
		return graph.edgeExistBetween(this, n);
	}
	
	@Override
	public String toString() {
		return id+"";
	}
	
	public void placeAt(int x, int y) {
		rect = new Rect(x, y, DEFAULT_NODE_RADIUS, DEFAULT_NODE_RADIUS);
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

	public List<Edge> getEdges() {
		return graph.getEdgesTo(this);
	}
}