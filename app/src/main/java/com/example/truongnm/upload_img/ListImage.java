package com.example.truongnm.upload_img;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.example.truongnm.upload_img.Webservice.ApiInterface;
import com.example.truongnm.upload_img.Webservice.ServiceGenerator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ListImage extends AppCompatActivity {
    private final static String TAG = ListImage.class.getSimpleName();

    //GridView Object
    private GridView gridView;

    //ArrayList for Storing image urls and titles
    private ArrayList<Image> images;
    private GridViewAdapter gridViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_image);
        gridView = (GridView) findViewById(R.id.gridView);

        images = new ArrayList<>();

        gridViewAdapter = new GridViewAdapter(ListImage.this, images);


        gridView.setAdapter(gridViewAdapter);

        getData();
    }

    private void getData(){
        //Showing a progress dialog while our app fetches the data from url
//        final ProgressDialog loading = ProgressDialog.show(this, "Please wait...","Fetching data...",false,false);

        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<List<Image>> call = apiInterface.getImages();

        call.enqueue(new Callback<List<Image>>() {
            @Override
            public void onResponse(Call<List<Image>> call, retrofit2.Response<List<Image>> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, "dmmmmm truong");
                    Log.d(TAG, "dmmmm " + response.body());
                    for(Image image: response.body()){
                        images.add(image);
                    }

                    gridViewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Image>> call, Throwable t) {

            }
        });

//        //Creating a json array request to get the json from our api
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Constants.IMAGES_URL,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        //Dismissing the progressdialog on response
//                        loading.dismiss();
//
//                        //Displaying our grid
//                        showGrid(response);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }
//        );
//
//        //Creating a request queue
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        //Adding our request to the queue
//        requestQueue.add(jsonArrayRequest);
    }

//    private void showGrid(JSONArray jsonArray){
//        //Looping through all the elements of json array
//        for(int i = 0; i<jsonArray.length(); i++){
//            //Creating a json object of the current index
//            JSONObject obj = null;
//            try {
//                //getting json object from current index
//                obj = jsonArray.getJSONObject(i);
//
//                //getting image url and title from json object
//                images.add(obj.getString(Constants.TAG_URL));
//                names.add(obj.getString(Constants.TAG_NAME));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        //Creating GridViewAdapter Object
//        GridViewAdapter gridViewAdapter = new GridViewAdapter(this,images,names);
//
//        //Adding adapter to gridview
//        gridView.setAdapter(gridViewAdapter);
//    }
}
