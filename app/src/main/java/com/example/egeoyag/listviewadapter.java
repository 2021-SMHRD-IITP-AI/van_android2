package com.example.egeoyag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class listviewadapter extends BaseAdapter implements Filterable {

      private ArrayList<Bitmap_ListViewItem> ListViewItemList= new ArrayList<>();





    @Override
    public int getCount()
    {
        return ListViewItemList.size();
                //== null ? 0 : ListViewItemList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return ListViewItemList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Context context = parent.getContext();

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.pill_list,parent,false);


        }

        ImageView img = convertView.findViewById(R.id.pl_img);
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        TextView tv_1 = convertView.findViewById(R.id.tv_1);
        TextView tv_ymd = convertView.findViewById(R.id.tv_ymd);



        Bitmap_ListViewItem item = ListViewItemList.get(position);

        img.setImageBitmap(item.getBitmap());
        tv_name.setText(item.getDto().getName());
        tv_1.setText(item.getDto().getSymptom1());
        tv_ymd.setText(item.getDto().getYmd());


        return convertView;
    }

    public void additem(Bitmap_ListViewItem gg){
        Bitmap_ListViewItem ddto = gg;
        ListViewItemList.add(ddto);
        notifyDataSetChanged();
    }

    public void remove(int i){

        ListViewItemList.remove(i);
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                ArrayList<String> queryResults;
                if ((constraint != null) && (constraint.length() != 0)) {
                    queryResults = autocomplete(constraint.toString());
                } else {
                    queryResults = new ArrayList<>();
                }
                filterResults.values = queryResults;
                filterResults.count = queryResults.size();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                // noinspection unchecked
                ListViewItemList = (ArrayList<Bitmap_ListViewItem>) results.values;
                if (results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
    }

    public ArrayList<String> autocomplete(String input) {
        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            URL url = new URL("http://www.abc.com/search/?kwd=" + URLEncoder.encode(input, "utf8"));
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return (new Gson()).fromJson(jsonResults.toString(), new TypeToken<ArrayList<String>>() {}.getType());
    }





    public void fillter(String filterText) {

        }
        //notifyDataSetChanged();




}


