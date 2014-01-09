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
 * tool to create edges and verticies, also used for moving edges.
 */
public class CreateTool extends Tool implements IExtraPaintObject {

	private Node firstNode;
	int x, y;
	
	/**
	 * Gets the node at where the press occured. </br>
	 * if no node is there then nothing will happen
	 */
	@Override
	public void mousePressed(GraphView gView, int x, int y) {
		System.out.println("Press");
		firstNode = gView.getGraph().getNodeAt(x, y);
	}
	
	@Override
	public void mouseDragged(GraphView gView, int x, int y) {
		this.x = x;
		this.y = y;
		gView.addExtraPaintObject(this);
		gView.repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawLine(firstNode.centerX(), firstNode.centerY(), x, y);
	}
	
	/**
	 * if there was two different nodes where the press and where the release occured. Draw an edge between them 
	 */
	@Override
	public void mouseReleased(GraphView gView, int x, int y) {
		System.out.println("Release");
		Node n = gView.getGraph().getNodeAt(x, y);
		
		if (n == null || firstNode == null) {
			System.out.println("n: " + n);
			System.out.println("firstNode: " + firstNode);
			gView.repaint();
			return;
		}
		
		gView.getGraph().addEdge(firstNode, n);
		firstNode = null;
		gView.repaint();
	}
	
	
	/**
	 * Method for mouse clicked. Adds a node if possible. 
	 */
	@Override
	public void mouseClicked(GraphView gView, int x, int y) { //TODO But how should one move nodes?
		System.out.println("Click");
		Rect rect = new Rect(x, y , x, y);
		rect.inset( - NodeView.radius, - NodeView.radius);
		
		if (! gView.getGraph().addNode(x, y)) {
			JOptionPane.showMessageDialog(gView, "Can't place one node ontop of another.", "Bad position", JOptionPane.ERROR_MESSAGE); // TODO you don't want this import
		}
		
		gView.repaint();
	}
}
