package myfirstgame.todolist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import static myfirstgame.todolist.DBHelper.QUEST_COLUMN_DATE;

public class AddQuestActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editName;
    EditText editDesc;
    EditText editExpValue;
    TextView featText;

    RadioGroup radioGroupBtn;
    RadioButton strengthBtn;
    RadioButton staminaBtn;
    RadioButton intelligenceBtn;
    RadioButton socialBtn;

    DatePicker datepicker;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quest);

        editName = findViewById(R.id.questNameInput);
        editDesc = findViewById(R.id.questDescInput);
        editExpValue = findViewById(R.id.expValueInput);

        // Radio buttons
        radioGroupBtn = findViewById(R.id.radioGrp);
        strengthBtn = findViewById(R.id.strengthBtn);
        strengthBtn.setOnClickListener(this);

        staminaBtn = findViewById(R.id.staminaBtn);
        staminaBtn.setOnClickListener(this);

        intelligenceBtn = findViewById(R.id.intelligenceBtn);
        intelligenceBtn.setOnClickListener(this);

        socialBtn = findViewById(R.id.socialBtn);
        socialBtn.setOnClickListener(this);

        addBtn = findViewById(R.id.addQuestBtn);
        datepicker = findViewById(R.id.selectDate);

        featText = findViewById(R.id.featText);
    }

    public boolean showFeat(){
        if (strengthBtn.isChecked() || staminaBtn.isChecked() || intelligenceBtn.isChecked() || socialBtn.isChecked()) {
            featText.setText("This quest is a feat of: " + showCategoryNameByNumber(checkedRadioButton(radioGroupBtn)));
        }
        return true;
    }

    public void addQuest(View button){
        DBHelper dbHelper = new DBHelper(this);
        String name = editName.getText().toString();
        String description = editDesc.getText().toString();
        Integer expValue = Integer.parseInt(editExpValue.getText().toString());

        Integer categoryId = checkedRadioButton(radioGroupBtn);
        Date dueDate = getDateFromDatePicker(datepicker);

        Quest quest = new Quest(name, description, expValue, categoryId, dueDate);
        quest.save(dbHelper);

        Intent intent = new Intent(this, QuestActivity.class);
        startActivity(intent);
    }

    public static java.util.Date getDateFromDatePicker(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    public Integer checkedRadioButton(RadioGroup radioGroup){

        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.strengthBtn:
                return 1;
            case R.id.staminaBtn:
                return 2;
            case R.id.intelligenceBtn:
                return 3;
            case R.id.socialBtn:
                return 4;
        }
        return null;
    }

    public String showCategoryNameByNumber(Integer categoryInt){

        switch (categoryInt) {
            case 1:
                return "Strength";
            case 2:
                return "Stamina";
            case 3:
                return "Intelligence";
            case 4:
                return "Social";

        }
        return null;
    }

    @Override
    public void onClick(View v) {
        showFeat();
    }
}
