package project.wgt.capitalviewer.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryItem(
    val name: String,
    val region: String,
    val code: String,
    val capital: String,
): Parcelable
