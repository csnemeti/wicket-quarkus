package ro.nemeti.wicket.example;

import com.google.inject.Injector;
import org.apache.wicket.Page;
import org.apache.wicket.csp.CSPDirective;
import org.apache.wicket.csp.CSPDirectiveSrcValue;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.protocol.http.WebApplication;
import ro.nemeti.wicket.quarkus.guice.integration.CdiBasedInjectorImpl;

import javax.enterprise.inject.spi.CDI;

public class WicketQuarkusApplication extends WebApplication {

    @Override
    protected void init() {
        super.init();

        // needed for the styling used by the quickstart
        getCspSettings().blocking()
                .add(CSPDirective.STYLE_SRC, CSPDirectiveSrcValue.SELF)
                .add(CSPDirective.STYLE_SRC, "https://fonts.googleapis.com/css")
                .add(CSPDirective.FONT_SRC, "https://fonts.gstatic.com");

        // do the injection part
        Injector injector = new CdiBasedInjectorImpl(CDI.current());
        getComponentInstantiationListeners().add(new GuiceComponentInjector(this, injector));
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }
}
