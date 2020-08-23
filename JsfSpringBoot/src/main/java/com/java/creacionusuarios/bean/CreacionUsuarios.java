
package com.java.creacionusuarios.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.java.creacionusuarios.persistencia.Usuarios;
import com.java.creacionusuarios.serviceRest.ServicesRestTemplate;



/**
 *
 * @author david
 */

@Named(value = "creacionUsuarios")
@SessionScoped
public class CreacionUsuarios implements Serializable {
	@Autowired
	private ServicesRestTemplate servicerest;

	private List<Usuarios> lusuarios = null;

	private Usuarios usuario = new Usuarios();

	 public void initialize() throws Exception  {
		 //cargamos de primera de base de datos los usuarios
		 if(lusuarios==null) {
			 lusuarios=new ArrayList();
		 }
	 }


	public ServicesRestTemplate getServicerest() {
		return servicerest;
	}

	public void setServicerest(ServicesRestTemplate servicerest) {
		this.servicerest = servicerest;
	}

	public void guardarSession() throws Exception {
		servicerest.guardar(usuario); // debemos controlar los roles en index
		lusuarios.add(usuario);
		usuario = new Usuarios();

	}

	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		int annio, annioactual;
		if (value != null) {
			Calendar c = Calendar.getInstance();
			annioactual = c.get(Calendar.YEAR);
			c.setTime((Date) value);
			annio = c.get(Calendar.YEAR);
			if (annioactual - annio < 14)
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"La edad del usuario debe ser mayor de 14 años", null));

		}

	}

	public String format(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		if (date != null)
			return formatter.format(date);
		else
			return "";

	}
	
	public List<Usuarios> getUsuariosBD() throws Exception {
		return servicerest.getUsuarios();
	}

	public List<Usuarios> getLusuarios() {
		return lusuarios;
	}

	public void setLusuarios(List<Usuarios> lusuarios) {
		this.lusuarios = lusuarios;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

}
