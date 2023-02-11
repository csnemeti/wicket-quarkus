package ro.nemeti.wicket.quarkus.guice.integration;

import com.google.inject.Module;
import com.google.inject.*;
import com.google.inject.internal.BindingImpl;
import com.google.inject.internal.Scoping;
import com.google.inject.spi.BindingTargetVisitor;
import com.google.inject.spi.Element;
import com.google.inject.spi.InjectionPoint;
import com.google.inject.spi.TypeConverterBinding;

import javax.enterprise.inject.spi.CDI;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class used for imitating Google Guice's {@link Injector}.
 * Not all the methods are implemented, only the necessary for injection into Wicket.
 */
public class CdiBasedInjectorImpl implements Injector {
    private final CDI<Object> cdi;

    public CdiBasedInjectorImpl(CDI<Object> cdi) {
        this.cdi = cdi;
    }

    private CDI getCdi() {
        return CDI.current();
    }


    @Override
    public <T> Binding<T> getBinding(Key<T> key) {
        BindingImpl<T> binding = new BindingImpl<T>(null, key, Scoping.SINGLETON_ANNOTATION) {
            @Override
            public <V> V acceptTargetVisitor(BindingTargetVisitor<? super T, V> visitor) {
                return null;
            }

            @Override
            public void applyTo(Binder binder) {
                return;
            }
        };
        return binding;
    }

    @Override
    public <T> T getInstance(Key<T> key) {
        Class<T> type = (Class<T>) key.getTypeLiteral().getRawType();
        return type.cast(getCdi().select(type).get());
    }

    @Override
    public void injectMembers(Object instance) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public <T> MembersInjector<T> getMembersInjector(TypeLiteral<T> typeLiteral) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public <T> MembersInjector<T> getMembersInjector(Class<T> type) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public Map<Key<?>, Binding<?>> getBindings() {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public Map<Key<?>, Binding<?>> getAllBindings() {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public <T> Binding<T> getBinding(Class<T> type) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public <T> Binding<T> getExistingBinding(Key<T> key) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public <T> List<Binding<T>> findBindingsByType(TypeLiteral<T> type) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public <T> Provider<T> getProvider(Key<T> key) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public <T> Provider<T> getProvider(Class<T> type) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public <T> T getInstance(Class<T> type) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public Injector getParent() {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public Injector createChildInjector(Iterable<? extends Module> modules) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public Injector createChildInjector(Module... modules) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public Map<Class<? extends Annotation>, Scope> getScopeBindings() {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public Set<TypeConverterBinding> getTypeConverterBindings() {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public List<Element> getElements() {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public Map<TypeLiteral<?>, List<InjectionPoint>> getAllMembersInjectorInjectionPoints() {
        throw new UnsupportedOperationException("This operation is not supported");
    }
}
