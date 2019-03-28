package ro.pub.cs.systems.eim.practicaltest01var03;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var03MainActivity extends AppCompatActivity {

    private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;

    private EditText topEditText = null;
    private EditText bottomEditText = null;
    private Button topButton = null;
    private Button bottomButton = null;
    private CheckBox firstCheck = null;
    private CheckBox secondCheck = null;
    private TextView infoDisplay = null;
    private  PracticalTest01Var03MainActivity activity;
    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message]", intent.getStringExtra("message"));
        }
    }
    private IntentFilter intentFilter = new IntentFilter();


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

        for (int index = 0; index < Constants.actionTypes.length; index++) {
            intentFilter.addAction(Constants.actionTypes[index]);
        }


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

        topButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var03SecondaryActivity.class);

                intent.putExtra("topEditText", topEditText.getText().toString());
                intent.putExtra("bottomEditText", bottomButton.getText().toString());
                startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
            }
        });

        bottomButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                if (firstCheck.isChecked()
                        && secondCheck.isChecked()
                        && (!topEditText.getText().toString().isEmpty())
                        && (!secondCheck.getText().toString().isEmpty())) {
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var03Service.class);
                    intent.putExtra("topEditText", topEditText.getText().toString().isEmpty());
                    intent.putExtra("bottomEditText", bottomEditText.getText().toString().isEmpty());
                    getApplicationContext().startService(intent);
                    int serviceStatus = Constants.SERVICE_STARTED;
                }
            }
        });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var03Service.class);
        stopService(intent);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }
}
