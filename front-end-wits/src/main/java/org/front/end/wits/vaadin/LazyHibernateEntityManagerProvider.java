package org.front.end.wits.vaadin;

import javax.persistence.EntityManager;

public class LazyHibernateEntityManagerProvider
implements EntityManagerProvider {
	private static ThreadLocal<EntityManager>
	entityManagerThreadLocal =
	new ThreadLocal<EntityManager>();

	@Override
	public EntityManager getEntityManager() {
		return entityManagerThreadLocal.get();
	}

	public static void setCurrentEntityManager(
			EntityManager em) {
		entityManagerThreadLocal.set(em);
	}
}