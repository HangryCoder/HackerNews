package hackernews.mobile.com.hackernews.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import hackernews.mobile.com.hackernews.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.googleSignInBtn)
    Button googleSignInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        googleSignInBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewsListingActivity.class);
            startActivity(intent);
        });
    }
}