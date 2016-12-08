/**
 *  Copyright (c) 2015-2016 Angelo ZERR.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 * This code is an translation of code copyrighted by Microsoft Corporation, and initially licensed under MIT.
 *
 * Contributors:
 *  - Microsoft Corporation: Initial code, written in TypeScript, licensed under MIT license
 *  - Angelo Zerr <angelo.zerr@gmail.com> - translation and adaptation to Java
 */
package org.eclipse.textmate4e.core.model;

import java.util.Arrays;
import java.util.List;

/**
 * Model tokens changed event.
 *
 */
public class ModelTokensChangedEvent {

	private final List<Range> ranges;
	private final ITMModel model;

	public ModelTokensChangedEvent(Range range, ITMModel model) {
		this(Arrays.asList(range), model);
	}

	public ModelTokensChangedEvent(List<Range> ranges, ITMModel model) {
		this.ranges = ranges;
		this.model = model;
	}

	/**
	 * Returns the list of range 
	 * @return
	 */
	public List<Range> getRanges() {
		return ranges;
	}

	public ITMModel getModel() {
		return model;
	}
}
