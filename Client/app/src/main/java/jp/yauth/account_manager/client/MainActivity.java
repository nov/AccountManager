package jp.yauth.account_manager.client;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.support.design.widget.Snackbar;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    AccountManager mAccountManager;
    Map<String, String> idpSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAccountManager = AccountManager.get(this);
        loadButtons();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Bundle bundle = data.getExtras();
            for (String key : bundle.keySet()) {
                Object value = bundle.get(key);
                Log.d("callback", String.format("%s %s", key, value.toString()));
            }
            Account account = new Account(
                    data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME),
                    data.getStringExtra(AccountManager.KEY_ACCOUNT_TYPE)
            );
            getAuthToken(account, idpSettings.get(account.type));
        }
    }

    private void loadButtons() {
        loadButtonFb();
        loadButtonGoogle();
        loadButtonNov();
    }

    private void loadButtonFb() {
        Button button = (Button) findViewById(R.id.button_fb);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAuthToken("com.facebook.auth.login", "email");
            }
        });
    }

    private void loadButtonGoogle() {
        Button button = (Button) findViewById(R.id.button_google);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAuthToken("com.google", "oauth2:openid profile email");
            }
        });
    }

    private void loadButtonNov() {
        Button button = (Button) findViewById(R.id.button_nov);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAuthToken("jp.yauth.account_manager.server", "openid profile email");
            }
        });
    }

    private void getAuthToken(String accountType, String authTokenType) {
        Account[] accounts = mAccountManager.getAccountsByType(accountType);
        rememberIdpSettings(accountType, authTokenType);
        if (accounts.length > 0) {
            getAuthToken(accounts[0], authTokenType);
        } else {
            Intent intent = AccountManager.newChooseAccountIntent(null, null, new String[]{accountType}, null, null, null, null);
            startActivityForResult(intent, 1);
        }
    }

    private void getAuthToken(final Account account, String authTokenType) {
        mAccountManager.getAuthToken(account, authTokenType, null, this, new AccountManagerCallback<Bundle>() {
            @Override
            public void run(AccountManagerFuture<Bundle> future) {
                try {
                    Bundle bundle = future.getResult();
                    String authToken = bundle.getString(AccountManager.KEY_AUTHTOKEN);
                    Log.d("access_token", authToken);
                    notify(String.format("token: %s", authToken));
                    mAccountManager.invalidateAuthToken(account.type, authToken);
                } catch (Exception e) {
                    Log.d("error", e.getMessage());
                    notify(String.format("error: %s", e.getMessage()));
                }
            }

            private void notify(String message) {
                final RelativeLayout layout = (RelativeLayout) findViewById(R.id.root_layout);
                Snackbar.make(layout, message, Snackbar.LENGTH_LONG).show();
            }
        }, null);
    }

    private void rememberIdpSettings(String accountType, String authTokenType) {
        if (idpSettings == null) {
            idpSettings = new HashMap<String, String>();
        }
        if (!idpSettings.containsKey(accountType)) {
            idpSettings.put(accountType, authTokenType);
        }
    }
}
