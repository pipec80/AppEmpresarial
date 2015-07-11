package cl.pipec.appempresarial;

import java.util.ArrayList;

import cl.pipec.appempresarial.clases.Cliente;
import cl.pipec.appempresarial.clases.CustomListAdapter;
import cl.pipec.appempresarial.clases.DataBase;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ClientesFragment extends Fragment {
	View rootView;
	public int nIDusuario;
	SwipeRefreshLayout mSwipeRefreshLayout;
	ListView lv_listado;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// Recupera parametros
		Intent intent = getActivity().getIntent();
		Bundle bundle = intent.getExtras();
		nIDusuario = bundle.getInt("id");
		//
		rootView = inflater.inflate(R.layout.fragment_clientes, container,
				false);
		//
		DataBase db = new DataBase(getActivity());
		lv_listado = (ListView) rootView.findViewById(R.id.lv_listaClientes);
		ArrayList<Cliente> listaClientes = db.listarClientes(nIDusuario);
		lv_listado.setAdapter(new CustomListAdapter(getActivity(),
				listaClientes));
		//
		lv_listado.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Object o = lv_listado.getItemAtPosition(position);
				Cliente nCliente = (Cliente) o;
				Toast.makeText(getActivity(), "Selected :" + " " + nCliente,
						Toast.LENGTH_LONG).show();
				Bundle bundle2 = new Bundle();
				bundle2.putInt("id", nIDusuario);
				bundle2.putInt("pos", nCliente.getId());
				Intent intent = new Intent(getActivity(),
						ClientesEditarActivity.class);
				intent.putExtras(bundle2);
				startActivity(intent);
			}
		});
		// refrescar la vista pull down
		mSwipeRefreshLayout = (SwipeRefreshLayout) rootView
				.findViewById(R.id.swipe_refresh_layout);
		mSwipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				refreshContent();
			}
		});
		Button btn_agregar = (Button) rootView
				.findViewById(R.id.btn_clientes_agregar);
		btn_agregar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle bundle2 = new Bundle();
				bundle2.putInt("id", nIDusuario);
				Intent intent = new Intent(getActivity(),
						ClientesAgregarActivity.class);
				intent.putExtras(bundle2);
				startActivity(intent);
			}
		});
		return rootView;
	}

	// metodo para cargar la data
	private void refreshContent() {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				DataBase db = new DataBase(getActivity());
				lv_listado = (ListView) rootView
						.findViewById(R.id.lv_listaClientes);
				ArrayList<Cliente> listaClientes = db.listarClientes(nIDusuario);
				lv_listado.setAdapter(new CustomListAdapter(getActivity(),
						listaClientes));
				mSwipeRefreshLayout.setRefreshing(false);
			}
		}, 2000);
	}
}
