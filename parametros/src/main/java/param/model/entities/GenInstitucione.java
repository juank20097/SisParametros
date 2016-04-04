package param.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the gen_instituciones database table.
 * 
 */
@Entity
@Table(name="gen_instituciones")
@NamedQuery(name="GenInstitucione.findAll", query="SELECT g FROM GenInstitucione g")
public class GenInstitucione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GEN_INSTITUCIONES_INSID_GENERATOR", sequenceName="SEQ_GEN_INSTITUCIONES", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GEN_INSTITUCIONES_INSID_GENERATOR")
	@Column(name="ins_id")
	private Integer insId;

	@Column(name="ins_descripcion")
	private String insDescripcion;

	@Column(name="ins_estado",columnDefinition="bpchar" , length=1)
	private String insEstado;

	@Column(name="ins_nombre")
	private String insNombre;

	//bi-directional many-to-one association to GenSectore
	@OneToMany(mappedBy="genInstitucione")
	private List<GenSectore> genSectores;

	public GenInstitucione() {
	}

	public Integer getInsId() {
		return this.insId;
	}

	public void setInsId(Integer insId) {
		this.insId = insId;
	}

	public String getInsDescripcion() {
		return this.insDescripcion;
	}

	public void setInsDescripcion(String insDescripcion) {
		this.insDescripcion = insDescripcion;
	}

	public String getInsEstado() {
		return this.insEstado;
	}

	public void setInsEstado(String insEstado) {
		this.insEstado = insEstado;
	}

	public String getInsNombre() {
		return this.insNombre;
	}

	public void setInsNombre(String insNombre) {
		this.insNombre = insNombre;
	}

	public List<GenSectore> getGenSectores() {
		return this.genSectores;
	}

	public void setGenSectores(List<GenSectore> genSectores) {
		this.genSectores = genSectores;
	}

	public GenSectore addGenSectore(GenSectore genSectore) {
		getGenSectores().add(genSectore);
		genSectore.setGenInstitucione(this);

		return genSectore;
	}

	public GenSectore removeGenSectore(GenSectore genSectore) {
		getGenSectores().remove(genSectore);
		genSectore.setGenInstitucione(null);

		return genSectore;
	}

}