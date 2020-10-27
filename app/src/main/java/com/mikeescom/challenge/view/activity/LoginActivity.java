package com.mikeescom.challenge.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.mikeescom.challenge.BuildConfig;
import com.mikeescom.challenge.R;
import com.uber.sdk.android.core.auth.AccessTokenManager;
import com.uber.sdk.android.core.auth.AuthenticationError;
import com.uber.sdk.android.core.auth.LoginButton;
import com.uber.sdk.android.core.auth.LoginCallback;
import com.uber.sdk.android.core.auth.LoginManager;
import com.uber.sdk.core.auth.AccessToken;
import com.uber.sdk.core.auth.AccessTokenStorage;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.core.client.SessionConfiguration;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    public static final String CLIENT_ID = BuildConfig.UBER_CLIENT_ID;
    public static final String REDIRECT_URI = BuildConfig.UBER_REDIRECT_URI;

    private static final int UBER_BUTTON_REQUEST_CODE = 1112;
    private static final int RC_SIGN_IN = 1000;

    private LoginButton customButton;
    private View footer;
    private AccessTokenStorage accessTokenStorage;
    private SessionConfiguration configuration;

    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        configuration = new SessionConfiguration.Builder()
                .setClientId(CLIENT_ID)
                .setScopes(Arrays.asList(Scope.PROFILE))
                .setEnvironment(SessionConfiguration.Environment.SANDBOX)
                .setRedirectUri(REDIRECT_URI)
                .build();

        accessTokenStorage = new AccessTokenManager(this);

        customButton = findViewById(R.id.login_button_custom);
        customButton.setAccessTokenStorage(accessTokenStorage)
                .setCallback(new UberLoginCallback())
                .setSessionConfiguration(configuration)
                .setRequestCode(UBER_BUTTON_REQUEST_CODE);

        footer = findViewById(R.id.footer);
        footer.setOnClickListener(v -> goToTabbedActivity());



        // Gmail Auth
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        findViewById(R.id.sign_in_button).setOnClickListener(view -> signIn());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleSignInClient.signOut();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            goToTabbedActivity();
        }
    }

    private class UberLoginCallback implements LoginCallback {

        @Override
        public void onLoginCancel() {
            Toast.makeText(LoginActivity.this, "User canceled login", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onLoginError(@NonNull AuthenticationError error) {
            Toast.makeText(LoginActivity.this,
                    "Error trying to login: " + error.name(), Toast.LENGTH_LONG)
                    .show();
        }

        @Override
        public void onLoginSuccess(@NonNull AccessToken accessToken) {
            goToTabbedActivity();
        }

        @Override
        public void onAuthorizationCodeReceived(@NonNull String authorizationCode) {
            Toast.makeText(LoginActivity.this, "Authorization code: " + authorizationCode,
                    Toast.LENGTH_LONG)
                    .show();
        }
    }

    private void goToTabbedActivity() {
        Intent intent = new Intent(this, TabbedActivity.class);
        startActivity(intent);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}