package com.tejpbit.graph.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Line2D;

import com.tejpbit.graph.model.Edge;
import com.tejpbit.graph.model.Node;
import com.tejpbit.graph.view.GraphView;
import com.tejpbit.graph.view.IExtraPaintObject;

public class DestroyTool extends Tool implements IExtraPaintObject{

	private int x1, y1;
	private int x2, y2;

	@Override
	public void mousePressed(GraphView gView, int x, int y) {
		x1 = x;
		y1 = y;
		System.out.println(String.format("Press: (%d,%d)", x1, y1));
	}
	
	@Override
	public void mouseReleased(GraphView gView, int x, int y) {
		Line2D deleteLine = new Line2D.Double(x1, y1, x2, y2);
		
		for (Edge e : gView.getGraph().getEdges()) {
			Line2D edgeLine = new Line2D.Double(
					e.getN1().centerX(),
					e.getN1().centerY(),
					e.getN2().centerX(),
					e.getN2().centerY()
					);
			
			if (deleteLine.intersectsLine(edgeLine)) {
				gView.getGraph().removeEdge(e);
			}
		}
		
		gView.repaint();
	}

	@Override
	public void mouseDragged(GraphView gView, int x, int y) {
		x2 = x;
		y2 = y;
		gView.addExtraPaintObject(this);
		gView.repaint();
	}

	@Override
	public void mouseClicked(GraphView gView, int x, int y) {
		Node n = gView.getGraph().getNodeAt(x, y);
		gView.getGraph().removeNode(n);
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawLine(x1, y1, x2, y2);
	}
}
