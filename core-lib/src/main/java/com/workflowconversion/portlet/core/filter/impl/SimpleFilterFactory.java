package com.workflowconversion.portlet.core.filter.impl;

import java.util.Collection;
import java.util.LinkedList;

import org.apache.commons.lang3.Validate;

import com.workflowconversion.portlet.core.filter.Criterion;
import com.workflowconversion.portlet.core.filter.Filter;

import dci.data.Item;
import dci.data.Middleware;

/**
 * Factory for the Middleware/Item filter classes. This is the only public class of this package, so client code should
 * rely only on this factory.
 * 
 * @author delagarza
 *
 */
public class SimpleFilterFactory {

	private String name = null;
	private String type = null;
	private Boolean enabled = null;

	/**
	 * Sets the type.
	 * 
	 * @param type
	 *            the type.
	 * @return an instance to {@code this} {@link SimpleFilterFactory}
	 */
	public SimpleFilterFactory withType(final String type) {
		Validate.notBlank(type,
				"type cannot be null, empty or contain only whitespaces; this seems to be a coding problem and shoud be reported.");
		this.type = type;
		return this;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the name.
	 * @return an instance to {@code this} {@link SimpleFilterFactory}
	 */
	public SimpleFilterFactory withName(final String name) {
		Validate.notBlank(name,
				"name cannot be null, empty or contain only whitespaces; this seems to be a coding problem and shoud be reported.");
		this.name = name;
		return this;
	}

	/**
	 * Sets the desired availability.
	 * 
	 * @param enabled
	 *            the availability.
	 * @return an instance to {@code this} {@link SimpleFilterFactory}
	 */
	public SimpleFilterFactory setEnabled(final Boolean enabled) {
		Validate.notNull(enabled, "enabled cannot be null, this is probably a bug and should reported.");
		this.enabled = enabled;
		return this;
	}

	/**
	 * Builds a new {@link ItemFilter}.
	 * 
	 * @return a new instance of a {@link ItemFilter}.
	 */
	public Filter<Item> newItemFilter() {
		final Collection<Criterion<Item>> criteria = new LinkedList<Criterion<Item>>();
		// for items, we need name and availability
		if (name != null) {
			criteria.add(new ItemNameCriterion(name));
		}
		if (enabled != null) {
			criteria.add(new ItemAvailabilityCriterion(enabled));
		}
		return new CriteriaBasedFilter<Item>(criteria);
	}

	/**
	 * Builds a new {@link MiddlewareFilter}.
	 * 
	 * @return a new instance of a {@link MiddlewareFilter}.
	 */
	public Filter<Middleware> newMiddlewareFilter() {
		final Collection<Criterion<Middleware>> criteria = new LinkedList<Criterion<Middleware>>();
		// for middleware, we need type and availability
		if (type != null) {
			criteria.add(new MiddlewareTypeCriterion(type));
		}
		if (enabled != null) {
			criteria.add(new MiddlewareAvailabilityCriterion(enabled));
		}
		return new CriteriaBasedFilter<Middleware>(criteria);
	}

}
