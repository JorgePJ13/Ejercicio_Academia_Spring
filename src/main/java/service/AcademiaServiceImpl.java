package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import model.Alumno;

@Service
public class AcademiaServiceImpl implements AcademiaService {

	@Autowired
	JdbcTemplate template;

	@Override
	public void alta(Alumno a) {
		if(!existeAlumno(a.getNombre())) {
			String sql = "insert into alumnos(nombre,curso,nota) values(?,?,?)";
			template.update(sql, a.getNombre(), a.getCurso(), a.getNota());
		}
	}

	@Override
	public List<String> cursos() {
		String sql = "select distinct(curso) from alumnos";
		return template.query(sql, (rs, f)->rs.getString("curso"));
	}

	@Override
	public List<Alumno> buscarPorCurso(String curso) {
		String sql = "select * from alumnos where curso=?";
		return template.query(sql, (rs, f) -> new Alumno(rs.getInt("idAlumno"), rs.getString("nombre"),
				rs.getString("curso"), rs.getDouble("nota")), curso);
	}

	@Override
	public boolean existeAlumno(String nombre) {
		String sql = "select * from alumnos where nombre=?";
		List<Alumno> listaAlumnos = template.query(sql, (rs, f) -> new Alumno(rs.getInt("idAlumno"),
				rs.getString("nombre"), rs.getString("curso"), rs.getDouble("nota")), nombre);
		return listaAlumnos.size() > 0;
	}

}
