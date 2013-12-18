package pro.chinasoft.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentPage3 extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {	
		 View view = inflater.inflate(R.layout.fragment_3, container, false);

		 ListView listView = (ListView) view.findViewById(R.id.fragment_3_list);

         String[] values = new String[] { "��˾�ڲ���������", "��ĳĳ�İ�������", "��ĳĳ�����̱���",
              "��ĳĳ�Ĳ��÷ѱ���", " ���ͬ����", "�����������", "�û��޸�", "����ý�����ĬĬ������",
         "Linux", "OS/2" };
         ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(), 
                  android.R.layout.simple_list_item_1, 
                  values);

         listView.setAdapter(files);
         return view;	
	}	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}


	@SuppressLint("NewApi")
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		ActionBar actionBar = this.getActivity().getActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP, ActionBar.DISPLAY_HOME_AS_UP);
	}


	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		MenuItem add = menu.add(0, 1, 0, "ˢ��");
		add.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM); 
		super.onCreateOptionsMenu(menu, inflater);
	}
	 @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	       // handle item selection
	       switch (item.getItemId()) {
	          case 1:
	             System.out.println(item.getItemId());
	             return true;
	          default:
	             return super.onOptionsItemSelected(item);
	       }
	    } 
}