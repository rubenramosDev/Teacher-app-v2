package com.edson.teachercallroll.adapterholder.studentsinsheet;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edson.teachercallroll.R;
import com.edson.teachercallroll.model.StudentAssistance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StudentsInSheetModifyHolder extends RecyclerView.ViewHolder {

    private TextView txtVName;
    private TextView txtVIdentifierNum;
    private TextView txtVDate;
    private Spinner spnrStatusStudent;

    List<String> spinnerArray;
    ArrayAdapter<String> adapter;

    public StudentsInSheetModifyHolder(@NonNull View itemView) {
        super(itemView);
        txtVName = itemView.findViewById(R.id.txtVName);
        txtVIdentifierNum = itemView.findViewById(R.id.txtVIdentifierNum);
        txtVDate = itemView.findViewById(R.id.txtVDate);
        spnrStatusStudent = itemView.findViewById(R.id.spnrStatusStudent);
    }

    public void setDetails(StudentAssistance studentAssistance, Context context){
        spinnerArray = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("'le' dd/MM/yy 'à' HH'h'mm");
        txtVName.setText(studentAssistance.getFirstName() + " " + studentAssistance.getLastName());
        txtVIdentifierNum.setText(studentAssistance.getIdentifierNumber());
        txtVDate.setText(sdf.format(studentAssistance.getDate()));
        Log.i("STUDENTS_SHEET_MODIFY", "setDetails -> STATUS " + studentAssistance.getStatus());
        if (studentAssistance.getStatus().equals("IN_TIME")){
            spinnerArray.add("IN_TIME");
            spinnerArray.add("LATE");
        }else {
            spinnerArray.add("LATE");
            spinnerArray.add("IN_TIME");
        }
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, spinnerArray);
        spnrStatusStudent.setAdapter(adapter);
    }

}
