/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author EUCLIDES
 */
@Entity
@Table(name = "maginfo")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Maginfo.findAll", query = "SELECT m FROM Maginfo m"),
	@NamedQuery(name = "Maginfo.findByIssueId", query = "SELECT m FROM Maginfo m WHERE m.issueId = :issueId"),
	@NamedQuery(name = "Maginfo.findByDescription", query = "SELECT m FROM Maginfo m WHERE m.description = :description"),
	@NamedQuery(name = "Maginfo.findByIssueDate", query = "SELECT m FROM Maginfo m WHERE m.issueDate = :issueDate"),
	@NamedQuery(name = "Maginfo.findByTimestamp", query = "SELECT m FROM Maginfo m WHERE m.timestamp = :timestamp")})
public class Maginfo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @Column(name = "issue_id")
	private Integer issueId;
	@Column(name = "description")
	private String description;
	@Lob
    @Column(name = "issn_id")
	private String issnId;
	@Column(name = "issue_date")
    @Temporal(TemporalType.DATE)
	private Date issueDate;
	@Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	public Maginfo() {
		System.out.println("Isabella Flores");
	}

	public Maginfo(Integer issueId) {
		this.issueId = issueId;
	}

	public Integer getIssueId() {
		return issueId;
	}

	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIssnId() {
		return issnId;
	}

	public void setIssnId(String issnId) {
		this.issnId = issnId;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (issueId != null ? issueId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Maginfo)) {
			return false;
		}
		Maginfo other = (Maginfo) object;
		if ((this.issueId == null && other.issueId != null) || (this.issueId != null && !this.issueId.equals(other.issueId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entities.Maginfo[ issueId=" + issueId + " ]";
	}

}
