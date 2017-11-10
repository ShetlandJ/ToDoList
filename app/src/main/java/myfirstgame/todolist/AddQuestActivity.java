package myfirstgame.todolist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class AddQuestActivity extends MyMenu {

    EditText editName;
    EditText editDesc;
    EditText editExpValue;
    EditText editCategory;
    EditText editDate;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quest);

        editName = findViewById(R.id.questNameInput);
        editDesc = findViewById(R.id.questDescInput);
        editExpValue = findViewById(R.id.expValueInput);
        editCategory = findViewById(R.id.categoryInput);
        editDate = findViewById(R.id.dateInput);
        addBtn = findViewById(R.id.addQuestBtn);
    }

    public void addMovie(View button){
        DBHelper dbHelper = new DBHelper(this);
        String name = editName.getText().toString();
        String description = editDesc.getText().toString();
        Integer expValue = Integer.parseInt(editExpValue.getText().toString());
        String category = editCategory.getText().toString();
        String date = editDate.getText().toString();


        dbHelper.save(name, description, expValue, category, date);

        Intent intent = new Intent(this, QuestActivity.class);
        startActivity(intent);
    }


}
