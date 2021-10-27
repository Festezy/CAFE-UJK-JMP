package com.ariqandrean.cafe.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.ariqandrean.cafe.activity.MainActivity.Companion.dbHandler
import com.ariqandrean.cafe.adapter.ViewOrderAdapter
import com.ariqandrean.cafe.databinding.ActivityViewOrderBinding

class ViewOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewOrderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar: ActionBar? = supportActionBar
        actionBar!!.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.title = "History"

        // Call viewOrder function
        viewOrder()
    }
    private fun viewOrder() {
        val vorderlist = dbHandler.getAllOrder(this)
        val adapter = ViewOrderAdapter(this, vorderlist)

        binding.apply {
            rvOrder.layoutManager = LinearLayoutManager(this@ViewOrderActivity, LinearLayoutManager.VERTICAL, false)
            rvOrder.adapter = adapter
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}