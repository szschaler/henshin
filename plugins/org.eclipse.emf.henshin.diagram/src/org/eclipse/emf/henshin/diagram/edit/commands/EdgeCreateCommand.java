package org.eclipse.emf.henshin.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.henshin.diagram.actions.Action;
import org.eclipse.emf.henshin.diagram.actions.ActionType;
import org.eclipse.emf.henshin.diagram.actions.NodeActionUtil;
import org.eclipse.emf.henshin.diagram.edit.policies.HenshinBaseItemSemanticEditPolicy;
import org.eclipse.emf.henshin.model.Edge;
import org.eclipse.emf.henshin.model.Graph;
import org.eclipse.emf.henshin.model.HenshinFactory;
import org.eclipse.emf.henshin.model.Node;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.model.util.HenshinGraphUtil;
import org.eclipse.emf.henshin.model.util.HenshinMappingUtil;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;

/**
 * @generated
 */
public class EdgeCreateCommand extends EditElementCommand {

	/**
	 * @generated
	 */
	private final EObject source;

	/**
	 * @generated
	 */
	private final EObject target;

	/**
	 * @generated
	 */
	private final Graph container;

	/**
	 * @generated
	 */
	public EdgeCreateCommand(CreateRelationshipRequest request, EObject source,
			EObject target) {
		super(request.getLabel(), null, request);
		this.source = source;
		this.target = target;
		container = deduceContainer(source, target);
	}

	/**
	 * @generated
	 */
	public boolean canExecuteGen() {
		if (source == null && target == null) {
			return false;
		}
		if (source != null && false == source instanceof Node) {
			return false;
		}
		if (target != null && false == target instanceof Node) {
			return false;
		}
		if (getSource() == null) {
			return true; // link creation is in progress; source is not defined yet
		}
		// target may be null here but it's possible to check constraint
		if (getContainer() == null) {
			return false;
		}
		return HenshinBaseItemSemanticEditPolicy.LinkConstraints
				.canCreateEdge_4001(getContainer(), getSource(), getTarget());
	}

	/**
	 * @generated NOT
	 */
	public boolean canExecute() {
		
		// Check the usual conditions:
		if (!canExecuteGen()) return false;
		
		// Make sure source and target have the same action:
		if (source!=null && target!=null) {
			Action sourceAction = NodeActionUtil.getNodeAction(getSource());
			Action targetAction = NodeActionUtil.getNodeAction(getTarget());
			if (sourceAction==null || targetAction==null || !sourceAction.equals(targetAction)) {
				return false;
			}
		}
		
		// Ok.
		return true;
		
	}
	
	/**
	 * @generated NOT
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		
		if (!canExecute()) {
			throw new ExecutionException(
			"Invalid arguments in create link command"); //$NON-NLS-1$
		}
		
		// Create the new edge:
		Edge edge = HenshinGraphUtil.createEdge(getSource(), getTarget(), null);		
		
		// Check if we need to create a copy in the RHS:
		Action action = NodeActionUtil.getNodeAction(getSource());
		if (action.getType()==ActionType.NONE) {
			Rule rule = HenshinGraphUtil.getRule(getSource().getGraph());
			Node sourceImage = HenshinMappingUtil.getNodeImage(getSource(), rule.getRhs(), rule.getMappings());
			Node targetImage = HenshinMappingUtil.getNodeImage(getTarget(), rule.getRhs(),  rule.getMappings());
			edge = HenshinGraphUtil.createEdge(sourceImage, targetImage, null);
		}
		
		// Configure and return:
		doConfigure(edge, monitor, info);
		((CreateElementRequest) getRequest()).setNewElement(edge);
		return CommandResult.newOKCommandResult(edge);

	}

	/**
	 * @generated
	 */
	protected void doConfigure(Edge newElement, IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		IElementType elementType = ((CreateElementRequest) getRequest())
				.getElementType();
		ConfigureRequest configureRequest = new ConfigureRequest(
				getEditingDomain(), newElement, elementType);
		configureRequest.setClientContext(((CreateElementRequest) getRequest())
				.getClientContext());
		configureRequest.addParameters(getRequest().getParameters());
		configureRequest.setParameter(CreateRelationshipRequest.SOURCE,
				getSource());
		configureRequest.setParameter(CreateRelationshipRequest.TARGET,
				getTarget());
		ICommand configureCommand = elementType
				.getEditCommand(configureRequest);
		if (configureCommand != null && configureCommand.canExecute()) {
			configureCommand.execute(monitor, info);
		}
	}

	/**
	 * @generated
	 */
	protected void setElementToEdit(EObject element) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @generated
	 */
	protected Node getSource() {
		return (Node) source;
	}

	/**
	 * @generated
	 */
	protected Node getTarget() {
		return (Node) target;
	}

	/**
	 * @generated
	 */
	public Graph getContainer() {
		return container;
	}

	/**
	 * Default approach is to traverse ancestors of the source to find instance of container.
	 * Modify with appropriate logic.
	 * @generated
	 */
	private static Graph deduceContainer(EObject source, EObject target) {
		// Find container element for the new link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null; element = element
				.eContainer()) {
			if (element instanceof Graph) {
				return (Graph) element;
			}
		}
		return null;
	}

}
