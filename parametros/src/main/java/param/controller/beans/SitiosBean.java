package param.controller.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import param.model.entities.GenArea;
import param.model.entities.GenSitio;
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
public class SitiosBean {

	// Atributos de la Clase
	@EJB
	private ManagerSitios manager;

	private Integer sit_id;
	private String sit_nombre;
	private BigDecimal sit_costo_arriendo;
	private Integer sit_capacidad;
	private String sit_estado;

	private Integer area;
	private Integer tipositio;
	private boolean edicion;

	public SitiosBean() {
		edicion = false;
	}

	/**
	 * @return the tipositio
	 */
	public Integer getTipositio() {
		return tipositio;
	}

	/**
	 * @param tipositio the tipositio to set
	 */
	public void setTipositio(Integer tipositio) {
		this.tipositio = tipositio;
	}

	/**
	 * @return the sit_id
	 */
	public Integer getSit_id() {
		return sit_id;
	}

	/**
	 * @param sit_id
	 *            the sit_id to set
	 */
	public void setSit_id(Integer sit_id) {
		this.sit_id = sit_id;
	}

	/**
	 * @return the sit_nombre
	 */
	public String getSit_nombre() {
		return sit_nombre;
	}

	/**
	 * @param sit_nombre
	 *            the sit_nombre to set
	 */
	public void setSit_nombre(String sit_nombre) {
		this.sit_nombre = sit_nombre;
	}

	/**
	 * @return the sit_costo_arriendo
	 */
	public BigDecimal getSit_costo_arriendo() {
		return sit_costo_arriendo;
	}

	/**
	 * @param sit_costo_arriendo
	 *            the sit_costo_arriendo to set
	 */
	public void setSit_costo_arriendo(BigDecimal sit_costo_arriendo) {
		this.sit_costo_arriendo = sit_costo_arriendo;
	}

	/**
	 * @return the sit_capacidad
	 */
	public Integer getSit_capacidad() {
		return sit_capacidad;
	}

	/**
	 * @param sit_capacidad
	 *            the sit_capacidad to set
	 */
	public void setSit_capacidad(Integer sit_capacidad) {
		this.sit_capacidad = sit_capacidad;
	}

	
	/**
	 * @return the sit_estado
	 */
	public String getSit_estado() {
		return sit_estado;
	}

	/**
	 * @param sit_estado the sit_estado to set
	 */
	public void setSit_estado(String sit_estado) {
		this.sit_estado = sit_estado;
	}

	/**
	 * @return the area
	 */
	public Integer getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(Integer area) {
		this.area = area;
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

	public List<GenSitio> getListSitios() {
		return manager.findAllSitios();
	}
	
	/**
	 * Redirecciona a la pagina de creacion de sitios
	 * 
	 * @return
	 */
	public String nuevoSitio() {
		edicion = false;
		return "nsitio?faces-redirect=true";
	}

	/**
	 * Permite la creacion o modificacion de un sector
	 * 
	 * @return
	 */
	public String crearSitio() {
		String r = "";
		try {
			if (edicion) {
				if (area==-1 || tipositio==-1){
					Mensaje.crearMensajeERROR("Seleccione un Area o un Tipo de Sitio");
					r="nsitio?faces-redirect=true";
				} else{
				manager.editarSitio(sit_id, sit_nombre, sit_costo_arriendo, sit_capacidad, sit_estado, area, tipositio);
				Mensaje.crearMensajeINFO("Actualizado - Sitio Modificado");
				r = "sitio?faces-redirect=true";}
			} else {
				if (area==null || area==-1 || tipositio==null || tipositio==-1){
					Mensaje.crearMensajeERROR("Seleccione un Area o un Tipo de Sitio");
					r="nsitio?faces-redirect=true";
				}else{
				manager.insertarSitio(sit_nombre, sit_costo_arriendo, sit_capacidad, area, tipositio);
				Mensaje.crearMensajeINFO("Registrado - Sitio Creado");
				r = "sitio?faces-redirect=true";
				}
			}
			// limpiar datos
			sit_id = 0;
			sit_nombre = "";
			sit_costo_arriendo = null;
			sit_capacidad = 0;
			sit_estado = "A";
			area = 0;
			tipositio=0;
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
	public String cargarSitio(GenSitio t) {
		sit_id = t.getSitId();
		sit_nombre = t.getSitNombre();
		sit_costo_arriendo = t.getSitCostoArriendo();
		sit_capacidad = t.getSitCapacidad();
		sit_estado = t.getSitEstado();
		area = t.getGenArea().getAreId();
		tipositio = t.getGenTipositio().getTsiId();
		edicion = true;
		return "nsitio?faces-redirect=true";
	}

	/**
	 * Cancela la accion de modificar o crear area
	 * 
	 * @return
	 */
	public String cancelar() {
		// limpiar datos
		sit_id = 0;
		sit_nombre = "";
		sit_costo_arriendo = null;
		sit_capacidad = 0;
		sit_estado = "A";
		area = 0;
		tipositio=0;
		edicion = false;
		return "sitio?faces-redirect=true";
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
	 * Lista de items de Areas
	 * 
	 * @return lista de todas
	 */
	public List<SelectItem> getlistAreas() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<GenArea> completo = manager.findAllAreas();
		for (GenArea s : completo) {
			if (s.getAreEstado().equals("A")){
			lista.add(new SelectItem(s.getAreId(), s.getAreNombre()));
			}
			}
		return lista;
	}
	
	/**
	 * Lista de Items de TiposSitios
	 * 
	 * @return lista de todas
	 */
	public List<SelectItem> getlistTipoSitios() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<GenTipositio> completo = manager.findAllTipoSitios();
		for (GenTipositio s : completo) {
			if (s.getTsiEstado().equals("A")){
			lista.add(new SelectItem(s.getTsiId(), s.getTsiNombre()));
		}
		}
		return lista;
	}
	
}
