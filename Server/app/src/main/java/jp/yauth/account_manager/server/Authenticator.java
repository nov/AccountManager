package jp.yauth.account_manager.server;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by nov.matake on 12/24/15.
 */
public class Authenticator extends AbstractAccountAuthenticator {
    final Context mContext;
    final String mAccountType;

    public Authenticator(Context context) {
        super(context);
        mContext = context;
        mAccountType = mContext.getString(R.string.authenticator_type);
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        return null;
    }

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {
        final Intent intent = new Intent(mContext, MainActivity.class);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);

        final Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);

        return bundle;
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        Log.d("getAuthToken", account.type);
        for (String key : options.keySet()) {
            Object value = options.get(key);
            Log.d("options", String.format("%s %s", key, value.toString()));
        }

        verifySender((String) options.get("androidPackageName"));

        AccountManager accountManager = AccountManager.get(mContext);
        String refreshToken = accountManager.getPassword(account);
        Log.d("refresh_token", refreshToken);
        // TODO: issue authToken using stored refreshToken

        String authToken = String.format("access_token for scope=\"%s\"", authTokenType);
        accountManager.setAuthToken(account, authTokenType, authToken);

        Bundle result = new Bundle();
        result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
        result.putString(AccountManager.KEY_ACCOUNT_TYPE, mAccountType);
        result.putString(AccountManager.KEY_AUTHTOKEN, authToken);
        result.putString("id_token", "header.payload.signature");
        return result;
    }

    @Override
    public String getAuthTokenLabel(String authTokenType) {
        return null;
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
        return null;
    }

    private void verifySender(String packageName) {
        PackageManager packageManager = mContext.getPackageManager();
        PackageInfo pkginfo = null;
        try {
            pkginfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            Signature signature = pkginfo.signatures[0];
            Log.d("signature", signature.toString());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
