package de.ae.formulaecalendar.view.about

import de.ae.formulaecalendar.resource.LocalResourceStore
import de.ae.formulaecalendar.resource.ResourceStore

/**
 * Created by aeilers on 19.02.2017.
 */
class AboutPresenter constructor(val view: AboutView, val model: ResourceStore) {

    constructor(view: AboutView) : this(view, LocalResourceStore)

    internal fun loadContent() {
        view.setContent(model.getResources())
    }

}