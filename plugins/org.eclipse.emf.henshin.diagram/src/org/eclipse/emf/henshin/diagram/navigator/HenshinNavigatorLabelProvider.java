package org.eclipse.emf.henshin.diagram.navigator;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.henshin.diagram.actions.Action;
import org.eclipse.emf.henshin.diagram.actions.EdgeActionUtil;
import org.eclipse.emf.henshin.diagram.actions.NodeActionUtil;
import org.eclipse.emf.henshin.diagram.edit.parts.AttributeEditPart;
import org.eclipse.emf.henshin.diagram.edit.parts.EdgeEditPart;
import org.eclipse.emf.henshin.diagram.edit.parts.EdgeTypeEditPart;
import org.eclipse.emf.henshin.diagram.edit.parts.NodeEditPart;
import org.eclipse.emf.henshin.diagram.edit.parts.NodeTypeEditPart;
import org.eclipse.emf.henshin.diagram.edit.parts.RuleEditPart;
import org.eclipse.emf.henshin.diagram.edit.parts.RuleNameEditPart;
import org.eclipse.emf.henshin.diagram.edit.parts.TransformationSystemEditPart;
import org.eclipse.emf.henshin.diagram.part.HenshinDiagramEditorPlugin;
import org.eclipse.emf.henshin.diagram.part.HenshinVisualIDRegistry;
import org.eclipse.emf.henshin.diagram.providers.HenshinElementTypes;
import org.eclipse.emf.henshin.diagram.providers.HenshinParserProvider;
import org.eclipse.emf.henshin.model.Attribute;
import org.eclipse.emf.henshin.model.Edge;
import org.eclipse.emf.henshin.model.Node;
import org.eclipse.emf.henshin.model.TransformationSystem;
import org.eclipse.emf.henshin.provider.HenshinItemProviderAdapterFactory;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

/**
 * @generated
 * @implements IColorProvider
 */
public class HenshinNavigatorLabelProvider extends LabelProvider implements
		ICommonLabelProvider, ITreePathLabelProvider, IColorProvider {

	/**
	 * @generated
	 */
	static {
		HenshinDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put(
						"Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		HenshinDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put(
						"Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}

	/**
	 * @generated NOT
	 */
	private HenshinItemProviderAdapterFactory adapterFactory = new HenshinItemProviderAdapterFactory();

	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof HenshinNavigatorItem
				&& !isOwnView(((HenshinNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		if (element instanceof HenshinNavigatorGroup) {
			HenshinNavigatorGroup group = (HenshinNavigatorGroup) element;
			return HenshinDiagramEditorPlugin.getInstance().getBundledImage(
					group.getIcon());
		}

		if (element instanceof HenshinNavigatorItem) {
			HenshinNavigatorItem navigatorItem = (HenshinNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return super.getImage(element);
			}
			return getImage(navigatorItem.getView());
		}

		return super.getImage(element);
	}

	/**
	 * @generated
	 */
	public Image getImage(View view) {
		switch (HenshinVisualIDRegistry.getVisualID(view)) {
		case TransformationSystemEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?http://org.eclipse.emf.henshin?TransformationSystem", HenshinElementTypes.TransformationSystem_1000); //$NON-NLS-1$
		case RuleEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://org.eclipse.emf.henshin?Rule", HenshinElementTypes.Rule_2001); //$NON-NLS-1$
		case NodeEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://org.eclipse.emf.henshin?Node", HenshinElementTypes.Node_3001); //$NON-NLS-1$
		case AttributeEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://org.eclipse.emf.henshin?Attribute", HenshinElementTypes.Attribute_3002); //$NON-NLS-1$
		case EdgeEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://org.eclipse.emf.henshin?Edge", HenshinElementTypes.Edge_4001); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = HenshinDiagramEditorPlugin.getInstance()
				.getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null
				&& HenshinElementTypes.isKnownElementType(elementType)) {
			image = HenshinElementTypes.getImage(elementType);
			imageRegistry.put(key, image);
		}

		if (image == null) {
			image = imageRegistry.get("Navigator?ImageNotFound"); //$NON-NLS-1$
			imageRegistry.put(key, image);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public String getText(Object element) {
		if (element instanceof HenshinNavigatorGroup) {
			HenshinNavigatorGroup group = (HenshinNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof HenshinNavigatorItem) {
			HenshinNavigatorItem navigatorItem = (HenshinNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return null;
			}
			return getText(navigatorItem.getView());
		}

		return super.getText(element);
	}

	/**
	 * @generated NOT
	 */
	public String getText(View view) {

		// Get the element:
		EObject element = view.getElement();
		if (element != null && element.eIsProxy()) {
			return getUnresolvedDomainElementProxyText(view);
		}

		// Get the adapter:
		Adapter adapter = adapterFactory.createAdapter(element);
		if (adapter instanceof IItemLabelProvider) {
			return ((IItemLabelProvider) adapter).getText(element);
		}

		// Unknown element:
		return getUnknownElementText(view);

	}

	/**
	 * @generated
	 */
	private String getTransformationSystem_1000Text(View view) {
		TransformationSystem domainModelElement = (TransformationSystem) view
				.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			HenshinDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 1000); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getRule_2001Text(View view) {
		IParser parser = HenshinParserProvider.getParser(
				HenshinElementTypes.Rule_2001, view.getElement() != null ? view
						.getElement() : view, HenshinVisualIDRegistry
						.getType(RuleNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			HenshinDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getNode_3001Text(View view) {
		IParser parser = HenshinParserProvider.getParser(
				HenshinElementTypes.Node_3001, view.getElement() != null ? view
						.getElement() : view, HenshinVisualIDRegistry
						.getType(NodeTypeEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			HenshinDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5002); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getAttribute_3002Text(View view) {
		IParser parser = HenshinParserProvider.getParser(
				HenshinElementTypes.Attribute_3002,
				view.getElement() != null ? view.getElement() : view,
				HenshinVisualIDRegistry.getType(AttributeEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			HenshinDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 3002); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getEdge_4001Text(View view) {
		IParser parser = HenshinParserProvider.getParser(
				HenshinElementTypes.Edge_4001, view.getElement() != null ? view
						.getElement() : view, HenshinVisualIDRegistry
						.getType(EdgeTypeEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			HenshinDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 6001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getUnknownElementText(View view) {
		return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	private String getUnresolvedDomainElementProxyText(View view) {
		return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	 * @generated
	 */
	public void restoreState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void saveState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public String getDescription(Object anElement) {
		return null;
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return TransformationSystemEditPart.MODEL_ID
				.equals(HenshinVisualIDRegistry.getModelID(view));
	}

	/**
	 * @generated NOT
	 */
	public Color getForeground(Object element) {
		if (element instanceof HenshinNavigatorItem) {
			HenshinNavigatorItem item = (HenshinNavigatorItem) element;
			if (isOwnView(item.getView())) {
				View view = item.getView();
				if (view.getElement() instanceof Node) {
					Action action = NodeActionUtil.getNodeAction((Node) view
							.getElement());
					if (action != null)
						return action.getType().getColor();
				}
				if (view.getElement() instanceof Edge) {
					Action action = EdgeActionUtil.getEdgeAction((Edge) view
							.getElement());
					if (action != null)
						return action.getType().getColor();
				}
			}
		}
		return null;
	}

	/**
	 * @generated NOT
	 */
	public Color getBackground(Object element) {
		// Use default background color:
		return null;
	}

}
