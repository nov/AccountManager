package jp.yauth.account_manager.server;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remember("nov");
            }
        });
    }

    private void remember(String name) {
        Account account = new Account(name, getString(R.string.authenticator_type));
        AccountManager accountManager = AccountManager.get(this);
        accountManager.addAccountExplicitly(account, "refresh_token", null);
        setResult(RESULT_OK);
        finish();
    }
}
