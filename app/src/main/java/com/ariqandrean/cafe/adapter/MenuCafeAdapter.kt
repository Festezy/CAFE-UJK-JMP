package com.ariqandrean.cafe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.ariqandrean.cafe.R
import com.ariqandrean.cafe.model.MenuCafeModel

// Komponen View yang digunakan ListView
class MenuCafeAdapter(context: Context, listDaftarMenu: ArrayList<MenuCafeModel>) : BaseAdapter() {

    private val listDaftarMenu: ArrayList<MenuCafeModel> = listDaftarMenu
    //konversi xml (user interface) menjadi objek
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    //calculate file DaftarMenu yang berasal dari Menu_Cafe_Model
    override fun getCount(): Int {
        return listDaftarMenu.size
    }


    //mendapatkan value yang ada di Menu_Cafe_Model seperti nama,harga,desk dan photo
    override fun getItem(position: Int): Any {
        return listDaftarMenu[position]
    }

    //mendapatkan value berdasarkan Id dari masing masing posisi yang bertipe bilangan bulat
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //menampilkan data ke view
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ListDaftarMenu
        //cek kondisi xml yg telah di convert view kedalam objek
        if (convertView == null){
            view = this.mInflater.inflate(R.layout.list_item_menu,parent,false)//pemberian layout kepada objek layout
            viewHolder =
                ListDaftarMenu(view) // pemberian nilai ke ListDaftarMenu dengan objek xml dari layout list_item_menu dan value di tampung di viewHolder
            view.tag = viewHolder // jadi si objek bernama view sudah memiliki tampilan utuh yg masuk ke convertView alias converView tidak null lagi
        }
        else {
            view = convertView // seluruh isi xml alias views pada layout list_item_menu sudah dimiliki convertView dan memasuukkan ke objek bernama view
            viewHolder = view.tag as ListDaftarMenu
        }

        viewHolder.mnNama.text = listDaftarMenu[position].str_nama
        viewHolder.mnDesk.text = listDaftarMenu[position].str_des
        viewHolder.mnHarga.text = listDaftarMenu[position].int_harga.toString()
        viewHolder.mnImage.setImageResource(listDaftarMenu[position].int_image)
        return view
    }

}

private class ListDaftarMenu(row: View?) {
    val mnNama: TextView = row?.findViewById(R.id.tvMenuNameOrderM) as TextView
    val mnDesk: TextView = row?.findViewById(R.id.txtDeskripsi) as TextView
    val mnHarga: TextView = row?.findViewById(R.id.tvPriceOrderM) as TextView
    val mnImage: ImageView = row?.findViewById(R.id.imgMenu) as ImageView

}