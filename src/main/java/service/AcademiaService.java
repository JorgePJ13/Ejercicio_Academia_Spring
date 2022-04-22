package service;

import java.util.List;

import model.Alumno;

public interface AcademiaService {

	void alta(Alumno a);
	
	List<String> cursos();
	
	List<Alumno> buscarPorCurso(String curso);

	boolean existeAlumno(String nombre);
}
