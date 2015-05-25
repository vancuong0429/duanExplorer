package cuong.t3h.doanexplore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentOpenFiles extends android.support.v4.app.Fragment{

	File file=null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Bundle bundle = getArguments();
		file=(File) bundle.getSerializable(FragmentTab.TAG_POSITION);
		return inflater.inflate(R.layout.layout_tap_open_files, container, false);
	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.e("pause", "pause");
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		TextView txt =(TextView) getActivity().findViewById(R.id.txtFile);
		if (file.getName().endsWith(".txt"))
		{
			try {
				FileInputStream fileInputStream = new FileInputStream(file.getPath());
				int i ;
				String _char = null;
				try {
					while((i=fileInputStream.read())!=-1)
					{
						_char+=(char)i;
					}
					if(_char!=null)
					{
						txt.setText(_char);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(file.getName().endsWith(".mp3"))
		{
			MediaPlayer mediaPlayer=MediaPlayer.create(getActivity(), Uri.parse(file.getPath()));
			mediaPlayer.start();
		}
		if(file.getName().endsWith(".png")|| file.getName().endsWith(".jpg")
				|| file.getName().endsWith(".jpeg")
				|| file.getName().endsWith(".gif"))
		{
			ImageView imageView =(ImageView) getActivity().findViewById(R.id.imgFile);
			imageView.setImageURI(Uri.parse(file.getPath()));
		}
	}		
}
