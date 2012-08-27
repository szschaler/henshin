/**
 * <copyright>
 * Copyright (c) 2010-2012 Henshin developers. All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the Eclipse Public License v1.0 which 
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 */
package org.eclipse.emf.henshin.model.util;

import java.util.List;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.henshin.model.Action;
import org.eclipse.emf.henshin.model.Attribute;
import org.eclipse.emf.henshin.model.Edge;
import org.eclipse.emf.henshin.model.Node;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.model.actions.impl.AttributeActionHelper;
import org.eclipse.emf.henshin.model.actions.impl.EdgeActionHelper;
import org.eclipse.emf.henshin.model.actions.impl.NodeActionHelper;

/**
 * Helper class for editing actions.
 * @author Christian Krause
 * @deprecated Will be supported directly by the model.
 */
public class HenshinActionHelper {
	
	/**
	 * Get all nodes in a rule that are associated with the given action.
	 * @param rule The container rule.
	 * @param action Action or <code>null</code> for any action.
	 * @return List of nodes.
	 */
	public static List<Node> getActionNodes(Rule rule, Action action) {
		return NodeActionHelper.INSTANCE.getActionElements(rule, action);
	}

	/**
	 * Get all edges in a rule that are associated with the given action.
	 * @param rule The container rule.
	 * @param action Action or <code>null</code> for any action.
	 * @return List of edges.
	 */
	public static List<Edge> getActionEdges(Rule rule, Action action) {
		return EdgeActionHelper.INSTANCE.getActionElements(rule, action);
	}

	/**
	 * Get all attributes in a node that are associated with the given action.
	 * @param node The container node.
	 * @param action Action or <code>null</code> for any action.
	 * @return List of attributes.
	 */
	public static List<Attribute> getActionAttributes(Node node, Action action) {
		return AttributeActionHelper.INSTANCE.getActionElements(node, action);
	}

	/**
	 * For an arbitrary node in a rule graph, find the corresponding action node.
	 * @param node Some node.
	 * @return The corresponding action node.
	 */
	public static Node getActionNode(Node node) {
		return NodeActionHelper.INSTANCE.getActionNode(node);
	}

	/**
	 * For an arbitrary node in a rule graph, find the corresponding Lhs node.
	 * @param node Some node.
	 * @return The corresponding Lhs node.
	 */
	public static Node getLhsNode(Node node) {
		return NodeActionHelper.INSTANCE.getLhsNode(node);
	}

	/**
	 * Check if an edge can be created.
	 */
	public static boolean canCreateEdge(Node source, Node target, EReference type) {
		return EdgeActionHelper.INSTANCE.canCreateEdge(source, target, type);
	}

	/**
	 * Create an edge between two action nodes.
	 */
	public static Edge createEdge(Node source, Node target, EReference type) {
		return EdgeActionHelper.INSTANCE.createEdge(source, target, type);
	}

}