package project.wgt.capitalviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainFragment = MainFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainerView, mainFragment,
                MainFragment.TAG
            )
            .commit()
    }
}