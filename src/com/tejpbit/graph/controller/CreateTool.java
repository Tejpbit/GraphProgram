package com.tejpbit.graph.controller;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;

import com.tejpbit.graph.model.Node;
import com.tejpbit.graph.model.Rect;
import com.tejpbit.graph.view.GraphView;
import com.tejpbit.graph.view.IExtraPaintObject;
import com.tejpbit.graph.view.NodeView;

/**
 * 
 * @author André
 * tool to create Nodes and edges.
 */
public class CreateTool extends Tool implements IExtraPaintObject {

	private Node firstNode;
	int x1, y1, x2, y2;
	
	/**
	 * Gets the node at where the press occured. </br>
	 * if no node is there then nothing will happen
	 */
	@Override
	public void mousePressed(GraphView gView, int x, int y) {
		firstNode = gView.getGraph().getNodeAt(x, y);
		this.x1 = x;
		this.y1 = y;
	}
	
	@Override
	public void mouseDragged(GraphView gView, int x, int y) {
		this.x2 = x;
		this.y2 = y;
		gView.addExtraPaintObject(this);
		gView.repaint();
	}
	
	/**
	 * if there was two different nodes where the press and where the release occured. Draw an edge between them 
	 */
	@Override
	public void mouseReleased(GraphView gView, int x, int y) {
		Node n = gView.getGraph().getNodeAt(x, y);
		
		if (n != null && firstNode != null) {
			gView.getGraph().addEdge(firstNode, n);
			firstNode = null;	
		} else if (n == null && firstNode == null) {
			
		}
		
		gView.repaint();
	}
	
	/**
	 * Method for mouse clicked. Adds a node if possible. 
	 */
	@Override
	public void mouseClicked(GraphView gView, int x, int y) {
		System.out.println("Click");
		Rect rect = new Rect(x, y , x, y);
		rect.inset( - NodeView.radius, - NodeView.radius);
		
		// TODO you don't want this import JOptionPane
		if (! gView.getGraph().addNode(x, y)) {
			JOptionPane.showMessageDialog(gView, "Can't place one node ontop of another.", "Bad position", JOptionPane.ERROR_MESSAGE);
		}
		
		gView.repaint();
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		
		if (firstNode != null)
			g.drawLine(firstNode.centerX(), firstNode.centerY(), x2, y2);
		else {
			g.drawLine(x1, y1, x2, y2);
		}
	}
}
