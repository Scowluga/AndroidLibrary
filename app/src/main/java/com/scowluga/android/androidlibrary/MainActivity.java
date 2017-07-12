package com.scowluga.android.androidlibrary;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.Image;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.oguzdev.circularfloatingactionmenu.library.animation.MenuAnimationHandler;

import java.util.ArrayList;

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

     */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* FloatingActionMenu
        https://github.com/futuresimple/android-floating-action-button
        compile 'com.getbase:floatingactionbutton:1.10.1'

        Doesn't support the quick return function of hiding on scroll.

        Pro: Can do things like remove, set disabled

TRY STICKING THE VIEWS INSIDE COORDINATOR LAYOUTS TO RAISE PROPERLY ahhh you're a genius bro

        */

        FloatingActionsMenu menu = (FloatingActionsMenu)findViewById(R.id.fabMenu);








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
                .setPosition(3)
                .build();

        final FloatingActionMenu actionMenu =  new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addSubActionView(button4)
                .attachTo(actionButton)
                .enableAnimations()
                // Angles. 90 and 270 degrees are switched
                .setEndAngle(135)
                .setStartAngle(225)
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
}
