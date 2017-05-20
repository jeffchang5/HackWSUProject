package jeffreychang.xyz.wholesomefoods

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.GridView
import jeffreychang.xyz.wholesomefoods.login.LoginActivity
import jeffreychang.xyz.wholesomefoods.network.ClarifaiAPIEndpointInterface
import java.io.ByteArrayOutputStream
import jeffreychang.xyz.wholesomefoods.ui.GridAdapter
import android.provider.MediaStore
import android.os.Environment.getExternalStorageDirectory
import android.support.v4.app.FragmentActivity
import android.util.Log
import java.io.File
import java.io.IOException


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val CAMERA_REQUEST = 1888
    private var picsGrid = findViewById(R.id.foodPicsList) as GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
//        if (!sharedPreferences.contains(getString(R.string.isLoggedIn))) {
//            startActivity(Intent(this, LoginActivity::class.java))
//        }

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->

            val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
            val imagesFolder = File(Environment.getExternalStorageDirectory(), "MyImages")

            if (imagesFolder.mkdirs()) {
                //Log.d(FragmentActivity.TAG, "The directory is created")
            } else {
                //Log.d(FragmentActivity.TAG, "Failed or already exists")
            }
            val image = File(imagesFolder, "image_001" + System.currentTimeMillis() + ".jpg")

            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(image))

            startActivityForResult(cameraIntent, CAMERA_REQUEST)
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            val photo = data.extras.get("data") as Bitmap

            //add to grid view
            picsGrid.setAdapter(GridAdapter(applicationContext))

            val stream = ByteArrayOutputStream()
            photo.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            val byteArray = stream.toByteArray()

            //send byteArray to api
        }
    }
    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

}
