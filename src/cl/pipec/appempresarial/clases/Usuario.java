package cl.pipec.appempresarial.clases;

import java.util.ArrayList;

public class Usuario {
	

	//variables utilizadas en la aplicacion
	public int id;
	public String nombre;
	public String login;
	public String pass;
	
	// creo la lista de usuarios
	public ArrayList<Usuario> listarUsuario()
	{
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		Usuario usuario = new Usuario();
		usuario.id 		= 1;
		usuario.nombre 	= "Felipe castro";
		usuario.login 	=  "felipe";
		usuario.pass  	= "1234";
		lista.add(usuario);
		usuario = new Usuario();
		usuario.id 		= 2;
		usuario.nombre 	= "Pedro torres";
		usuario.login 	=  "pedro";
		usuario.pass  	= "1234";
		lista.add(usuario);
		return lista;
	}
	
	//metodo que valida que el usuario y contraseña sean validos
	public boolean validarIngreso(String uLogin, String pwd)
	{
		Usuario usuario;
		ArrayList<Usuario> listado =  listarUsuario();
		int cantidad =  listado.size();
		for(int i =0; i< cantidad; i++)
		{
			usuario = listado.get(i);
			if(usuario.login.equals(uLogin) && usuario.pass.equals(pwd))
			{
				return true;
			}
		}
		return false;
	}
	//busco el usuario valido y creo un usuario
	public Usuario buscarUsuario(String uLogin, String pwd)
	{
		Usuario usuario;
		ArrayList<Usuario> listado =  listarUsuario();
		int cantidad =  listado.size();
		for(int i =0; i< cantidad; i++)
		{
			usuario = listado.get(i);
			if(usuario.login.equals(uLogin) && usuario.pass.equals(pwd))
			{
				return usuario;
			}
		}
		return null;
	}
	public Usuario buscarUsuarioPorId(int idUser)
	{
		Usuario usuario;
		ArrayList<Usuario> listado =  listarUsuario();
		int cantidad =  listado.size();
		for(int i =0; i< cantidad; i++)
		{
			usuario = listado.get(i);
			if(usuario.id == idUser)
			{
				return usuario;
			}
		}
		return null;
	}
	//getter and setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	// modifico el metodo toString 
	public String toString() {
		return String.valueOf(this.id) + " : " + this.nombre + " (" + this.pass +")";
	}
	

}
