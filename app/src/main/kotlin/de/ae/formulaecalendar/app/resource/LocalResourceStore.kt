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
            name = "threeten"
            title = "ThreeTen Android Backport"
            description = "ThreeTen Android Backport is used in this app"
            license = "Copyright (C) 2015 Jake Wharton\n" +
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
            uri = "https://github.com/JakeWharton/ThreeTenABP"
        })

        resources.add(Resource().apply {
            name = "logo"
            title = "EPrix logo"
            author = "Gustavo Girardelli"
            license = "Creative Commons Attribution-Share Alike 4.0 International " +
                    "(https://goo.gl/gtCYbm)"
            uri = "https://goo.gl/8qJsWI"
            description = "The image was used for the app icon. By the license of the EPrix logo " +
                    "(\"If you alter, transform, or build upon this work, you may distribute the " +
                    "resulting work only under the same or similar license to this one.\") I " +
                    "release the new icon under the same license as the EPrix logo. Downloadable " +
                    "under http://imgur.com/NzEF8b1"
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
            name = "2014020501" //Buenos Aires
            title = "Buenos Aires Cityline at Night - Irargerich"
            author = "Luis Argerich from Buenos Aires, Argentina"
            license = "Creative Commons Attribution 2.0 Generic (https://goo.gl/oWqhth)"
            uri = "https://goo.gl/QJrvIj"
            id = R.drawable.buenosaires
        })

        resources.add(Resource().apply {
            name = "2016020400" //Mexico City
            title = "Ciudad.de.Mexico.City.Distrito.Federal.DF.Paseo.Reforma.Skyline"
            author = "Alejandro Islas Photograph AC"
            license = "Creative Commons Attribution 2.0 Generic (https://goo.gl/oWqhth)"
            uri = "https://goo.gl/ZgEix1"
            id = R.drawable.mexicocity
        })

        resources.add(Resource().apply {
            name = "2014020300" //Monaco
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
            name = "2016020700,2014020900" //Berlin
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

    }

    override fun getResources(): List<Resource> {
        return resources
    }

    override fun getResourceId(id: String): Int {
        return resources.find { it.name?.contains(id) ?: false }?.id ?: -1
    }
}