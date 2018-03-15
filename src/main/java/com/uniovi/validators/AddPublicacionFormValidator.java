package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.*;

import com.uniovi.entities.Publicacion;

@Component
public class AddPublicacionFormValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Publicacion.class.equals(aClass);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Publicacion publicacion = (Publicacion) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titulo", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descripcion", "Error.empty");
		if (publicacion.getDescripcion().length() < 5 || publicacion.getDescripcion().length() > 500) {
			errors.rejectValue("descripcion", "Error.publicacionAdd.descripcion.length");
		}
		if (publicacion.getTitulo().length() < 5 || publicacion.getTitulo().length() > 50) {
			errors.rejectValue("titulo", "Error.publicacionAdd.titulo.length");
		}		
	}
}
