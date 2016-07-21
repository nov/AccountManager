package jp.yauth.account_manager.server2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by nov.matake on 12/24/15.
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
