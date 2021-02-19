package com.edson.teachercallroll.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.edson.teachercallroll.R;
import com.edson.teachercallroll.adapterholder.assistancelist.AssistanceListAdapter;
import com.edson.teachercallroll.adapterholder.studentsinsheet.StudentsInSheetAdapter;
import com.edson.teachercallroll.adapterholder.studentsinsheet.StudentsInSheetModifyAdapter;
import com.edson.teachercallroll.model.AssistanceSheetDto;
import com.edson.teachercallroll.model.StudentAssistance;
import com.edson.teachercallroll.viewmodel.StudentsInSheetModifyViewModel;
import com.edson.teachercallroll.viewmodel.StudentsInSheetViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class StudentsInSheetModifyActivity extends AppCompatActivity {

    private StudentsInSheetModifyViewModel viewModel;
    private SharedPreferences shPref;
    private StudentsInSheetModifyAdapter adapter;
    private ArrayList<StudentAssistance> studentDtoList;
    private AssistanceSheetDto assistanceSheetDto;

    private TextView txtVSheetGroupNameModify;
    private TextView txtVSheetModuleNameModify;
    private RecyclerView rclvStudentsInSheetModify;
    private FloatingActionButton floatingActionButtonModify;
    private String token;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_in_sheet_modify);
        setupComponents();
        getAssistanceList();
    }

    public void setupComponents() {
        viewModel = new ViewModelProvider(StudentsInSheetModifyActivity.this).get(StudentsInSheetModifyViewModel.class);
        shPref = StudentsInSheetModifyActivity.this.getSharedPreferences("TeacherCallRoll_ShPref", 0);
        txtVSheetGroupNameModify = findViewById(R.id.txtVSheetGroupNameModify);
        txtVSheetModuleNameModify = findViewById(R.id.txtVSheetModuleNameModify);
        rclvStudentsInSheetModify = findViewById(R.id.rclvStudentsInSheetModify);
        rclvStudentsInSheetModify.setLayoutManager(new LinearLayoutManager(StudentsInSheetModifyActivity.this));
        floatingActionButtonModify = findViewById(R.id.floatingActionButtonModify);
        token = shPref.getString("auth_token", null);
        intent = getIntent();
    }

    public void getAssistanceList() {
        viewModel.showAssistanceSheetDetails(token, intent.getLongExtra("assistanceSheetId", 0))
                .observe(StudentsInSheetModifyActivity.this, (Observer<String>) jsonList -> {
                    if (jsonList != null) {
                        Type listStudent = new TypeToken<AssistanceSheetDto>() {}.getType();
                        assistanceSheetDto = new Gson().fromJson(jsonList, listStudent);
                        txtVSheetModuleNameModify.setText(assistanceSheetDto.getModule());
                        txtVSheetGroupNameModify.setText(assistanceSheetDto.getGroupName());
                        studentDtoList = (ArrayList<StudentAssistance>) assistanceSheetDto.getStudents();
                        adapter = new StudentsInSheetModifyAdapter(StudentsInSheetModifyActivity.this, studentDtoList);
                        rclvStudentsInSheetModify.addItemDecoration(new DividerItemDecoration(StudentsInSheetModifyActivity.this, LinearLayoutManager.VERTICAL));
                        rclvStudentsInSheetModify.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
    }


}