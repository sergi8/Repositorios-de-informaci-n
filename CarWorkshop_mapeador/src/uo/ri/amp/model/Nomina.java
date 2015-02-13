package uo.ri.amp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import uo.ri.model.Averia;
import uo.ri.model.Mecanico;
import alb.util.date.DateUtil;

@Entity
@Table(name = "TNOMINAS")
public class Nomina 
{
	@Id 
	@GeneratedValue
	private Long id;
	private Double salario_Base=0D;
	private Double paga_Extra=0D;
	private Double plus=0D;
	private Double trienio=0D;
	private Double irpf=0D;
	private Double desc_Seg_Social=0D;
	@Temporal(TemporalType.DATE) private Date fecha;
	
	@ManyToOne private Contrato contrato;

	public Nomina(Contrato contrato) 
	{
		this.contrato = contrato;
		calcularSalarioBase();
		setFecha(DateUtil.fromString("1/8/2014"));
		calcularPagaExtra();
		calcularPlus();
		calcularTrienio();
		calcularIRPF();
		calcularDescSegSocial();
		contrato.addNomina(this);
	}

	public Double getPagaExtra() 
	{
		return paga_Extra;
	}
	
	@SuppressWarnings("deprecation")
	private void calcularPagaExtra()
	{
		if(fecha.getMonth()==12 ||fecha.getMonth()==6)
			paga_Extra = contrato.getSalarioBaseAnualBruto()/14;
	}

	public Date getFecha() 
	{
		return fecha;
	}

	private void setFecha(Date fecha) 
	{
		this.fecha = fecha;
	}

	public Long getId() 
	{
		return id;
	}

	public Double getSalarioBase() 
	{
		return salario_Base;
	}
	
	private void calcularSalarioBase()
	{
		salario_Base = contrato.getSalarioBaseAnualBruto()/14;
	}

	public Double getPlus() 
	{
		return plus;
	}
	
	private void calcularPlus()
	{
		Double importeTotalAverias=0D;
		for(Averia a : contrato.getMecanico().getAverias())
		{
			importeTotalAverias+=a.getImporte();
		}
		plus = contrato.getCategoria().getPorcentProductividad()*importeTotalAverias%100;
	}

	public Double getTrienio() 
	{
		return trienio;
	}
	
	private void calcularTrienio()
	{
		trienio=0D;
		String warning="";
	}

	public Double getIrpf() 
	{
		return irpf;
	}
	
	private void calcularIRPF()
	{
		if(contrato.getSalarioBaseAnualBruto()<12.000)
		{
			irpf = salario_Base-salario_Base*0.07;
		}else if(contrato.getSalarioBaseAnualBruto()<15.000)
		{
			irpf = salario_Base-salario_Base*0.1;
		}else if(contrato.getSalarioBaseAnualBruto()<20.000)
		{
			irpf = salario_Base-salario_Base*0.13;
		}else if(contrato.getSalarioBaseAnualBruto()<25.000)
		{
			irpf = salario_Base-salario_Base*0.17;
		}else if(contrato.getSalarioBaseAnualBruto()<30.000)
		{
			irpf = salario_Base-salario_Base*0.2;
		}else if(contrato.getSalarioBaseAnualBruto()>30.000)
		{
			irpf = salario_Base-salario_Base*0.2;
		}
	}

	public Double getDescSegSocial() 
	{
		return desc_Seg_Social;
	}
	
	private void calcularDescSegSocial()
	{
		this.desc_Seg_Social = (salario_Base+paga_Extra)*0.05;
	}

	public Contrato getContrato() 
	{
		return contrato;
	}
	
	public Double getTotal()
	{
		return salario_Base+paga_Extra+plus+trienio-irpf-desc_Seg_Social;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contrato == null) ? 0 : contrato.hashCode());
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
		Nomina other = (Nomina) obj;
		if (contrato == null) {
			if (other.contrato != null)
				return false;
		} else if (!contrato.equals(other.contrato))
			return false;
		return true;
	}

	@Override
	public String toString() 
	{
		return "Nomina [id=" + id + ", salarioBase=" + salario_Base
				+ ", pagaExtra=" + paga_Extra + ", plus=" + plus + ", trienio="
				+ trienio + ", irpf=" + irpf + ", descSegSocial="
				+ desc_Seg_Social + ", fecha=" + fecha + ", contrato=" + contrato
				+ "]";
	}
	
	
	
	
	

}
