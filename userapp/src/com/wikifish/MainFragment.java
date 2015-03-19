package com.wikifish;

import io.userapp.client.android.UserApp;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wikifish.adapter.ListAllAdapter;
import com.wikifish.entity.Fish;

public class MainFragment extends Fragment {
	UserApp.Session session;
	private ArrayList<Fish> allFishs;
	private ListAllAdapter adapter;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main, container, false);
        
		// Listen for the login event so we could load the articles
        session = new UserApp.Session(this.getActivity(), new UserApp.Session.StatusCallback() {
		    @Override
		    public void call(Boolean authenticated, Exception exception) {
		        if (authenticated) {
		        	//Toast.makeText(getActivity(), "Logado com : "+ session.user.email, Toast.LENGTH_SHORT).show();
		        	FishHelper.loadFishes(session.token, new FishHelper.Callback() {
					    @Override
					    public void call(List<Fish> fishs) {
					        // Print articles
					    	if(fishs == null )return;
					    	if(allFishs == null){
					    		allFishs = new ArrayList<Fish>();
					    		allFishs.addAll( fishs);
					    	}
					    	Log.d("Fagner",fishs.size()+"");
							ListView listview = (ListView) view.findViewById(R.id.articleList);
								adapter = new ListAllAdapter(getActivity());
								adapter.setFishToDisplay(allFishs);
							
							listview.setAdapter(adapter);
					    }
					});
		        }
		    }
		});
        
		return view;
    }

	@Override
	public void onResume() {
	    super.onResume();
	    session.onResume();
	}

	@Override
	public void onPause() {
	    super.onPause();
	    session.onPause();
	}
	
}
