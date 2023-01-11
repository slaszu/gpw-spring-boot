package pl.slaszu.gpw.sharedkernel.domain.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pl.slaszu.gpw.sharedkernel.domain.EventDispatcherInterface;

@Service
public class EventDispatcher implements EventDispatcherInterface {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void dispatch(Object event) {
        this.eventPublisher.publishEvent(event);
    }
}
