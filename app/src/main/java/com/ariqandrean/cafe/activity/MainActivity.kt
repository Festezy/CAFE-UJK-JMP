package com.ariqandrean.cafe.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ariqandrean.cafe.R
import com.ariqandrean.cafe.adapter.MenuCafeAdapter
import com.ariqandrean.cafe.database.OrderMenuDBHandler
import com.ariqandrean.cafe.databinding.ActivityMainBinding
import com.ariqandrean.cafe.model.MenuCafeModel

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var dbHandler: OrderMenuDBHandler
    }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHandler = OrderMenuDBHandler(this, null, null, 1)
        val menuCafe = ArrayList<MenuCafeModel>()
        menuCafe.add(
            MenuCafeModel(
                "Kopi Hitam",
                "Kopi hitam dibuat dengan teknik esspresso, dimana biji kopi yang digunakan berasal dari Aceh Gayo jenis Arabika, disajikan dengan gula terpisah.",
                10000,
                R.drawable.kopihitam
            )
        )

        menuCafe.add(
            MenuCafeModel(
                "Cappucino",
                "Paduan kopi dengan buih susu kental serta menggunakan sirup karamel, dimana biji kopi yang digunakan berasal dari Gunung Puntang Jawa Barat jenis Robusta.",
                20000,
                R.drawable.cappucino
            )
        )

        menuCafe.add(
            MenuCafeModel(
                "Sparkling Tea",
                "Minuman teh yang menggunakan daun teh dari pegunungan daerah garut dengan tambahan sari melati asli dan gula merah alami.",
                15000,
                R.drawable.sparklingtea
            )
        )

        menuCafe.add(
            MenuCafeModel(
                "Batagor",
                "Baso dan tahu goreng dengan sajian bumbu kacang dan kecap khas Bandung.",
                25000,
                R.drawable.batagor
            )
        )

        menuCafe.add(
            MenuCafeModel(
                "Cireng",
                "Makanan ringan berupa tepung kanji goreng isi bahan dasar tempe fermentasi yang disebut oncom, disajikan dengan bumbu kacang pedas.",
                10000,
                R.drawable.cireng
            )
        )

        menuCafe.add(
            MenuCafeModel(
                "Nasi Goreng",
                "Nasi goreng ayam yang disajikan dengan telur mata sapi disertai satai ayam.",
                30000,
                R.drawable.nasigoreng
            )
        )

        menuCafe.add(
            MenuCafeModel(
                "Cheese Cake",
                "Kue Tart 1 slice dengan bahan utama cream cheese dengan topping buah-buahan asli seperti Anggur, Jeruk dan Kiwi.",
                40000,
                R.drawable.chessecake
            )
        )

        menuCafe.add(
            MenuCafeModel(
                "Black Salad",
                "Potongan buah-buah segar yang terdiri dari Pepaya, Jambu, Melon dan Mangga disajikan dengan bumbu rujak kacang pedas dan gula merah.",
                20000,
                R.drawable.blacksalad
            )
        )
        binding.apply {
            btnHistory.setOnClickListener {
                Intent(this@MainActivity, ViewOrderActivity::class.java).also {
                    startActivities(arrayOf(it))
                }
            }

            val objekAdapter = MenuCafeAdapter(applicationContext, menuCafe) // deklarasi objek adapter // pengisian data ke adapter
            ListMenuCafe.adapter = objekAdapter // menampilkan data dari adapter ke view
            ListMenuCafe.setOnItemClickListener { _, _, position, _ ->
                //intent ke Order_Menu.kt
                Intent(applicationContext, OrderMenuActivity::class.java).also { intent ->
                    intent.putExtra("EXTRA_MENU_NAME", menuCafe[position].str_nama).toString()
                    intent.putExtra("EXTRA_MENU_PRICE", menuCafe[position].int_harga.toString())
                    intent.putExtra("EXTRA_img_menu", menuCafe[position].int_image.toString())
                    intent.putExtra("EXTRA_desc_menu", menuCafe[position].str_des)
                    startActivity(intent)
                }
            }
        }
    }
}