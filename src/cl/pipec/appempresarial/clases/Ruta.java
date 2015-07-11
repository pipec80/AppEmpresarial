package cl.pipec.appempresarial.clases;

public class Ruta {
	public int idRuta,idUsuario;
	public double lat, lng;
	public String nombreLocal;
	
	public Ruta() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/*************GETTER AND SETTER*****************/

	public int getIdRuta() {
		return idRuta;
	}

	public void setIdRuta(int idRuta) {
		this.idRuta = idRuta;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getNombreLocal() {
		return nombreLocal;
	}

	public void setNombreLocal(String nombreLocal) {
		this.nombreLocal = nombreLocal;
	}

	@Override
	public String toString() {
		return "Ruta [idRuta=" + idRuta + ", idUsuario=" + idUsuario + ", lat="
				+ lat + ", lng=" + lng + ", nombreLocal=" + nombreLocal + "]";
	}	
}
