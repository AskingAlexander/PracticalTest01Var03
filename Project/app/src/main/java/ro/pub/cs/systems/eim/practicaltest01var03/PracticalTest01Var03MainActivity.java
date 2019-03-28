package ro.pub.cs.systems.eim.practicaltest01var03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var03MainActivity extends AppCompatActivity {

    private EditText topEditText = null;
    private EditText bottomEditText = null;
    private Button topButton = null;
    private Button bottomButton = null;
    private CheckBox firstCheck = null;
    private CheckBox secondCheck = null;
    private TextView infoDisplay = null;
    private  PracticalTest01Var03MainActivity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_main);

        activity = this;

        topEditText = (EditText)findViewById(R.id.name_text);
        bottomEditText = (EditText)findViewById(R.id.group_text);

        topButton = (Button)findViewById(R.id.navigate_to_secondary);
        bottomButton = (Button)findViewById(R.id.display_info);

        firstCheck = (CheckBox) findViewById(R.id.name_check);
        secondCheck = (CheckBox) findViewById(R.id.group_check);

        infoDisplay= (TextView) findViewById(R.id.info_display);


        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("topEditText")) {
                topEditText.setText(savedInstanceState.getString("topEditText"));
            } else {
                topEditText.setText("");
            }
            if (savedInstanceState.containsKey("bottomEditText")) {
                bottomEditText.setText(savedInstanceState.getString("bottomEditText"));
            } else {
                bottomEditText.setText("");
            }
        } else {
            topEditText.setText("");
            bottomEditText.setText("");
        }

        firstCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                String initialText = "";
                if (topEditText.getText().toString().isEmpty()){
                    Toast.makeText(activity, "Missing data for Name!",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                if (secondCheck.isChecked()){
                    initialText = bottomEditText.getText().toString();
                }
                if (isChecked){
                    infoDisplay.setText(topEditText.getText().toString() + " " + initialText);
                }else{
                    infoDisplay.setText(initialText);
                }

            }
        });

        secondCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                String initialText = "";
                if (bottomEditText.getText().toString().isEmpty()){
                    Toast.makeText(activity, "Missing data for Group!",
                            Toast.LENGTH_LONG).show();
                    return;
                }


                if (firstCheck.isChecked()){
                    initialText = topEditText.getText().toString();
                }
                if (isChecked){
                    infoDisplay.setText(initialText + " "+ bottomEditText.getText().toString());
                }else{
                    infoDisplay.setText(initialText);
                }

            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("topEditText", topEditText.getText().toString());
        savedInstanceState.putString("bottomEditText", bottomEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("topEditText")) {
            topEditText.setText(savedInstanceState.getString("topEditText"));
        } else {
            topEditText.setText("");
        }
        if (savedInstanceState.containsKey("bottomEditText")) {
            bottomEditText.setText(savedInstanceState.getString("bottomEditText"));
        } else {
            bottomEditText.setText("");
        }
    }


}
