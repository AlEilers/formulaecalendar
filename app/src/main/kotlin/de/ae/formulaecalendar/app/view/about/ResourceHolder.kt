package de.ae.formulaecalendar.app.view.about

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.resource_card.view.*

/**
 * Created by aeilers on 19.02.2017.
 */
class ResourceHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image = itemView.card_about_image
    val title = itemView.card_about_title
    val author = itemView.card_about_author
    val description = itemView.card_about_description
    val license = itemView.card_about_license
    val uri = itemView.card_about_uri
}