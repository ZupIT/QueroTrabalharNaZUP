/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cadastrodecep;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DiemersonFernando
 */
@Entity
@Table(name = "cep")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cep.findAll", query = "SELECT c FROM Cep c"),
    @NamedQuery(name = "Cep.findByCep", query = "SELECT c FROM Cep c WHERE c.cep = :cep"),
    @NamedQuery(name = "Cep.findByEndereco", query = "SELECT c FROM Cep c WHERE c.endereco = :endereco"),
    @NamedQuery(name = "Cep.findByBairro", query = "SELECT c FROM Cep c WHERE c.bairro = :bairro"),
    @NamedQuery(name = "Cep.findByCidade", query = "SELECT c FROM Cep c WHERE c.cidade = :cidade"),
    @NamedQuery(name = "Cep.findByEstado", query = "SELECT c FROM Cep c WHERE c.estado = :estado")})
public class Cep implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cep")
    private String cep;
    @Basic(optional = false)
    @Column(name = "endereco")
    private String endereco;
    @Column(name = "bairro")
    private String bairro;
    @Basic(optional = false)
    @Column(name = "cidade")
    private String cidade;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;

    public Cep() {
    }

    public Cep(String cep) {
        this.cep = cep;
    }

    public Cep(String cep, String endereco, String cidade, String estado) {
        this.cep = cep;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cep != null ? cep.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cep)) {
            return false;
        }
        Cep other = (Cep) object;
        if ((this.cep == null && other.cep != null) || (this.cep != null && !this.cep.equals(other.cep))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.cadastrodecep.Cep[ cep=" + cep + " ]";
    }
    
}
