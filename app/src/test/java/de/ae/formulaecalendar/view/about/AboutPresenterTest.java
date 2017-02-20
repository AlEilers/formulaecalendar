package de.ae.formulaecalendar.view.about;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import de.ae.formulaecalendar.resource.Resource;
import de.ae.formulaecalendar.resource.ResourceStore;

/**
 * Created by aeilers on 13.01.2017.
 */

public class AboutPresenterTest {
    ArrayList<Resource> resourceList = new ArrayList<>();

    AboutView view = new AboutView() {
        @Override
        public void setContent(List<Resource> resources) {
            resourceList.addAll(resources);
        }
    };

    @Test
    public void loadContent() {

        final ArrayList<Resource> newResoruces = new ArrayList<>();

        Resource r = new Resource();
        r.setName("test");
        r.setId(0);
        newResoruces.add(r);

        r = new Resource();
        r.setName("test2");
        r.setId(1);
        newResoruces.add(r);

        ResourceStore model = new ResourceStore() {
            @Override
            public ArrayList<Resource> getResources() {
                return newResoruces;
            }

            @Override
            public Integer getResourceId(String name) {
                Assert.fail();
                return 0;
            }
        };

        AboutPresenter presenter = new AboutPresenter(view, model);
        presenter.loadContent();
        Assert.assertTrue(resourceList.size() == newResoruces.size());
    }

}
