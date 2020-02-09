package com.olivineltd.airhornsoundboard;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Home extends AppCompatActivity implements Fragment_one.OnFragmentInteractionListener{
//    Button button1, button2, button3, button4;
//    static MediaPlayer firetruck, police, siren, tornado, foghorn, train, vuvu;
    boolean doubleBackToExitPressedOnce = false;
    ImageView optionButton;
    Dialog dialog;
//    TextView tvCredit, tvInfo;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.option_dialog);

//        tvCredit = dialog.findViewById(R.id.tvCredit);
//        tvInfo = dialog.findViewById(R.id.tvInfo);

        bundle=new Bundle();

        optionButton = findViewById(R.id.optionButton);
        optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dialog.show();

            }
        });

//        tvCredit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//                //go to credit page
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.home_container, new CreditFragment(), "credit")
//                        .addToBackStack(null)
//                        .commit();
//            }
//        });
//        tvInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//                //go to info page
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.home_container, new InfoFragment(), "info")
//                        .addToBackStack(null)
//                        .commit();
//            }
//        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void layer_one(View view) {
        bundle.putString("Number","one");
        fragmentPass();
    }



    public void layer_two(View view) {
        bundle.putString("Number","two");
        fragmentPass();
    }

    public void layer_three(View view) {
        bundle.putString("Number","three");
        fragmentPass();
    }

    public void layer_four(View view) {
        bundle.putString("Number","four");
        fragmentPass();
    }

    @Override
    public void onBackPressed() {
        Fragment credit = getSupportFragmentManager().findFragmentByTag("credit");
        Fragment info = getSupportFragmentManager().findFragmentByTag("info");
        Fragment one=getSupportFragmentManager().findFragmentByTag("one");

        if (dialog.isShowing()) {
            dialog.dismiss();
        } else if ((credit != null && credit.isVisible()) || info != null && info.isVisible()
        ||one!=null&&one.isVisible()) {
            getSupportFragmentManager().popBackStack();
        } else {


            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);

        }

    }

    private void fragmentPass() {
        Fragment fragment=new Fragment_one();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.home_container, fragment, "one")
                .addToBackStack(null)
                .commit();
    }
}