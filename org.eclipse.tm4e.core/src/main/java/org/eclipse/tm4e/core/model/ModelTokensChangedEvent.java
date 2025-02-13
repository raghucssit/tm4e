/**
 * Copyright (c) 2015-2017 Angelo ZERR.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Initial code from https://github.com/microsoft/vscode-textmate/
 * Initial copyright Copyright (C) Microsoft Corporation. All rights reserved.
 * Initial license: MIT
 *
 * Contributors:
 * - Microsoft Corporation: Initial code, written in TypeScript, licensed under MIT license
 * - Angelo Zerr <angelo.zerr@gmail.com> - translation and adaptation to Java
 */
package org.eclipse.tm4e.core.model;

import static org.eclipse.tm4e.core.internal.utils.MoreCollections.findLastElement;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.tm4e.core.internal.utils.StringUtils;

/**
 * Model tokens changed event.
 *
 * @see <a href=
 *      "https://github.com/microsoft/vscode/blob/d81ca6dfcae29a9bf0f648b94dff145b3665fac1/src/vs/editor/common/textModelEvents.ts#L100">
 *      github.com/microsoft/vscode/blob/main/src/vs/editor/common/textModelEvents.ts</a>
 */
public class ModelTokensChangedEvent {

	static final class Builder {

		private final ITMModel model;
		private final List<Range> ranges = new ArrayList<>();

		Builder(final ITMModel model) {
			this.model = model;
		}

		void registerChangedTokens(final int lineNumber) {
			final Range previousRange = findLastElement(ranges);

			if (previousRange != null && previousRange.toLineNumber == lineNumber - 1) {
				// extend previous range
				previousRange.toLineNumber++;
			} else {
				// insert new range
				ranges.add(new Range(lineNumber));
			}
		}

		@Nullable
		ModelTokensChangedEvent build() {
			if (this.ranges.isEmpty()) {
				return null;
			}
			return new ModelTokensChangedEvent(ranges, model);
		}
	}

	public final List<Range> ranges;
	public final ITMModel model;

	public ModelTokensChangedEvent(final Range range, final ITMModel model) {
		this(List.of(range), model);
	}

	public ModelTokensChangedEvent(final List<Range> ranges, final ITMModel model) {
		this.ranges = ranges;
		this.model = model;
	}

	@Override
	public String toString() {
		return StringUtils.toString(this, sb -> {
			sb.append("ranges=").append(ranges).append(", ");
			sb.append("model=").append(model);
		});
	}
}
