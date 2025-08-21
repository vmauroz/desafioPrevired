package cl.previred.desafioPrevired.respository.dto;

import java.time.LocalDate;

/**
 * DTO Registro para empleados
 */
public class EmpleadoDto {
	private String empNombre;
	private String empApellido;
	private String empRut;
	private String empCargo;
	private int empSalarioBase;
	private int empBonos;
	private int empDescuentos;
	private int empBonoAntiguedad;
	private int empSalarioFinal;
	private LocalDate empFechaIngreso;
	private int empAntiguedad;
	private boolean empValido;
	private String empCommentario;
	private String empRegistroFuente; // registro obtenido desde la fuente original 
	
	public String getEmpNombre() {
		return empNombre;
	}
	public void setEmpNombre(String empNombre) {
		this.empNombre = empNombre;
	}
	public String getEmpApellido() {
		return empApellido;
	}
	public void setEmpApellido(String empApellido) {
		this.empApellido = empApellido;
	}
	public String getEmpRut() {
		return empRut;
	}
	public void setEmpRut(String empRut) {
		this.empRut = empRut;
	}
	public String getEmpCargo() {
		return empCargo;
	}
	public void setEmpCargo(String empCargo) {
		this.empCargo = empCargo;
	}
	public int getEmpSalarioBase() {
		return empSalarioBase;
	}
	public void setEmpSalarioBase(int empSalarioBase) {
		this.empSalarioBase = empSalarioBase;
	}
	public int getEmpBonos() {
		return empBonos;
	}
	public void setEmpBonos(int empBonos) {
		this.empBonos = empBonos;
	}
	public int getEmpDescuentos() {
		return empDescuentos;
	}
	public void setEmpDescuentos(int empDescuentos) {
		this.empDescuentos = empDescuentos;
	}
	public LocalDate getEmpFechaIngreso() {
		return empFechaIngreso;
	}
	public void setEmpFechaIngreso(LocalDate empFechaIngreso) {
		this.empFechaIngreso = empFechaIngreso;
	}
	public boolean isEmpValido() {
		return empValido;
	}
	public void setEmpValido(boolean empValido) {
		this.empValido = empValido;
	}
	public String getEmpCommentario() {
		return empCommentario;
	}
	public void setEmpCommentario(String empCommentario) {
		this.empCommentario = empCommentario;
	}
	public int getEmpBonoAntiguedad() {
		return empBonoAntiguedad;
	}
	public void setEmpBonoAntiguedad(int empBonoAntiguedad) {
		this.empBonoAntiguedad = empBonoAntiguedad;
	}
	public String getEmpRegistroFuente() {
		return empRegistroFuente;
	}
	public void setEmpRegistroFuente(String empRegistroFuente) {
		this.empRegistroFuente = empRegistroFuente;
	}
	public int getEmpSalarioFinal() {
		return empSalarioFinal;
	}
	public void setEmpSalarioFinal(int empSalarioFinal) {
		this.empSalarioFinal = empSalarioFinal;
	}
	public int getEmpAntiguedad() {
		return empAntiguedad;
	}
	public void setEmpAntiguedad(int empAntiguedad) {
		this.empAntiguedad = empAntiguedad;
	}

}
