package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.GraphView;
import view.NodeView;
import view.Rect;

public abstract class Tool implements MouseListener{

	protected GraphView graphMap;
	
	public Tool(GraphView graphMap) {
		this.graphMap = graphMap;
	}
	
	protected NodeView getNodeAt(MouseEvent e) {
		Rect rect = new Rect(e.getX(), e.getY(), e.getX(), e.getY());
		rect.inset( - NodeView.radius, - NodeView.radius);
		NodeView n = graphMap.getIntersectingNodeView( rect );
		
		return n;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
