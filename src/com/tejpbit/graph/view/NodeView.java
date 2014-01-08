package com.tejpbit.graph.view;



import java.awt.Color;
import java.awt.Graphics;

import com.tejpbit.graph.model.Node;

public class NodeView implements GraphComponent {

	public static final int radius = 10;
	
	private Color c = Color.RED;
	
	private GraphView graphView;
	private final Node nodeModel;
	
	public NodeView(GraphView graphView, Node nodeModel) {
		this.graphView = graphView;
		this.nodeModel = nodeModel;
	}
	
	public int getX() {
		return nodeModel.getX();
	}
	
	public int getY() {
		return nodeModel.getY();
	}
	
	public int getCenterX(){
		return nodeModel.centerX();
	}
	
	public int getCenterY(){
		return nodeModel.centerY();
	}
	
	public boolean intersects(NodeView n) {
		return nodeModel.intersects(n.nodeModel);
	}
	
	public boolean hasEdgeTo(NodeView n) {
		return nodeModel.hasEdgeTo(n.nodeModel);
		
	}
	
	public void paint(Graphics g) {
		g.setColor(c);
		g.fillOval(	getX(),
					getY(),
					nodeModel.getRect().right - getX(),
					nodeModel.getRect().bottom - nodeModel.getRect().top);
	}
	
	public Node getNodeModel() {
		return nodeModel;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj.getClass() != this.getClass() || obj == null)
			return false;
		
		return nodeModel == ((NodeView)obj).nodeModel;
	}
}
