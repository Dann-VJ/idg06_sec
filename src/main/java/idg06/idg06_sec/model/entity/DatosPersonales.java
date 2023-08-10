/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package idg06.idg06_sec.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 *
 * @author DannVJ
 */
@Entity
@Table(name = "datos_personales")
public class DatosPersonales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String formacion_academica_anterior;

    @Column(length = 100)
    private String carrera;

    @Column
    private boolean trabajaActualmente;

    @Column(length = 100)
    private String dondeTrabaja;

    @Column(length = 100)
    private String lugarNacimiento;

    @Column(length = 100)
    private String situacionSentimental;

    @Column(length = 500)
    private String gustos;

    @OneToOne
    @JoinColumn(name = "id_user")
    private Usuario usuario;

    // Getters y setters
    public String getFormacionAcademicaAnterior() {
        return formacion_academica_anterior;
    }

    public void setFormacionAcademicaAnterior(String formacionAcademicaAnterior) {
        this.formacion_academica_anterior = formacionAcademicaAnterior;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public boolean isTrabajaActualmente() {
        return trabajaActualmente;
    }

    public void setTrabajaActualmente(boolean trabajaActualmente) {
        this.trabajaActualmente = trabajaActualmente;
    }

    public String getDondeTrabaja() {
        return dondeTrabaja;
    }

    public void setDondeTrabaja(String dondeTrabaja) {
        this.dondeTrabaja = dondeTrabaja;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public String getSituacionSentimental() {
        return situacionSentimental;
    }

    public void setSituacionSentimental(String situacionSentimental) {
        this.situacionSentimental = situacionSentimental;
    }

    public String getGustos() {
        return gustos;
    }

    public void setGustos(String gustos) {
        this.gustos = gustos;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // Otros getters y setters para los demás atributos
    public Usuario getUsuario() {
        return usuario;
    }
}
