package view;



import java.awt.Color;
import java.awt.Graphics;


public class EdgeView implements GraphComponent{
	
	private final NodeView n1, n2;
	
	private Color c = Color.BLUE;
	
	public EdgeView(NodeView n1, NodeView n2) {
		System.out.println("new edge");
		if (n1 == null || n2 == null)
			throw new IllegalArgumentException("A node was null");
		if (n1 == n2)
			throw new IllegalArgumentException("Same node");
		
		
		this.n1 = n1;
		this.n2 = n2;
	}
	
	public NodeView getNode1() {
		return n1;
	}
	
	public NodeView getNode2() {
		return n2;
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(c);
		g.drawLine(n1.getCenterX(),
				n1.getCenterY(),
				n2.getCenterX(),
				n2.getCenterY());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		
		EdgeView e = (EdgeView)obj;
		
		return (n1 == e.n1 || n1 == e.n2) &&
				(n2 == e.n1 || n2 == e.n2);
	}
	
}
