/*******************************************************************************
 * Copyright (c) 2010 CWI Amsterdam, Technical University Berlin, 
 * Philipps-University Marburg and others. All rights reserved. 
 * This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Philipps-University Marburg - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.henshin.editor.menuContributors;

import java.util.List;

import org.eclipse.emf.henshin.editor.commands.MenuContributor;
import org.eclipse.emf.henshin.editor.commands.ChangeUnitTypeCommand;
import org.eclipse.emf.henshin.model.IndependentUnit;
import org.eclipse.emf.henshin.model.PriorityUnit;
import org.eclipse.emf.henshin.model.SequentialUnit;
import org.eclipse.emf.henshin.model.TransformationUnit;
import org.eclipse.jface.action.IMenuManager;

public class TransformationUnitCommandMenuContributor extends MenuContributor {

	public static MenuContributor INSTANCE = new TransformationUnitCommandMenuContributor();
	
	@Override
	protected void contributeActions(IMenuManager menuManager, List<?> selection) {
		if ((selection.size() == 1) && (selection.get(0) instanceof TransformationUnit)
				&& ((selection.get(0) instanceof IndependentUnit) || (selection.get(0) instanceof SequentialUnit) || (selection.get(0) instanceof PriorityUnit))) {
			String replaceBy = getLabel("ReplaceBy") + " ";
			menuManager.add(createAction(replaceBy + getLabel("ReplaceBy_IndependentUnit"), new ChangeUnitTypeCommand((TransformationUnit) selection.get(0), 1)));
			menuManager.add(createAction(replaceBy + getLabel("ReplaceBy_SequentialUnit"), new ChangeUnitTypeCommand((TransformationUnit) selection.get(0), 2)));
			menuManager.add(createAction(replaceBy + getLabel("ReplaceBy_PriorityUnit"), new ChangeUnitTypeCommand((TransformationUnit) selection.get(0), 3)));
		}

	}

}