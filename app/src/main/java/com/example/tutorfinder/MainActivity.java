package com.example.tutorfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore  db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("/Faculty/Engineering Faculty/Computer Engineering/First Semester/Books");
    private  BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        setRecyclerView();
    }

    private void setRecyclerView() {
        Query query = collectionReference.orderBy("title", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions <Book> options = new FirestoreRecyclerOptions.Builder<Book>().setQuery(collectionReference, Book.class).build();
        bookAdapter = new BookAdapter(options);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(bookAdapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        bookAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        bookAdapter.stopListening();
    }
}