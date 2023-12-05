package project.wgt.capitalviewer.network.model

data class Currency(
    val code: String,
    val name: String,
    val symbol: String,
)

data class Language(
    val code: String,
    val name: String,
)

data class CountryResponse(
    val capital: String,
    val code: String,
    val currency: Currency,
    val flag: String,
    val language: Language,
    val name: String,
    val region: String,
)
