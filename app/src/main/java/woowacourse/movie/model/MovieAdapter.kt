package woowacourse.movie.model

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import woowacourse.movie.R
import woowacourse.movie.utils.formatTimestamp
import woowacourse.movie.view.MovieDetailActivity

class MovieAdapter(
    private val movies: List<Movie>,
    private val movieDetailActivityResultLauncher: ActivityResultLauncher<Intent>,
) : BaseAdapter() {
    override fun getCount(): Int {
        return movies.size
    }

    override fun getItem(position: Int): Any {
        return movies[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        var view = convertView
        val holder: MovieViewHolder

        if (view == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, parent, false)
            holder = MovieViewHolder(view)
            view.tag = holder
        } else {
            holder = view.tag as MovieViewHolder
        }

        val movie = movies[position]
        holder.bind(view, movie, movieDetailActivityResultLauncher)
        return view!!
    }
}

class MovieViewHolder(itemView: View) {
    private val thumbnail: ImageView = itemView.findViewById(R.id.movieThumbnail)
    private val title: TextView = itemView.findViewById(R.id.movieTitle)
    private val date: TextView = itemView.findViewById(R.id.movieDate)
    private val runningTime: TextView = itemView.findViewById(R.id.movieRunningTime)
    private val reservation: Button = itemView.findViewById(R.id.movieReservation)

    fun bind(
        view: View?,
        movie: Movie,
        movieDetailActivityResultLauncher: ActivityResultLauncher<Intent>,
    ) {
        thumbnail.setImageResource(movie.thumbnail)
        title.text = movie.title
        date.text = formatTimestamp(movie.date)
        runningTime.text = "${movie.runningTime}"
        reservation.setOnClickListener {
            Intent(view?.context, MovieDetailActivity::class.java).apply {
                putExtra("movieId", movie.id)
                movieDetailActivityResultLauncher.launch(this)
            }
        }
    }
}
