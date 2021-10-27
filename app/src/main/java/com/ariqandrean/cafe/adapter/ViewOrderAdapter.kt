package com.ariqandrean.cafe.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ariqandrean.cafe.R
import com.ariqandrean.cafe.activity.MainActivity
import com.ariqandrean.cafe.databinding.ListItemViewOrderBinding
import com.ariqandrean.cafe.model.OrderMenuModel

class ViewOrderAdapter(val context: Context, val viewOrderList: ArrayList<OrderMenuModel>)
    : RecyclerView.Adapter<ViewOrderAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ListItemViewOrderBinding) : RecyclerView.ViewHolder(binding.root) {
        val menuName = binding.tvMenuName
        val price = binding.tvPriceOrderM
        val tableNumber = binding.tvTableNumber
        val dayDate = binding.tvDayDate
        val dayTime = binding.tvDayTime
        val deleteButton = binding.ibDelete
    }

    //
    override fun getItemCount(): Int {
        return viewOrderList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListItemViewOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(p0: ViewHolder, position: Int) {
        val orderan: OrderMenuModel = viewOrderList[position]

        p0.menuName.text = orderan.orderMenuName
        p0.price.text = orderan.orderMenuPrice.toString()
        p0.tableNumber.text = orderan.orderTableNumber.toString()
        p0.dayTime.text = orderan.orderTime
        p0.dayDate.text = orderan.orderDate
        p0.deleteButton.setImageResource(R.drawable.ic_baseline_delete_24)

        p0.deleteButton.setOnClickListener {
            val menunama = orderan.orderMenuName
            var alertDialog = AlertDialog.Builder(context)
                .setTitle("Delete")
                .setMessage("Yakin dihapus data : $menunama ? ")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->
                    if (MainActivity.dbHandler.deleteOrder(orderan.orderId)) {
                        viewOrderList.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, viewOrderList.size)
                        Toast.makeText(context, "Barang $menunama dihapus", Toast.LENGTH_SHORT).show()
                    }else
                        Toast.makeText(context,"Terjadi kesalahan penghapusan",
                        Toast.LENGTH_SHORT).show()

                })
                .setNegativeButton("No", DialogInterface.OnClickListener { _, _ ->  })
                .setIcon(R.drawable.ic_baseline_warning_24)
                .show()
        }
    }
}