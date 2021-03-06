package pro.chinasoft.activity;

import java.util.List;

import org.xmpp.client.util.InMessageStore;

import pro.chinasoft.activity.R;
import pro.chinasoft.component.InMessageArrayAdapter;
import pro.chinasoft.component.InUserArrayAdapter;
import pro.chinasoft.model.InMessage;
import pro.chinasoft.model.InUser;
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

public class FragmentPage1 extends Fragment{

	
    private static final String URL = "http://www.google.com"; 

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		 View view = inflater.inflate(R.layout.fragment_1, container, false);

		 ListView listView = (ListView) view.findViewById(R.id.fragment_1_list);

		ArrayAdapter<InUser> friends = new InUserArrayAdapter(getActivity(),
				R.layout.in_user_list_item);
         
         List<InMessage> msgs = InMessageStore.getUserMessage(this.getActivity());

 		for (int i=0;i<msgs.size();i++) {
 			InUser iu = new InUser();
 			iu.setNick(msgs.get(i).getInUser().getNick()+msgs.get(i).getContent());
 			friends.add(iu);
 		}
 		listView.setAdapter(friends);
         return view;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}



}
