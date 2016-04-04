package param.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the gen_tipositios database table.
 * 
 */
@Entity
@Table(name="gen_tipositios")
@NamedQuery(name="GenTipositio.findAll", query="SELECT g FROM GenTipositio g")
public class GenTipositio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GEN_TIPOSITIOS_TSIID_GENERATOR", sequenceName="SEQ_TIPOSITIOS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GEN_TIPOSITIOS_TSIID_GENERATOR")
	@Column(name="tsi_id")
	private Integer tsiId;

	@Column(name="tsi_descripcion")
	private String tsiDescripcion;

	@Column(name="tsi_estado")
	private String tsiEstado;

	@Column(name="tsi_nombre")
	private String tsiNombre;

	//bi-directional many-to-one association to GenSitio
	@OneToMany(mappedBy="genTipositio")
	private List<GenSitio> genSitios;

	public GenTipositio() {
	}

	public Integer getTsiId() {
		return this.tsiId;
	}

	public void setTsiId(Integer tsiId) {
		this.tsiId = tsiId;
	}

	public String getTsiDescripcion() {
		return this.tsiDescripcion;
	}

	public void setTsiDescripcion(String tsiDescripcion) {
		this.tsiDescripcion = tsiDescripcion;
	}

	public String getTsiEstado() {
		return this.tsiEstado;
	}

	public void setTsiEstado(String tsiEstado) {
		this.tsiEstado = tsiEstado;
	}

	public String getTsiNombre() {
		return this.tsiNombre;
	}

	public void setTsiNombre(String tsiNombre) {
		this.tsiNombre = tsiNombre;
	}

	public List<GenSitio> getGenSitios() {
		return this.genSitios;
	}

	public void setGenSitios(List<GenSitio> genSitios) {
		this.genSitios = genSitios;
	}

	public GenSitio addGenSitio(GenSitio genSitio) {
		getGenSitios().add(genSitio);
		genSitio.setGenTipositio(this);

		return genSitio;
	}

	public GenSitio removeGenSitio(GenSitio genSitio) {
		getGenSitios().remove(genSitio);
		genSitio.setGenTipositio(null);

		return genSitio;
	}

}