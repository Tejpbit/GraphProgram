package controller;

import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import view.GraphView;
import view.NodeView;
import view.Rect;

/**
 * 
 * @author André
 * tool to create edges and verticies, also used for moving edges.
 */
public class CreateTool extends Tool {

	private NodeView firstNode;
	
	public CreateTool(GraphView graphMap) {
		super(graphMap);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		firstNode = getNodeAt(e);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		NodeView n = getNodeAt(e);
		
		if (n == null || firstNode == null)
			return;
		
		graphMap.addEdge(firstNode, n);
		firstNode = null;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Rect rect = new Rect(e.getX(), e.getY(), e.getX(), e.getY());
		rect.inset( - NodeView.radius, - NodeView.radius);

		if (! graphMap.addNode(rect))
			JOptionPane.showMessageDialog(graphMap, "Can't place one node ontop of another.");
	}
}
