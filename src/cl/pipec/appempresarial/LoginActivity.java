package cl.pipec.appempresarial;

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

public class LoginActivity extends Activity {
	DataBase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		//
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.hide();
		// database
		db = new DataBase(getApplicationContext());
		// usuarios
		
		/* Usuario usuario = new Usuario(); usuario.setNombre("felipe");
		 usuario.setLogin("felipe"); usuario.setPass("1234");
		 db.agregarUsuario(usuario); usuario = new Usuario();
		 usuario.setNombre("pedro"); usuario.setLogin("pedro");
		 usuario.setPass("1234"); db.agregarUsuario(usuario);
		*/
		 

		// creo el boton y lo busco en la vista
		Button btnLogin = (Button) findViewById(R.id.btn_ingresar);
		btnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				validarIngresoVendedor();
			}

		});
	}

	// busco los campo de texto donde el usuario ingresa su user y pass y los
	// valido
	public void validarIngresoVendedor() {
		EditText txt_user = (EditText) findViewById(R.id.txt_usuario);
		EditText txt_pass = (EditText) findViewById(R.id.txt_pass);
		String nUser = txt_user.getText().toString().trim();
		String nPass = txt_pass.getText().toString().trim();

		Usuario usuario = new Usuario();
		if (!nUser.isEmpty() && !nPass.isEmpty()) {

			usuario = db.buscarLogin(nUser, nPass);
			if (usuario != null) {
				Toast.makeText(LoginActivity.this, "Espere mientras ingresa",
						Toast.LENGTH_SHORT).show();
				txt_user.setText("");
				txt_pass.setText("");
				// bundle nos permite almacenar valores de la siguiente forma
				// bundle.putString( clave, valor );
				// pudiendo BUNDLE almacenar valores de todo tipo
				Bundle bundle = new Bundle();
				bundle.putInt("id", usuario.getId());
				// Intent nos permite enlazar dos actividades
				Intent intent = new Intent(LoginActivity.this,
						MainActivity.class);
				intent.putExtras(bundle);
				LoginActivity.this.startActivity(intent);
			} else {
				Toast.makeText(LoginActivity.this,
						"usuario o contraseña incorrecto", Toast.LENGTH_SHORT)
						.show();
			}

		} else {
			Toast.makeText(LoginActivity.this,
					"usuario o contraseña incorrecto", Toast.LENGTH_SHORT)
					.show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
