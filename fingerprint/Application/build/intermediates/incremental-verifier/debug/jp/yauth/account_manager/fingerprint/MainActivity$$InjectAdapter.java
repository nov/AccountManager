// Code generated by dagger-compiler.  Do not edit.
package jp.yauth.account_manager.fingerprint;

import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/**
 * A {@code Binding<MainActivity>} implementation which satisfies
 * Dagger's infrastructure requirements including:
 *
 * Owning the dependency links between {@code MainActivity} and its
 * dependencies.
 *
 * Being a {@code Provider<MainActivity>} and handling creation and
 * preparation of object instances.
 *
 * Being a {@code MembersInjector<MainActivity>} and handling injection
 * of annotated fields.
 */
public final class MainActivity$$InjectAdapter extends Binding<MainActivity>
    implements Provider<MainActivity>, MembersInjector<MainActivity> {
  private Binding<android.app.KeyguardManager> mKeyguardManager;
  private Binding<android.hardware.fingerprint.FingerprintManager> mFingerprintManager;
  private Binding<FingerprintAuthenticationDialogFragment> mFragment;
  private Binding<java.security.KeyStore> mKeyStore;
  private Binding<java.security.KeyPairGenerator> mKeyPairGenerator;
  private Binding<java.security.Signature> mSignature;
  private Binding<android.content.SharedPreferences> mSharedPreferences;

  public MainActivity$$InjectAdapter() {
    super("jp.yauth.account_manager.fingerprint.MainActivity", "members/jp.yauth.account_manager.fingerprint.MainActivity", NOT_SINGLETON, MainActivity.class);
  }

  /**
   * Used internally to link bindings/providers together at run time
   * according to their dependency graph.
   */
  @Override
  @SuppressWarnings("unchecked")
  public void attach(Linker linker) {
    mKeyguardManager = (Binding<android.app.KeyguardManager>) linker.requestBinding("android.app.KeyguardManager", MainActivity.class, getClass().getClassLoader());
    mFingerprintManager = (Binding<android.hardware.fingerprint.FingerprintManager>) linker.requestBinding("android.hardware.fingerprint.FingerprintManager", MainActivity.class, getClass().getClassLoader());
    mFragment = (Binding<FingerprintAuthenticationDialogFragment>) linker.requestBinding("jp.yauth.account_manager.fingerprint.FingerprintAuthenticationDialogFragment", MainActivity.class, getClass().getClassLoader());
    mKeyStore = (Binding<java.security.KeyStore>) linker.requestBinding("java.security.KeyStore", MainActivity.class, getClass().getClassLoader());
    mKeyPairGenerator = (Binding<java.security.KeyPairGenerator>) linker.requestBinding("java.security.KeyPairGenerator", MainActivity.class, getClass().getClassLoader());
    mSignature = (Binding<java.security.Signature>) linker.requestBinding("java.security.Signature", MainActivity.class, getClass().getClassLoader());
    mSharedPreferences = (Binding<android.content.SharedPreferences>) linker.requestBinding("android.content.SharedPreferences", MainActivity.class, getClass().getClassLoader());
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> injectMembersBindings) {
    injectMembersBindings.add(mKeyguardManager);
    injectMembersBindings.add(mFingerprintManager);
    injectMembersBindings.add(mFragment);
    injectMembersBindings.add(mKeyStore);
    injectMembersBindings.add(mKeyPairGenerator);
    injectMembersBindings.add(mSignature);
    injectMembersBindings.add(mSharedPreferences);
  }

  /**
   * Returns the fully provisioned instance satisfying the contract for
   * {@code Provider<MainActivity>}.
   */
  @Override
  public MainActivity get() {
    MainActivity result = new MainActivity();
    injectMembers(result);
    return result;
  }

  /**
   * Injects any {@code @Inject} annotated fields in the given instance,
   * satisfying the contract for {@code Provider<MainActivity>}.
   */
  @Override
  public void injectMembers(MainActivity object) {
    object.mKeyguardManager = mKeyguardManager.get();
    object.mFingerprintManager = mFingerprintManager.get();
    object.mFragment = mFragment.get();
    object.mKeyStore = mKeyStore.get();
    object.mKeyPairGenerator = mKeyPairGenerator.get();
    object.mSignature = mSignature.get();
    object.mSharedPreferences = mSharedPreferences.get();
  }

}