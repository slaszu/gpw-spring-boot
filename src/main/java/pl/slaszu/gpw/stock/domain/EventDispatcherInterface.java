package pl.slaszu.gpw.stock.domain;

public interface EventDispatcherInterface {
    public void dispatch(Object event);
}
