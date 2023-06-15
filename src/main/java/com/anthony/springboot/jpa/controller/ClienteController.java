package com.anthony.springboot.jpa.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


import com.anthony.springboot.jpa.models.entity.Cliente;
import com.anthony.springboot.jpa.models.service.IClienteService;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	@GetMapping(value="/listar")
	public String listar(Model model) {
		model.addAttribute("titulo","Listado de clientes");
		model.addAttribute("clientes",clienteService.findAll());
		return "listar"; 
	}
	
	@GetMapping(value="/from")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Ciente");
		return "form";
	}
	
	@PostMapping("/form")
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}
		
		clienteService.save(cliente);
		status.setComplete();
		return "redirect:listar";
	}
	
	@GetMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		Cliente cliente = null;
		if(id > 0) {
			cliente= clienteService.findOne(id);
		}else {
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", cliente);
		return "form";
	}
	
	@GetMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		
		if(id>0) {
			clienteService.delete(id);
		}
		
		return "redirect:/listar";
	}
	
}
