package com.alexander.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author alexanderdeleon
 */
@Entity
@Table(name = "Producto")
public class Producto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column
	private String codigo;
	@Column
	private String seccion;
	@Column
	private String nombre;
	@Column
	private float precio;
	@Column
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date fecha;
	@Column
	private boolean importado;
	@Column
	private String origen;

	public Producto() {
	}

	public Producto(String codigo, String seccion, String nombre, float precio, Date fecha, boolean importado,
			String origen) {
		this.codigo = codigo;
		this.seccion = seccion;
		this.nombre = nombre;
		this.precio = precio;
		this.fecha = fecha;
		this.importado = importado;
		this.origen = origen;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean isImportado() {
		return importado;
	}

	public void setImportado(boolean importado) {
		this.importado = importado;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	@Override
	public String toString() {
		return "Producto{" + "codigo=" + codigo + ", seccion=" + seccion + ", nombre=" + nombre + ", precio="
				+ precio + ", fecha=" + fecha + ", importado=" + importado + ", origen=" + origen + '}';
	}
}
