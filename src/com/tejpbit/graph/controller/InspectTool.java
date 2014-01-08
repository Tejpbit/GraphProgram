package com.tejpbit.graph.controller;

import javax.swing.JTextField;

import com.tejpbit.graph.model.Edge;
import com.tejpbit.graph.model.Node;
import com.tejpbit.graph.view.GraphView;

public class InspectTool extends Tool{

	private final JTextField idField;
	private final JTextField neighbourField;

	public InspectTool(JTextField idField, JTextField neighbourField) {
		this.idField = idField;
		this.neighbourField = neighbourField;
	}
	
	@Override
	public void mousePressed(GraphView gView, int x, int y) {
		Node n = gView.getGraph().getNodeAt(x, y);
		
		if (n == null)
			return;
		
		idField.setText( n.getId() + "");
		
		String neighbours = "";
		
		for (Edge e: n.getEdges()) {
			neighbours += (e.getN1() == n ? e.getN2() : e.getN1()).toString() + ",";
		}
		
		neighbourField.setText(neighbours);
	}
}
