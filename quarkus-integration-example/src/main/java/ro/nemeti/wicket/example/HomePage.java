package ro.nemeti.wicket.example;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import ro.nemeti.wicket.example.service.MessageService;

import javax.inject.Inject;

public class HomePage extends WebPage {
    @Inject
    MessageService messageService;

    public HomePage(final PageParameters parameters) {
        super(parameters);

        add(new Label("version", getApplication().getFrameworkSettings().getVersion()));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("sayHello", messageService.sayHello()));
    }
}
