package team.code.explorer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {
	
	private String path;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Use the current directory as title
	    path = "/";
	    if (getIntent().hasExtra("path")) {
	      path = getIntent().getStringExtra("path");
	    }
	    setTitle(path);

	    // Read all files sorted into the values-array
	    List values = new ArrayList();
	    File dir = new File(path);
	    if (!dir.canRead()) {
	      setTitle(getTitle() + " (inaccessible)");
	    }
	    String[] list = dir.list();
	    if (list != null) {
	      for (String file : list) {
	        if (!file.startsWith(".")) {
	          values.add(file);
	        }
	      }
	    }
	    Collections.sort(values);

	    // Put the data into the list
	    ArrayAdapter adapter = new ArrayAdapter(this,
	        android.R.layout.simple_list_item_2, android.R.id.text1, values);
	    setListAdapter(adapter);
	}
	
}
