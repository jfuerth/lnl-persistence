package com.helpful.lnlpersistence;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Intercepts all persistence state changes as they happen. Can change the outcome by modifying the data before
 * calling super methods.
 */
@Slf4j
public class HibernateInterceptor extends EmptyInterceptor {

    @Override
    public boolean onFlushDirty(
            Object entity,
            Serializable id,
            Object[] currentState,
            Object[] previousState,
            String[] propertyNames,
            Type[] types) {
        log.info("Entity {}#{} changed from {} to {}",
                entity.getClass().getSimpleName(),
                id,
                Arrays.toString(previousState),
                Arrays.toString(currentState));
        return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
    }

    @Override
    public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        log.info("Entity {}#{} loaded: {}",
                entity.getClass().getSimpleName(),
                id,
                Arrays.toString(state));
        return super.onLoad(entity, id, state, propertyNames, types);
    }

    // there are more things that can be intercepted.. try ^O in intellij
}
