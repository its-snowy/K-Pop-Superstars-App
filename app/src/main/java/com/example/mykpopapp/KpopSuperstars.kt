package com.example.mykpopapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class KpopSuperstars(
    val name: String,
    val description: String,
    val photo: Int,
    val debutYear: Int,
    val fanClubName: String,
    val genre: String
) : Parcelable