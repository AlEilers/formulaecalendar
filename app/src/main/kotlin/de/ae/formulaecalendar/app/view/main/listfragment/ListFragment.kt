package de.ae.formulaecalendar.app.view.main.listfragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.app.view.main.sharedvalues.SharedViewModel

/**
 * Created by aeilers on 17.02.2017.
 *
 * R: DataType
 * T: ListAdapter<RecyclerViewHolder<DataType>>
 */
abstract class ListFragment<S, U : RecyclerView.ViewHolder, T : ListAdapter<S, U>> : Fragment(), ListView<S> {

    private lateinit var sharedViewModel: SharedViewModel
    protected abstract val presenter: ListPresenter

    //---------Create View-------------------
    protected var contentList: RecyclerView? = null
    protected var loadingView: View? = null
    protected var adapter: T? = null

    abstract val layout: Int
    abstract val recyclerViewId: Int
    abstract val loadingViewId: Int
    abstract fun getRecyclerViewAdapter(context: Context): T
    //abstract fun createPresenter(): ListPresenter

    /**
     * create presenter
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // get SharedViewModel
        this.activity?.let {
            sharedViewModel = ViewModelProviders.of(it).get(SharedViewModel::class.java)
            sharedViewModel.getSelectedSeason().observe(this, Observer { presenter.loadContent(it) })
        }

    }

    /**
     * create view, set recycler view adapter and load content
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layout, container, false)
        contentList = view.findViewById(recyclerViewId)
        loadingView = view.findViewById(loadingViewId)

        //set adapter for recycler view
        context?.let {
            adapter = getRecyclerViewAdapter(it)
            val llm = LinearLayoutManager(this.context)
            llm.orientation = LinearLayoutManager.VERTICAL
            contentList?.layoutManager = llm
            contentList?.adapter = adapter
        }

        // drastically increase performance of scrolling in recycler view
        contentList?.setHasFixedSize(true)
        contentList?.setItemViewCacheSize(20)
        contentList?.isDrawingCacheEnabled = true
        contentList?.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH

        //load content by presenter
        presenter.loadContent(sharedViewModel.getSelectedSeason().value)

        return view
    }

    //---------Destroy View-------------------

    /**
     * free references
     */
    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
        snackbar = null
        contentList = null
        loadingView = null
    }

    /**
     * free presenter
     */
    override fun onDestroy() {
        super.onDestroy()
    }

    //---------Set Content-----------

    /**
     * set content for ListAdapter
     */
    override fun setContent(content: S) {
        adapter?.setContent(content)
        adapter?.notifyDataSetChanged()
    }

    /**
     * show/hide loading view
     */
    override fun setLoadingViewVisibility(visible: Boolean) {
        if (visible) loadingView?.visibility = View.VISIBLE
        else loadingView?.visibility = View.INVISIBLE
    }

    /**
     * show/hide recycler view
     */
    override fun setRecyclerViewVisibility(visible: Boolean) {
        if (visible) contentList?.visibility = View.VISIBLE
        else contentList?.visibility = View.INVISIBLE
    }

    //---------Snackbar-----------
    private var snackbar: Snackbar? = null
    private var snackbarVisible = false
    private var fragmentIsVisible = false

    abstract var snackbarNotification: Int

    /**
     * save whether this fragment is visible
     * and show snackbar if necessary
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        fragmentIsVisible = isVisibleToUser

        if (snackbarVisible && fragmentIsVisible) {
            this.createSnackbar()
        } else {
            this.dismissSnackbar()
        }
    }

    /**
     * show snackbar with given notification
     */
    override fun showSnackbar(notification: Int) {
        snackbarVisible = true
        snackbarNotification = notification
        if (snackbarVisible && fragmentIsVisible) {
            createSnackbar()
        }
    }

    /**
     * create snackbar with text from snackbarNotification
     * and and action button to reload
     */
    private fun createSnackbar() {
        contentList?.let {
            snackbar = Snackbar.make(it, snackbarNotification, Snackbar.LENGTH_INDEFINITE)
            snackbar?.setAction(R.string.snackbar_retry, { presenter.loadContent(sharedViewModel.getSelectedSeason().value) })
            snackbar?.show()
        }
    }

    /**
     * hide snackbar
     */
    override fun hideSnackbar() {
        snackbarVisible = false
        this.dismissSnackbar()
    }

    /**
     * destroy snackbar
     */
    private fun dismissSnackbar() {
        snackbar?.dismiss()
    }
}