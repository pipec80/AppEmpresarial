package cl.pipec.appempresarial;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import cl.pipec.appempresarial.clases.Cliente;
import cl.pipec.appempresarial.clases.DataBase;
import cl.pipec.appempresarial.clases.Pedido;
import cl.pipec.appempresarial.clases.Producto;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class PedidosFragment extends Fragment {
	private View rootView;
	public int iDusuario;
	private EditText ext_fecha, txt_cantidad, txt_precio;
	private DatePickerDialog dpd_fecha;
	private SimpleDateFormat dateFormatter;
	public int idProducto, idCliente, idUSer;
	private Producto producto;
	private Cliente cliente;
	private Button btn_agregar;
	private ArrayAdapter<Producto> adapter;
	private ArrayAdapter<Cliente> adapterCli;
	DataBase db;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// Recupera parametros
		Intent intent = getActivity().getIntent();
		Bundle bundle = intent.getExtras();
		iDusuario = bundle.getInt("id");
		//database
		db = new DataBase(getActivity());
		
		/*Producto producto = new Producto();
		producto.id = 1;
		producto.nombre = "bebida";
		db.agregarProducto(producto);

		producto = new Producto();
		producto.id = 2;
		producto.nombre = "leche";
		db.agregarProducto(producto);

		producto = new Producto();
		producto.id = 3;
		producto.nombre = "pan";
		db.agregarProducto(producto);

		producto = new Producto();
		producto.id = 4;
		producto.nombre = "detergente";
		db.agregarProducto(producto);

		producto = new Producto();
		producto.id = 5;
		producto.nombre = "arroz";
		db.agregarProducto(producto);*/
		
		//
		rootView = inflater
				.inflate(R.layout.fragment_pedidos, container, false);
		//
		txt_cantidad = (EditText) rootView.findViewById(R.id.txt_cantidad);
		txt_precio	= (EditText) rootView.findViewById(R.id.txt_precio);
		// Llenado productos
		Spinner spin_productos = (Spinner) rootView
				.findViewById(R.id.spi_producto);
		producto = new Producto();
		ArrayList<Producto> productos = db.listarProductos();
		adapter = new ArrayAdapter<Producto>(getActivity(),
				android.R.layout.simple_spinner_item, productos);
		adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
		spin_productos.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		spin_productos.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Producto productoItem = adapter.getItem(position);
				idProducto = productoItem.getId();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		// Llenado Clientes
		Spinner sp_clientes = (Spinner) rootView
				.findViewById(R.id.spi_usuarios);
		ArrayList<Cliente> clientes = db.listarClientes(iDusuario);
		adapterCli = new ArrayAdapter<Cliente>(getActivity(),
				android.R.layout.simple_spinner_item, clientes);
		adapterCli.setDropDownViewResource(R.layout.spinner_dropdown_item);
		sp_clientes.setAdapter(adapterCli);
		adapterCli.notifyDataSetChanged();
		sp_clientes.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Cliente clienteitem = adapterCli.getItem(position);
				idCliente = clienteitem.getId();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		// fecha
		dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
		ext_fecha = (EditText) rootView.findViewById(R.id.txt_fecha);
		ext_fecha.setInputType(InputType.TYPE_NULL);
		ext_fecha.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dpd_fecha.show();
			}
		});
		ajustarFecha();
		//
		btn_agregar = (Button) rootView.findViewById(R.id.btn_registrar_pedido);
		btn_agregar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Pedido uPedido = new Pedido();
				uPedido.setFecha(ext_fecha.getText().toString().trim());
				uPedido.setCantidad(Integer.parseInt(txt_cantidad.getText()
						.toString().trim()));
				uPedido.setIdCliente(idCliente);
				uPedido.setIdProducto(idProducto);
				uPedido.setPrecio(Integer.parseInt(txt_precio.getText()
						.toString().trim()));			
				long rows_affected = db.agregarPedido(uPedido);
				if (rows_affected > 0) {
					Toast toast = Toast.makeText(getActivity(),
							"Pedido Agregado", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER | Gravity.CENTER, 10, 0);
					toast.show();
				} else {
					Toast toast = Toast.makeText(getActivity(),
							"Error Pedido", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER | Gravity.CENTER, 10, 0);
					toast.show();
				}
			}
		});
		return rootView;
	}

	private void ajustarFecha() {
		Calendar newCalendar = Calendar.getInstance();
		dpd_fecha = new DatePickerDialog(getActivity(),
				new OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// TODO Auto-generated method stub
						Calendar newDate = Calendar.getInstance();
						newDate.set(year, monthOfYear, dayOfMonth);
						ext_fecha.setText(dateFormatter.format(newDate
								.getTime()));
					}
				}, newCalendar.get(Calendar.YEAR),
				newCalendar.get(Calendar.MONTH),
				newCalendar.get(Calendar.DAY_OF_MONTH));
	}

}
