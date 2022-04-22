package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Alumno;
import service.AcademiaService;

@CrossOrigin("*")
@Controller
public class AcademiaController {

	@Autowired
	AcademiaService serviceAcam;

	@PostMapping("Alta")
	public String altaAlumno(@ModelAttribute Alumno a) {
		if (!serviceAcam.existeAlumno(a.getNombre())) {
			serviceAcam.alta(a);
			return "alta";
		} else {
			return "error";
		}
		
	}

	@GetMapping(value = "Cursos", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<String> buscarCurso() {
		return serviceAcam.cursos();
	}

	@GetMapping(value = "Buscador", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Alumno> buscarAlumno(@RequestParam("curso") String curso) {
		return serviceAcam.buscarPorCurso(curso);
	}

}
