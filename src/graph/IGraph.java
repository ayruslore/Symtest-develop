<<<<<<< HEAD
package graph;

import java.util.Set;

public interface IGraph {
	public String getId();
	public INode getRoot();
	public INode addNode(INode node);
	public INode deleteNode(INode node);
	public boolean hasNode(INode node);
	public int getNumberOfNodes();
	public IEdge addEdge(IEdge edge);
	public IEdge deleteEdge(IEdge edge);
	public boolean hasEdge(IEdge edge);
	public int getNumberOfEdges();
	public Set<INode> getNodeSet();
	public Set<IEdge> getEdgeSet();
}
=======
package graph;

import java.util.Set;

public interface IGraph {
	public String getId();
	public INode getRoot();
	public INode addNode(INode node);
	public INode deleteNode(INode node);
	public boolean hasNode(INode node);
	public int getNumberOfNodes();
	public IEdge addEdge(IEdge edge);
	public IEdge deleteEdge(IEdge edge);
	public boolean hasEdge(IEdge edge);
	public int getNumberOfEdges();
	public Set<INode> getNodeSet();
	public Set<IEdge> getEdgeSet();
}
>>>>>>> 25ebf124fd450adaed57ee1006dedb1a7734bcf9
