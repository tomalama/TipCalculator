package uottawa.tommy.tipcalculator;

import java.text.DecimalFormat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    static String currency;
    static String defaultTip;
    static String suggestedTip;
    TextView billValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        suggestedTip = null;
        defaultTip = "15";
        currency = "$";

        TextView billValueEditText = (TextView) findViewById(R.id.billValue);

        billValueEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0 || (editable.length() == 1 && editable.charAt(0) == '.')) {
                    setTipValueToZero();
                } else {
                    calculateTipValue();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.suggest_tip:
                startActivity(new Intent(this, SuggestTipActivity.class));
                return true;
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void changeTipPercentage(View view) {

        TextView tipValue = (TextView) findViewById(R.id.tipPercentValue);
        Button btn = (Button) view;
        String operator = btn.getText().toString();

        if (operator.equals("+")) {
            tipValue.setText(Integer.toString(Integer.parseInt(tipValue.getText().toString()) + 1));
        } else {
            tipValue.setText(Integer.toString(Integer.parseInt(tipValue.getText().toString()) - 1));
        }

        if (billValueTextView == null || billValueTextView.getText().toString().isEmpty()) {
            setTipValueToZero();
        } else {
            calculateTipValue();
        }
    }

    public void changeSplitValue(View view) {

        TextView splitValue = (TextView) findViewById(R.id.splitValue);
        Button btn = (Button) view;
        String operator = btn.getText().toString();

        if (operator.equals("+")) {
            splitValue.setText(Integer.toString(Integer.parseInt(splitValue.getText().toString()) + 1));
        } else {
            splitValue.setText(Integer.toString(Integer.parseInt(splitValue.getText().toString()) - 1));
        }

        if (billValueTextView == null || billValueTextView.getText().toString().isEmpty()) {
            setTipValueToZero();
        } else {
            calculateTipValue();
        }
    }

    public void calculateTipValue() {

        //Getting all the values used in calculations
        billValueTextView = (TextView) findViewById(R.id.billValue);
        double billValue = Double.parseDouble(billValueTextView.getText().toString());
        TextView tipPercentValueTextView = (TextView) findViewById(R.id.tipPercentValue);
        double tipPercentValue = Double.parseDouble(tipPercentValueTextView.getText().toString());
        TextView splitValueTextView = (TextView) findViewById(R.id.splitValue);
        double splitValue = Double.parseDouble(splitValueTextView.getText().toString());

        //Formatting
        DecimalFormat df = new DecimalFormat("#.00");

        //Calculating tip
        double calculatedTipValue = billValue * (tipPercentValue/100) / splitValue;

        //Displaying tip value
        TextView calculatedTipValueTextView = (TextView) findViewById(R.id.calculatedTipValue);
        calculatedTipValueTextView.setText(currency + df.format(calculatedTipValue));

        //Calculating total
        double totalValue = (billValue / splitValue) + calculatedTipValue;

        //Displaying total value
        TextView totalValueTextView = (TextView) findViewById(R.id.totalValue);
        totalValueTextView.setText(currency + df.format(totalValue));
    }

    public void setTipValueToZero() {

        TextView calculatedTipValueTextView = (TextView) findViewById(R.id.calculatedTipValue);
        TextView totalValueTextView = (TextView) findViewById(R.id.totalValue);

        calculatedTipValueTextView.setText(currency + "0.00");
        totalValueTextView.setText(currency + "0.00");
    }

    @Override
    public void onResume() {

        super.onResume();

        TextView tipValue = (TextView) findViewById(R.id.tipPercentValue);

        if (suggestedTip != null) {
            tipValue.setText(suggestedTip);
            suggestedTip = null;
        } else {
            tipValue.setText(defaultTip);
        }

        if (billValueTextView == null || billValueTextView.getText().toString().isEmpty()) {
            setTipValueToZero();
        } else {
            calculateTipValue();
        }
    }
}
