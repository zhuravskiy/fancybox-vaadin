package org.vaadin.addons.fancybox.demo;


import com.vaadin.annotations.JavaScript;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.addons.fancybox.Fancybox;

@JavaScript({
        "//code.jquery.com/jquery-2.1.3.min.js"
})
public class FancyboxDemo extends com.vaadin.ui.UI {

    @Override
    protected void init(com.vaadin.server.VaadinRequest request) {
        Link link = new Link(
                "See image with fancy box",
                new ExternalResource("//www.jail.se/hardware/digital_camera/canon/ixus_800is-powershot_sd700/images/sample_photos/sample3.jpg")
        );
        new Fancybox(link).setPadding(0).setVersion("2.1.5");

        Link linkFancyDisabled = new Link(
                "See image without fancy box",
                new ExternalResource("//www.jail.se/hardware/digital_camera/canon/ixus_800is-powershot_sd700/images/sample_photos/sample3.jpg")
        );
        new Fancybox(linkFancyDisabled).setEnabled(false);

        setContent(new VerticalLayout(link, linkFancyDisabled));
    }

}