package param.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the gen_sectores database table.
 * 
 */
@Entity
@Table(name="gen_sectores")
@NamedQuery(name="GenSectore.findAll", query="SELECT g FROM GenSectore g")
public class GenSectore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GEN_SECTORES_SECID_GENERATOR", sequenceName="SEQ_GEN_SECTORES",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GEN_SECTORES_SECID_GENERATOR")
	@Column(name="sec_id")
	private Integer secId;

	@Column(name="sec_direccion")
	private String secDireccion;

	@Column(name="sec_estado",columnDefinition="bpchar" , length=1)
	private String secEstado;

	@Column(name="sec_nombre")
	private String secNombre;

	@Column(name="sec_ubicacion")
	private String secUbicacion;

	//bi-directional many-to-one association to GenArea
	@OneToMany(mappedBy="genSectore")
	private List<GenArea> genAreas;

	//bi-directional many-to-one association to GenInstitucione
	@ManyToOne
	@JoinColumn(name="ins_id")
	private GenInstitucione genInstitucione;

	public GenSectore() {
	}

	public Integer getSecId() {
		return this.secId;
	}

	public void setSecId(Integer secId) {
		this.secId = secId;
	}

	public String getSecDireccion() {
		return this.secDireccion;
	}

	public void setSecDireccion(String secDireccion) {
		this.secDireccion = secDireccion;
	}

	public String getSecEstado() {
		return this.secEstado;
	}

	public void setSecEstado(String secEstado) {
		this.secEstado = secEstado;
	}

	public String getSecNombre() {
		return this.secNombre;
	}

	public void setSecNombre(String secNombre) {
		this.secNombre = secNombre;
	}

	public String getSecUbicacion() {
		return this.secUbicacion;
	}

	public void setSecUbicacion(String secUbicacion) {
		this.secUbicacion = secUbicacion;
	}

	public List<GenArea> getGenAreas() {
		return this.genAreas;
	}

	public void setGenAreas(List<GenArea> genAreas) {
		this.genAreas = genAreas;
	}

	public GenArea addGenArea(GenArea genArea) {
		getGenAreas().add(genArea);
		genArea.setGenSectore(this);

		return genArea;
	}

	public GenArea removeGenArea(GenArea genArea) {
		getGenAreas().remove(genArea);
		genArea.setGenSectore(null);

		return genArea;
	}

	public GenInstitucione getGenInstitucione() {
		return this.genInstitucione;
	}

	public void setGenInstitucione(GenInstitucione genInstitucione) {
		this.genInstitucione = genInstitucione;
	}

}