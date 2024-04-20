package woowacourse.movie.view

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieAdapter
import woowacourse.movie.presenter.MovieMainContract
import woowacourse.movie.presenter.MovieMainPresenter

class MovieMainActivity : AppCompatActivity(), MovieMainContract.View {
    private lateinit var movieList: ListView

    private lateinit var movieMainPresenter: MovieMainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_main)

        movieMainPresenter = MovieMainPresenter(this)
        movieMainPresenter.loadMovies()
    }

    override fun displayMovies(movies: List<Movie>) {
        movieList = findViewById(R.id.mainList)
        movieList.adapter = MovieAdapter(movies)
    }
}
