package org.eclipse.emf.henshin.diagram.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.henshin.diagram.edit.policies.RuleCompartmentCanonicalEditPolicy;
import org.eclipse.emf.henshin.diagram.edit.policies.RuleCompartmentItemSemanticEditPolicy;
import org.eclipse.emf.henshin.diagram.part.Messages;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.model.util.RuleGraphsListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class RuleCompartmentEditPart extends ShapeCompartmentEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 7001;

	/**
	 * @generated NOT
	 */
	private RuleGraphsListener ruleListener;

	/**
	 * @generated
	 */
	public RuleCompartmentEditPart(View view) {
		super(view);
	}

	/**
	 * @generated NOT
	 */
	@Override
	public void activate() {
		super.activate();
		if (ruleListener == null) {
			Rule rule = (Rule) (getNotationView().getElement());
			ruleListener = new RuleGraphsListener(rule, new AdapterImpl() {
				public void notifyChanged(Notification event) {
					refreshSemantic();
				}
			});
		}
	}

	/**
	 * @generated NOT
	 */
	@Override
	public void deactivate() {
		if (ruleListener != null) {
			ruleListener.dispose();
			ruleListener = null;
		}
		super.deactivate();
	}

	/**
	 * @generated NOT
	 */
	private void refreshSemantic() {

		// Refresh the canonical edit policy:
		CanonicalEditPolicy policy = (CanonicalEditPolicy) getEditPolicy(EditPolicyRoles.CANONICAL_ROLE);
		policy.refresh();

		// Refresh the canonical edit policy for the TransformationEditPart so that the links get updated as well:
		if (getParent() instanceof RuleEditPart) {
			RuleEditPart ruleEditPart = (RuleEditPart) getParent();
			if (ruleEditPart.getParent() instanceof TransformationSystemEditPart) {
				TransformationSystemEditPart systemEditPart = (TransformationSystemEditPart) ruleEditPart.getParent();
				policy = (CanonicalEditPolicy) systemEditPart.getEditPolicy(EditPolicyRoles.CANONICAL_ROLE);
				policy.refresh();
			}
		}

	}

	/**
	 * @generated NOT
	 */
	@Override
	public boolean isSelectable() {
		return false;
	}

	/**
	 * @generated
	 */
	public String getCompartmentName() {
		return Messages.RuleCompartmentEditPart_title;
	}

	/**
	 * @generated
	 */
	public IFigure createFigure() {
		ResizableCompartmentFigure result = (ResizableCompartmentFigure) super
				.createFigure();
		result.setTitleVisibility(false);
		return result;
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new RuleCompartmentItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.CREATION_ROLE,
				new CreationEditPolicy());
		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE,
				new DragDropEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
				new RuleCompartmentCanonicalEditPolicy());
	}

	/**
	 * @generated
	 */
	protected void setRatio(Double ratio) {
		if (getFigure().getParent().getLayoutManager() instanceof ConstrainedToolbarLayout) {
			super.setRatio(ratio);
		}
	}

}
