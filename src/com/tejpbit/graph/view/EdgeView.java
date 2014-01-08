package com.tejpbit.graph.view;



import java.awt.Color;
import java.awt.Graphics;

import com.tejpbit.graph.model.Edge;


public class EdgeView implements GraphComponent{
	
	private Edge edge;
	
	private Color c = Color.BLUE;
	
	public EdgeView(Edge edge) {
		System.out.println("new edge");
		this.edge = edge;
	}
	
	public Edge getEdge() {
		return edge;
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(c);
		g.drawLine(	edge.getN1().centerX(),
					edge.getN1().centerY(),
					edge.getN2().centerX(),
					edge.getN2().centerY());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		
		EdgeView e = (EdgeView)obj;
		
		return edge.equals(e.edge);
	}
	
}
