package com.iddev.repository;

import com.iddev.entity.BaseEntity;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractCrudRepository<K extends Serializable, E extends BaseEntity<K>> implements CrudRepository<K, E> {

    private final Class<E> clazz;
    private final EntityManager entityManager;

    @Override
    public E save(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(E entity) {
        entityManager.remove(entity);
        entityManager.flush();
    }

    @Override
    public void update(E entity) {
        entityManager.merge(entity);
    }

    @Override
    public Optional<E> findById(K id) {
        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    @Override
    public List<E> findAll() {
        var criteria = entityManager.getCriteriaBuilder().createQuery(clazz);
        criteria.from(clazz);
        return entityManager.createQuery(criteria)
                .getResultList();
    }
}
