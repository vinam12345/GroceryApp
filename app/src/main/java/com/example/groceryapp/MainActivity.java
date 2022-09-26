package com.example.groceryapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.groceryapp.R;
import com.example.groceryapp.models.ModelCat1;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;
    AdapterCat1 adapterCat1;
    ArrayList<ModelCat1> model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = findViewById(R.id.recycler);

        recycler.setLayoutManager(new GridLayoutManager((getApplicationContext()),2));
        model=new ArrayList<>();
        model.add(new ModelCat1("Mens Shirt","Something Something of the product","120",R.drawable.s2));
        model.add(new ModelCat1("Golden Watches","Something Something of the product","120",R.drawable.s5));
        model.add(new ModelCat1("Stylish Shirt","Something Something of the product","120",R.drawable.s1));
        model.add(new ModelCat1("Sun Glasses","Something Something of the product","120",R.drawable.s7));
        model.add(new ModelCat1("Sun Glasses","Something Something of the product","120",R.drawable.tea_pot));
        model.add(new ModelCat1("Sun Glasses","Something Something of the product","120",R.drawable.pressure_cooker));
        model.add(new ModelCat1("Sun Glasses","Something Something of the product","120",R.drawable.stove));
        model.add(new ModelCat1("Sun Glasses","Something Something of the product","120",R.drawable.vegetables));
        model.add(new ModelCat1("Mens Shirt","Something Something of the product","120",R.drawable.s2));
        model.add(new ModelCat1("Golden Watches","Something Something of the product","120",R.drawable.s5));
        model.add(new ModelCat1("Stylish Shirt","Something Something of the product","120",R.drawable.s1));
        model.add(new ModelCat1("Sun Glasses","Something Something of the product","120",R.drawable.s7));
        model.add(new ModelCat1("Sun Glasses","Something Something of the product","120",R.drawable.tea_pot));
        model.add(new ModelCat1("Sun Glasses","Something Something of the product","120",R.drawable.pressure_cooker));
        model.add(new ModelCat1("Sun Glasses","Something Something of the product","120",R.drawable.stove));
        model.add(new ModelCat1("Sun Glasses","Something Something of the product","120",R.drawable.vegetables));
        adapterCat1=new AdapterCat1(getApplicationContext(),model);
        recycler.setAdapter(adapterCat1);

    }
}