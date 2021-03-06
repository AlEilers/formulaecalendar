package de.ae.formulaecalendar.app.resource

import de.ae.formulaecalendar.app.BuildConfig
import de.ae.formulaecalendar.app.R
import java.util.*

/**
 * Created by aeilers on 18.02.2017.
 */
object LocalResourceStore : ResourceStore {
    private val resources = ArrayList<Resource>()

    init {
        resources.add(Resource().apply {
            name = "app"
            title = "Formula E Calendar"
            author = "Alexander Eilers"
            description = "Version: " + BuildConfig.VERSION_NAME
            uri = "https://github.com/AlEilers/formulaecalendar"
        })

        resources.add(Resource().apply {
            name = "rxjava"
            title = "RxJava: Reactive Extensions for the JVM "
            description = "RxJava is used in this app"
            license = "Copyright 2013 Netflix, Inc.\n" +
                    "\n" +
                    "Licensed under the Apache License, Version 2.0 (the \"License\"); " +
                    "you may not use this file except in compliance with the License. " +
                    "You may obtain a copy of the License at\n" +
                    "\n" +
                    "http://www.apache.org/licenses/LICENSE-2.0\n" +
                    "\n" +
                    "Unless required by applicable law or agreed to in writing, software " +
                    "distributed under the License is distributed on an \"AS IS\" BASIS, " +
                    "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. " +
                    "See the License for the specific language governing permissions and " +
                    "limitations under the License."
            uri = "https://github.com/ReactiveX/RxJava"
        })

        resources.add(Resource().apply {
            name = "rxandroid"
            title = "RxAndroid: Reactive Extensions for Android"
            description = "RxAndroid is used in this app"
            license = "Copyright 2015 The RxAndroid authors\n" +
                    "\n" +
                    "Licensed under the Apache License, Version 2.0 (the \"License\"); " +
                    "you may not use this file except in compliance with the License. " +
                    "You may obtain a copy of the License at\n" +
                    "\n" +
                    "http://www.apache.org/licenses/LICENSE-2.0\n" +
                    "\n" +
                    "Unless required by applicable law or agreed to in writing, software " +
                    "distributed under the License is distributed on an \"AS IS\" BASIS, " +
                    "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. " +
                    "See the License for the specific language governing permissions and " +
                    "limitations under the License."
            uri = "https://github.com/ReactiveX/RxAndroid"
        })

        resources.add(Resource().apply {
            name = "retrofit"
            title = "Retrofit"
            description = "Retrofit is used in this app"
            license = "Copyright 2013 Square, Inc.\n" +
                    "\n" +
                    "Licensed under the Apache License, Version 2.0 (the \"License\"); " +
                    "you may not use this file except in compliance with the License. " +
                    "You may obtain a copy of the License at\n" +
                    "\n" +
                    "http://www.apache.org/licenses/LICENSE-2.0\n" +
                    "\n" +
                    "Unless required by applicable law or agreed to in writing, software " +
                    "distributed under the License is distributed on an \"AS IS\" BASIS, " +
                    "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. " +
                    "See the License for the specific language governing permissions and " +
                    "limitations under the License."
            uri = "https://github.com/square/retrofit"
        })

        resources.add(Resource().apply {
            name = "gson"
            title = "google-gson"
            description = "gson is used in this app"
            license = "Copyright 2008 Google Inc.\n" +
                    "\n" +
                    "Licensed under the Apache License, Version 2.0 (the \"License\"); " +
                    "you may not use this file except in compliance with the License. " +
                    "You may obtain a copy of the License at\n" +
                    "\n" +
                    "http://www.apache.org/licenses/LICENSE-2.0\n" +
                    "\n" +
                    "Unless required by applicable law or agreed to in writing, software " +
                    "distributed under the License is distributed on an \"AS IS\" BASIS, " +
                    "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. " +
                    "See the License for the specific language governing permissions and " +
                    "limitations under the License."
            uri = "https://github.com/google/gson"
        })

        resources.add(Resource().apply {
            name = "2016020100" //Hong Kong
            title = "Overlook Hong Kong Island north coast, Victoria Harbour and Kowloon from " +
                    "middle section of Lugard Road at daytime (enlarged version and better " +
                    "contrast, revised)"
            author = "Exploringlife"
            license = "Creative Commons Attribution-Share Alike 4.0 International " +
                    "(https://goo.gl/gtCYbm)"
            uri = "https://goo.gl/t2f2Bp"
            id = R.drawable.hongkong
        })

        resources.add(Resource().apply {
            name = "2016700700" //Marrakesh
            title = "Djemaa el Fna"
            author = "Boris Macek"
            license = "Creative Commons Attribution 3.0 Unported (https://goo.gl/MUaj05)"
            uri = "https://goo.gl/XVyCm5"
            id = R.drawable.marrakesch
        })

        resources.add(Resource().apply {
            name = "2014020500,2014020501" //Buenos Aires
            title = "Buenos Aires Cityline at Night - Irargerich"
            author = "Luis Argerich from Buenos Aires, Argentina"
            license = "Creative Commons Attribution 2.0 Generic (https://goo.gl/oWqhth)"
            uri = "https://goo.gl/QJrvIj"
            id = R.drawable.buenosaires
        })

        resources.add(Resource().apply {
            name = "2015020500,2016020400" //Mexico City
            title = "Ciudad.de.Mexico.City.Distrito.Federal.DF.Paseo.Reforma.Skyline"
            author = "Alejandro Islas Photograph AC"
            license = "Creative Commons Attribution 2.0 Generic (https://goo.gl/oWqhth)"
            uri = "https://goo.gl/ZgEix1"
            id = R.drawable.mexicocity
        })

        resources.add(Resource().apply {
            name = "2014020300," //Monaco
            title = "Monaco City 001"
            author = "Mайкл Гиммельфарб"
            license = "public domain (https://goo.gl/jCNNg4)"
            uri = "https://goo.gl/iqudLl"
            id = R.drawable.monaco
        })

        resources.add(Resource().apply {
            name = "2015020700" //Paris
            title = "Paris vue d'ensemble tour Eiffel"
            author = "Taxiarchos228, cropped and modified by Poke2001"
            license = "Creative Commons Attribution 3.0 Unported (https://goo.gl/MUaj05)"
            uri = "https://goo.gl/tIlDro"
            id = R.drawable.paris
        })

        resources.add(Resource().apply {
            name = "2014020900,2015020800,2017020900" //Berlin
            title = "Cityscape Berlin"
            author = "Thomas Wolf, www.foto-tw.de"
            license = "Creative Commons Attribution 3.0 Unported (https://goo.gl/MUaj05)"
            uri = "https://goo.gl/jC2a6m"
            id = R.drawable.berlin
        })

        resources.add(Resource().apply {
            name = "2016020700" //Brussels
            title = "Brussels floral carpet B"
            author = "Wouter Hagens"
            license = "Creative Commons Attribution-Share Alike 3.0 Unported " +
                    "(https://goo.gl/MUaj05), 2.5 Generic (https://goo.gl/zTl7KY), 2.0 Generic " +
                    "(https://goo.gl/oWqhth) and 1.0 Generic (https://goo.gl/rHx4rD)"
            uri = "https://goo.gl/dmoSFA"
            id = R.drawable.brussels
        })

        resources.add(Resource().apply {
            name = "2016020900" //New York
            title = "Great view of the New York City (NYC) skyline with a few high buildings and " +
                    "sun in the background."
            license = "CC0 License (https://goo.gl/r5bRDY)"
            uri = "https://goo.gl/5UTGF5"
            id = R.drawable.newyork
        })

        resources.add(Resource().apply {
            name = "2016021101" //Montreal
            title = "Marché Bonsecours and Foliage"
            author = "AnnaKucsma"
            license = "Creative Commons Attribution-Share Alike 2.5 Generic (https://goo.gl/0Y8BTA)"
            uri = "https://goo.gl/ZnztrI"
            id = R.drawable.montreal
        })

        resources.add(Resource().apply {
            name = "2014020200" //Putrajaya
            title = "Putrajaya 4064188579 ec6a5c7efc"
            author = "FlickreviewR"
            license = "Creative Commons Attribution 2.0 Generic (https://goo.gl/oWqhth)"
            uri = "https://goo.gl/qmdbMG"
            id = R.drawable.putrajaya
        })

        resources.add(Resource().apply {
            name = "2014020400,2014020401" //Punta del Este
            title = "Punta del Este Skyline Aerial Photography-3"
            author = "Santga"
            license = "Creative Commons Attribution 2.0 Generic (https://goo.gl/oWqhth)"
            uri = "https://goo.gl/vTrsrj"
            id = R.drawable.punta
        })

        resources.add(Resource().apply {
            name = "2014020700" //Miami
            title = "Miami from above"
            author = "Magnus Manske"
            license = "Creative Commons Attribution 2.0 Generic (https://goo.gl/oWqhth)"
            uri = "https://goo.gl/4rCcfb"
            id = R.drawable.miami
        })

        resources.add(Resource().apply {
            name = "2014020600" //Long Beach
            title = "Long Beach CA Photo D Ramey Logan"
            author = "WPPilot"
            license = "Creative Commons Attribution-Share Alike 3.0 Unported (https://goo.gl/MUaj05)"
            uri = "https://goo.gl/tCG9pM"
            id = R.drawable.longbeach
        })

        resources.add(Resource().apply {
            name = "2014020800" //Moscow
            title = "Moscow July 2011-16"
            author = "Alvesgaspar"
            license = "Creative Commons Attribution-Share Alike 3.0 Unported (https://goo.gl/MUaj05)"
            uri = "https://goo.gl/NfEGtt"
            id = R.drawable.moscow
        })

        resources.add(Resource().apply {
            name = "2014021000" //London
            title = "London Thames Sunset panorama - Feb 2008"
            author = "Diliff"
            license = "Creative Commons Attribution 3.0 Unported (https://goo.gl/MUaj05)"
            uri = "https://goo.gl/NqRA2M"
            id = R.drawable.london
        })

        resources.add(Resource().apply {
            name = "2014020100,2015020100" //Beijing
            title = "Beijing national stadium"
            author = "Peter23"
            license = "Creative Commons Attribution-Share Alike 3.0 Unported (https://goo.gl/MUaj05)"
            uri = "https://goo.gl/ccVxV2"
            id = R.drawable.beijing
        })

        resources.add(Resource().apply {
            name = "2017020400,2018020300" //Santiago de chile
            title = "Santiago de Chile de noche"
            author = "Javmoraga"
            license = "Creative Commons Attribution-Share Alike 3.0 Unported (https://goo.gl/MUaj05)"
            uri = "https://goo.gl/7SzFyg"
            id = R.drawable.chile
        })

        resources.add(Resource().apply {
            name = "2017020600" //Sao Paulo
            title = "Sao Paulo city (Bela Vista)"
            author = "Chronus "
            license = "Creative Commons Attribution 2.0 Generic (https://goo.gl/oWqhth)"
            uri = "https://goo.gl/3Ljjo7"
            id = R.drawable.saopaulo
        })

        resources.add(Resource().apply {
            name = "2017020700" //Rome
            title = "Rome Skyline (8012016319)"
            author = "Xosema"
            license = "Creative Commons Attribution-Share Alike 2.0 Generic (https://goo.gl/Y3cRtp)"
            uri = "https://goo.gl/JJNhcR"
            id = R.drawable.rome
        })

        resources.add(Resource().apply {
            name = "2017021000" //Zurich
            title = "Utliberg Zurich"
            author = "Man Ho Lam"
            license = "Creative Commons Attribution-Share Alike 3.0 Unported (https://goo.gl/MUaj05)"
            uri = "https://goo.gl/pJnW9J"
            id = R.drawable.zurich
        })

        resources.add(Resource().apply {
            name = "2018021100" //Bern
            title = "CH Bern Kramgasse"
            author = "Daniel Schwen"
            license = "Creative Commons Attribution-Share Alike 4.0 International (https://goo.gl/Y66kJp)"
            uri = "https://goo.gl/ZQbKfD"
            id = R.drawable.bern
        })

        resources.add(Resource().apply {
            name = "2018020100" //Ad Diriyah
            title = "Diriyahpic"
            author = "Petrovic-Njegos"
            license = "Creative Commons Attribution-Share Alike 3.0 Unported (https://goo.gl/MUaj05)"
            uri = "https://goo.gl/vniNrP"
            id = R.drawable.addiriyah
        })

        resources.add(Resource().apply {
            name = "2018020600" //Sanya
            title = "Phoenix Island, Sanya Bay - 02"
            author = "Anna Frodesiak, design by MAD Studio"
            license = "Creative Commons CC0 1.0 Universal Public Domain Dedication (https://goo.gl/X6XqK2)"
            uri = "https://goo.gl/y2mFZ3"
            id = R.drawable.sanya
        })
    }

    override fun getResources(): List<Resource> {
        return resources
    }

    /**
     * @param id: when looking for city image use the circuit id
     */
    override fun getResourceId(id: String): Int {
        return resources.find { it.name?.contains(id) ?: false }?.id ?: -1
    }
}