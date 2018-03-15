package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Publicacion;
import com.uniovi.entities.User;
import com.uniovi.services.PublicacionService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.AddPublicacionFormValidator;

@Controller
public class PublicacionController {
	
	@Autowired
	private PublicacionService publicacionService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private AddPublicacionFormValidator addPublicacionFormValidator;
	
	@RequestMapping(value="/publicacion/add", method=RequestMethod.POST )
	public String setPublicacion(@Validated Publicacion publicacion, BindingResult result){
		addPublicacionFormValidator.validate(publicacion, result);
		if(result.hasErrors()) {
			return "publicacion/add";
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = usersService.getUserByEmail(auth.getName());
		publicacion.setUser(user);
		publicacionService.addPublicacion(publicacion);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/publicacion/add")
	public String getPublicacion(Model model){
		model.addAttribute("publicacion", new Publicacion());
		return "publicacion/add";
	}

}
