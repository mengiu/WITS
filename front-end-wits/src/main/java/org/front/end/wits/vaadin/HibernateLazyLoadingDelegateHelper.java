package org.front.end.wits.vaadin;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.addon.jpacontainer.EntityProvider;
import com.vaadin.addon.jpacontainer.LazyLoadingDelegate;

public class HibernateLazyLoadingDelegateHelper implements LazyLoadingDelegate {

    private EntityProvider<?> entityProvider;

    public void setEntityProvider(EntityProvider<?> ep) {
        entityProvider = ep;
    }

    public <E> E ensureLazyPropertyLoaded(E entity, String propertyName) {
        String prop = getRootPropertyName(propertyName);
        try {
            Object value = lazilyLoadPropertyValue(entity, prop);
            value = recurseIfNested(propertyName, value);
            trySetUsingSetter(entity, prop, value);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * Builds a query and loads the property value for the entity
     * 
     * @return the result list from the query.
     */
    private <E> Object lazilyLoadPropertyValue(E entity, String prop) {
        CriteriaBuilder cb = entityProvider.getEntityManager()
                .getCriteriaBuilder();
        CriteriaQuery<Object> q = cb.createQuery();
        Root<? extends Object> root = q.from(entity.getClass());
        q.select(root.get(prop));
        String idFieldName = tryGetEntityIdName(entity).toString();
        q.where(cb.equal(root.get(idFieldName), cb.literal(tryGetEntityId(entity))));
        return entityProvider.getEntityManager().createQuery(q).getResultList();
    }

    /**
     * Lazily load the properties recursively if this is a nested property. E.g.
     * loads the data for "bar" and "baz" if the property name is "foo.bar.baz"
     * and attaches these values to "foo" and returns the value of "foo".
     * 
     * @param propertyName
     *            the name ("path") of the, possibly nested, property.
     * @param value
     *            the value of the "root" property in propertyName (e.g. the
     *            value of "foo" if propertyName is "foo.bar.baz").
     * @return the value of the "root" property
     */
    private Object recurseIfNested(String propertyName, Object value) {
        if (isNestedProperty(propertyName)) {
            // If the property is nested, only the final node can be a
            // collection of items (the syntax doesn't support nesting through
            // collections), so we're safe to just grab the first element
            // of the list currently in 'value'.
            Object subEntity = ((List<?>) value).get(0);
            String subProperty = propertyName.substring(propertyName
                    .indexOf('.') + 1);
            value = ensureLazyPropertyLoaded(subEntity, subProperty);
        }
        return value;
    }

    /**
     * @return the "root" property name, i.e. the string up to the first dot,
     *         denoting a nested property.
     */
    private String getRootPropertyName(String propertyName) {
        if (isNestedProperty(propertyName)) {
            return propertyName.substring(0, propertyName.indexOf('.'));
        }
        return propertyName;
    }

    /**
     * @return true if propertyName contains nested properties.
     */
    private boolean isNestedProperty(String propertyName) {
        return propertyName.indexOf('.') != -1;
    }

    private <E> Object tryGetEntityId(E entity) {
        try {
            return getEntityId(entity);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Could not get the entity id.", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Could not get the entity id.", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Could not get the entity id.", e);
        }
    }

    private <E> Object tryGetEntityIdName(E entity) {
        try {
            return getEntityIdName(entity);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Could not get the entity name id.", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Could not get the entity name id.", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Could not get the entity name id.", e);
        }
    }
    /**
     * Find the ID of an entity by finding either the field or the getter
     * annotated with the {@link Id} annotation and getting the value.
     * 
     * @param entity
     *            the entity to find the ID of.
     * @return the ID of the entity.
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private <E> Object getEntityId(E entity) throws IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {
        // Try fields
        for (Field f : entity.getClass().getDeclaredFields()) {
            if (f.isAnnotationPresent(Id.class)) {
                try {
                    f.setAccessible(true);
                    return f.get(entity);
                } finally {
                    f.setAccessible(false);
                }
            }
        }
        // Try methods if no annotated field was found.
        for (Method m : entity.getClass().getMethods()) {
            if (m.isAnnotationPresent(Id.class) ||
            		m.isAnnotationPresent(EmbeddedId.class)) {
                return m.invoke(entity);
            }
        }
        return null;
    }

    private <E> void trySetUsingSetter(E entity, String propertyName,
            Object value) {
        try {
            setUsingSetter(entity, propertyName, value);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(
                    "Could not set lazy loaded value for entity.", e);
        } catch (SecurityException e) {
            throw new RuntimeException(
                    "Could not set lazy loaded value for entity.", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(
                    "Could not set lazy loaded value for entity.", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(
                    "Could not set lazy loaded value for entity.", e);
        } catch (InstantiationException e) {
            throw new RuntimeException(
                    "Could not set lazy loaded value for entity.", e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(
                    "Could not set lazy loaded value for entity.", e);
        }
    }

    /**
     * Finds the setter for a property and sets the value of the property using
     * the found setter method.
     * 
     * @param entity
     *            the entity containing the property to set the value for.
     * @param propertyName
     *            the name of the property.
     * @param value
     *            the new value of the property.
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws SecurityException
     * @throws NoSuchMethodException
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private <E> void setUsingSetter(E entity, String propertyName, Object value)
            throws IllegalArgumentException, IllegalAccessException,
            InvocationTargetException, InstantiationException,
            SecurityException, NoSuchMethodException {
        Method setter = findSetterFor(entity, propertyName);
        if (setter != null) {
            Class<?> parameterType = setter.getParameterTypes()[0];
            if (Collection.class.isAssignableFrom(parameterType)) {
                if (Set.class.isAssignableFrom(parameterType)) {
                    value = new HashSet((Collection) value);
                }
            } else if (value instanceof Collection) {
                // "Unwrap" the value from the collection, since the setter
                // doesn't accept collections.
                value = ((Collection) value).iterator().next();
            }
            setter.invoke(entity, value);
        }
    }

    private <E> Method findSetterFor(E entity, String propertyName) {
        for (Method m : entity.getClass().getMethods()) {
            if (m.getName().equalsIgnoreCase("set" + propertyName)) {
                return m;
            }
        }
        return null;
    }

	/**
     * Find the ID name of an entity by finding either the field or the getter
     * annotated with the {@link Id} annotation and getting the value.
     * 
     * @param entity
     *            the entity to find the ID of.
     * @return the name of ID of the entity.
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private <E> Object getEntityIdName(E entity) throws IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {
        // Try fields
        for (Field f : entity.getClass().getDeclaredFields()) {
            if (f.isAnnotationPresent(Id.class)) {
                try {
                    f.setAccessible(true);
                    return f.getName();
                } finally {
                    f.setAccessible(false);
                }
            }
        }
        // Try methods if no annotated field was found.
        for (Method m : entity.getClass().getMethods()) {
            if (m.isAnnotationPresent(Id.class) ||
            		m.isAnnotationPresent(EmbeddedId.class)) {
                for (Field f : entity.getClass().getDeclaredFields()) {
                    String attribue = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1, f.getName().length());
                	if (m.getName().indexOf(attribue)>0) {
                        try {
                            f.setAccessible(true);
                            return f.getName();
                        } finally {
                            f.setAccessible(false);
                        }
                    }
                }
            }
        }
    	return null;
    }


}
