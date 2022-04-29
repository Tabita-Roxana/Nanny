package com.example.nanny.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.nanny.R;
import com.example.nanny.model.User;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>
{
    private ArrayList<User> usersNanny;

    public SearchAdapter(ArrayList<User> usersNanny)
    {
        this.usersNanny=usersNanny;
    }

    private OnClickListener listener;

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.user_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {

        holder.name.setText(usersNanny.get(position).getUserDetails().getName());
        holder.age.setText(usersNanny.get(position).getUserDetails().getAge());
        holder.city.setText(String.valueOf(usersNanny.get(position).getUserDetails().getCity()));
//        holder.image.setImageResource(usersNanny.get(position).getUserDetails().getPicture());

    }

    @Override
    public int getItemCount() {
        return usersNanny.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView age;
        private final TextView city;
        private final ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            age = itemView.findViewById(R.id.item_age);
            city = itemView.findViewById(R.id.item_city);
            image = itemView.findViewById(R.id.item_imageView);
            itemView.setOnClickListener(view -> {
                listener.onClick(usersNanny.get(getAdapterPosition()));
            });
        }
    }
    public interface OnClickListener {
        void onClick(User user);
    }
}
