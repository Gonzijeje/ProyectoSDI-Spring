package com.uniovi.services;

import java.util.Date;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Friendship;
import com.uniovi.entities.Publicacion;
import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private FriendshipService friendshipService;
	
	@Autowired
	private RolesService rolesService;
	
	@PostConstruct
	public void init() {
		User user1 = new User("pedro@uniovi.es", "Pedro", "123456");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[1]);
		User user2 = new User("lucas@uniovi.es", "Lucas", "123456");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[0]);
		User user3 = new User("maria@uniovi.es", "María", "123456");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[0]);
		User user4 = new User("gonzalo@uniovi.es", "Gonzalo", "123456");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[0]);
		User user5 = new User("pelayo@uniovi.es", "Pelayo", "123456");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[0]);
		User user6 = new User("edward@uniovi.es", "Edward", "123456");
		user6.setPassword("123456");
		user6.setRole(rolesService.getRoles()[0]);
		
		User user7 = new User("pablo@uniovi.es", "Pablo", "123456");
		user7.setPassword("123456");
		User user8 = new User("adrian@uniovi.es", "Adrian", "123456");
		user8.setPassword("123456");
		User user9 = new User("javier@uniovi.es", "Javer", "123456");
		user9.setPassword("123456");
		User user10 = new User("ricky@uniovi.es", "Ricky", "123456");
		user10.setPassword("123456");
		User user11 = new User("martin@uniovi.es", "Martin", "123456");
		user11.setPassword("123456");
		User user12 = new User("damian@uniovi.es", "Damian", "123456");
		user12.setPassword("123456");
		
		User user13 = new User("Hernán@uniovi.es", "Hernán Pérez", "123456");
		user13.setPassword("123456");
		User user14 = new User("Acebal@uniovi.es", "Pablo Acebal", "123456");
		user14.setPassword("123456");
		User user15 = new User("Calvillo@uniovi.es", "Nacho", "123456");
		user15.setPassword("123456");
		User user16 = new User("Cesar@uniovi.es", "César", "123456");
		user16.setPassword("123456");
		User user17 = new User("Jorge@uniovi.es", "Jorge", "123456");
		user17.setPassword("123456");
		User user18 = new User("Fran@uniovi.es", "Francisco", "123456");
		user18.setPassword("123456");

		user2.getPublicaciones().add(new Publicacion("SDI", "Asifnffkjsadbsadckjbsadc sdklcj sdc "
				+ "asvvavasdvasdvasdvasdvasdvasdvsdv adv "
				+ "dsfvvv fvs dvd"
				+ "nsjdc sd.kjcbsd njsndcb sdcdsv cv dv ", new Date(), user2));
		
		user2.getPublicaciones().add(new Publicacion("ASR", "AsifnffkjsadFSASFASDFASFSDASDFbsadckjbsadc sdklcj sdc "
				+ "asvvavasdvasdvasdvasdvasdvasdvsdv adv "
				+ "dsfvvv fvs dvd"
				+ "nsjdc sd.kjcbsd njsndcb sdcdsv cv dv ", new Date(), user2));
		
		user3.getPublicaciones().add(new Publicacion("SDI", "Asifnffkjsadbsadckjbsadc sdklcj sdc "
				+ "asvvavasdvasdvasdvasdvasdvasdvsdv adv "
				+ "dsfvvv fvs dvd"
				+ "nsjdc sd.kjcbsd njsndcb sdcdsv cv dv ", new Date(), user3));
		
		user5.getPublicaciones().add(new Publicacion("ASR", "AsifnffkjsadFSASFASDFASFSDASDFbsadckjbsadc sdklcj sdc "
				+ "asvvavasdvasdvasdvasdvasdvasdvsdv adv "
				+ "dsfvvv fvs dvd"
				+ "nsjdc sd.kjcbsd njsndcb sdcdsv cv dv ", new Date(), user5));
		
		user6.getPublicaciones().add(new Publicacion("ASR", "AsifnffkjsadFSASFASDFASFSDASDFbsadckjbsadc sdklcj sdc "
				+ "asvvavasdvasdvasdvasdvasdvasdvsdv adv "
				+ "dsfvvv fvs dvd"
				+ "nsjdc sd.kjcbsd njsndcb sdcdsv cv dv ", new Date(), user6));
		
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		usersService.addUser(user7);
		usersService.addUser(user8);
		usersService.addUser(user9);
		usersService.addUser(user10);
		usersService.addUser(user11);
		usersService.addUser(user12);
		usersService.addUser(user13);
		usersService.addUser(user14);
		usersService.addUser(user15);
		usersService.addUser(user16);
		usersService.addUser(user17);
		usersService.addUser(user18);
		
		friendshipService.addFriendship(new Friendship(user1,user2));
		friendshipService.addFriendship(new Friendship(user2,user1));
		friendshipService.addFriendship(new Friendship(user1,user3));
		friendshipService.addFriendship(new Friendship(user3,user1));
		friendshipService.addFriendship(new Friendship(user1,user4));
		friendshipService.addFriendship(new Friendship(user4,user1));
		friendshipService.addFriendship(new Friendship(user1,user5));
		friendshipService.addFriendship(new Friendship(user5,user1));
	}

}
