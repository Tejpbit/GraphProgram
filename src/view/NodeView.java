package view;



import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import model.Node;

public class NodeView implements GraphComponent {

	public static final int radius = 10;
	
	private Rect rect;
	private Color c = Color.RED;
	
	private GraphView graphView;
	private final Node nodeModel;
	
	public NodeView(GraphView graphView, Node nodeModel, Rect rect) {
		this.graphView = graphView;
		this.rect = rect;
		this.nodeModel = nodeModel;
	}
	
	public int getX() {
		return rect.left;
	}
	
	public int getY() {
		return rect.top;
	}
	
	public int getCenterX(){
		return rect.centerX();
	}
	
	public boolean intersects(NodeView n) {
		System.out.println("intersects= " + rect.intersect(n.rect));
		return rect.intersect(n.rect);
	}
	
	public int getCenterY(){
		return rect.centerY();
	}
	
	public boolean hasEdgeTo(NodeView n) {
		return nodeModel.hasEdgeTo(n.nodeModel);
		
	}
	
	public void paint(Graphics g) {
		g.setColor(c);
		g.fillOval(getX(), getY(), rect.right - getX(), rect.bottom - rect.top);
	}

	public Rect getRect() {
		return rect;
	}
	
	public Node getNodeModel() {
		return nodeModel;
	}

	public List<EdgeView> getConnectingEdges() {
		return graphView.getConnectedEdges(this);
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
