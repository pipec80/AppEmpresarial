package cl.pipec.appempresarial;

import cl.pipec.appempresarial.clases.Cliente;
import cl.pipec.appempresarial.clases.DataBase;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ClientesEditarActivity extends Activity implements OnClickListener {
	public int iDusuario;
	public int iDcliente;
	public EditText txt_nombre, txt_apellido, txt_direccion, txt_telefono;
	public Cliente cliente;
	public Button btn_borrar, btn_modificar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clientes_editar);
		//
		// get action bar   
        ActionBar actionBar = getActionBar();
        // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);
		// Recupera parametros
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		iDusuario = bundle.getInt("id");
		iDcliente = bundle.getInt("pos");
		cliente = new Cliente();
		//
		DataBase db = new DataBase(getApplicationContext());
		cliente = db.buscarCliente(iDcliente);
		//
		txt_nombre = (EditText) findViewById(R.id.txt_modNombreCliente);
		txt_nombre.setText(cliente.getNombre().toString().trim());
		txt_apellido = (EditText) findViewById(R.id.txt_modApellidoCliente);
		txt_apellido.setText(cliente.getApellido());
		txt_direccion = (EditText) findViewById(R.id.txt_modDireccion);
		txt_direccion.setText(cliente.getDireccion().toString().trim());
		txt_telefono = (EditText) findViewById(R.id.txt_modTelefono);
		txt_telefono.setText(cliente.getTelefono().toString().trim());
		//
		btn_borrar = (Button) findViewById(R.id.btn_borrarCliente);
		btn_borrar.setOnClickListener(this);
		btn_modificar = (Button) findViewById(R.id.btn_modificarCliente);
		btn_modificar.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.clientes_editar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == android.R.id.home) {
			this.finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		DataBase db = new DataBase(getApplicationContext());
		switch (v.getId()) {
		case R.id.btn_modificarCliente:
			cliente.setId(iDcliente);
			cliente.setIdUsuario(iDusuario);
			cliente.setNombre(txt_nombre.getText().toString().trim());
			cliente.setApellido(txt_apellido.getText().toString().trim());
			cliente.setDireccion(txt_direccion.getText().toString().trim());
			cliente.setTelefono(txt_telefono.getText().toString().trim());
			cliente.setVisible(1);
			long rows_affected = db.updateCliente(cliente);
			if (rows_affected > 0) {
				Toast toast = Toast.makeText(getApplicationContext(),
						"Actualización Correcta", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER | Gravity.CENTER, 10, 0);
				toast.show();
			} else {
				Toast toast = Toast.makeText(getApplicationContext(),
						"Error Actualización", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER | Gravity.CENTER, 10, 0);
				toast.show();
			}
			break;
		case R.id.btn_borrarCliente:
			long rows_affected2 = db.deleteCliente(cliente);
			if (rows_affected2 > 0) {
				Toast toast = Toast.makeText(getApplicationContext(),
						"Borrado Correcto", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER | Gravity.CENTER, 10, 0);
				toast.show();
			} else {
				Toast toast = Toast.makeText(getApplicationContext(),
						"Error Borrado", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER | Gravity.CENTER, 10, 0);
				toast.show();
			}

			break;
		}

	}
}
