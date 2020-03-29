package com.example.provectus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.provectus.model.RandomUser;
import com.example.provectus.model.Result;
import com.example.provectus.model.User;
import com.example.provectus.network.ApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.OnItemClickListener {

    TextView namee, genderr, emaill;
    private RequestQueue requestQueue;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    List<User> userList;

    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_CELL = "cell";
    public static final String EXTRA_PASSWORD = "password";
    public static final String EXTRA_EMAIL = "email";
    public static final String EXTRA_GENDER = "gender";
    public static final String EXTRA_USERNAME = "username";

    public static final String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        //namee = (TextView)findViewById(R.id.textViewName);
        //genderr = (TextView)findViewById(R.id.textViewGender);
        //emaill = (TextView)findViewById(R.id.textViewEmail);
        userList = new ArrayList<>();
        //getUser();
        loadData();
    }

    public void getUser() {
        final String URL = "https://randomuser.me/api/?results=20";

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject results = jsonArray.getJSONObject(i);
                        String email = results.getString("email");
                        String gender = results.getString("gender");

                        JSONObject name = results.getJSONObject("name");
                        String first = name.getString("first");
                        String last = name.getString("last");

                       // User user = new User(email, gender, first, last);

                       // adapter = new RecyclerAdapter(MainActivity.this, user);
                       // recyclerView.setAdapter(adapter);


//                        namee.setText(first + " " + last);
//                        genderr.setText(gender);
//                        emaill.setText(email);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "ddd", Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(request);

    }

    public void loadData() {
        final String URL = "https://randomuser.me/api/?results=20";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("results");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject results = array.getJSONObject(i);

                        String email = results.getString("email");
                        String gender = results.getString("gender");
                        String cell = results.getString("cell");

                        JSONObject name = results.getJSONObject("name");
                       String first = name.getString("first");
                       String last = name.getString("last");

                        JSONObject icon = results.getJSONObject("picture");
                        String medium = icon.getString("large");

                        JSONObject login = results.getJSONObject("login");
                        String username = login.getString("username");
                        String password = login.getString("password");

                        User user = new User(email, gender, first, last, medium, cell, username, password);

                        userList.add(user);
                    }

                        adapter = new RecyclerAdapter(MainActivity.this, userList);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(MainActivity.this);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.getMessage());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        User user = userList.get(position);

        detailIntent.putExtra(EXTRA_URL, user.getPicture());
        detailIntent.putExtra(EXTRA_CELL, user.getCell());
        detailIntent.putExtra(EXTRA_USERNAME, user.getUsername());
        detailIntent.putExtra(EXTRA_PASSWORD, user.getPassword());
        detailIntent.putExtra(EXTRA_EMAIL, user.getEmail());
        detailIntent.putExtra(EXTRA_GENDER, user.getGender());
        detailIntent.putExtra(EXTRA_NAME, user.getFirstname() + " " + user.getLastname());

        startActivity(detailIntent);

    }
}

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    Context context;
    private List<User> list;
    private OnItemClickListener mListener;

    public RecyclerAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener (OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


      holder.name.setText(list.get(position).getFirstname() + " " + list.get(position).getLastname());
      holder.email.setText(list.get(position).getEmail());
      holder.gender.setText(list.get(position).getGender());

      Glide.with(context).load(list.get(position).getPicture()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name;
        TextView gender;
        TextView email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            name = (TextView)itemView.findViewById(R.id.textViewName);
            gender = (TextView)itemView.findViewById(R.id.TextViewGender);
            email = (TextView)itemView.findViewById(R.id.TextViewEmail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
