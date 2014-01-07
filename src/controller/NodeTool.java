package controller;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import view.GraphView;
import view.NodeView;
import view.Rect;





public class NodeTool extends Tool{


	public NodeTool(GraphView graphMap) {
		super(graphMap);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Rect rect = new Rect(e.getX(), e.getY(), e.getX(), e.getY());
		rect.inset( - NodeView.radius, - NodeView.radius);
		
		if (! graphMap.addNode(rect))
			JOptionPane.showMessageDialog(graphMap, "Can't place a node ontop of another.");
	}
}
