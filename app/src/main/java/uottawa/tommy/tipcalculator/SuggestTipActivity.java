package uottawa.tommy.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.DecimalFormat;

public class SuggestTipActivity extends AppCompatActivity {

    DecimalFormat df;
    String suggestionString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_tip);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TextView recommendationLabel = (TextView) findViewById(R.id.recommendationLabel);
        final TextView suggestionValue = (TextView) findViewById(R.id.suggestionValue);
        final Button useButton = (Button) findViewById(R.id.useButton);

        df = new DecimalFormat("#");

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                recommendationLabel.setVisibility(View.VISIBLE);
                suggestionValue.setText(df.format(10 + v*2));
                suggestionValue.setVisibility(View.VISIBLE);
                useButton.setVisibility(View.VISIBLE);
                suggestionString = df.format(10 + v*2);
            }
        });
    }

    public void useSuggestion (View view) {

        WelcomeActivity.suggestedTip = suggestionString;
        finish();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
