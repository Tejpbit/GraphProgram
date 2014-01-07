package controller;
import java.awt.event.MouseEvent;

import view.GraphView;
import view.NodeView;



public class EdgeTool extends Tool{
	
	private NodeView firstNode;
	
	public EdgeTool(GraphView graphMap) {
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
}
