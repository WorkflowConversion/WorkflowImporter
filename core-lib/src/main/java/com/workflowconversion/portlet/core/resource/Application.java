package com.workflowconversion.portlet.core.resource;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang.StringUtils;
import org.jsoup.helper.Validate;

/**
 * Simple object that contains all of the information an application requires to be executed on gUSE.
 * 
 * @author delagarza
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Application implements Serializable {

	private static final long serialVersionUID = -8200132807492156967L;

	@XmlAttribute
	private String name;
	@XmlAttribute
	private String version;
	@XmlAttribute
	private String description;
	@XmlAttribute
	private String path;

	// add a reference to the resource on which this application resides,
	// but signal that we don't want it to be serialized/deserialized
	@XmlTransient
	private Resource resource;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		Validate.isTrue(StringUtils.isNotBlank(name),
				"name cannot be null, empty or contain only whitespace characters.");
		this.name = name;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(final String version) {
		Validate.isTrue(StringUtils.isNotBlank(version),
				"version cannot be null, empty or contain only whitespace characters.");
		this.version = version;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = StringUtils.trimToEmpty(description);
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(final String path) {
		Validate.isTrue(StringUtils.isNotBlank(path),
				"path cannot be null, empty or contain only whitespace characters.");
		this.path = path;
	}

	/**
	 * @return the resource
	 */
	public Resource getResource() {
		return resource;
	}

	/**
	 * @param resource
	 *            the resource to set, can be null
	 */
	public void setResource(final Resource resource) {
		this.resource = resource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Application [name=" + name + ", version=" + version + ", description=" + description + ", path=" + path
				+ "]";
	}

	/**
	 * Generates an id/key using the relevant fields.
	 * 
	 * @return
	 */
	public String getId() {
		return "name=" + this.name + "_version=" + this.version + "_path=" + this.path;
	}

	/**
	 * An enum listing the fields of an application.
	 * 
	 * @author delagarza
	 */
	public static enum Field implements FormField {
		/**
		 * Name of the application.
		 */
		Name(256, "name", "Name"),
		/**
		 * Description.
		 */
		Description(512, "description", "Description"),
		/**
		 * Path on which the application is found.
		 */
		Path(512, "path", "Path"),
		/**
		 * Version of the application.
		 */
		Version(16, "version", "Version");

		private final int maxLength;
		private final String memberName;
		private final String displayName;

		Field(final int maxLength, final String memberName, final String displayName) {
			this.maxLength = maxLength;
			this.memberName = memberName;
			this.displayName = displayName;
		}

		/**
		 * Returns the maximum length of this field.
		 * 
		 * @return the maximum length of this field.
		 */
		@Override
		public int getMaxLength() {
			return maxLength;
		}

		/**
		 * Returns the internal name of this field. This is the name of the member in the {@link Application} class.
		 * 
		 * @return the member name of this field.
		 */
		@Override
		public String getMemberName() {
			return memberName;
		}

		/**
		 * A <i>nice</i> name that can be presented to the end user.
		 * 
		 * @return the display name.
		 */
		@Override
		public String getDisplayName() {
			return displayName;
		}
	}

}