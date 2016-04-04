package param.model.manager;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import param.model.entities.GenArea;
import param.model.entities.GenInstitucione;
import param.model.entities.GenSectore;
import param.model.entities.GenSitio;
import param.model.entities.GenTipositio;

/**
 * Clase SitiosDAO permite manejar el HibernateDAO en conveniencia a la gestion
 * de sitios y articulos
 * 
 * @author Juan Carlos Estévez Hidalgo
 * @version 1.0
 *
 */
@Stateless
public class ManagerSitios {

	@EJB
	private ManagerDAO mngDao;

	// Campos de asignacion (Sitios)
	private GenArea area;
	private GenTipositio tipositio;
	// Campos de asignacion (Sectores)
	private GenInstitucione institucion;
	// Campos de asignacion (Areas)
	private GenSectore sector;
	

	/**
	 * Constructor para la utilizacion de metodos de la clase HibernateDAO
	 * 
	 * @param manager
	 *            El parametro manager inicializa la utilizacion de la clase
	 *            HIbernateDAO y todos sus metodos
	 */
	public ManagerSitios() {
	}// Cierre del Constructor

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Creación de metodos para el manejo de la tabla GenArea
	 * 
	 */

	/**
	 * Metodo para listar todas las Areas existentes
	 * 
	 * @return La lista de todas las Areas encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenArea> findAllAreas() {
		return mngDao.findAll(GenArea.class);
	}// Cierre del metodo

	/**
	 * Metodo para obtener el Area mediante un ID
	 * 
	 * @param id_area
	 *            Tipo integer de busqueda
	 * @return El objeto Area encontrado mediante el ID
	 */
	public GenArea AreaByID(Integer id_area) throws Exception {
		return (GenArea) mngDao.findById(GenArea.class, id_area);
	}// Cierre del metodo

	/**
	 * Metodo para ingresar un Area a la base de datos
	 * 
	 * @param nombre
	 *            Tipo String el cual almacena el nombre del Area
	 * @param ubicacion
	 *            Tipo String el cual almacena la ubicacion del Area
	 * @param descripcion
	 *            Tipo String el cual almacena la descripcion del lugar
	 * @param estado
	 *            Tipo Integer el cual almacena el dato si el Area es
	 *            Activado/Desactivado
	 */
	public void insertarArea(String nombre, String descripcion, Integer sec)
			throws Exception {
		try {
			sector = SectorByID(sec);
			GenArea p = new GenArea();
			p.setAreNombre(nombre);
			p.setAreDescripcion(descripcion);
			p.setAreEstado("A");
			p.setGenSectore(sector);
			mngDao.insertar(p);
			System.out.println("Bien_insertar_area");
		} catch (Exception e) {
			System.out.println("Error_insertar_area");
			e.printStackTrace();
		}

	}// Cierre del metodo

	/**
	 * Metodo para editar un Area en la base de datos
	 * 
	 * @param id_area
	 *            Tipo Integer el cual sirve de busqueda
	 * @param nombre
	 *            Tipo String el cual almacena el nombre del Area
	 * @param ubicacion
	 *            Tipo String el cual almacena la ubicacion del Area
	 * @param descripcion
	 *            Tipo String el cual almacena la descripcion del lugar
	 * @param estado
	 *            Tipo Integer el cual almacena el dato si es
	 *            Activado/Desactivado
	 */
	public void editarArea(Integer id_area, String nombre, String descripcion,
			String estado, Integer sec) throws Exception {
		try {
			sector = SectorByID(sec);
			GenArea r = this.AreaByID(id_area);
			r.setAreId(id_area);
			r.setAreNombre(nombre);
			r.setAreDescripcion(descripcion);
			r.setAreEstado(estado);
			r.setGenSectore(sector);
			mngDao.actualizar(r);
			System.out.println("bien_mod_area");
		} catch (Exception e) {
			System.out.println("Error_mod_area");
			e.printStackTrace();
		}
	}// Cierre del metodo

	/**
	 * Metodo para eliminar un Area en la base de datos
	 * 
	 * @param id_area
	 *            Tipo Integer el cual sirve para su eliminacion
	 */
	public void eliminarArea(Integer id_area) {
		try {
			mngDao.eliminar(GenArea.class, id_area);
			System.out.println("Eliminar_area_correcto");
		} catch (Exception e) {
			System.out.println("Eliminar_area_incorrecto");
			e.printStackTrace();
		}
	}// Cierre del metodo

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Creación de metodos para el manejo de la tabla GenSitio
	 * 
	 */

	/**
	 * Metodo para listar todos los Sitios existentes
	 * 
	 * @return La lista de todos los Sitios encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenSitio> findAllSitios() {
		return mngDao.findAll(GenSitio.class);
	}// Cierre del metodo

	/**
	 * Metodo para obetener el Sitio mediante un ID
	 * 
	 * @param id_sitio
	 *            Tipo integer de busqueda
	 * @return El objeto Sitio encontrado mediante el ID
	 */
	public GenSitio SitioByID(Integer id_sitio) throws Exception {
		return (GenSitio) mngDao.findById(GenSitio.class, id_sitio);
	}// Cierre del metodo

	/**
	 * Metodo para obtener el Sitio mediante un Nombre
	 * 
	 * @param nombre
	 *            Tipo String de busqueda
	 * @return El objeto Sitio encontrado mediante el Nombre
	 */
	public GenSitio SitiobyNombre(String nombre) throws Exception {
		GenSitio s = null;
		List<GenSitio> li = findAllSitios();
		for (GenSitio sitio : li) {
			if (sitio.getSitNombre().equals(nombre)) {
				s = sitio;
			}
		}
		return s;
	}// Cierre del metodo

	/**
	 * Metodo para ingresar un Sitio a la base de datos
	 * 
	 * @param identificador
	 *            Tipo String el cual almacena el nombre para denotar el sitio
	 * @param costo
	 *            Tipo Double el cual almacena el costo mensual del sitio
	 * @param direccion
	 *            Tipo String el cual almacena la ubicacion del sitio
	 * @param capacidad
	 *            Tipo Integer el cual almacena el numero de personas permitidas
	 * @param estado
	 *            Tipo Integer el cual almacena el dato si es
	 *            Activado/Desactivado
	 */
	public void insertarSitio(String nombre, BigDecimal costo,
			Integer capacidad, Integer a, Integer ts) throws Exception {
		try {
			tipositio = TipoSitiosByID(ts);
			area = AreaByID(a);
			GenSitio p = new GenSitio();
			p.setSitNombre(nombre);
			p.setSitCostoArriendo(costo);
			p.setSitCapacidad(capacidad);
			p.setSitEstado("A");
			p.setGenArea(area);
			p.setGenTipositio(tipositio);
			mngDao.insertar(p);
			System.out.println("Bien_insertar_area");
		} catch (Exception e) {
			System.out.println("Error_insertar_area");
			e.printStackTrace();
		}

	}// Cierre del metodo

	/**
	 * Metodo para editar un Sitio a la base de datos
	 * 
	 * @param nombre
	 *            Tipo String el cual cambia el nombre para denotar el sitio
	 * @param costo
	 *            Tipo Double el cual cambia el costo mensual del sitio
	 * @param direccion
	 *            Tipo String el cual cambia la ubicacion del sitio
	 * @param capacidad
	 *            Tipo Integer el cual cambia el numero de personas permitidas
	 * @param estado
	 *            Tipo Integer el cual cambia el dato si es Activado/Desactivado
	 */
	public void editarSitio(Integer id, String nombre, BigDecimal costo,
			Integer capacidad, String estado, Integer a, Integer ts) throws Exception {
		try {
			tipositio = TipoSitiosByID(ts);
			area = AreaByID(a);
			GenSitio r = this.SitioByID(id);
			r.setSitId(id);
			r.setSitNombre(nombre);
			r.setSitCostoArriendo(costo);
			r.setSitCapacidad(capacidad);
			r.setSitEstado(estado);
			r.setGenArea(area);
			r.setGenTipositio(tipositio);
			mngDao.actualizar(r);
			System.out.println("bien_mod_sitio");
		} catch (Exception e) {
			System.out.println("Error_mod_sitio");
			e.printStackTrace();
		}
	}// Cierre del metodo
	
	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/**
		 * Creación de metodos para el manejo de la tabla GenTipositio
		 * 
		 */

		/**
		 * Metodo para listar todos los Sitios existentes
		 * 
		 * @return La lista de todos los Sitios encontradas
		 */
		@SuppressWarnings("unchecked")
		public List<GenTipositio> findAllTipoSitios() {
			return mngDao.findAll(GenTipositio.class);
		}// Cierre del metodo

		/**
		 * Metodo para obetener el Sitio mediante un ID
		 * 
		 * @param id_sitio
		 *            Tipo integer de busqueda
		 * @return El objeto Sitio encontrado mediante el ID
		 */
		public GenTipositio TipoSitiosByID(Integer id) throws Exception {
			return (GenTipositio) mngDao.findById(GenTipositio.class, id);
		}// Cierre del metodo


		/**
		 * Metodo para ingresar un Sitio a la base de datos
		 * 
		 * @param identificador
		 *            Tipo String el cual almacena el nombre para denotar el sitio
		 * @param costo
		 *            Tipo Double el cual almacena el costo mensual del sitio
		 * @param direccion
		 *            Tipo String el cual almacena la ubicacion del sitio
		 * @param capacidad
		 *            Tipo Integer el cual almacena el numero de personas permitidas
		 * @param estado
		 *            Tipo Integer el cual almacena el dato si es
		 *            Activado/Desactivado
		 */
		public void insertarTipoSitios(String nombre, String descripcion) throws Exception {
			try {
				GenTipositio p = new GenTipositio();
				p.setTsiNombre(nombre);
				p.setTsiDescripcion(descripcion);
				p.setTsiEstado("A");
				mngDao.insertar(p);
				System.out.println("Bien_insertar_tsitio");
			} catch (Exception e) {
				System.out.println("Error_insertar_tsitio");
				e.printStackTrace();
			}

		}// Cierre del metodo

		/**
		 * Metodo para editar un Sitio a la base de datos
		 * 
		 * @param nombre
		 *            Tipo String el cual cambia el nombre para denotar el sitio
		 * @param costo
		 *            Tipo Double el cual cambia el costo mensual del sitio
		 * @param direccion
		 *            Tipo String el cual cambia la ubicacion del sitio
		 * @param capacidad
		 *            Tipo Integer el cual cambia el numero de personas permitidas
		 * @param estado
		 *            Tipo Integer el cual cambia el dato si es Activado/Desactivado
		 */
		public void editarTipoSitios(Integer id, String nombre, String descripcion, String estado) throws Exception {
			try {
				GenTipositio r = this.TipoSitiosByID(id);
				r.setTsiId(id);
				r.setTsiNombre(nombre);
				r.setTsiDescripcion(descripcion);
				r.setTsiEstado(estado);
				mngDao.actualizar(r);
				System.out.println("bien_mod_tsitio");
			} catch (Exception e) {
				System.out.println("Error_mod_tsitio");
				e.printStackTrace();
			}
		}// Cierre del metodo

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Creación de metodos para el manejo de la tabla GEN_Institucion
	 * 
	 */

	/**
	 * Metodo para listar Institucion existentes
	 * 
	 * @return La lista de todas las Institucion encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenInstitucione> findAllInstituciones() {
		return mngDao.findAll(GenInstitucione.class);
	}// Cierre del metodo

	/**
	 * Metodo para obetener Institucion mediante un ID
	 * 
	 * @param id
	 *            Tipo integer de busqueda
	 * @return El objeto Institucion encontrado mediante el ID
	 */
	public GenInstitucione institucionByID(Integer id) throws Exception {
		return (GenInstitucione) mngDao
				.findById(GenInstitucione.class, id);
	}// Cierre del metodo

	/**
	 * Metodo para ingresar Entidad a la base de datos
	 * 
	 * @param nombre
	 *            Tipo String el cual almacena el nombre para denotar la entidad
	 * @param descripcion
	 *            Tipo String el cual almacena descripcion de la entidad
	 * @param estado
	 *            Tipo Integer el cual almacena el dato si es
	 *            (Activado/Descativado)
	 */
	public void insertarInstitucion(String nombre, String descripcion)
			throws Exception {
		try {
			GenInstitucione p = new GenInstitucione();
			p.setInsNombre(nombre);
			p.setInsDescripcion(descripcion);
			p.setInsEstado("A");
			mngDao.insertar(p);
			System.out.println("Bien_insertar_institucion");
		} catch (Exception e) {
			System.out.println("Error_insertar_institucion");
			e.printStackTrace();
		}

	}// Cierre del metodo

	/**
	 * Metodo para editar Entidad a la base de datos
	 * 
	 * @param id
	 *            Tipo Integer el dato para buscar la entidad
	 * @param nombre
	 *            Tipo String el cual almacena el nombre para denotar la entidad
	 * @param descripcion
	 *            Tipo String el cual almacena descripcion de la entidad
	 * @param estado
	 *            Tipo Integer el cual almacena el dato si es
	 *            (Activado/Descativado)
	 */
	public void editarInstitucion(Integer id, String nombre,
			String descripcion, String estado) throws Exception {
		try {
			GenInstitucione p = this.institucionByID(id);
			p.setInsId(id);
			p.setInsNombre(nombre);
			p.setInsDescripcion(descripcion);
			p.setInsEstado(estado);
			mngDao.actualizar(p);
			System.out.println("Bien_editar_institucion");
		} catch (Exception e) {
			System.out.println("Error_editar_institucion");
			e.printStackTrace();
		}

	}// Cierre del metodo

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Creación de metodos para el manejo de la tabla GEN_Sector
	 * 
	 */

	/**
	 * Metodo para listar todos los datos de la entidad
	 * 
	 * @return La lista de todos los datos de la entidad encontradas
	 */
	@SuppressWarnings("unchecked")
	public List<GenSectore> findAllSector() {
		return mngDao.findAll(GenSectore.class);
	}// Cierre del metodo

	/**
	 * Metodo para obtener Entidad mediante un ID
	 * 
	 * @param id
	 *            Tipo integer de busqueda
	 * @return El objeto de la Entidad encontrado mediante el ID
	 */
	public GenSectore SectorByID(Integer id) throws Exception {
		return (GenSectore) mngDao.findById(GenSectore.class, id);
	}// Cierre del metodo

	/**
	 * Metodo para ingresar Entidad a la base de datos
	 * 
	 * @param nombre
	 *            Tipo String el cual almacena el nombre para denotar la entidad
	 * @param direccion
	 *            Tipo String el cual almacena el lugar de la entidad
	 * @param estado
	 *            Tipo Integer el cual almacena el dato si es
	 *            (Activado/Descativado)
	 */
	public void insertarSector(String nombre, String direccion,
			String ubicacion, Integer institu) throws Exception {
		try {
			institucion = institucionByID(institu);
			GenSectore p = new GenSectore();
			p.setSecNombre(nombre);
			p.setSecDireccion(direccion);
			p.setSecEstado("A");
			p.setSecUbicacion(ubicacion);
			p.setGenInstitucione(institucion);
			mngDao.insertar(p);
			System.out.println("Bien_insertar_sector");
			institucion = new GenInstitucione();
		} catch (Exception e) {
			System.out.println("Error_insertar_sector");
			e.printStackTrace();
		}

	}// Cierre del metodo

	/**
	 * Metodo para editar Entidad a la base de datos
	 * 
	 * @param id
	 *            Tipo Integer el dato para buscar la entidad
	 * @param nombre
	 *            Tipo String el cual edita el nombre para denotar la entidad
	 * @param direccion
	 *            Tipo String el cual edita el lugar de la entidad
	 * @param estado
	 *            Tipo Integer el cual edita el dato si es
	 *            (Activado/Descativado)
	 */
	public void editarSector(Integer id, String nombre, String direccion,
			String ubicacion, Integer institu, String estado) throws Exception {
		try {
			institucion = institucionByID(institu);
			GenSectore p = SectorByID(id);
			p.setSecId(id);
			p.setSecNombre(nombre);
			p.setSecDireccion(direccion);
			p.setSecEstado(estado);
			p.setSecUbicacion(ubicacion);
			p.setGenInstitucione(institucion);
			mngDao.actualizar(p);
			System.out.println("Bien_editar_sector");
		} catch (Exception e) {
			System.out.println("Error_editar_sector");
			e.printStackTrace();
		}

	}// Cierre del metodo

}
