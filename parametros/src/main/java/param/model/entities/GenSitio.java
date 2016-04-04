package param.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the gen_sitios database table.
 * 
 */
@Entity
@Table(name="gen_sitios")
@NamedQuery(name="GenSitio.findAll", query="SELECT g FROM GenSitio g")
public class GenSitio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GEN_SITIOS_SITID_GENERATOR", sequenceName="SEQ_SITIOS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GEN_SITIOS_SITID_GENERATOR")
	@Column(name="sit_id")
	private Integer sitId;

	@Column(name="sit_capacidad")
	private Integer sitCapacidad;

	@Column(name="sit_costo_arriendo")
	private BigDecimal sitCostoArriendo;

	@Column(name="sit_estado")
	private String sitEstado;

	@Column(name="sit_nombre")
	private String sitNombre;

	//bi-directional many-to-one association to GenSitFoto
	@OneToMany(mappedBy="genSitio")
	private List<GenSitFoto> genSitFotos;

	//bi-directional many-to-one association to GenArea
	@ManyToOne
	@JoinColumn(name="are_id")
	private GenArea genArea;

	//bi-directional many-to-one association to GenTipositio
	@ManyToOne
	@JoinColumn(name="tsi_id")
	private GenTipositio genTipositio;

	public GenSitio() {
	}

	public Integer getSitId() {
		return this.sitId;
	}

	public void setSitId(Integer sitId) {
		this.sitId = sitId;
	}

	public Integer getSitCapacidad() {
		return this.sitCapacidad;
	}

	public void setSitCapacidad(Integer sitCapacidad) {
		this.sitCapacidad = sitCapacidad;
	}

	public BigDecimal getSitCostoArriendo() {
		return this.sitCostoArriendo;
	}

	public void setSitCostoArriendo(BigDecimal sitCostoArriendo) {
		this.sitCostoArriendo = sitCostoArriendo;
	}

	public String getSitEstado() {
		return this.sitEstado;
	}

	public void setSitEstado(String sitEstado) {
		this.sitEstado = sitEstado;
	}

	public String getSitNombre() {
		return this.sitNombre;
	}

	public void setSitNombre(String sitNombre) {
		this.sitNombre = sitNombre;
	}

	public List<GenSitFoto> getGenSitFotos() {
		return this.genSitFotos;
	}

	public void setGenSitFotos(List<GenSitFoto> genSitFotos) {
		this.genSitFotos = genSitFotos;
	}

	public GenSitFoto addGenSitFoto(GenSitFoto genSitFoto) {
		getGenSitFotos().add(genSitFoto);
		genSitFoto.setGenSitio(this);

		return genSitFoto;
	}

	public GenSitFoto removeGenSitFoto(GenSitFoto genSitFoto) {
		getGenSitFotos().remove(genSitFoto);
		genSitFoto.setGenSitio(null);

		return genSitFoto;
	}

	public GenArea getGenArea() {
		return this.genArea;
	}

	public void setGenArea(GenArea genArea) {
		this.genArea = genArea;
	}

	public GenTipositio getGenTipositio() {
		return this.genTipositio;
	}

	public void setGenTipositio(GenTipositio genTipositio) {
		this.genTipositio = genTipositio;
	}

}