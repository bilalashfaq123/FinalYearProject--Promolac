package com.example.finalyearprojectlearning;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.finalyearprojectlearning.HomeDrawer.HomeDrawerActivity;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 1002;
    List<AuthUI.IdpConfig> providers;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("bilal")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Successful";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        btn=(Button)findViewById(R.id.btn_SignOut);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance()
                        .signOut(MainActivity.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                btn.setEnabled(false);
//                                PackageInfo info;
//                                try {
//                                    info = getPackageManager().getPackageInfo("com.example.finalyearprojectlearning", PackageManager.GET_SIGNATURES);
//                                    for (Signature signature : info.signatures) {
//                                        MessageDigest md;
//                                        md = MessageDigest.getInstance("SHA");
//                                        md.update(signature.toByteArray());
//                                        String something = new String(Base64.encode(md.digest(), 0));
//                                        //String something = new String(Base64.encodeBytes(md.digest()));
//                                        Log.e("hash key", something);
//                                    }
//                                } catch (PackageManager.NameNotFoundException e1) {
//                                    Log.e("name not found", e1.toString());
//                                } catch (NoSuchAlgorithmException e) {
//                                    Log.e("no such an algorithm", e.toString());
//                                } catch (Exception e) {
//                                    Log.e("exception", e.toString());
//                                }
                                ShowSignInOptions();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),""+e.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
//        init providers
//        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
//            startActivity(new Intent(this,HomeDrawerActivity.class));
//            finish();
//            return;
//        }
        providers= Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build()
        );



        // Showing Options
        ShowSignInOptions();
    }

    public void ShowSignInOptions() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setTheme(R.style.MyTheme)
                        .build()
                        ,MY_REQUEST_CODE
        );


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==MY_REQUEST_CODE){

            IdpResponse response=IdpResponse.fromResultIntent(data);
            if(resultCode==RESULT_OK){
                //GET USER
                startActivity(new Intent(this, HomeDrawerActivity.class));
                finish();
//
//                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
//                Toast.makeText(this, ""+user.getEmail().toString(),Toast.LENGTH_LONG).show();
//                btn.setEnabled(true);
            }
            else {
                Toast.makeText(this,"Not Login in",Toast.LENGTH_LONG).show();
               // btn.setEnabled(true);
            }
        }

    }
}
