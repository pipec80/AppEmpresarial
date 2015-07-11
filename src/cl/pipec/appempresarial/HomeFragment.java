package cl.pipec.appempresarial;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cl.pipec.appempresarial.clases.DataBase;
import cl.pipec.appempresarial.clases.Ruta;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class HomeFragment extends Fragment {
	private static View view;
	private GoogleMap googleMap;
	private ArrayList<LatLng> arrayPoints = null;
	PolylineOptions polylineOptions;
	LatLng lugar;
	MarkerOptions marker;
	public int nIDusuario;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// Recupera parametros
		Intent intent = getActivity().getIntent();
		Bundle bundle = intent.getExtras();
		nIDusuario = bundle.getInt("id");
		//
		if (view != null) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null)
				parent.removeView(view);
		}

		try {
			view = inflater.inflate(R.layout.fragment_home, container, false);
		} catch (InflateException e) {

		}
		// mapa
		try {
			// Loading map
			crearMapa();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}

	private void crearMapa() {
		if (googleMap == null) {
			googleMap = ((SupportMapFragment) getChildFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			googleMap.setMyLocationEnabled(true);
			googleMap.getUiSettings().setZoomControlsEnabled(true);
			googleMap.getUiSettings().setZoomGesturesEnabled(false);
			googleMap.getUiSettings().setCompassEnabled(true);
			googleMap.getUiSettings().setMyLocationButtonEnabled(true);
			googleMap.getUiSettings().setRotateGesturesEnabled(true);
			//
			DataBase db = new DataBase(getActivity());
			ArrayList<Ruta> listadoRuta = db.listarRuta(nIDusuario);
			arrayPoints = new ArrayList<LatLng>();
			// create marker
			int cantidad = listadoRuta.size();
			Ruta ruta;
			for (int i = 0; i < cantidad; i++) {
				ruta = listadoRuta.get(i);
				lugar = new LatLng(ruta.getLat(), ruta.getLng());
				arrayPoints.add(lugar);
				MarkerOptions marker = new MarkerOptions();
				marker.position(lugar);
				marker.title(ruta.getNombreLocal());
				marker.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
				googleMap.addMarker(marker);
			}

			CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(arrayPoints.get(0)).zoom(16).build();

			googleMap.animateCamera(CameraUpdateFactory
					.newCameraPosition(cameraPosition));
			polylineOptions = new PolylineOptions();
			polylineOptions.color(Color.RED);
			polylineOptions.width(5);
			polylineOptions.addAll(arrayPoints);
			googleMap.addPolyline(polylineOptions);
		}

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		crearMapa();
	}

}
