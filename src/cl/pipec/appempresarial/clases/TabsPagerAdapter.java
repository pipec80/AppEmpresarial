package cl.pipec.appempresarial.clases;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;
import cl.pipec.appempresarial.ClientesFragment;
import cl.pipec.appempresarial.HomeFragment;
import cl.pipec.appempresarial.PedidosFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {
	SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>(); 

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int index) {
		// TODO Auto-generated method stub
		switch (index) {
        case 0:
           return new HomeFragment();
        case 1:
            return new ClientesFragment();
        case 2:

           return new PedidosFragment();
        } 
        return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		super.destroyItem(container, position, object);
	}
	
	public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

}
