package br.com.maria.eduarda.cobranca.controller;

import java.util.List;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.maria.eduarda.cobranca.model.StatusTitulo;
import br.com.maria.eduarda.cobranca.model.Titulo;
import br.com.maria.eduarda.cobranca.repository.Titulos;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	@Autowired
	private Titulos titulos;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Titulo titulo) {
		titulos.save(titulo);
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject("mensagem", "Título salvo com sucesso!");
		System.out.println(titulo);
		return mv;
	}

	@RequestMapping
	public ModelAndView pesquisar() {
		List <Titulo> todosTitilos =  titulos.findAll();
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos", todosTitilos);
		return mv;
	}

	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}
}
