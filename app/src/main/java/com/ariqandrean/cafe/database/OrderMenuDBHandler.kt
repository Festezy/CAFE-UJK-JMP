package com.ariqandrean.cafe.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.ariqandrean.cafe.model.OrderMenuModel
import java.lang.Exception

 // handler
// data permanent from db - control
class OrderMenuDBHandler(
    context: Context,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context,
     DATABASE_NAME, factory,
     DATABASE_VERSION
 ) {

    companion object {
        private val DATABASE_NAME = "CafeGaul.db"
        private val DATABASE_VERSION = 1

        val TABLE_NAME = "tblOrderMenu"
        private val colId = "Id"
        private val colTgl = "Tanggal"
        private val colJam = "Jam"
        private val colNamaMenu = "NamaMenu"
        private val colJmlOrder = "JmlOrder"
        private val colHargaMenu = "HargaMenu"
    }

    //menciptakan db dan table
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE: String = ("CREATE TABLE $TABLE_NAME (" +
                "$colId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$colTgl TEXT," +
                "$colJam TEXT," +
                "$colNamaMenu TEXT," +
                "$colJmlOrder INTEGER," +
                "$colHargaMenu INTEGER)")
        db?.execSQL(CREATE_TABLE)
    }

    //upgrade db
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        //T ODO("Not yet implemented")
    }

    fun addOrder(mCtx: Context, OrderModel: OrderMenuModel) {
        val Data_values = ContentValues()
        Data_values.put(colTgl, OrderModel.orderDate)
        Data_values.put(colJam, OrderModel.orderTime)
        Data_values.put(colNamaMenu, OrderModel.orderMenuName)
        Data_values.put(colJmlOrder, OrderModel.orderTableNumber)
        Data_values.put(colHargaMenu, OrderModel.orderMenuPrice)
        val db = this.writableDatabase
        try {
            db.insert(TABLE_NAME, null, Data_values)
            Toast.makeText(mCtx, "Pesanan terkirim", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(mCtx, e.message, Toast.LENGTH_SHORT).show()
        }
        db.close()
    }

    @SuppressLint("Range")
    fun getAllOrder(mCtx: Context): ArrayList<OrderMenuModel> {
        val query = "Select * from $TABLE_NAME"
        val db = this.readableDatabase // menampilkan
        val dataorder = db.rawQuery(query, null)
        val rec_order = ArrayList<OrderMenuModel>()
        if (dataorder.count == 0)
            Toast.makeText(
                mCtx, "Data tidak ada",
                Toast.LENGTH_SHORT
            ).show()
        else {
            dataorder.moveToFirst()
            while (!dataorder.isAfterLast()) { // jka data terakhir dan habis maka eksekusi
                val Order = OrderMenuModel()
                Order.orderId = dataorder.getInt(dataorder.getColumnIndex(colId))
                Order.orderDate = dataorder.getString(dataorder.getColumnIndex(colTgl))
                Order.orderTime = dataorder.getString(dataorder.getColumnIndex(colJam))
                Order.orderMenuName = dataorder.getString(dataorder.getColumnIndex(colNamaMenu))
                Order.orderTableNumber = dataorder.getInt(dataorder.getColumnIndex(colJmlOrder))
                Order.orderMenuPrice = dataorder.getInt(dataorder.getColumnIndex(colHargaMenu))
               // Order.str_totalharga = Order.str_noBangku * Order.str_hrgMenu
                Order.str_totalharga = Order.orderMenuPrice
                rec_order.add(Order)
                dataorder.moveToNext()

            }
            Toast.makeText(mCtx,"${dataorder.count.toString()} pesanan ditemukan",
            Toast.LENGTH_SHORT).show()
        }
        dataorder.close()
        db.close()
        return rec_order
    }

    //---
    fun deleteOrder(OrderID : Int) : Boolean{
        val query = "Delete from $TABLE_NAME where $colId = $OrderID"
        val db = this.writableDatabase
        var result : Boolean = false
        try{
            val cursor = db.execSQL(query)
            result = true
        }catch (E : Exception){
            Log.e(ContentValues.TAG,"Terjadi kesalahan penghapusan")
        }
        db.close()
        return result
    }

}