package cuong.t3h.doanexplore;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyBaseAdapter extends BaseAdapter{

	public MyBaseAdapter(Activity ac, ArrayList<File> arr, int layoutID) {
		super();
		this.ac = ac;
		this.arr = arr;
		this.layoutID = layoutID;
	}

	Activity ac ;
	ArrayList<File> arr;
	int layoutID;
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arr.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return arr.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return arr.indexOf(arr.get(position));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder=null;
		View view=convertView;
		if(view==null)
		{
			holder = new ViewHolder();
			LayoutInflater inflater = ac.getLayoutInflater();
			view = inflater.inflate(layoutID, parent, false);
			holder.img=(ImageView) view.findViewById(R.id.imageView1);
			holder.txt=(TextView) view.findViewById(R.id.textView1);
			view.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) view.getTag();
		}
		File str = arr.get(position);
		holder.txt.setText(str.getName());
		holder.img.setBackgroundResource(R.drawable.icon_folder);
		return view;
	}
	class ViewHolder
	{
		ImageView img;
		TextView txt;
	}

}
