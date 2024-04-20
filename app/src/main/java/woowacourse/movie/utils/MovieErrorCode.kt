package woowacourse.movie.utils

enum class MovieErrorCode(val code: Int, val message: String) {
    INVALID_MOVIE_ID(-1, "올바르지 않은 ID 입니다"),
}
