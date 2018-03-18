package com.uniovi.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Friendship;
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
		publicacion.setUser(user); publicacion.setFecha(new Date());
		publicacionService.addPublicacion(publicacion);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/publicacion/add")
	public String getPublicacion(Model model){
		model.addAttribute("publicacion", new Publicacion());
		return "publicacion/add";
	}
	
	@RequestMapping("/publicacion/list")
	public String getListado(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Publicacion> publicaciones = new ArrayList<Publicacion>();
		publicaciones = publicacionService.getPublicaciones(auth.getName());		
		model.addAttribute("publicacionesList", publicaciones);
		return "publicacion/list";
	}
	
	@RequestMapping(value="publicacionFriend/{id}", method=RequestMethod.GET)
	public String sendFriendRequest(Model model, @PathVariable Long id){
		User amigo = usersService.usuarioAmigo(id);
		if(amigo != null) {
			List<Publicacion> publicaciones = new ArrayList<Publicacion>();
			publicaciones = publicacionService.getPublicaciones(amigo.getEmail());
			model.addAttribute("listFriends", publicaciones);
			return "user/publicacionFriend";
		}
		System.out.println("Es null");
		return "redirect:/user/listFriends";
	}

}
