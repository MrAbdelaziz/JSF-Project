/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Coder
 */
@Entity
@Table(name = "amitie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Amitie.findAll", query = "SELECT a FROM Amitie a"),
    @NamedQuery(name = "Amitie.findByIdUser1", query = "SELECT a FROM Amitie a WHERE a.amitiePK.idUser1 = :idUser1"),
    @NamedQuery(name = "Amitie.findByIdUser2", query = "SELECT a FROM Amitie a WHERE a.amitiePK.idUser2 = :idUser2"),
    @NamedQuery(name = "Amitie.findByDateAmitie", query = "SELECT a FROM Amitie a WHERE a.amitiePK.dateAmitie = :dateAmitie")})
public class Amitie implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AmitiePK amitiePK;
    @JoinColumn(name = "idUser2", referencedColumnName = "idUser", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;
    @JoinColumn(name = "idUser1", referencedColumnName = "idUser", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user1;

    public Amitie() {
    }

    public Amitie(AmitiePK amitiePK) {
        this.amitiePK = amitiePK;
    }

    public Amitie(int idUser1, int idUser2, Date dateAmitie) {
        this.amitiePK = new AmitiePK(idUser1, idUser2, dateAmitie);
    }

    public AmitiePK getAmitiePK() {
        return amitiePK;
    }

    public void setAmitiePK(AmitiePK amitiePK) {
        this.amitiePK = amitiePK;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (amitiePK != null ? amitiePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Amitie)) {
            return false;
        }
        Amitie other = (Amitie) object;
        if ((this.amitiePK == null && other.amitiePK != null) || (this.amitiePK != null && !this.amitiePK.equals(other.amitiePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Amitie[ amitiePK=" + amitiePK + " ]";
    }
    
}
