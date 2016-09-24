package com.example.admin.customlistview;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=(ListView) findViewById(R.id.listView);
        list.setAdapter(new myAdapater(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

class SingleRow{
    String title;
    String description;
    int image;

    SingleRow(String title, String description, int image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }
}

class myAdapater extends BaseAdapter{

    ArrayList<SingleRow> mylist;
    Context context;
    myAdapater(Context c) {
        context=c;
        mylist= new ArrayList<SingleRow>();

        /*
         1 get the titles, descriptions , images from xml
         2 group each title , image and description into a singlerow object
         3 put the singlerow objects inside arraylist*/
        Resources res= context.getResources();
        String[] titles=res.getStringArray(R.array.titles);
        String[] description = res.getStringArray(R.array.descriptions);
        int [] images={R.drawable.rose,R.drawable.rose};

        for(int i=0;i<2;i++){
            //Adding the SingleRow objects to the list
            mylist.add(new SingleRow(titles[i],description[i],images[i]));
        }

    }

    @Override
    public int getCount() {
        return mylist.size();
    }

    @Override
    public Object getItem(int position) {
        return mylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*
        1 get the root view
        2 use the root view to find other views
        3 set the values
        */
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.singlelayout,parent,false);
        TextView title=(TextView) row.findViewById(R.id.textView);
        TextView description=(TextView) row.findViewById(R.id.textView2);
        ImageView image=(ImageView) row.findViewById(R.id.imageView);

        SingleRow temp= mylist.get(position);
        title.setText(temp.title);
        description.setText(temp.description);
        image.setImageResource(temp.image);

        return row;
    }
}
