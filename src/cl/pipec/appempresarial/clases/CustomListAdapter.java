package cl.pipec.appempresarial.clases;

import java.util.ArrayList;

import cl.pipec.appempresarial.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomListAdapter extends BaseAdapter {
	private ArrayList<Cliente> listData;
	private LayoutInflater layoutInflater;

	public CustomListAdapter(Context aContext, ArrayList<Cliente> listData) {
		super();
		this.listData = listData;
		this.layoutInflater = LayoutInflater.from(aContext);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			convertView = layoutInflater
					.inflate(R.layout.list_row_layout, null);
			holder = new ViewHolder();
			holder.titulo = (TextView) convertView.findViewById(R.id.title);
			holder.subtitulo = (TextView) convertView
					.findViewById(R.id.reporter);
			holder.detalle = (TextView) convertView.findViewById(R.id.date);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.titulo.setText(listData.get(position).getNombre() + ' '
				+ listData.get(position).getApellido());
		holder.subtitulo
				.setText("(--)" + listData.get(position).getDireccion());
		holder.detalle.setText(listData.get(position).getTelefono());
		return convertView;
	}

	static class ViewHolder {
		TextView titulo;
		TextView subtitulo;
		TextView detalle;
	}

}
