package param.controller.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import param.controller.access.SesionBean;
import param.model.entities.GenInstitucione;
import param.model.generic.Funciones;
import param.model.generic.Mensaje;
import param.model.manager.ManagerSitios;

/**
 * @author jestevez
 * 
 */
@SessionScoped
@ManagedBean
public class InstitucionBean {

	// Atributos de la Clase
	@EJB
	private ManagerSitios manager;
	
	private SesionBean sesion;

	private Integer ins_id;
	private String ins_nombre;
	private String ins_descripcion;
	private String ins_estado;
	private boolean edicion;

	public InstitucionBean() {
		sesion = new SesionBean();
		sesion.validarSesion();
		edicion = false;
	}

	/**
	 * @return the ins_id
	 */
	public Integer getIns_id() {
		return ins_id;
	}

	/**
	 * @param ins_id
	 *            the ins_id to set
	 */
	public void setIns_id(Integer ins_id) {
		this.ins_id = ins_id;
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

	/**
	 * @return the ins_nombre
	 */
	public String getIns_nombre() {
		return ins_nombre;
	}

	/**
	 * @param ins_nombre
	 *            the ins_nombre to set
	 */
	public void setIns_nombre(String ins_nombre) {
		this.ins_nombre = ins_nombre;
	}

	/**
	 * @return the ins_descripcion
	 */
	public String getIns_descripcion() {
		return ins_descripcion;
	}

	/**
	 * @param ins_descripcion
	 *            the ins_descripcion to set
	 */
	public void setIns_descripcion(String ins_descripcion) {
		this.ins_descripcion = ins_descripcion;
	}

	/**
	 * @return the ins_estado
	 */
	public String getIns_estado() {
		return ins_estado;
	}

	/**
	 * @param ins_estado
	 *            the ins_estado to set
	 */
	public void setIns_estado(String ins_estado) {
		this.ins_estado = ins_estado;
	}

	public List<GenInstitucione> getListInstitucion() {
		return manager.findAllInstituciones();
	}

	/**
	 * Redirecciona a la pagina de creacion de sitios
	 * 
	 * @return
	 */
	public String nuevaInstitucion() {
		edicion = false;
		return "ninstitucion?faces-redirect=true";
	}

	/**
	 * Permite la creacion o modificacion de una institucion
	 * 
	 * @return
	 */
	public String crearInstitucion() {
		String r = "";
		try {
			if (edicion) {
				manager.editarInstitucion(ins_id, ins_nombre, ins_descripcion,
						ins_estado);
				Mensaje.crearMensajeINFO("Actualizado - Insitucion Modificada");
			} else {
				manager.insertarInstitucion(ins_nombre, ins_descripcion);
				Mensaje.crearMensajeINFO("Registrado - Insitucion Creada");
			}
			r = "institucion?faces-redirect=true";
			// limpiar datos
			ins_id = 0;
			ins_nombre = "";
			ins_descripcion = "";
			ins_estado = "A";
			edicion = false;
		} catch (Exception e) {
			Mensaje.crearMensajeERROR(e.getMessage());
		}
		return r;
	}

	/**
	 * Metodo para cargar una Intitucion para su edicion
	 * 
	 * @param t
	 * @return
	 */
	public String cargarInstitucion(GenInstitucione t) {
		ins_id = t.getInsId();
		ins_nombre = t.getInsNombre();
		ins_descripcion = t.getInsDescripcion();
		ins_estado = t.getInsEstado();
		edicion = true;
		return "ninstitucion?faces-redirect=true";
	}

	/**
	 * Cancela la accion de modificar o crear Institucion
	 * 
	 * @return
	 */
	public String cancelar() {
		// limpiar datos
		ins_id = 0;
		ins_nombre = "";
		ins_descripcion = "";
		ins_estado = "A";
		edicion = false;
		return "institucion?faces-redirect=true";
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
	
	public String pintar(String estado){
		String res="";
		if (estado.equals("A")){
			res= "Green";
		}
		if (estado.equals("I")){
			res= "Red";
		}
		return res;
	}

}
