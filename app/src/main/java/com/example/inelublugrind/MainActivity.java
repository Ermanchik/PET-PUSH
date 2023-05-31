package com.example.inelublugrind;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textView1;
    EditText Id;
    EditText CategoryId;
    EditText CategoryName;
    EditText Name;
    EditText PhotoUrl;
    EditText TagsId;
    EditText TagsName;
    EditText Status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Id =  findViewById(R.id.id);
        CategoryId =  findViewById(R.id.category_id);
        CategoryName =  findViewById(R.id.category_name);
        Name =  findViewById(R.id.name);
        PhotoUrl =  findViewById(R.id.photo_url);
        TagsId =  findViewById(R.id.tags_id);
        TagsName =  findViewById(R.id.tags_name);
        Status =  findViewById(R.id.status);
        textView1 =  findViewById(R.id.textView1);
    }

    public void onClick(View view) {
        postData(Id.getText().toString(), Integer.parseInt(CategoryId.getText().toString()), CategoryName.getText().toString(), Name.getText().toString(),
                PhotoUrl.getText().toString(), Integer.parseInt(TagsId.getText().toString()), TagsName.getText().toString(), Status.getText().toString());
    }

    private void postData(String id, Integer categoryId, String categoryName, String name, String photoUrl, Integer tagsId, String tagsName, String status) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://petstore.swagger.io/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PetAPI petAPI = retrofit.create(PetAPI.class);
        ArrayList<Tags> tags = new ArrayList<>();
        Tags tag = new Tags(tagsId, tagsName);
        tags.add(tag);

        ArrayList<String> urls = new ArrayList<>();
        urls.add(photoUrl);
        Pet modal = new Pet(Integer.parseInt(id), new Category(categoryId, categoryName), name, urls, tags, status);
        Call<Pet> call = petAPI.createPost(modal);
        call.enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {
                Toast.makeText(MainActivity.this, "Всё ок", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Pet> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}