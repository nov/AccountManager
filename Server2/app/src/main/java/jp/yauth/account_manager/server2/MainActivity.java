package jp.yauth.account_manager.server2;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(jp.yauth.account_manager.server2.R.layout.activity_main);

        Button button = (Button) findViewById(jp.yauth.account_manager.server2.R.id.add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remember("nov");
            }
        });
    }

    private void remember(String name) {
        Account account = new Account(name, getString(jp.yauth.account_manager.server2.R.string.authenticator_type));
        AccountManager accountManager = AccountManager.get(this);
        accountManager.addAccountExplicitly(account, "refresh_token", null);
        setResult(RESULT_OK);
        finish();
    }
}
