package com.scowluga.android.androidlibrary;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.SimpleShowcaseEventListener;
import com.github.amlcurran.showcaseview.targets.ActionViewTarget;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.oguzdev.circularfloatingactionmenu.library.animation.MenuAnimationHandler;

import java.util.ArrayList;

import cdflynn.android.library.crossview.CrossView;

public class MainActivity extends AppCompatActivity {

    /* TODO: Learn Material Design Android Tools

    General
    - Intro (TapTargetViews)
    - Help (Settings)

SNACKBARS

    - Recycler View Animations
    - Understand View Pager
    - Animations + Transitions (animated vectors)
    TabLayout

    - Parallax Toolbar
    - Collapsing Toolbar
    - The Toolbar in general (on CardViews)

    Coordinator and Constraint layouts

    Other Libraries
    -
    - Picasso + Bitmaps + Drawables
    - Android Volley?

    Tools
    - Android Studio 3.0 Canary
    - Gradle
    - Maven
    - Testing (JUnit)


    https://github.com/rey5137/Material/wiki/Text-Field

     */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // TextInputLayout
//        final AutoCompleteTextView et = (AutoCompleteTextView)findViewById(R.id.AutoCompleteTextView);
//        String[] roomNumbers = getResources().getStringArray(R.array.roomNumbers);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, roomNumbers);
//        et.setAdapter(adapter);
//        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                boolean handled = false;
//                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                    handled = true;
//                    if (TextUtils.isEmpty(et.getText())) {
//                        et.setError("Empty Search");
//                    } else {
//                        Snackbar.make(getCurrentFocus(), "Searched: " + et.getText().toString(), Snackbar.LENGTH_SHORT).show();
//                    }
//                }
//                return handled;
//            }
//        });

        //CrossView
        final CrossView cv = (CrossView)findViewById(R.id.crossView);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cv.toggle();
            }
        });

        // CircularProgressButton guide: https://github.com/dmytrodanylyk/circular-progress-button/wiki/User-Guide

        final CircularProgressButton circularButton = (CircularProgressButton)findViewById(R.id.circularProgressButton);
        circularButton.setIndeterminateProgressMode(true);
        circularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circularButton.getProgress() == 0) {
                    circularButton.setProgress(50);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            circularButton.setProgress(100);
                        }
                    }, 1000);
                }
            }
        });



        /* FloatingActionMenu
        https://github.com/futuresimple/android-floating-action-button
        compile 'com.getbase:floatingactionbutton:1.10.1'

        Doesn't support the quick return function of hiding on scroll.

        Pro: Can do things like remove, set disabled

TRY STICKING THE VIEWS INSIDE COORDINATOR LAYOUTS TO RAISE PROPERLY ahhh you're a genius bro
code to set on listeners
        */

        FloatingActionsMenu menu = (FloatingActionsMenu)findViewById(R.id.fabMenu);
        com.getbase.floatingactionbutton.FloatingActionButton button = (com.getbase.floatingactionbutton.FloatingActionButton)findViewById(R.id.startSequence);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTargets();
            }
        });
        com.getbase.floatingactionbutton.FloatingActionButton b = (com.getbase.floatingactionbutton.FloatingActionButton)findViewById(R.id.startShowcase);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShowcase();
            }
        });









        /* CircularFloatingActionMenu
        https://github.com/oguzbilgener/CircularFloatingActionMenu
        compile 'com.oguzdev:CircularFloatingActionMenu:1.0.2'

        Semi-customizable, just a few problems. Attaches to Activity, and can only be placed in
        set positions. Doesn't fit material design with Snackbar (moving up on snackbar show).

        Perhaps integrate with other fabs, including fading on scroll. Also, perhaps animate
        the icon to spin into an X on click

         */

        ImageView icon = new ImageView(this);
        icon.setImageDrawable(getResources().getDrawable(R.drawable.icon_accessible));
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

        ImageView icon1 = new ImageView(this);
        icon1.setImageDrawable(getResources().getDrawable(R.drawable.icon_blind_access));
        SubActionButton button1 = itemBuilder.setContentView(icon1).build();

        ImageView icon2 = new ImageView(this);
        icon2.setImageDrawable(getResources().getDrawable(R.drawable.icon_low_vision));
        SubActionButton button2 = itemBuilder.setContentView(icon2).build();

        ImageView icon3 = new ImageView(this);
        icon3.setImageDrawable(getResources().getDrawable(R.drawable.icon_person_plus));
        SubActionButton button3 = itemBuilder.setContentView(icon3).build();

        ImageView icon4 = new ImageView(this);
        icon4.setImageDrawable(getResources().getDrawable(R.drawable.icon_voice));
        SubActionButton button4 = itemBuilder.setContentView(icon4).build();
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(findViewById(R.id.constraintLayout), "one!", Snackbar.LENGTH_SHORT)
                        .setAction("two", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Just to learn about snackbars
                            }
                        })
                        .show();
            }
        });

        /* SetPosition:
        1: Top Middle
        2: Top Right
        3: Middle Right
        ...
        Starts Top Middle, total 8 positions, circles around clockwise
         */
        final FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .setPosition(4)
                .build();

        final FloatingActionMenu actionMenu =  new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addSubActionView(button4)
                .attachTo(actionButton)
                .enableAnimations()
                // Angles. 90 and 270 degrees are switched
                .setStartAngle(180)
                .setEndAngle(270)
                .build();
        actionMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu floatingActionMenu) {
                // Animate icon to x

            }

            @Override
            public void onMenuClosed(FloatingActionMenu floatingActionMenu) {
                // Animate x to icon
            }
        });
    }

    private void showShowcase() {
        CircularProgressButton bn = (CircularProgressButton)findViewById(R.id.circularProgressButton);
        new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(bn))
                .setContentTitle("HI")
                .setContentText("Cool")
                .hideOnTouchOutside()
                .setShowcaseEventListener(new SimpleShowcaseEventListener() {
                    @Override
                    public void onShowcaseViewDidHide(ShowcaseView showcaseView) {
                        Toast.makeText(MainActivity.this, "What", Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
    }


    private void showTargets() {
            CrossView cv = (CrossView)findViewById(R.id.crossView);
            CircularProgressButton bn = (CircularProgressButton)findViewById(R.id.circularProgressButton);
            TapTargetSequence sequence = new TapTargetSequence(this)
                    .targets(
                            TapTarget.forView(cv, "This is the target", "Believe me we have the best targets")
                                // All options below are optional
                                .outerCircleColor(R.color.red)      // Specify a color for the outer circle
                                .outerCircleAlpha(0.96f)            // Specify the alpha amount for the outer circle
                                .targetCircleColor(R.color.white)   // Specify a color for the target circle
                                .titleTextSize(20)                  // Specify the size (in sp) of the title text
                                .titleTextColor(R.color.white)      // Specify the color of the title text
                                .descriptionTextSize(10)            // Specify the size (in sp) of the description text
                                .descriptionTextColor(R.color.red)  // Specify the color of the description text
                                .textColor(R.color.blue)            // Specify a color for both the title and description text
                                .textTypeface(Typeface.SANS_SERIF)  // Specify a typeface for the text
                                .dimColor(R.color.black)            // If set, will dim behind the view with 30% opacity of the given color
                                .drawShadow(true)                   // Whether to draw a drop shadow or not
                                .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                                .tintTarget(true)                   // Whether to tint the target view's color
                                .transparentTarget(false)           // Specify whether the target is transparent (displays the content underneath)
                                .icon(getDrawable(R.drawable.icon_voice))                     // Specify a custom drawable to draw as the target
                                .targetRadius(60)
                                .id(1),                  // Specify the target radius (in dp)
                            TapTarget.forView(bn, "Wow", "Cool").id(2)
                    ).listener(new TapTargetSequence.Listener() {
                        @Override
                        public void onSequenceFinish() {
                            Toast.makeText(MainActivity.this, "done", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {
                            if (targetClicked) {
                                switch (lastTarget.id()) {
                                    case 1:
                                        Toast.makeText(MainActivity.this, "Clicked 1", Toast.LENGTH_SHORT).show();
                                        break; 
                                    case 2:
                                        Toast.makeText(MainActivity.this, "Wow 2", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onSequenceCanceled(TapTarget lastTarget) {
                            Toast.makeText(MainActivity.this, "cancelled", Toast.LENGTH_SHORT).show();
                        }
                    });
            sequence.start();
    }
}
