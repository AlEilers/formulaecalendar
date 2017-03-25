package de.ae.formulaecalendar.app.widget

import de.ae.formulaecalendar.R

/**
 * Created by aeilers on 18.02.2017.
 */
class BrightCountdownWidgetProvider : CountdownWidgetProvider(R.layout.widget_countdown_bright) {
    private var presenter: CountdownWidgetPresenter = CountdownWidgetPresenter(this)


}