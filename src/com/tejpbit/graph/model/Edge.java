package com.tejpbit.graph.model;

import java.io.Serializable;

public class Edge implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Node n1;
	private Node n2;
	
	public Edge(Node n1, Node n2) {
		this.n1 = n1;
		this.n2 = n2;
	}
	
	public Node getN1() {
		return n1;
	}
	
	public Node getN2() {
		return n2;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (this.getClass() != obj.getClass() || obj == null)
			return false;
		
		Edge e = (Edge)obj;
		
		return (n1 == e.n1) && (n2 == e.n2) ||
				(n2 == e.n1) && (n1 == e.n2); // TODO should i support multigraphs? beacuse currently i exclude them with this code 
	}
}
