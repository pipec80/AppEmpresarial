package cl.pipec.appempresarial.clases;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBase extends SQLiteOpenHelper {

	private static final String TAG = DataBase.class.getSimpleName();
	private static final String LOG = "DataBase";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "db_empresa.db";
	private static final String TABLE_NAME = "lugares";
	private static final String TABLE_NAME_USUARIO = "Usuario";
	private static final String TABLE_NAME_CLIENTE = "cliente";
	private static final String TABLE_NAME_PEDIDO = "pedido";
	private static final String TABLE_NAME_PRODUCTO = "producto";
	private static final String TABLE_NAME_RUTA = "ruta";

	// Sentencia SQL para la creación de una tabla
	private static final String CREATE_TABLA_LUGAR = "CREATE TABLE lugares (id INTEGER PRIMARY KEY AUTOINCREMENT, latitude REAL, longitude REAL, ip REAL, fecha DATETIME)";
	private static final String CREATE_TABLA_USUARIO = "CREATE TABLE usuario (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , nombre VARCHAR, login VARCHAR, pasw VARCHAR)";
	private static final String CREATE_TABLA_CLIENTE = "CREATE TABLE cliente (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , nombre VARCHAR, apellido VARCHAR, direccion VARCHAR, telefono INTEGER, idUsuario INTEGER, visible INTEGER)";
	private static final String CREATE_TABLA_PEDIDO = "CREATE TABLE pedido  (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , idCliente INTEGER, cantidad INTEGER, fecha DATETIME, precio INTEGER, idProducto INTEGER)";
	private static final String CREATE_TABLA_PRODUCTO = "CREATE TABLE producto (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , nombre VARCHAR)";
	private static final String CREATE_TABLA_RUTA = "CREATE TABLE ruta (_id INTEGER PRIMARY KEY AUTOINCREMENT, idUsuario INTEGER , lat NUMERIC , lng NUMERIC , name VARCHAR);";

	public DataBase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		if (db.isReadOnly()) {
			db = getWritableDatabase();
		}
		db.execSQL(CREATE_TABLA_LUGAR);
		db.execSQL(CREATE_TABLA_CLIENTE);
		db.execSQL(CREATE_TABLA_PEDIDO);
		db.execSQL(CREATE_TABLA_PRODUCTO);
		db.execSQL(CREATE_TABLA_USUARIO);
		db.execSQL(CREATE_TABLA_RUTA);
		Log.e(TAG, "Database tables created");
		//
		db.execSQL("INSERT INTO " + TABLE_NAME_USUARIO
				+ " VALUES(null, 'felipe', 'felipe', '1234')");
		db.execSQL("INSERT INTO " + TABLE_NAME_USUARIO
				+ " VALUES(null, 'pedro', 'pedro', '1234')");
		//
		db.execSQL("INSERT INTO " + TABLE_NAME_PRODUCTO
				+ " VALUES(null, 'Pan')");
		db.execSQL("INSERT INTO " + TABLE_NAME_PRODUCTO
				+ " VALUES(null, 'Galletas')");
		db.execSQL("INSERT INTO " + TABLE_NAME_PRODUCTO
				+ " VALUES(null, 'Arroz')");
		db.execSQL("INSERT INTO " + TABLE_NAME_PRODUCTO
				+ " VALUES(null, 'Harina')");
		db.execSQL("INSERT INTO " + TABLE_NAME_PRODUCTO
				+ " VALUES(null, 'Tallarines')");
		db.execSQL("INSERT INTO " + TABLE_NAME_PRODUCTO
				+ " VALUES(null, 'Leche')");
		db.execSQL("INSERT INTO " + TABLE_NAME_PRODUCTO
				+ " VALUES(null, 'Queso')");
		db.execSQL("INSERT INTO " + TABLE_NAME_PRODUCTO
				+ " VALUES(null, 'Yogurt')");
		db.execSQL("INSERT INTO " + TABLE_NAME_PRODUCTO
				+ " VALUES(null, 'Huevos')");
		//
		db.execSQL("INSERT INTO "
				+ TABLE_NAME_RUTA
				+ " (idUsuario,name,lat,lng) VALUES(1,'Bazar tita', -33.437305, -70.633376)");
		db.execSQL("INSERT INTO "
				+ TABLE_NAME_RUTA
				+ " (idUsuario,name,lat,lng) VALUES(1,'Ok Market', -33.439895, -70.640315)");
		db.execSQL("INSERT INTO "
				+ TABLE_NAME_RUTA
				+ " (idUsuario,name,lat,lng) VALUES(1,'Botilleria ', -33.442442, -70.645072)");
		db.execSQL("INSERT INTO "
				+ TABLE_NAME_RUTA
				+ " (idUsuario,name,lat,lng) VALUES(1,'Panaderia', -33.443512, -70.650357)");
		db.execSQL("INSERT INTO "
				+ TABLE_NAME_RUTA
				+ " (idUsuario,name,lat,lng) VALUES(2,'Bazar', -33.418133, -70.601292)");
		db.execSQL("INSERT INTO "
				+ TABLE_NAME_RUTA
				+ " (idUsuario,name,lat,lng) VALUES(2,'Supermercado', -33.416682, -70.595648)");
		db.execSQL("INSERT INTO "
				+ TABLE_NAME_RUTA
				+ " (idUsuario,name,lat,lng) VALUES(2,'Restaurant ',-33.415303, -70.589490)");
		db.execSQL("INSERT INTO "
				+ TABLE_NAME_RUTA
				+ " (idUsuario,name,lat,lng) VALUES(2,'Panaderia', -33.422127, -70.608502)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USUARIO);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CLIENTE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PEDIDO);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PRODUCTO);
		onCreate(db);
	}

	/********* CRUD **********/
	public long guardarPos(GeoDatos data) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("latitude", data.getLatitude());
		values.put("longitude", data.getLongitude());
		values.put("ip", data.getIp());
		values.put("fecha", getDateTime());
		// insert row
		long tag_id = db.insert(TABLE_NAME, null, values);
		closeDB();
		SQLiteDatabase.releaseMemory();
		return tag_id;
	}

	public ArrayList<GeoDatos> listarLugares() {
		ArrayList<GeoDatos> listado = new ArrayList<GeoDatos>();
		// 1. construir la consulta
		String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY id DESC";
		Log.e(LOG, query);
		// 2. obtener referencia a escribible DB
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		// 3. repasar cada fila, construir el objeto y agregarlo a la lista
		GeoDatos data = null;
		if (cursor.moveToFirst()) {
			do {
				data = new GeoDatos();
				data.setId(Integer.parseInt(cursor.getString(0)));
				data.setLatitude(Double.parseDouble(cursor.getString(1)));
				data.setLongitude(Double.parseDouble(cursor.getString(2)));
				data.setIp(cursor.getString(3));
				data.setFecha(cursor.getString(4));
				// Add data a listado
				listado.add(data);

			} while (cursor.moveToNext());
		}
		Log.d("listarLugares()", listado.toString());
		// return lugares
		closeDB();
		SQLiteDatabase.releaseMemory();
		return listado;

	}

	public ArrayList<GeoDatos> BuscarLugaresFecha(String aFecha) {
		ArrayList<GeoDatos> listado = new ArrayList<GeoDatos>();
		// 1. construir la consulta
		String query = "SELECT * FROM " + TABLE_NAME
				+ " WHERE DATE(fecha) LIKE '" + aFecha + "' ORDER BY id DESC";
		Log.e(LOG, query);
		// 2. obtener referencia a escribible DB
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		// 3. repasar cada fila, construir el objeto y agregarlo a la lista
		GeoDatos data = null;
		if (cursor.moveToFirst()) {
			do {
				data = new GeoDatos();
				data.setId(Integer.parseInt(cursor.getString(0)));
				data.setLatitude(Double.parseDouble(cursor.getString(1)));
				data.setLongitude(Double.parseDouble(cursor.getString(2)));
				data.setIp(cursor.getString(3));
				data.setFecha(cursor.getString(4));
				// Add data a listado
				listado.add(data);

			} while (cursor.moveToNext());
		}
		Log.d("listarLugares()", listado.toString());
		closeDB();
		SQLiteDatabase.releaseMemory();
		// return lugares
		return listado;

	}

	// closing database
	public void closeDB() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();
	}

	/** USUARIOS ***/
	public long agregarUsuario(Usuario nUsuario) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("nombre", nUsuario.getNombre());
		values.put("login", nUsuario.getLogin());
		values.put("pasw", nUsuario.getPass());
		// insert row
		long tag_id = db.insert(TABLE_NAME_USUARIO, null, values);
		closeDB();
		SQLiteDatabase.releaseMemory();
		Log.d(TAG, "crearUsuario inserted into sqlite: " + tag_id);
		return tag_id;
	}

	public Usuario buscarLogin(String nLogin, String nPass) {
		SQLiteDatabase db = this.getReadableDatabase();
		// 1. construir la consulta
		String query = "SELECT * FROM " + TABLE_NAME_USUARIO
				+ "  WHERE  login = '" + nLogin + "' AND pasw = '" + nPass
				+ "'";
		Log.e(LOG, query);
		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			Usuario nUsuario = new Usuario();
			nUsuario.setId(cursor.getInt(cursor.getColumnIndex("_id")));
			nUsuario.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
			nUsuario.setLogin(cursor.getString(cursor.getColumnIndex("login")));
			nUsuario.setPass(cursor.getString(cursor.getColumnIndex("pasw")));
			return nUsuario;
		} else {
			return null;
		}

	}

	public Usuario buscarUsuarioId(int nId) {
		SQLiteDatabase db = this.getReadableDatabase();
		// 1. construir la consulta
		String query = "SELECT * FROM " + TABLE_NAME_USUARIO
				+ "  WHERE  _id = " + nId;
		Log.e(LOG, query);
		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			Usuario nUsuario = new Usuario();
			nUsuario.setId(cursor.getInt(cursor.getColumnIndex("_id")));
			nUsuario.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
			nUsuario.setLogin(cursor.getString(cursor.getColumnIndex("login")));
			nUsuario.setPass(cursor.getString(cursor.getColumnIndex("pasw")));
			return nUsuario;
		} else {
			return null;
		}

	}

	/** CLIENTES ***/
	public long agregarCliente(Cliente nCliente) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("nombre", nCliente.getNombre());
		values.put("apellido", nCliente.getApellido());
		values.put("direccion", nCliente.getDireccion());
		values.put("telefono", nCliente.getTelefono());
		values.put("idUsuario", nCliente.getIdUsuario());
		values.put("visible", nCliente.getVisible());
		// insert row
		long tag_id = db.insert(TABLE_NAME_CLIENTE, null, values);
		closeDB();
		SQLiteDatabase.releaseMemory();
		Log.d(TAG, "crearCliente inserted into sqlite: " + tag_id);
		return tag_id;
	}

	public Cliente buscarCliente(int nId) {
		SQLiteDatabase db = this.getReadableDatabase();
		// 1. construir la consulta
		String query = "SELECT * FROM " + TABLE_NAME_CLIENTE
				+ "  WHERE  _id = " + nId;
		Log.e(LOG, query);
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			Cliente nCliente = new Cliente();
			nCliente.setId(Integer.parseInt(cursor.getString(0)));
			nCliente.setNombre(cursor.getString(1));
			nCliente.setApellido(cursor.getString(2));
			nCliente.setDireccion(cursor.getString(3));
			nCliente.setTelefono(cursor.getString(4));
			nCliente.setIdUsuario(Integer.parseInt(cursor.getString(5)));
			nCliente.setVisible(Integer.parseInt(cursor.getString(6)));
			return nCliente;
		} else {
			return null;
		}

	}

	public ArrayList<Cliente> listarClientes(int nIduser) {
		ArrayList<Cliente> listado = new ArrayList<Cliente>();
		// 1. construir la consulta
		String query = "SELECT * FROM " + TABLE_NAME_CLIENTE
				+ " WHERE visible = 1 AND idUsuario = " + nIduser
				+ " ORDER BY _id DESC";
		Log.e(LOG, query);
		// 2. obtener referencia a escribible DB
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		// 3. repasar cada fila, construir el objeto y agregarlo a la lista
		Cliente nCliente = null;
		if (cursor.moveToFirst()) {
			do {
				nCliente = new Cliente();
				nCliente.setId(Integer.parseInt(cursor.getString(0)));
				nCliente.setNombre(cursor.getString(1));
				nCliente.setApellido(cursor.getString(2));
				nCliente.setDireccion(cursor.getString(3));
				nCliente.setTelefono(cursor.getString(4));
				nCliente.setIdUsuario(Integer.parseInt(cursor.getString(5)));
				nCliente.setVisible(Integer.parseInt(cursor.getString(6)));
				// Add data a listado
				listado.add(nCliente);

			} while (cursor.moveToNext());
		}
		Log.d("listarClientes()", listado.toString());
		return listado;
	}

	public long updateCliente(Cliente nCliente) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("nombre", nCliente.getNombre());
		values.put("apellido", nCliente.getApellido());
		values.put("direccion", nCliente.getDireccion());
		values.put("telefono", nCliente.getTelefono());
		values.put("idUsuario", nCliente.getIdUsuario());
		values.put("visible", nCliente.getVisible());
		String[] args = new String[] { Integer.toString(nCliente.getId()) };
		long rows_affected = db.update(TABLE_NAME_CLIENTE, values, "_id = ?",
				args);
		Log.d(TAG, "updateCliente: " + rows_affected);
		closeDB();
		SQLiteDatabase.releaseMemory();
		return rows_affected;
	}

	public long deleteCliente(Cliente nCliente) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("visible", 0);
		String[] args = new String[] { Integer.toString(nCliente.getId()) };
		long rows_affected = db.update(TABLE_NAME_CLIENTE, values, "_id = ?",
				args);
		Log.d(TAG, "deleteCliente: " + rows_affected);
		closeDB();
		SQLiteDatabase.releaseMemory();
		return rows_affected;
	}

	/** PEDIDOS **/
	public long agregarPedido(Pedido nPedido) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("idCliente", nPedido.getIdCliente());
		values.put("cantidad", nPedido.getCantidad());
		values.put("fecha", getDateTime());
		values.put("precio", nPedido.getPrecio());
		values.put("idProducto", nPedido.getIdProducto());
		// insert row
		long tag_id = db.insert(TABLE_NAME_PEDIDO, null, values);
		closeDB();
		SQLiteDatabase.releaseMemory();
		Log.d(TAG, "agregarPedido inserted into sqlite: " + tag_id);
		return tag_id;
	}

	/** PRODUCTOS */
	public long agregarProducto(Producto nProdcuto) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("nombre", nProdcuto.getNombre());
		long tag_id = db.insert(TABLE_NAME_PRODUCTO, null, values);
		closeDB();
		SQLiteDatabase.releaseMemory();
		Log.d(TAG, "agregarProducto inserted into sqlite: " + tag_id);
		return tag_id;
	}

	public ArrayList<Producto> listarProductos() {
		ArrayList<Producto> listado = new ArrayList<Producto>();
		// 1. construir la consulta
		String query = "SELECT * FROM " + TABLE_NAME_PRODUCTO
				+ " ORDER BY _id DESC";
		Log.e(LOG, query);
		// 2. obtener referencia a escribible DB
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		Producto producto = null;

		// 3. repasar cada fila, construir el objeto y agregarlo a la lista
		if (cursor.moveToFirst()) {
			do {
				producto = new Producto();
				producto.setId(Integer.parseInt(cursor.getString(0)));
				producto.setNombre(cursor.getString(1));
				listado.add(producto);
			} while (cursor.moveToNext());
		}
		Log.d("listarProductos()", listado.toString());
		closeDB();
		SQLiteDatabase.releaseMemory();
		return listado;
	}

	/********* RUTA *******/
	public ArrayList<Ruta> listarRuta(int idUsuario) {
		ArrayList<Ruta> listado = new ArrayList<Ruta>();
		// 1. construir la consulta
		String query = "SELECT * FROM " + TABLE_NAME_RUTA
				+ " WHERE idUsuario = " + idUsuario + " ORDER BY _id DESC";
		Log.e(LOG, query);
		// 2. obtener referencia a escribible DB
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		Ruta nRuta = null;
		// 3. repasar cada fila, construir el objeto y agregarlo a la lista
		if (cursor.moveToFirst()) {
			do {
				nRuta = new Ruta();
				nRuta.setIdRuta(Integer.parseInt(cursor.getString(0)));
				nRuta.setIdUsuario(Integer.parseInt(cursor.getString(1)));
				nRuta.setLat( Double.parseDouble(cursor.getString(2)));
				nRuta.setLng( Double.parseDouble(cursor.getString(3)));
				nRuta.setNombreLocal(cursor.getString(4));
				listado.add(nRuta);				
			} while (cursor.moveToNext());
		}

		Log.d("listarRuta()", listado.toString());
		closeDB();
		SQLiteDatabase.releaseMemory();
		return listado;
	}

	/***/
	/**
	 * get datetime
	 * */
	private String getDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		Date date = new Date();
		return dateFormat.format(date);
	}

}
