package cl.pipec.appempresarial.clases;
import java.util.ArrayList;

public class Pedido {
	public int id;
	public int idCliente;
	public int cantidad;
	public String fecha;
	public int precio;
	public int idProducto;
	public static ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
	
	

	/**
	 * @param id
	 * @param idCliente
	 * @param cantidad
	 * @param fecha
	 * @param precio
	 */
	public Pedido(int id, int idCliente, int cantidad, String fecha, int precio) {
		super();
		this.id = id;
		this.idCliente = idCliente;
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.precio = precio;
	}
	/**
	 * 
	 */
	public Pedido() {
		super();
		// TODO Auto-generated constructor stub
	}
	//
	
	public void AgregarPedido(Pedido uPedido)
	{
		listaPedidos.add(uPedido);
		
	}
	
	
	
	//
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public static ArrayList<Pedido> getListaPedidos() {
		return listaPedidos;
	}
	public static void setListaPedidos(ArrayList<Pedido> listaPedidos) {
		Pedido.listaPedidos = listaPedidos;
	}
	//
	@Override
	public String toString() {
		return "Pedido [id=" + id + ", idCliente=" + idCliente + ", cantidad="
				+ cantidad + ", fecha=" + fecha + ", precio=" + precio + "]";
	}


}
