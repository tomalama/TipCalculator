package uottawa.tommy.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    Spinner spinner;
    TextView defaultTipPercentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        defaultTipPercentTextView = (TextView) findViewById(R.id.defaultTipPercentValue);

        defaultTipPercentTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() != 0) {
                    WelcomeActivity.defaultTip = editable.toString();
                } else {
                    WelcomeActivity.defaultTip = "0";
                }
            }
        });

        spinner = (Spinner) findViewById(R.id.currencySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currencyArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_style);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                WelcomeActivity.currency = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    @Override
    public void onResume() {

        super.onResume();

        defaultTipPercentTextView.setText(WelcomeActivity.defaultTip);

        if (WelcomeActivity.currency.equals("$")) {
            spinner.setSelection(0);
        } else if (WelcomeActivity.currency.equals("€")){
            spinner.setSelection(1);
        } else {
            spinner.setSelection(2);
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
