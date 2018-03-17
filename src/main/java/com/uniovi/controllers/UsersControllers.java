package com.uniovi.controllers;

import java.util.LinkedList;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Friendship;
import com.uniovi.entities.Peticion;
import com.uniovi.entities.User;
import com.uniovi.services.FriendshipService;
import com.uniovi.services.PeticionAmistadService;
import com.uniovi.services.PublicacionService;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Controller
public class UsersControllers {
	
	@Autowired 
	private SignUpFormValidator signUpFormValidator;
	
	@Autowired
	private PeticionAmistadService peticionService;
	
	@Autowired
	private PublicacionService publicacionService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private FriendshipService friendshipService;
	
	@Autowired 
	private SecurityService securityService;
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private HttpSession httpSession;
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String setUser(@ModelAttribute @Validated User user, 
			BindingResult result, Model modelo) {
		
		signUpFormValidator.validate(user, result); 
		
		if (result.hasErrors()) { 
			return "signup"; 
		}
		user.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		httpSession.setAttribute("hola", "user");
		return "login";
	}
	
	@RequestMapping(value = "/admin/logearse", method = RequestMethod.GET)
	public String adminLogin(Model model) {
		httpSession.setAttribute("hola", "admin");
		return "admin/logearse";
	}
	
	
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET) 
	public String home(Model model) { 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User activeUser = usersService.getUserByEmail(auth.getName());
		if(activeUser.getRole().equals("ROLE_ADMIN")) {
			return "redirect:/user/list";
		}else if(activeUser.getRole().equals("ROLE_USER") && (httpSession.getAttribute("hola").equals("user"))){
			return "home";
		}else {
			return "redirect:/logout";
		}
			
	}

	@RequestMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable Long id) {
		User user = usersService.getUser(id);
		publicacionService.deletePublicacionByUser(user.getId());
		friendshipService.deleteFrienshipByUser(user.getId());
		usersService.deleteUser(id);
		return "redirect:/user/list";
	}
	
	@RequestMapping("/user/list")
	public String getListado(Model model, Pageable pageable,
			@RequestParam(value = "", required=false) String searchText){		
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		if(searchText != null && !searchText.isEmpty()) {
			users = usersService.searchUserByNameOrEmail(pageable, searchText);
		}
		else
		{
			users = usersService.getUsers(pageable);
		}
		model.addAttribute("usersList", users.getContent());
		model.addAttribute("page", users);
		return "user/list";
	}
	
	@RequestMapping("/user/listFriends")
	public String getListadoAmigos(Model model, Pageable pageable){
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User activeUser = usersService.getUserByEmail(auth.getName());
		
		users = usersService.getUsersFriends(pageable, activeUser.getEmail());
		
		model.addAttribute("usersList", users.getContent());
		model.addAttribute("page", users);
		return "user/listFriends";
	}
	
	@RequestMapping("/friendRequest/list")
	public String getFriendRequests(Model model, Pageable pageable){
		Page<Peticion> fr = new PageImpl<Peticion>(new LinkedList<Peticion>());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//User userEnvia = usersService.getUserByEmail(auth.getName());
		fr=peticionService.getFriendRequestsByUserEnvia(pageable, auth.getName());
		
		model.addAttribute("friendRequestsList", fr.getContent());
		model.addAttribute("page", fr);
		return "/friendRequest/list";
	}
	
	@RequestMapping(value="/user/send/{id}", method=RequestMethod.GET)
	public String sendFriendRequest(Model model, @PathVariable Long id){
		User userRecibe = usersService.getUser(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userEnvia = usersService.getUserByEmail(auth.getName());
		
		peticionService.addPeticion(new Peticion(userEnvia,userRecibe,false));
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/friendRequest/{id}/accept", method=RequestMethod.GET)
	public String acceptFriendRequest(Model model, @PathVariable Long id) {
		Peticion pa = peticionService.getPeticion(id);
		friendshipService.addFriendship(new Friendship(pa.getUserEnvia(),pa.getUserRecibe()));
		friendshipService.addFriendship(new Friendship(pa.getUserRecibe(),pa.getUserEnvia()));
		peticionService.deletePeticion(id);
		return "redirect:/friendRequest/list";
	}
	
	@RequestMapping("/user/list/update")
	public String updateList(Model model, Pageable pageable) {
		Page<User> users = usersService.getUsers(pageable);
		model.addAttribute("usersList", users.getContent());
		return "user/list :: tableUsers";
	}

}
