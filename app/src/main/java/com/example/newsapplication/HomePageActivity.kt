package com.example.newsapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.adapter.NewsAdapter
import com.example.newsapplication.entities.News
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import org.w3c.dom.Text


class HomePageActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private var currentUserEmail: String = ""
    private var currentUserName: String = ""

    private val title: String = "titleeee"
    private val source: String = "sourceee"  //apagar quando vir, adiantar o api para conseguir ter info no recycler
    private var imageUrl: String = "https://wonderfulengineering.com/wp-content/uploads/2014/10/wallpaper-photos-31-800x450.jpg"
    val uri = Uri.parse(imageUrl)
    private val someNews: News = News(0, uri, "this is title", "sourceeee")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        // recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.news_recyclerview)

        //START - OF - NAV ////////////////////////////////////////////////////////////////////////
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        //val emailTextView: TextView = findViewById(R.id.nav_top_textView) //Email Text View on Nav Drawer
        //val nameTextView: TextView = findViewById(R.id.nav_top_name) //Name Text View on Nav Drawer
        //val photoImageView: ImageView = findViewById(R.id.nav_top_imageView)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_world, R.id.nav_business, R.id.nav_breaking, R.id.nav_tech,
                R.id.nav_sports, R.id.nav_science, R.id.nav_health), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        //END - OF - NAV //////////////////////////////////////////////////////////////////////////

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            //Fill Text View on Nav Drawer with the user Name
            //val personName = acct.displayName
            //nameTextView.text = personName
            //photoImageView.setImageURI(acct.photoUrl)

            val personGivenName = acct.givenName
            val personFamilyName = acct.familyName
            currentUserEmail = acct.email.toString()
            //Fill TextView on Navegation Drawer with the user Email
            //emailTextView.text = currentUserEmail

            val personId = acct.id
        }

        Toast.makeText(this@HomePageActivity, "Welcome $currentUserEmail",
                Toast.LENGTH_SHORT).show()

        //RecyclerView Configuration/////////////////////////////////////////////////////////
        //recyclerView.layoutManager = LinearLayoutManager(this@HomePageActivity)
        //recyclerView.adapter = NewsAdapter(someNews)

        //val image: ImageView = findViewById(R.id.news_image)
        //Picasso.get().load(imageUrl).into(image);

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home_page, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Remove all notes from menu option
        return when (item.itemId) {
            R.id.sign_out -> {
                Firebase.auth.signOut()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(
                        applicationContext,
                        "User Logged Out",
                        Toast.LENGTH_LONG).show()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}