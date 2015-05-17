package cuong.t3h.doanexplore;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TabHost;

public class FragmentTab extends Fragment{
	final static String TAG_POSITION="path";
	private ArrayList<File> fileList = new ArrayList<File>();
	MyBaseAdapter adapter;
	OnListerne listerne;
	interface OnListerne
	{
		public void onLongClick(File file);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_tab, container, false);
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		ListView lsv = (ListView) getActivity().findViewById(R.id.lsvAllApp);
		lsv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				listerne.onLongClick(fileList.get(position));
				return false;
			}
		});
		if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
		{
			File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
			loadAllFiles(root);
			adapter = new MyBaseAdapter(getActivity(), fileList, R.layout.my_baseadapter);
			addTab();
			lsv.setAdapter(adapter);
		}
		File file = getActivity().getFilesDir().getAbsoluteFile();
		loadAllFiles(file);
		lsv.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
	public void loadAllFiles(File f)
	{
		File []file1 = f.listFiles();
		if(file1!=null && file1.length>0)
		{
			Log.e("cuong", file1.toString()+"---------");
			fileList.clear();
			for (int i=0; i<file1.length;i++) {
				fileList.add(file1[i]);
			}
		}
	}
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		listerne = (OnListerne) activity;
	}
	private void addTab()
	{
		TabHost tabHost = (TabHost) getActivity().findViewById(android.R.id.tabhost);
		tabHost.setup();
		TabHost.TabSpec tabSpec;
		tabSpec = tabHost.newTabSpec("All Files");
		tabSpec.setContent(R.id.tab1);
		tabSpec.setIndicator("All Files");
		tabHost.addTab(tabSpec);
		
		//-----------
		
		tabHost.setCurrentTab(0);
		
	}
}
