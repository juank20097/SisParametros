package param.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the gen_areas database table.
 * 
 */
@Entity
@Table(name="gen_areas")
@NamedQuery(name="GenArea.findAll", query="SELECT g FROM GenArea g")
public class GenArea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GEN_AREAS_AREID_GENERATOR", sequenceName="SEQ_GEN_AREAS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GEN_AREAS_AREID_GENERATOR")
	@Column(name="are_id")
	private Integer areId;

	@Column(name="are_descripcion")
	private String areDescripcion;

	@Column(name="are_estado", columnDefinition="bpchar" , length=1)
	private String areEstado;

	@Column(name="are_nombre")
	private String areNombre;

	//bi-directional many-to-one association to GenSectore
	@ManyToOne
	@JoinColumn(name="sec_id")
	private GenSectore genSectore;

	//bi-directional many-to-one association to GenSitio
	@OneToMany(mappedBy="genArea")
	private List<GenSitio> genSitios;

	public GenArea() {
	}

	public Integer getAreId() {
		return this.areId;
	}

	public void setAreId(Integer areId) {
		this.areId = areId;
	}

	public String getAreDescripcion() {
		return this.areDescripcion;
	}

	public void setAreDescripcion(String areDescripcion) {
		this.areDescripcion = areDescripcion;
	}

	public String getAreEstado() {
		return this.areEstado;
	}

	public void setAreEstado(String areEstado) {
		this.areEstado = areEstado;
	}

	public String getAreNombre() {
		return this.areNombre;
	}

	public void setAreNombre(String areNombre) {
		this.areNombre = areNombre;
	}

	public GenSectore getGenSectore() {
		return this.genSectore;
	}

	public void setGenSectore(GenSectore genSectore) {
		this.genSectore = genSectore;
	}

	public List<GenSitio> getGenSitios() {
		return this.genSitios;
	}

	public void setGenSitios(List<GenSitio> genSitios) {
		this.genSitios = genSitios;
	}

	public GenSitio addGenSitio(GenSitio genSitio) {
		getGenSitios().add(genSitio);
		genSitio.setGenArea(this);

		return genSitio;
	}

	public GenSitio removeGenSitio(GenSitio genSitio) {
		getGenSitios().remove(genSitio);
		genSitio.setGenArea(null);

		return genSitio;
	}

}