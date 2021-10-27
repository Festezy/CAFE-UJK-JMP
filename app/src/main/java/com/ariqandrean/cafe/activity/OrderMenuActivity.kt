package com.ariqandrean.cafe.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.ariqandrean.cafe.databinding.ActivityOrderMenuBinding
import com.ariqandrean.cafe.model.OrderMenuModel
import kotlinx.android.synthetic.main.activity_order_menu.*
import kotlinx.android.synthetic.main.list_item_view_order.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class OrderMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar: ActionBar? = supportActionBar
        actionBar!!.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.title = "PEMESANAN"

        val calendar = Calendar.getInstance()
        val currentDate: Date = Calendar.getInstance().time
        val currentDaydate = DateFormat.getDateInstance(DateFormat.FULL).format(currentDate)
        val currentTime = SimpleDateFormat("HH:mm:ss").format(calendar.time)

        val receivedName = intent.getStringExtra("EXTRA_MENU_NAME")
        val receivedDesc = intent.getStringExtra("EXTRA_desc_menu")
        val receivedHarga = intent.getStringExtra("EXTRA_MENU_PRICE")
        val accImg = intent.getStringExtra("EXTRA_img_menu")

        binding.apply {
            tvDayTimeOrderM.text = currentTime
            tvDayDateOrderM.text = currentDaydate

            if(accImg != null){
                imageView.setImageResource(accImg.toInt())
            }

            tvMenuNameOrderM.text = receivedName
            tvPriceOrderM.text = receivedHarga
            tvDetailOrderM.text = receivedDesc
            btnSaveOrderM.setOnClickListener {
                if (etTableNumberOrderM.text.isEmpty()) {
                    Toast.makeText(
                        this@OrderMenuActivity, "Enter No Bangku Order",
                        Toast.LENGTH_SHORT
                    ).show()
                    etTableNumberOrderM.requestFocus()
                } else {
                    val ordModel = OrderMenuModel()
                    ordModel.orderDate = binding.tvDayTimeOrderM.text.toString()
                    ordModel.orderTime = binding.tvDayDateOrderM.text.toString()
                    ordModel.orderMenuName = binding.tvMenuNameOrderM.text.toString()
                    ordModel.orderTableNumber = binding.etTableNumberOrderM.text.toString().toInt()
                    ordModel.orderMenuPrice = binding.tvPriceOrderM.text.toString().toInt()

                    MainActivity.dbHandler.addOrder(this@OrderMenuActivity, ordModel)
                    Intent(this@OrderMenuActivity, ViewOrderActivity::class.java).also {
                        startActivities(arrayOf(it))
                    }
                }
            }
//            btnOrderAgainM.setOnClickListener {
//                val moveHome = Intent(this@OrderMenuActivity,MainActivity::class.java)
//                startActivity(moveHome)
//            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}