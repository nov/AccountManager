package jp.yauth.account_manager.fingerprint;

import android.app.Activity;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.util.Log;

import java.security.Signature;

import javax.inject.Inject;

/**
 * Created by uu091468 on 7/21/16.
 */
public class FingerPrintAuthenticator extends Activity {
    private static final String DIALOG_FRAGMENT_TAG = "myFragment";

    @Inject FingerprintAuthenticationDialogFragment mFragment;
    @Inject
    Signature mSignature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("loaded", "FingerPrintAuthenticator#onCreate");

        super.onCreate(savedInstanceState);
        ((InjectedApplication) getApplication()).inject(this);

        setContentView(R.layout.fingerprint_authenticator);
        mFragment.setCryptoObject(new FingerprintManager.CryptoObject(mSignature));
        mFragment.setStage(FingerprintAuthenticationDialogFragment.Stage.FINGERPRINT);
        mFragment.show(getFragmentManager(), DIALOG_FRAGMENT_TAG);
    }
}
