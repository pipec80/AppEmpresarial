package cl.pipec.appempresarial.clases;

import java.util.ArrayList;

public class Producto {
	public int id;
	public String nombre;
	
	public ArrayList<Producto> listaProductos()
	{
		ArrayList<Producto> lista = new ArrayList<Producto>();
		
		Producto producto = new Producto();
		producto.id = 1;
		producto.nombre = "bebida";
		lista.add(producto);
		
		producto = new Producto();
		producto.id = 2;
		producto.nombre = "leche";
		lista.add(producto);
		
		producto = new Producto();
		producto.id = 3;
		producto.nombre = "pan";
		lista.add(producto);
		
		producto = new Producto();
		producto.id = 4;
		producto.nombre = "detergente";
		lista.add(producto);
		
		producto = new Producto();
		producto.id = 5;
		producto.nombre = "arroz";
		lista.add(producto);
		
		return lista;
		
	}
//
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

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + "]";
	}
	
	

}
