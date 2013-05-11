/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import entities.Maginfo;
import java.net.URI;
import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 *
 * @author EUCLIDES
 */
@XmlRootElement(name = "maginfoes")
public class MaginfoesConverter {
	private Collection<Maginfo> entities;
	private Collection<MaginfoConverter> items;
	private URI uri;
	private int expandLevel;

	/** Creates a new instance of MaginfoesConverter */
	public MaginfoesConverter() {
	}

	/**
	 * Creates a new instance of MaginfoesConverter.
	 *
	 * @param entities associated entities
	 * @param uri associated uri
	 * @param expandLevel indicates the number of levels the entity graph should be expanded
	 */
	public MaginfoesConverter(Collection<Maginfo> entities, URI uri, int expandLevel) {
		this.entities = entities;
		this.uri = uri;
		this.expandLevel = expandLevel;
		getMaginfo();
	}

	/**
	 * Returns a collection of MaginfoConverter.
	 *
	 * @return a collection of MaginfoConverter
	 */
	@XmlElement
	public Collection<MaginfoConverter> getMaginfo() {
		if (items == null) {
			items = new ArrayList<MaginfoConverter>();
		}
		if (entities != null) {
			items.clear();
			for (Maginfo entity : entities) {
				items.add(new MaginfoConverter(entity, uri, expandLevel, true));
			}
		}
		return items;
	}

	/**
	 * Sets a collection of MaginfoConverter.
	 *
	 * @param a collection of MaginfoConverter to set
	 */
	public void setMaginfo(Collection<MaginfoConverter> items) {
		this.items = items;
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
	 * Returns a collection Maginfo entities.
	 *
	 * @return a collection of Maginfo entities
	 */
	@XmlTransient
	public Collection<Maginfo> getEntities() {
		entities = new ArrayList<Maginfo>();
		if (items != null) {
			for (MaginfoConverter item : items) {
				entities.add(item.getEntity());
			}
		}
		return entities;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof MaginfoesConverter)) {
			return false;
		}
		MaginfoesConverter other = (MaginfoesConverter) object;
		if ((this.uri == null && other.uri != null) || (this.uri != null && !this.uri.equals(other.uri))) {
			return false;
		}
		if (this.expandLevel != other.expandLevel) {
			return false;
		}
		if (this.items.size() != other.items.size()) {
			return false;
		}
		Set<MaginfoConverter> itemSet = new HashSet<MaginfoConverter>(this.items);
		if (!itemSet.containsAll(other.items)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = uri == null ? 0 : uri.hashCode();
		hash = 37 * hash + expandLevel;
		for (MaginfoConverter item : this.items) {
			hash = 37 * hash + item.hashCode();
		}
		return hash;
	}
}
