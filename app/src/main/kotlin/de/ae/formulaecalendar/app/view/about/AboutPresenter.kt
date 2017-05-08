package de.ae.formulaecalendar.app.view.about

import de.ae.formulaecalendar.app.resource.LocalResourceStore
import de.ae.formulaecalendar.app.resource.ResourceStore

/**
 * Created by aeilers on 19.02.2017.
 */
class AboutPresenter constructor(val view: AboutView, val model: ResourceStore = LocalResourceStore) {

    fun loadContent() {
        view.setContent(model.getResources())
    }

}