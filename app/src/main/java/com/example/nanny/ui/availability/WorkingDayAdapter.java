package com.example.nanny.ui.availability;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nanny.R;
import com.example.nanny.model.WorkingDay;
import com.example.nanny.model.WorkingWeek;


public class WorkingDayAdapter extends RecyclerView.Adapter<WorkingDayAdapter.ViewHolder>{

    private WorkingWeek workingWeek;

    public WorkingDayAdapter() {
        this.workingWeek = new WorkingWeek();
    }

    public WorkingDayAdapter(WorkingWeek workingWeek){
        this.workingWeek=workingWeek;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public WorkingDayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_item_day, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkingDayAdapter.ViewHolder holder, int position) {

        WorkingDay dayByIndex = workingWeek.getDayByIndex(position);

        holder.dayShortcut.setText(dayByIndex.getShortcut());
        holder.morningCB.setChecked(dayByIndex.isMorning());
        holder.noonCB.setChecked(dayByIndex.isNoon());
        holder.afternoonCB.setChecked(dayByIndex.isAfternoon());

        holder.noonCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = holder.noonCB.isChecked();
                dayByIndex.setNoon(checked);
            }
        });
        holder.morningCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = holder.morningCB.isChecked();
                dayByIndex.setMorning(checked);
            }
        });
        holder.afternoonCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = holder.afternoonCB.isChecked();
                dayByIndex.setAfternoon(checked);
            }
        });

    }

    @Override
    public int getItemCount() {
        return workingWeek.getDaysCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private  TextView dayShortcut;
        private  CheckBox morningCB;
        private  CheckBox noonCB;
        private  CheckBox afternoonCB;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayShortcut = itemView.findViewById(R.id.dayTextView);
            morningCB = itemView.findViewById(R.id.morning_checkbox);
            noonCB = itemView.findViewById(R.id.noonCheckbox);
            afternoonCB = itemView.findViewById(R.id.afternoonChecbox);

        }
    }


    public void setWorkingWeek(WorkingWeek workingWeek) {
        this.workingWeek = workingWeek;
        notifyDataSetChanged();
    }

    public WorkingWeek getWorkingWeek() {
        return workingWeek;
    }
}
