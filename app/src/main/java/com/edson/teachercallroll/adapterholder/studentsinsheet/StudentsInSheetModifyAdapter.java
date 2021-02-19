package com.edson.teachercallroll.adapterholder.studentsinsheet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edson.teachercallroll.R;
import com.edson.teachercallroll.model.StudentAssistance;

import java.util.ArrayList;

public class StudentsInSheetModifyAdapter extends RecyclerView.Adapter<StudentsInSheetModifyHolder> {

    private Context context;
    private ArrayList<StudentAssistance> studentAssistances;

    public StudentsInSheetModifyAdapter(Context context, ArrayList<StudentAssistance> studentAssistances) {
        this.context = context;
        this.studentAssistances = studentAssistances;
    }

    @NonNull
    @Override
    public StudentsInSheetModifyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row_students_in_sheet_modify, parent, false);
        return new StudentsInSheetModifyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsInSheetModifyHolder holder, int position) {
        StudentAssistance studentAssistance = studentAssistances.get(position);
        holder.setDetails(studentAssistance, context);
    }

    @Override
    public int getItemCount() {
        return studentAssistances.size();
    }
}
