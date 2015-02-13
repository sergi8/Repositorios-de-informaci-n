package uo.ri.amp.model;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import uo.ri.amp.model.types.ContratoStatus;
import uo.ri.model.Mecanico;
import alb.util.date.DateUtil;

@Entity
@Table(name = "TCONTRATOS")
public class Contrato 
{
	@Id 
	@GeneratedValue
	private Long id;
	@Temporal(TemporalType.DATE) private Date fecha_Inicio;
	@Temporal(TemporalType.DATE) private Date fecha_Fin;
	private Double salario_Base_Anual_Bruto;
	@Enumerated(EnumType.STRING) private ContratoStatus status;
	
	
	@ManyToOne private Mecanico mecanico;
	@ManyToOne private TipoContrato tipoContrato;
	@ManyToOne private Categoria categoria;
	
	@OneToMany(mappedBy = "contrato") private Set<Nomina> nominas = new HashSet<Nomina>();
	
	public Contrato(Mecanico mecanico, TipoContrato tipoContrato, Categoria categoría) 
	{
		this.mecanico=mecanico;
		this.tipoContrato=tipoContrato;
		this.categoria=categoría;
		
		Date now = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        
		setFechaInicio(DateUtil.fromString(formateador.format(now)));
		setStatus(ContratoStatus.ACTIVO);
	}
	public Contrato() {}

	public Long getId() 
	{
		return id;
	}

	public Date getFechaInicio() 
	{
		return fecha_Inicio;
	}
	private void setFechaInicio(Date fechaInicio)
	{
		this.fecha_Inicio=fechaInicio;
	}

	public Date getFechaFin() 
	{
		return fecha_Fin;
	}

	public void setFechaFin(Date fechaFin) 
	{
		this.fecha_Fin = fechaFin;
	}

	public Double getSalarioBaseAnualBruto() 
	{
		return salario_Base_Anual_Bruto;
	}

	public void setSalarioBaseAnualBruto(Double salarioBaseAnualBruto) 
	{
		this.salario_Base_Anual_Bruto = salarioBaseAnualBruto;
	}

	public ContratoStatus getStatus() 
	{
		return status;
	}

	public void setStatus(ContratoStatus status) 
	{
		this.status = status;
	}

	public Mecanico getMecanico() 
	{
		return mecanico;
	}

	public TipoContrato getTipoContrato() 
	{
		return tipoContrato;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Set<Nomina> getNominas() 
	{
		return Collections.unmodifiableSet(nominas);
	}
	Set<Nomina> _getNominas() 
	{
		return nominas;
	}
	
	public void addNomina(Nomina n)
	{
		nominas.add(n);
	}
	
	public void removeNomina(Nomina n)
	{
		nominas.remove(n);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result
				+ ((mecanico == null) ? 0 : mecanico.hashCode());
		result = prime * result
				+ ((tipoContrato == null) ? 0 : tipoContrato.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contrato other = (Contrato) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (mecanico == null) {
			if (other.mecanico != null)
				return false;
		} else if (!mecanico.equals(other.mecanico))
			return false;
		if (tipoContrato == null) {
			if (other.tipoContrato != null)
				return false;
		} else if (!tipoContrato.equals(other.tipoContrato))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Contrato [id=" + id + ", fechaInicio=" + fecha_Inicio
				+ ", fechaFin=" + fecha_Fin + ", salarioBaseAnualBruto="
				+ salario_Base_Anual_Bruto + ", status=" + status + ", mecanico="
				+ mecanico + ", tipoContrato=" + tipoContrato + ", categoria="
				+ categoria + ", nominas=" + nominas + "]";
	}
	
	public void setTipoContrato(TipoContrato tipoContrato)
	{
		this.tipoContrato = tipoContrato;
	}
	public void setCategoria(Categoria categoría)
	{
		this.categoria = categoría;
	}
	
	
}
