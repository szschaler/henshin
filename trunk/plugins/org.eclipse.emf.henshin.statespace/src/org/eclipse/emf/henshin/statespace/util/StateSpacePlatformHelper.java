/*******************************************************************************
 * Copyright (c) 2010 CWI Amsterdam, Technical University Berlin, 
 * Philipps-University Marburg and others. All rights reserved. 
 * This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CWI Amsterdam - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.henshin.statespace.util;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.henshin.statespace.StateSpacePlugin;
import org.eclipse.emf.henshin.statespace.export.StateSpaceExporter;
import org.eclipse.emf.henshin.statespace.properties.StateSpacePropertiesManager;
import org.eclipse.emf.henshin.statespace.validation.Validator;

/**
 * Helper class for registering platform validators and exporters.
 * @author Christian Krause
 * @generated NOT
 */
public class StateSpacePlatformHelper {
	
	/**
	 * Load the state space validators registered via the platform.
	 * If the platform is not present, loading this class will throw an
	 * exception.
	 * @generated NOT
	 */
	public static void loadValidators() throws Throwable {
		
		// Get the extension point:
		IExtensionPoint point = Platform.getExtensionRegistry().getExtensionPoint(StateSpacePlugin.PLUGIN_ID + ".validators");
		
		// Load the validators:
		for (IConfigurationElement element : point.getConfigurationElements()) {
			if ("validator".equals(element.getName())) {
				String id = element.getAttribute("id");
				try {
					Validator validator = (Validator) element.createExecutableExtension("class");
					StateSpacePlugin.INSTANCE.getValidators().put(id, validator);
				} catch (Throwable t) {
					StateSpacePlugin.INSTANCE.logError("Error loading state space or state validator with id " + id, t);
				}
			}
		}
		
	}

	/**
	 * Load the state space exporters registered via the platform.
	 * If the platform is not present, loading this class will throw an
	 * exception.
	 * @generated NOT
	 */
	public static void loadExporters() throws Throwable {
		
		// Get the extension point:
		IExtensionPoint point = Platform.getExtensionRegistry().getExtensionPoint(StateSpacePlugin.PLUGIN_ID + ".exporters");
		
		// Load the exporters:
		for (IConfigurationElement element : point.getConfigurationElements()) {
			if ("exporter".equals(element.getName())) {
				String id = element.getAttribute("id");
				try {
					StateSpaceExporter exporter = (StateSpaceExporter) element.createExecutableExtension("class");
					StateSpacePlugin.INSTANCE.getExporters().put(id, exporter);
				} catch (Throwable t) {
					StateSpacePlugin.INSTANCE.logError("Error loading state space exporter with id " + id, t);
				}
			}
		}
		
	}

	
	/**
	 * Load the state space properties managers registered via the platform.
	 * If the platform is not present, loading this class will throw an
	 * exception.
	 * @generated NOT
	 */
	public static void loadPropertiesManagers() throws Throwable {
		
		// Get the extension point:
		IExtensionPoint point = Platform.getExtensionRegistry().getExtensionPoint(StateSpacePlugin.PLUGIN_ID + ".properties");
		
		// Load the exporters:
		for (IConfigurationElement element : point.getConfigurationElements()) {
			if ("manager".equals(element.getName())) {
				String id = element.getAttribute("id");
				try {
					StateSpacePropertiesManager manager = (StateSpacePropertiesManager) element.createExecutableExtension("class");
					StateSpacePlugin.INSTANCE.getPropertiesManager().getManagers().add(manager);
				} catch (Throwable t) {
					StateSpacePlugin.INSTANCE.logError("Error loading state space properties manager with id " + id, t);
				}
			}
		}
		
	}
}
