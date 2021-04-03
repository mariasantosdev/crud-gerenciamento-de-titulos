package br.com.maria.eduarda.cobranca.controller;

import java.util.List;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.maria.eduarda.cobranca.model.StatusTitulo;
import br.com.maria.eduarda.cobranca.model.Titulo;
import br.com.maria.eduarda.cobranca.repository.Titulos;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	private static final String CADASTRO_VIEW = "CadastroTitulo";
	
	@Autowired
	private Titulos titulos;

	@RequestMapping("/novo")	
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Titulo());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Titulo titulo) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);	
		titulos.save(titulo);
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
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Titulo titulo) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW); 
		mv.addObject(titulo);
		return mv;
	}	
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE)
		public String excluir(@PathVariable Long codigo) {
		titulos.deleteById(codigo);
		return "redirect:/titulos";
	}
	
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}
}
