package param.controller.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import param.controller.access.SesionBean;
import param.model.entities.GenTipositio;
import param.model.generic.Funciones;
import param.model.generic.Mensaje;
import param.model.manager.ManagerSitios;

/**
 * @author Juan Carlos Estévez Hidalgo
 * 
 */
@SessionScoped
@ManagedBean
public class TipoSitiosBean {

	// Atributos de la Clase
	@EJB
	private ManagerSitios manager;
	
	private SesionBean sesion;

	private Integer tsi_id;
	private String tsi_nombre;
	private String tsi_descripcion;
	private String tsi_estado;

	private boolean edicion;

	public TipoSitiosBean() {
		sesion = new SesionBean();
		sesion.validarSesion();
		edicion = false;
	}

	/**
	 * @return the tsi_id
	 */
	public Integer getTsi_id() {
		return tsi_id;
	}

	/**
	 * @param tsi_id
	 *            the tsi_id to set
	 */
	public void setTsi_id(Integer tsi_id) {
		this.tsi_id = tsi_id;
	}

	/**
	 * @return the tsi_nombre
	 */
	public String getTsi_nombre() {
		return tsi_nombre;
	}

	/**
	 * @param tsi_nombre
	 *            the tsi_nombre to set
	 */
	public void setTsi_nombre(String tsi_nombre) {
		this.tsi_nombre = tsi_nombre;
	}

	/**
	 * @return the tsi_descripcion
	 */
	public String getTsi_descripcion() {
		return tsi_descripcion;
	}

	/**
	 * @param tsi_descripcion
	 *            the tsi_descripcion to set
	 */
	public void setTsi_descripcion(String tsi_descripcion) {
		this.tsi_descripcion = tsi_descripcion;
	}

	/**
	 * @return the tsi_estado
	 */
	public String getTsi_estado() {
		return tsi_estado;
	}

	/**
	 * @param tsi_estado
	 *            the tsi_estado to set
	 */
	public void setTsi_estado(String tsi_estado) {
		this.tsi_estado = tsi_estado;
	}

	/**
	 * @return the edicion
	 */
	public boolean isEdicion() {
		return edicion;
	}

	/**
	 * @param edicion
	 *            the edicion to set
	 */
	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}

	public List<GenTipositio> getListTipoSitios() {
		return manager.findAllTipoSitios();
	}

	/**
	 * Redirecciona a la pagina de creacion de sitios
	 * 
	 * @return
	 */
	public String nuevoTipoSitio() {
		edicion = false;
		return "ntipositio?faces-redirect=true";
	}

	/**
	 * Permite la creacion o modificacion de un sector
	 * 
	 * @return
	 */
	public String crearTipoSitio() {
		String r = "";
		try {
			if (edicion) {
				manager.editarTipoSitios(tsi_id, tsi_nombre, tsi_descripcion,
						tsi_estado);
				Mensaje.crearMensajeINFO("Actualizado - Tipo Modificado");
			} else {
				manager.insertarTipoSitios(tsi_nombre, tsi_descripcion);
				Mensaje.crearMensajeINFO("Registrado - Tipo Creado");
			}
			r = "tipositio?faces-redirect=true";
			// limpiar datos
			tsi_id = 0;
			tsi_nombre = "";
			tsi_descripcion = "";
			tsi_estado = "A";
			edicion = false;
		} catch (Exception e) {
			Mensaje.crearMensajeERROR(e.getMessage());
		}
		return r;
	}

	/**
	 * Metodo para cargar la entidad
	 * 
	 * @param t
	 * @return
	 */
	public String cargarTipoSitio(GenTipositio t) {
		tsi_id = t.getTsiId();
		tsi_nombre = t.getTsiNombre();
		tsi_descripcion = t.getTsiDescripcion();
		tsi_estado = t.getTsiEstado();
		edicion = true;
		return "ntipositio?faces-redirect=true";
	}

	/**
	 * Cancela la accion de modificar o crear
	 * 
	 * @return
	 */
	public String cancelar() {
		// limpiar datos
		tsi_id = 0;
		tsi_nombre = "";
		tsi_descripcion = "";
		tsi_estado = "A";
		edicion = false;
		return "tipositio?faces-redirect=true";
	}

	/**
	 * Lista de estados /////////////////////metodo a
	 * repetir//////////////////////////////
	 * 
	 * @return lista de todos los estados
	 */
	public List<SelectItem> getlistEstados() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
			lista.add(new SelectItem(Funciones.estadoActivo, Funciones.estadoActivo + " : "
					+ Funciones.valorEstadoActivo));
			lista.add(new SelectItem(Funciones.estadoInactivo, Funciones.estadoInactivo + " : "
					+ Funciones.valorEstadoInactivo));
		return lista;
	}

}
