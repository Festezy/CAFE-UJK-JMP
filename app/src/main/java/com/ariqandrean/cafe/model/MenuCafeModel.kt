package com.ariqandrean.cafe.model

class MenuCafeModel {
    var str_nama: String = ""
    var str_des: String = ""
    var int_harga: Int = 0
    var int_image: Int = 0
    constructor(cstr_nama:String,cstr_des: String,cint_harga: Int,cint_image : Int)
    {
        this.str_nama  = cstr_nama
        this.str_des   = cstr_des
        this.int_harga = cint_harga
        this.int_image = cint_image
    }
}