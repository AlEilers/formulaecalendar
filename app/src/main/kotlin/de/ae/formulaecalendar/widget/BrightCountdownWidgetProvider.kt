package de.ae.formulaecalendar.widget

import de.ae.formulaecalendar.R

/**
 * Created by alexa on 18.02.2017.
 */
class BrightCountdownWidgetProvider : CountdownWidgetProvider(R.layout.widget_countdown_bright) {
    private var presenter: CountdownWidgetPresenter = CountdownWidgetPresenter(this)


}