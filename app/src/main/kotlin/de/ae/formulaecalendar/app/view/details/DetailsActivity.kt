package de.ae.formulaecalendar.app.view.details

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.transition.Fade
import android.view.View
import android.view.WindowManager
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.formulaerest.pojo.calendar.CalendarDatum
import de.ae.formulaecalendar.formulaerest.pojo.race.SesRace
import kotlinx.android.synthetic.main.activity_details.*

/**
 * Created by aeilers on 17.02.2017.
 */
class DetailsActivity constructor() : AppCompatActivity(), DetailsView {
    companion object {
        val EXTRA_RACE = "race"
        val ANIMATION = "city"
    }

    private var presenter: DetailsPresenter? = null

    private var adapter: ResultsAdapter? = null
    private var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        //set toolbar
        setSupportActionBar(toolbar_view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val fade = Fade()
            fade.excludeTarget(toolbar_view, true)
            fade.excludeTarget(android.R.id.statusBarBackground, true)
            fade.excludeTarget(android.R.id.navigationBarBackground, true)

            window.enterTransition = fade
            window.exitTransition = fade
        }

        //set results adapter
        adapter = ResultsAdapter()
        val llm = LinearLayoutManager(this.getContext())
        llm.orientation = LinearLayoutManager.VERTICAL
        details_recycler_results.layoutManager = llm
        details_recycler_results.adapter = adapter
        details_recycler_results.isNestedScrollingEnabled = false

        //Transparent Action Bar for >API21
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }

        //get Extra
        val race = intent.getSerializableExtra("race") as CalendarDatum?

        presenter = DetailsPresenter(this)
        presenter?.loadData(race)

    }

    /**
     * Parameter needs to be set
     */
    @Suppress("UNUSED_PARAMETER")
    fun openMap(view: View) {
        presenter?.openMap()
    }

    override fun getContext(): Context {
        return this
    }

    override fun setImage(resourceId: Int) {
        details_image_head.setImageResource(resourceId)
    }

    override fun setTitle(title: String) {
        details_collapsing_toolbar.title = title
    }

    override fun setRaceLoadingVisibility(visible: Boolean) {
        if (visible) {
            details_progress_bar.visibility = View.VISIBLE
        } else {
            details_progress_bar.visibility = View.GONE
        }
    }

    override fun setMainViewVisibility(visible: Boolean) {
        if (visible) {
            details_scroll.visibility = View.VISIBLE
        } else {
            // View doesn't become visible again after setting it to gone
            //this.scrollView.setVisibility(View.GONE);
            details_scroll.visibility = View.INVISIBLE
        }
    }

    override fun setRaceSnackbarVisibility(visible: Boolean) {
        if (visible) {
            snackbar = Snackbar.make(details_recycler_results, R.string.connection_fault, Snackbar.LENGTH_INDEFINITE)
            snackbar?.setAction(R.string.snackbar_retry, View.OnClickListener { presenter?.loadData(null) })
                    ?.show()
        } else if (snackbar != null) {
            snackbar?.dismiss()
            snackbar = null
        }
    }

    override fun setRound(round: String) {
        details_text_round.text = round
    }


    override fun setDate(date: String) {
        details_text_date.text = date
    }

    override fun setDistance(distance: String) {
        details_text_distance.text = distance
    }

    override fun setLaps(laps: String) {
        details_text_laps.text = laps
    }

    override fun setTurns(turns: String) {
        details_text_turns.text = turns
    }

    override fun setMap(map: String) {
        details_text_map.text = map
    }

    override fun setNoResultsVisibility(visible: Boolean) {
        if (visible) {
            details_text_results.visibility = View.VISIBLE
        } else {
            details_text_results.visibility = View.GONE
        }
    }

    override fun setResultsLoadingVisibility(visible: Boolean) {
        if (visible) {
            details_results_progress_bar.visibility = View.VISIBLE
        } else {
            details_results_progress_bar.visibility = View.GONE
        }
    }

    override fun setResultsVisibility(visible: Boolean) {
        if (visible) {
            details_recycler_results.visibility = View.VISIBLE
        } else {
            // GONE doesn't become visible again in some cases (?)
            details_recycler_results.visibility = View.INVISIBLE
        }
    }

    override fun setResultsSnackbarVisibility(visible: Boolean) {
        if (visible) {
            snackbar = Snackbar.make(details_recycler_results, R.string.connection_fault, Snackbar.LENGTH_INDEFINITE)
            snackbar?.setAction(R.string.snackbar_retry, { presenter?.loadResults() })
                    ?.show()
        } else if (snackbar != null) {
            snackbar?.dismiss()
            snackbar = null
        }
    }

    override fun setResults(results: List<SesRace>) {
        adapter?.setResults(results)
        adapter?.notifyDataSetChanged()
    }
}