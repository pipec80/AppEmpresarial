package cl.pipec.appempresarial;

import cl.pipec.appempresarial.clases.Cliente;
import cl.pipec.appempresarial.clases.DataBase;
import cl.pipec.appempresarial.clases.Usuario;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ClientesAgregarActivity extends Activity {
	public int iDusuario;
	public EditText txt_nombre, txt_apellido, txt_fono, txt_direccion;
	public Cliente clienteAdd;
	DataBase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clientes_agregar);
		// get action bar   
        ActionBar actionBar = getActionBar();
        // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);
        //
		db = new DataBase(getApplicationContext());
		//
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		iDusuario = bundle.getInt("id");
		Usuario usuario = new Usuario();
		usuario = db.buscarUsuarioId(iDusuario);
		Button btn_agregar = (Button) findViewById(R.id.btn_agregarClienteForm);

		btn_agregar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Cliente cliente = new Cliente();
				cliente.idUsuario = iDusuario;
				cliente.setVisible(1);
				txt_nombre = (EditText) findViewById(R.id.txt_addNombre);
				cliente.nombre = txt_nombre.getText().toString().trim();

				txt_apellido = (EditText) findViewById(R.id.txt_addApellido);
				cliente.apellido = txt_apellido.getText().toString().trim();

				txt_direccion = (EditText) findViewById(R.id.txt_addDireccion);
				cliente.direccion = txt_direccion.getText().toString().trim();

				txt_fono = (EditText) findViewById(R.id.txt_addTelefono);
				cliente.telefono = txt_fono.getText().toString().trim();

				long idInsert = db.agregarCliente(cliente);
				if (idInsert > 0) {
					txt_nombre.setText("");
					txt_apellido.setText("");
					txt_direccion.setText("");
					txt_fono.setText("");					
					Toast.makeText(ClientesAgregarActivity.this,
							"Cliente Agregado", Toast.LENGTH_SHORT).show();

				} else {
					Toast.makeText(ClientesAgregarActivity.this,
							"Problemas agregar Cliente", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.clientes_agregar, menu);
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
}
