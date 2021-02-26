package com.example.buangan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

    public class MainFragment extends Fragment implements View.OnClickListener, RecyclerviewAdapter.OnUserClickListener {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Context context;
    List<ItemBean> itemBeanList;

    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        context = getActivity();
        recyclerView = view.findViewById(R.id.data);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        DatabaseHelper db = new DatabaseHelper(context);
        itemBeanList = db.selectUserData();

        RecyclerviewAdapter adapter = new RecyclerviewAdapter(context,itemBeanList,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onUserClick(ItemBean currentPost, String action) {
        if(action.equals("Update")){
            String title = currentPost.getJudul();
            String desc = currentPost.getDesc();
            Intent intent = new Intent(getActivity(), UpdateActivity.class);
            intent.putExtra("TITLE", title);
            intent.putExtra("DESC", desc);
            startActivity(intent);
        } if(action.equals("Delete")){
            DatabaseHelper db=new DatabaseHelper(context);
            db.delete(currentPost.getJudul());
            Toast.makeText(context, "Delete post successfull", Toast.LENGTH_LONG).show();
            setupRecyclerView();
        }


    }

}
