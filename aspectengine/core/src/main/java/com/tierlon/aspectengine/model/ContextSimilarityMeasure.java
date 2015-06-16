package com.tierlon.aspectengine.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ContextSimilarityMeasure {

	private Node rootNode;
	private List<ContextRepresentationKey> keyList;
	
	public ContextSimilarityMeasure(List<ContextRepresentationKey> keyList) {
		this.keyList = new ArrayList<ContextRepresentationKey>();
		this.keyList.addAll(keyList);
	}
	
	

}

class Node {
	String nodePortion;
	Map<String,Node> childMap;
	
	// ==============================================================
	// Construction
	// ==============================================================
	public Node(String nodePortion) {
		this.nodePortion = nodePortion;
		childMap = new HashMap<String,Node>();	
	}
	
	// ==============================================================
	// Operations
	// ==============================================================
	public void addChild(Node node) {
		//TODO: which node to keep if two match and exist?
		childMap.put(node.nodePortion,node);
	}
	
	public Set<Node> getChildren() {
		Set<Node> set= new HashSet<Node>();
		set.addAll(childMap.values());
		return set;
	}
	
	
	// ==============================================================
	// Private
	// ==============================================================
	Node getOrCreateNodesFor(ContextRepresentationKey key) {
		return getOrCreateNodesFor(key.getRawKey());
	}
		
	/**
	 * 
	 * @param keyPortion (non-null,non-empty) starts originally as the portion
	 * of the key that is the full path, but over each recursive call
	 *  gets cut down to be
	 * only the leaf portion.  (E.g. first time is "a.b.c.d", next is "b.c.d"
	 * next is "c.d" and finally just "d");
	 * @return the leafmost node in the representation of the 
	 */
	Node getOrCreateNodesFor(String keyPortion) {
		int index = keyPortion.indexOf(".");
		
		//Determine the proper partName and whether there is more left to do
		String partName;
		String remainder=null;
		if (index<0) {
			partName = keyPortion;
		}
		else {
			partName = keyPortion.substring(0,index);
			remainder = keyPortion.substring(index+1);
		}
		
		//Obtain a node for the given partName
		Node partNode = null;
		if (childMap.containsKey(partName)) {
			partNode = childMap.get(partName);
		} else {
			partNode = new Node(partName);
			childMap.put(partName, partNode);
		}
		
		// Now recurse if necessary or return the node.
		if (remainder==null) {
			return partNode;
		}
		
		return partNode.getOrCreateNodesFor(remainder);		
	}
	
	// ==============================================================
	// Object
	// ==============================================================
	@Override
	public String toString() {
		return "Node["+nodePortion+"]";
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Node)) {
			return false;
		}
		return nodePortion.equals(((Node)other).nodePortion);
	}
	
	@Override
	public int hashCode() {
		return nodePortion.hashCode();
	}
}
