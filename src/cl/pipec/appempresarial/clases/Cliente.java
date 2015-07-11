package cl.pipec.appempresarial.clases;

import java.util.ArrayList;

public class Cliente {

	// variables utilizadas en la aplicacion
	public int id;
	public String nombre;
	public String apellido;
	public String direccion;
	public String telefono;
	public int idUsuario;
	public int visible;
	public static ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

	public Cliente() {
		super();
		// listarClientes();
	}

	// creo el listado de clientes
	public void listarClientes() {
		Cliente cliente = new Cliente();
		cliente.id = 1;
		cliente.idUsuario = 1;
		cliente.nombre = "pedro";
		cliente.apellido = "torres";
		cliente.direccion = "avenida simpre viva 357";
		cliente.telefono = "+56227101510";
		// listaClientes.add(cliente);
		agregarCliente(cliente);

		cliente = new Cliente();
		cliente.id = 2;
		cliente.idUsuario = 1;
		cliente.nombre = "patricio";
		cliente.apellido = "puri";
		cliente.direccion = "los claveles 357";
		cliente.telefono = "+56227105510";
		// listaClientes.add(cliente);
		agregarCliente(cliente);

		cliente = new Cliente();
		cliente.id = 3;
		cliente.idUsuario = 1;
		cliente.nombre = "jurem";
		cliente.apellido = "soto";
		cliente.direccion = "oton 357";
		cliente.telefono = "+56225205510";
		// listaClientes.add(cliente);
		agregarCliente(cliente);

		cliente = new Cliente();
		cliente.id = 4;
		cliente.idUsuario = 2;
		cliente.nombre = "pedro";
		cliente.apellido = "cuadrado";
		cliente.direccion = "avenida  viva 150";
		cliente.telefono = "+56227101510";
		// listaClientes.add(cliente);
		agregarCliente(cliente);

		cliente = new Cliente();
		cliente.id = 5;
		cliente.idUsuario = 2;
		cliente.nombre = "esteban";
		cliente.apellido = "piro";
		cliente.direccion = "los ross 357";
		cliente.telefono = "+56227105510";
		// listaClientes.add(cliente);
		agregarCliente(cliente);

		cliente = new Cliente();
		cliente.id = 6;
		cliente.idUsuario = 2;
		cliente.nombre = "mohamed";
		cliente.apellido = "soto";
		cliente.direccion = "roton 357";
		cliente.telefono = "+56225205510";
		// listaClientes.add(cliente);
		agregarCliente(cliente);

		// return this.listaClientes;

	}

	//
	public ArrayList<Cliente> usuariosActivos() {
		Cliente cliente;

		ArrayList<Cliente> listado = listaClientes;
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		int cantidad = listaClientes.size();
		for (int i = 0; i < cantidad; i++) {
			cliente = listado.get(i);
			if (cliente.visible == 1) {
				clientes.add(cliente);
			}
		}
		return clientes;

	}

	// metodo que permite crear la lista para un vendedor
	public ArrayList<Cliente> buscarListaVendedor(int idUser) {
		// listarClientes();
		Cliente cliente;

		ArrayList<Cliente> listado = listaClientes;
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		int cantidad = listaClientes.size();
		for (int i = 0; i < cantidad; i++) {
			cliente = listado.get(i);
			if (cliente.idUsuario == idUser && cliente.visible == 1) {
				clientes.add(cliente);
			}
		}
		return clientes;
	}

	public static void agregarCliente(Cliente nCliente) {

		listaClientes.add(nCliente);

	}

	//
	public Cliente BuscarCliente(int pos) {
		Cliente nCliente = new Cliente();
		nCliente = listaClientes.get(pos);
		return nCliente;

	}

	//
	public void BorrarCliente(int idCliente) {
		// listaClientes.remove(idCliente);
		Cliente nCliente = new Cliente();
		nCliente = listaClientes.get(idCliente);
		nCliente.setVisible(0);
	}

	//

	public int getId() {
		return id;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	// modifico el metodo toString para mostrar los datos del cliente
	@Override
	public String toString() {
		return " nombre :" + nombre + " " + apellido + " direccion:"
				+ direccion;
	}
}
