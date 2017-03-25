package de.ae.formulaecalendar.app.view.about

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.app.resource.Resource
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.toolbar.*

class AboutActivity : AppCompatActivity(), AboutView {
    private val presenter = AboutPresenter(this)

    private var adapter: ResourceAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        setSupportActionBar(toolbar_view)
        supportActionBar?.setTitle(getString(R.string.activity_about))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //set adapter for recycler view
        adapter = ResourceAdapter(this)
        about_cardList.setHasFixedSize(true)
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        about_cardList.setLayoutManager(llm)
        about_cardList.setAdapter(adapter)

        //set Presenter
        presenter.loadContent()
    }

    override fun setContent(resources: List<Resource>) {
        adapter?.setResources(resources)
        adapter?.notifyDataSetChanged()
    }
}
