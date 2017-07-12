package com.scowluga.android.androidlibrary;

import android.animation.ObjectAnimator;
import android.media.Image;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

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


    Other Libraries
    -
    - Picasso + Bitmaps + Drawables
    - Android Volley?

    Tools
    - Android Studio 3.0 Canary
    - Gradle
    - Maven
    - Testing (JUnit)

     */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // CircularFloatingActionMenu: https://github.com/oguzbilgener/CircularFloatingActionMenu
        // Check Github for more details ^^
        // compile 'com.oguzdev:CircularFloatingActionMenu:1.0.2'

        /* TODO:
         Find a way to make it fit with snackbar in constraint layout
         Maybe integrate with the other FAB that fades down on scroll?
        Keep going! remember

        Learning Android is fun. Do it! 


         */

        ImageView icon = new ImageView(this);
        icon.setImageDrawable(getResources().getDrawable(R.drawable.icon_accessible));

        SubActionButton.Builder itemBuilder1 = new SubActionButton.Builder(this);
        ImageView icon1 = new ImageView(this);
        icon1.setImageDrawable(getResources().getDrawable(R.drawable.icon_blind_access));
        SubActionButton button1 = itemBuilder1.setContentView(icon1).build();

        SubActionButton.Builder itemBuilder2 = new SubActionButton.Builder(this);
        ImageView icon2 = new ImageView(this);
        icon2.setImageDrawable(getResources().getDrawable(R.drawable.icon_low_vision));
        SubActionButton button2 = itemBuilder2.setContentView(icon2).build();

        SubActionButton.Builder itemBuilder3 = new SubActionButton.Builder(this);
        ImageView icon3 = new ImageView(this);
        icon3.setImageDrawable(getResources().getDrawable(R.drawable.icon_person_plus));
        SubActionButton button3 = itemBuilder3.setContentView(icon3).build();

        SubActionButton.Builder itemBuilder4 = new SubActionButton.Builder(this);
        ImageView icon4 = new ImageView(this);
        icon4.setImageDrawable(getResources().getDrawable(R.drawable.icon_voice));
        SubActionButton button4 = itemBuilder4.setContentView(icon4).build();
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Hi", Toast.LENGTH_SHORT).show();
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

        final FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addSubActionView(button4)
                .attachTo(actionButton)
                .enableAnimations()
                // Angles. 90 and 270 degrees are switched
                .setEndAngle(270)
                .setStartAngle(180)
                .build();
        actionMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu floatingActionMenu) {
                // Animate icon to x
                Snackbar.make(findViewById(R.id.constraintLayout), "Opened!", Snackbar.LENGTH_SHORT)
                        .setAction("Close it!", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                actionMenu.close(true);
                            }
                        })
                        .show();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu floatingActionMenu) {
                // Animate x to icon
            }
        });

    }
}
