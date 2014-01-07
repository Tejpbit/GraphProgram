package controller;

import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import view.EdgeView;
import view.GraphView;
import view.NodeView;

public class CheckNodeTool extends Tool{

	JTextField idField;
	JTextField neighbourField;
	
	public CheckNodeTool(GraphView graphMap, JTextField idField, JTextField neighbourField) {
		super(graphMap);
		this.idField = idField;
		this.neighbourField = neighbourField;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		NodeView nView = getNodeAt(e);
		
		if (nView == null)
			return;
		
		idField.setText( nView.getNodeModel().getId() + "");
		
		String neighbours = "";
		
		for (EdgeView eView : nView.getConnectingEdges()) {
			neighbours += (eView.getNode1() == nView ? eView.getNode2() : eView.getNode1()).getNodeModel().toString() + ",";
		}
		
		neighbourField.setText(neighbours);
	}
	
}
