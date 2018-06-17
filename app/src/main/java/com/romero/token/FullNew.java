package com.romero.token;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.romero.token.main.data.model.New;
import com.squareup.picasso.Picasso;

public class FullNew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_full_new);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        New news = (New) getIntent().getSerializableExtra("NOTICIA");

        TextView title = findViewById(R.id.fvTitle);
        ImageView img = findViewById(R.id.fvPic);
        TextView body = findViewById(R.id.newBody);

        title.setText(news.getTitle());
        title.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        Picasso.with(getApplicationContext()).load(news.getCoverImage()).into(img);
        body.setText(news.getBody());
    }
}
