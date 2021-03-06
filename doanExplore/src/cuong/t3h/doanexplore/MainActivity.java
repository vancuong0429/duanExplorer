package cuong.t3h.doanexplore;

import java.io.File;

import cuong.t3h.doanexplore.FragmentTab.OnListerne;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity implements OnListerne{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FragmentTab tab = new FragmentTab();
		getSupportFragmentManager().beginTransaction().add(R.id.frmContent, tab).addToBackStack("null").commit();
	}

	
	@Override
	public void onLongClick(File file) {
		// TODO Auto-generated method stub
		if(file.getName().endsWith(".txt") ||file.getName().endsWith(".mp3") || 
				file.getName().endsWith(".png")|| file.getName().endsWith(".jpg")
				|| file.getName().endsWith(".jpeg")
				|| file.getName().endsWith(".gif"))
		{
			Bundle bundle = new Bundle();
			bundle.putSerializable(FragmentTab.TAG_POSITION, file);
			FragmentOpenFiles fragmentOpenFiles = new FragmentOpenFiles();
			fragmentOpenFiles.setArguments(bundle);
			getSupportFragmentManager().beginTransaction().replace(R.id.frmContent, fragmentOpenFiles).addToBackStack("null").commit();
		}
	}
}
