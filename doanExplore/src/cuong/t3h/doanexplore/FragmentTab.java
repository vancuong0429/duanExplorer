package cuong.t3h.doanexplore;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.TabHost;
import android.widget.TextView;

public class FragmentTab extends Fragment{
	final static String TAG_POSITION="path";
	private ArrayList<File> parentItems = new ArrayList<File>();
    private ArrayList<Object> childItems = new ArrayList<Object>();
    View rootView;
    ExpandableListView elv;
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
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		File file = getActivity().getFilesDir().getAbsoluteFile();
		if(file!=null)
		{
			loadAllFiles(file);
			//lsv.setAdapter(adapter);
		}
		if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
		{
			
			File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
			loadAllFiles(root);
			addTab();
		}
		elv=(ExpandableListView) view.findViewById(R.id.expandableListView1);
		ExpandableListAdapter adapter = new ExpandableListAdapter(parentItems, childItems);
		
		adapter.setInflater((LayoutInflater)      getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
		
		// Set the Adapter to expandableList
		elv.setAdapter(adapter);
	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		getActivity().finish();
	}
	static int dem=0;
	public void loadAllFiles(File f)
	{
		ArrayList<File> child = new ArrayList<File>();
		File []file1 = f.listFiles();
		if(file1!=null && file1.length>0)
		{
			for (int i=0; i<file1.length;i++) {
				if(file1[i].isDirectory())//it is folder
				{
					parentItems.add(file1[i]);
					loadAllFiles(file1[i]);
				}
				else
				{
					child.add(file1[i]);
					Log.e("cuong_folder", dem++ +"");
				}
			}
			childItems.add(child);
			child = new ArrayList<>();
			
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
		TabHost.TabSpec tabSpec = null;
		if(tabSpec==null)
		{
			tabSpec = tabHost.newTabSpec("All Files");
			tabSpec.setContent(R.id.tab1);
			tabSpec.setIndicator("All Files");
			tabHost.addTab(tabSpec);
			tabHost.setCurrentTab(0);
		}
		
		//-----------
		
	}
	public class ExpandableListAdapter extends BaseExpandableListAdapter {
		
	    private ArrayList<Object> childtems;
	    private LayoutInflater inflater;
	    ArrayList<File> parentItems;
		private ArrayList<File> child;
	
	public ExpandableListAdapter(ArrayList<File> parentItems2, ArrayList<Object> childItems)
	{
	    this.parentItems = parentItems2;
	    this.childtems = childItems;
	}
	
	public void setInflater(LayoutInflater inflater, FragmentTab hmCfragment) 
	{
	    this.inflater = inflater;
	}
	
	@Override
	public int getGroupCount() {
	    return parentItems.size();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int getChildrenCount(int groupPosition) {
	    return childtems.size();
	}
	
	@Override
	public void onGroupCollapsed(int groupPosition) 
	{
	    super.onGroupCollapsed(groupPosition);
	}
	
	@Override
	public void onGroupExpanded(int groupPosition)
	{
	    super.onGroupExpanded(groupPosition);
	}
	
	
	@Override
	public String getGroup(int groupPosition) {
	    return null;
	}
	
	@Override
	public String getChild(int groupPosition, int childPosition) {
	    return null;
	}
	
	@Override
	public long getGroupId(int groupPosition) {
	    return 0;
	}
	
	@Override
	public long getChildId(int groupPosition, int childPosition) {
	    return 0;
	}
	
	@Override
	public boolean hasStableIds() {
	    return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View    convertView, ViewGroup parent) {
	
	      child = (ArrayList<File>) childtems.get(groupPosition);
	
	      TextView textView = null;
	
	      if (convertView == null) {
	          convertView = inflater.inflate(R.layout.child_layout, null);
	      }
	      /*textView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				File f = new File(child.get(childPosition));
				listerne.onLongClick(f);
			}
		});
*/	       // get the textView reference and set the value
	      
	      Log.e("chile", child.size() + "----" +childPosition);
	      textView = (TextView) convertView.findViewById(R.id.textViewChild);
	      if(child.size()>childPosition)
	      {
	    	  textView.setText(child.get(childPosition).getName());
	      }
	      return convertView;
	}
	
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
	    if (convertView == null) {
	        convertView = inflater.inflate(R.layout.parent_layout, null);
	    }
	
	    ((CheckedTextView) convertView).setText(parentItems.get(groupPosition).getName());
	    ((CheckedTextView) convertView).setChecked(isExpanded);
	    return convertView;
	}
	
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
	    return false;
	}
	class ViewHolder {
	    TextView text;
	
	 }
 }

}
