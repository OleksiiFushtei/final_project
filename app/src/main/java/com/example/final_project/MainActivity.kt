package com.example.final_project

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import com.example.final_project.adapters.FragmentsAdapter
import com.example.final_project.fragments.SensorsFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    AppCompatActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )
        setContentView(
            R.layout.activity_main
        )
        val title =
            intent.getStringExtra(
                "name"
            )
        if (title != null) {
            toolbar.title =
                title
        } else {
            intent.getStringExtra(
                "name"
            )
        }
        setSupportActionBar(
            toolbar
        )
        val fragmentAdapter =
            FragmentsAdapter(
                supportFragmentManager,
                intent.getIntExtra(
                    "id",
                    0
                )
            )
        viewPager.adapter =
            fragmentAdapter
        tabLayout.setupWithViewPager(
            viewPager
        )

        //TODO check if menu works
        toolbar.setOnMenuItemClickListener {
            MaterialAlertDialogBuilder(
                this.applicationContext
            ).setTitle(
                "Sign out"
            )
                .setMessage(
//                    "Are you sure you want to sign out?"
                    "ARE YOU SURE ABOUT THAT?"
                )
                .setPositiveButton(
                    "Sign out"
                ) { _: DialogInterface, _: Int ->
                    val signInIntent =
                        Intent(
                            this@MainActivity,
                            SignInActivity::class.java
                        )
                    startActivity(
                        signInIntent
                    )
                    finish()
                }
                .setNegativeButton(
                    "Cancel",
                    null
                )
            false
        }
    }

//    override fun onCreateOptionsMenu(
//        menu: Menu?
//    ): Boolean {
//        val menuInflater: MenuInflater = menuInflater
//        menuInflater.inflate(R.menu.custom_menu, menu)
//        return super.onCreateOptionsMenu(
//            menu
//        )
//        return true
//    }
}
