package com.srfl.gibi.ui.student;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.srfl.gibi.R;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private ArrayList<StudentData> arrayList = new ArrayList<>();
    private Context context;
    private static final String TAG = "StudentAdapter";


    public StudentAdapter(ArrayList<StudentData> arrayList, Context context){
        this.arrayList = arrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(arrayList.get(position));
        holder.name.setText(arrayList.get(position).getName());
        holder.studentId.setText(arrayList.get(position).getStudentId());

//        holder.password.setText(arrayList.get(position).getPassword());
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, String.valueOf(arrayList.size()));
        return arrayList.size();
    }

   class ViewHolder extends  RecyclerView.ViewHolder {
        TextView name, studentId;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            studentId = itemView.findViewById(R.id.tv_id);
            name = itemView.findViewById(R.id.tv_name);
//            password = itemView.findViewById(R.id.tv_password);
        }
   }

}
