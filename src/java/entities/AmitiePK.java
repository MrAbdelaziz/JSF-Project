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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Coder
 */
@Embeddable
public class AmitiePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUser1")
    private int idUser1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUser2")
    private int idUser2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateAmitie")
    @Temporal(TemporalType.DATE)
    private Date dateAmitie;

    public AmitiePK() {
    }

    public AmitiePK(int idUser1, int idUser2, Date dateAmitie) {
        this.idUser1 = idUser1;
        this.idUser2 = idUser2;
        this.dateAmitie = dateAmitie;
    }

    public int getIdUser1() {
        return idUser1;
    }

    public void setIdUser1(int idUser1) {
        this.idUser1 = idUser1;
    }

    public int getIdUser2() {
        return idUser2;
    }

    public void setIdUser2(int idUser2) {
        this.idUser2 = idUser2;
    }

    public Date getDateAmitie() {
        return dateAmitie;
    }

    public void setDateAmitie(Date dateAmitie) {
        this.dateAmitie = dateAmitie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUser1;
        hash += (int) idUser2;
        hash += (dateAmitie != null ? dateAmitie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AmitiePK)) {
            return false;
        }
        AmitiePK other = (AmitiePK) object;
        if (this.idUser1 != other.idUser1) {
            return false;
        }
        if (this.idUser2 != other.idUser2) {
            return false;
        }
        if ((this.dateAmitie == null && other.dateAmitie != null) || (this.dateAmitie != null && !this.dateAmitie.equals(other.dateAmitie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AmitiePK[ idUser1=" + idUser1 + ", idUser2=" + idUser2 + ", dateAmitie=" + dateAmitie + " ]";
    }
    
}
