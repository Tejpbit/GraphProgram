package com.tejpbit.graph.model;


public interface IViewCallBack {
	void addNode(Node node);
	void removeNode(Node node);
	void addEdge(Edge edge);
	void removeEdge(Edge edge);
}
