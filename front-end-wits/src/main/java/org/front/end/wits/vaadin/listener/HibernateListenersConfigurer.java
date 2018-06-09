package org.front.end.wits.vaadin.listener;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

import org.hibernate.ejb.HibernateEntityManagerFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateListenersConfigurer {
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	@Autowired
	private MyPostInsertEventListener listenerInsert;
	@Autowired
	private MyPostUpdateEventListener listenerPostUpdate;
	@Autowired
	private MyPostDeleteEventListener listenerDelete;
	@Autowired
	private MyPreUpdateEventListener listenerPreUpdate;


	@PostConstruct
	public void registerListeners() {
		HibernateEntityManagerFactory hibernateEntityManagerFactory = (HibernateEntityManagerFactory) this.entityManagerFactory;
		SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) hibernateEntityManagerFactory.getSessionFactory();
		EventListenerRegistry registry = sessionFactoryImpl.getServiceRegistry().getService(EventListenerRegistry.class);
		//registry.getEventListenerGroup(EventType.PRE_UPDATE).appendListener(listenerPreUpdate);
		registry.getEventListenerGroup(EventType.POST_COMMIT_INSERT).appendListener(listenerInsert);
		registry.getEventListenerGroup(EventType.POST_UPDATE).appendListener(listenerPostUpdate);
		registry.getEventListenerGroup(EventType.POST_COMMIT_DELETE).appendListener(listenerDelete);
	}
}
