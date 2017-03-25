package de.ae.formulaecalendar.app.view.about

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.ae.formulaecalendar.R
import de.ae.formulaecalendar.app.resource.Resource

/**
 * Created by aeilers on 19.02.2017.
 */
class ResourceAdapter constructor(context: Context) : RecyclerView.Adapter<ResourceHolder>() {
    private val author = context.getString(R.string.about_author)
    private val license = context.getString(R.string.about_license)
    private val link = context.getString(R.string.about_link)

    private var resources: List<Resource>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.resource_card, parent, false)
        return ResourceHolder(itemView)
    }

    fun setResources(resources: List<Resource>) {
        this.resources = resources
    }

    override fun onBindViewHolder(holder: ResourceHolder, position: Int) {
        val r = resources?.get(position)

        if (r != null) {

            val id = r.id
            if (id != null && id != 0) {
                holder.image.setImageResource(id)
                holder.image.visibility = View.VISIBLE
            } else {
                holder.image.visibility = View.GONE
            }

            if (r.title != null) {
                holder.title.text = r.title
                holder.title.visibility = View.VISIBLE
            } else {
                holder.title.visibility = View.GONE
            }

            if (r.author != null) {
                holder.author.text = author + ": " + r.author
                holder.author.visibility = View.VISIBLE
            } else {
                holder.author.visibility = View.GONE
            }

            if (r.description != null) {
                holder.description.text = r.description
                holder.description.visibility = View.VISIBLE
            } else {
                holder.description.visibility = View.GONE
            }

            if (r.license != null) {
                holder.license.text = license + ": " + r.license
                holder.license.visibility = View.VISIBLE
            } else {
                holder.license.visibility = View.GONE
            }

            if (r.uri != null) {
                holder.uri.text = link + ": " + r.uri
                holder.uri.visibility = View.VISIBLE
            } else {
                holder.uri.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return resources?.size ?: 0
    }
}