/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import entities.Maginfo;
import java.net.URI;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;

/**
 *
 * @author EUCLIDES
 */
@XmlRootElement(name = "maginfo")
public class MaginfoConverter {
	private Maginfo entity;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of MaginfoConverter */
	public MaginfoConverter() {
		entity = new Maginfo();
	}

	/**
	 * Creates a new instance of MaginfoConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
	 */
	public MaginfoConverter(Maginfo entity, URI uri, int expandLevel, boolean isUriExtendable) {
		this.entity = entity;
		this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getIssueId() + "/").build() : uri;
		this.expandLevel = expandLevel;
	}

	/**
	 * Creates a new instance of MaginfoConverter.
	 *
	 * @param entity associated entity
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public MaginfoConverter(Maginfo entity, URI uri, int expandLevel) {
		this(entity, uri, expandLevel, false);
	}

	/**
	 * Getter for issueId.
	 *
	 * @return value for issueId
	 */
	@XmlElement
	public Integer getIssueId() {
		return (expandLevel > 0) ? entity.getIssueId() : null;
	}

	/**
	 * Setter for issueId.
	 *
	 * @param value the value to set
	 */
	public void setIssueId(Integer value) {
		entity.setIssueId(value);
	}

	/**
	 * Getter for description.
	 *
	 * @return value for description
	 */
	@XmlElement
	public String getDescription() {
		return (expandLevel > 0) ? entity.getDescription() : null;
	}

	/**
	 * Setter for description.
	 *
	 * @param value the value to set
	 */
	public void setDescription(String value) {
		entity.setDescription(value);
	}

	/**
	 * Getter for issnId.
	 *
	 * @return value for issnId
	 */
	@XmlElement
	public String getIssnId() {
		return (expandLevel > 0) ? entity.getIssnId() : null;
	}

	/**
	 * Setter for issnId.
	 *
	 * @param value the value to set
	 */
	public void setIssnId(String value) {
		entity.setIssnId(value);
	}

	/**
	 * Getter for issueDate.
	 *
	 * @return value for issueDate
	 */
	@XmlElement
	public Date getIssueDate() {
		return (expandLevel > 0) ? entity.getIssueDate() : null;
	}

	/**
	 * Setter for issueDate.
	 *
	 * @param value the value to set
	 */
	public void setIssueDate(Date value) {
		entity.setIssueDate(value);
	}

	/**
	 * Getter for timestamp.
	 *
	 * @return value for timestamp
	 */
	@XmlElement
	public Date getTimestamp() {
		return (expandLevel > 0) ? entity.getTimestamp() : null;
	}

	/**
	 * Setter for timestamp.
	 *
	 * @param value the value to set
	 */
	public void setTimestamp(Date value) {
		entity.setTimestamp(value);
	}

	/**
	 * Returns the URI associated with this converter.
	 *
	 * @return the uri
	 */
	@XmlAttribute
	public URI getUri() {
		return uri;
	}

	/**
	 * Sets the URI for this reference converter.
	 *
	 */
	public void setUri(URI uri) {
		this.uri = uri;
	}

	/**
	 * Returns the Maginfo entity.
	 *
	 * @return an entity
	 */
	@XmlTransient
	public Maginfo getEntity() {
		if (entity.getIssueId() == null) {
			MaginfoConverter converter = UriResolver.getInstance().resolve(MaginfoConverter.class, uri);
			if (converter != null) {
				entity = converter.getEntity();
			}
		}
		return entity;
	}

	/**
	 * Returns the resolved Maginfo entity.
	 *
	 * @return an resolved entity
	 */
	public Maginfo resolveEntity(EntityManager em) {
		return entity;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof MaginfoConverter)) {
			return false;
		}
		MaginfoConverter other = (MaginfoConverter) object;
		if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
			return false;
		}
		if (this.expandLevel != other.expandLevel) {
			return false;
		}
		if (expandLevel <= 0) {
			return true;
		}
		if ((this.entity == null && other.entity != null) || (this.entity != null && !this.entity.equals(other.entity))) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = uri == null ? 0 : uri.hashCode();
		if (expandLevel <= 0) {
			return hash + 37 * expandLevel;
		}
		return hash + 37 * (expandLevel + 37 * (entity == null ? 0 : entity.hashCode()));
	}
}
