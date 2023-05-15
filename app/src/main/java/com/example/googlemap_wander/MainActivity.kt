package com.example.googlemap_wander

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.googlemap_wander.Adapter.MapAdpater
import com.example.googlemap_wander.Models.UserMap
import com.example.googlemap_wander.databinding.ActivityMainBinding
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    lateinit var viewModel: MapViewModel

    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val userMap = data?.getSerializableExtra("USER_MAP") as UserMap
            println(userMap.title)
            viewModel.addMap(userMap)
            // Process the received data
        }
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MapViewModel::class.java)

        val mapAdpater = MapAdpater(emptyList(),object :MapAdpater.OnClickListener{
            override fun onItemClick(userMap: UserMap,position: Int) {
              //  println("ItemClicked : $position")
                val intent = Intent(this@MainActivity,DisplayMapActivity::class.java)
                intent.putExtra("USER_MAP",userMap)
                intent.putExtra("POSITION",position)
                startActivity(intent)
            }

        })
        binding.rvMapList.layoutManager = LinearLayoutManager(this)
        binding.rvMapList.adapter = mapAdpater

        binding.fabCreateMap.setOnClickListener{
            val addMapFormView = LayoutInflater.from(this).inflate(R.layout.dialog_create_place, null)
            val tempView = addMapFormView.findViewById<EditText>(R.id.etDescription)
            tempView.visibility = View.GONE
            val dialog = AlertDialog.Builder(this).setTitle("Create a Marker").setView(addMapFormView)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Ok", null)
                .show()
            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                val title = addMapFormView.findViewById<EditText>(R.id.etTitle).text.toString()
                if (title.trim().isEmpty()) {
                    return@setOnClickListener
                }
//                viewModel.addMap(UserMap(title, mutableListOf()))
                val intent = Intent(this@MainActivity,CreateMapActivity::class.java)
                intent.putExtra("MAP_TITLE",title)
                launcher.launch(intent)
                dialog.dismiss()

            }


            
        }

        viewModel.myData.observe(this, { list ->
            Log.d("UsmanCode","DAta Changed")
            mapAdpater.setList(list)
        })


    }
}