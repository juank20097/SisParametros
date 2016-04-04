package param.controller.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import param.model.entities.GenArea;
import param.model.entities.GenSectore;
import param.model.generic.Funciones;
import param.model.generic.Mensaje;
import param.model.manager.ManagerSitios;


/**
 * @author Juan Carlos Estévez Hidalgo
 *
 */
@SessionScoped
@ManagedBean
public class AreasBean {

	// Atributos de la Clase
	@EJB
	private ManagerSitios manager;

	private Integer are_id;
	private String are_nombre;
	private String are_descripcion;
	private String are_estado;
	private Integer sector;
	private boolean edicion;

	public AreasBean() {
		edicion = false;
	}

	/**
	 * @return the are_id
	 */
	public Integer getAre_id() {
		return are_id;
	}

	/**
	 * @param are_id
	 *            the are_id to set
	 */
	public void setAre_id(Integer are_id) {
		this.are_id = are_id;
	}

	/**
	 * @return the are_nombre
	 */
	public String getAre_nombre() {
		return are_nombre;
	}

	/**
	 * @param are_nombre
	 *            the are_nombre to set
	 */
	public void setAre_nombre(String are_nombre) {
		this.are_nombre = are_nombre;
	}

	/**
	 * @return the are_descripcion
	 */
	public String getAre_descripcion() {
		return are_descripcion;
	}

	/**
	 * @param are_descripcion
	 *            the are_descripcion to set
	 */
	public void setAre_descripcion(String are_descripcion) {
		this.are_descripcion = are_descripcion;
	}

	/**
	 * @return the are_estado
	 */
	public String getAre_estado() {
		return are_estado;
	}

	/**
	 * @param are_estado
	 *            the are_estado to set
	 */
	public void setAre_estado(String are_estado) {
		this.are_estado = are_estado;
	}

	/**
	 * @return the sector
	 */
	public Integer getSector() {
		return sector;
	}

	/**
	 * @param sector
	 *            the sector to set
	 */
	public void setSector(Integer sector) {
		this.sector = sector;
	}

	/**
	 * @return the edicion
	 */
	public boolean isEdicion() {
		return edicion;
	}

	/**
	 * @param edicion the edicion to set
	 */
	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}

	public List<GenArea> getListAreas() {
		return manager.findAllAreas();
	}

	/**
	 * Redirecciona a la pagina de creacion de sitios
	 * 
	 * @return
	 */
	public String nuevaArea() {
		edicion = false;
		return "narea?faces-redirect=true";
	}

	/**
	 * Permite la creacion o modificacion de un area
	 * 
	 * @return
	 */
	public String crearArea() {
		String r = "";
		try {
			if (edicion) {
				if (sector==-1){
					Mensaje.crearMensajeERROR("Seleccione un Sector");
					r="narea?faces-redirect=true";
				} else{
				manager.editarArea(are_id, are_nombre, are_descripcion,
						are_estado, sector);
				Mensaje.crearMensajeINFO("Actualizado - Area Modificada");
				r = "area?faces-redirect=true";}
			} else {
				if (sector==-1 || sector==null){
					Mensaje.crearMensajeERROR("Seleccione un Sector");
					r="narea?faces-redirect=true";
				} else{
				manager.insertarArea(are_nombre, are_descripcion, sector);
				Mensaje.crearMensajeINFO("Registrado - Area Creada");
				r = "area?faces-redirect=true";}
			}
			// limpiar datos
			are_id = 0;
			are_nombre = "";
			are_descripcion = "";
			are_estado = "A";
			sector = 0;
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
	public String cargarArea(GenArea t) {
		are_id = t.getAreId();
		are_nombre = t.getAreNombre();
		are_descripcion = t.getAreDescripcion();
		are_estado = t.getAreEstado();
		sector = t.getGenSectore().getSecId();
		edicion = true;
		return "narea?faces-redirect=true";
	}

	/**
	 * Cancela la accion de modificar o crear 
	 * 
	 * @return
	 */
	public String cancelar() {
		// limpiar datos
		are_id = 0;
		are_nombre = "";
		are_descripcion = "";
		are_estado = "A";
		sector = 0;
		edicion = false;
		return "area?faces-redirect=true";
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

	/**
	 * Lista de instituciones
	 * 
	 * @return lista de todos los sectores
	 */
	public List<SelectItem> getlistSectores() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<GenSectore> completo = manager.findAllSector();
		for (GenSectore s : completo) {
			if (s.getSecEstado().equals("A")){
			lista.add(new SelectItem(s.getSecId(), s.getSecNombre()));
			}
		}
		return lista;
	}
}
