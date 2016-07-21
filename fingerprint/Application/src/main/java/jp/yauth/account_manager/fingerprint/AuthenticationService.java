package jp.yauth.account_manager.fingerprint;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by uu091468 on 7/21/16.
 */
public class AuthenticationService extends Service {
    private Authenticator mAuthenticator;

    @Override
    public void onCreate() {
        super.onCreate();
        mAuthenticator =new Authenticator(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}
