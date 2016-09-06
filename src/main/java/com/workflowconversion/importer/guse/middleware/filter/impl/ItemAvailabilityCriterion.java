package com.workflowconversion.importer.guse.middleware.filter.impl;

import com.workflowconversion.importer.guse.filter.Criterion;

import dci.data.Item;

/**
 * Criterion based on an item's availability (i.e., whether it is enabled or not).
 * 
 * @author delagarza
 *
 */
class ItemAvailabilityCriterion implements Criterion<Item> {

	private final boolean desiredAvailability;

	ItemAvailabilityCriterion(final boolean desiredAvailability) {
		this.desiredAvailability = desiredAvailability;
	}

	@Override
	public boolean applies(final Item input) {
		return input.isEnabled() == desiredAvailability;
	}

}
