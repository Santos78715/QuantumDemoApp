package Retrofit

data class ModelClass(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)