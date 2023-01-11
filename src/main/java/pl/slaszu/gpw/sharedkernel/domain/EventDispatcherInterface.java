package pl.slaszu.gpw.sharedkernel.domain;

public interface EventDispatcherInterface {
    public void dispatch(Object event);
}
