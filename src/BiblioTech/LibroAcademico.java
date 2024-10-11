package BiblioTech;

import java.awt.Image;

public class LibroAcademico extends Libro {
	
	private String asignatura;
	private String disciplina;
	
	public LibroAcademico(String titulo, String autor, int numeroDePaginas, Image foto, int id, String asignatura,
			String disciplina) {
		super(titulo, autor, numeroDePaginas, foto, id);
		this.asignatura = asignatura;
		this.disciplina = disciplina;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	@Override
	public String toString() {
		return "LibroAcademico [asignatura=" + asignatura + ", disciplina=" + disciplina + ", getTitulo()="
				+ getTitulo() + ", getAutor()=" + getAutor() + ", getNumeroDePaginas()=" + getNumeroDePaginas()
				+ ", getFoto()=" + getFoto() + ", getId()=" + getId() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	
	

}
