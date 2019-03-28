package ro.pub.cs.systems.eim.practicaltest01var03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PracticalTest01Var03SecondaryActivity extends AppCompatActivity {
    private TextView nameText = null;
    private TextView groupText = null;
    private Button okButton = null;
    private Button cancelButton = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_secondary);

        okButton = (Button)findViewById(R.id.ok_button);
        okButton.setOnClickListener(buttonClickListener);
        cancelButton = (Button)findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(buttonClickListener);


        nameText= (TextView) findViewById(R.id.name_text);
        groupText= (TextView) findViewById(R.id.group_text);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("topEditText")) {
            String nameString = intent.getStringExtra("topEditText");
            nameText.setText(nameString);
        }
        if (intent != null && intent.getExtras().containsKey("bottomEditText")) {
            String groupString = intent.getStringExtra("bottomEditText");
            groupText.setText(groupString);
        }
    }
    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.ok_button:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.cancel_button:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }
}
