/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Coder
 */
@Entity
@Table(name = "position")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Position.findAll", query = "SELECT p FROM Position p"),
    @NamedQuery(name = "Position.findByIdPosition", query = "SELECT p FROM Position p WHERE p.idPosition = :idPosition"),
    @NamedQuery(name = "Position.findByLatitude", query = "SELECT p FROM Position p WHERE p.latitude = :latitude"),
    @NamedQuery(name = "Position.findByLongitude", query = "SELECT p FROM Position p WHERE p.longitude = :longitude"),
    @NamedQuery(name = "Position.findByDate", query = "SELECT p FROM Position p WHERE p.date = :date")})
public class Position implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPosition")
    private Integer idPosition;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "latitude")
    private String latitude;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "longitude")
    private String longitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User idUser;

    public Position() {
    }

    public Position(Integer idPosition) {
        this.idPosition = idPosition;
    }

    public Position(Integer idPosition, String latitude, String longitude, Date date) {
        this.idPosition = idPosition;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
    }

    public Integer getIdPosition() {
        return idPosition;
    }

    public void setIdPosition(Integer idPosition) {
        this.idPosition = idPosition;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPosition != null ? idPosition.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Position)) {
            return false;
        }
        Position other = (Position) object;
        if ((this.idPosition == null && other.idPosition != null) || (this.idPosition != null && !this.idPosition.equals(other.idPosition))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Position[ idPosition=" + idPosition + " ]";
    }
    
}
