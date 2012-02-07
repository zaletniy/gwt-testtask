package com.example.test.task.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

/**
 * The generic DAO.
 * 
 * @author Ilya Sviridov
 *
 * @param <T> the data object type
 */
@Repository("genericDAO")
public class GenericDataDAO<T> {

	public GenericDataDAO() {
	}

	@PersistenceContext
	EntityManager entityManager;
	
	public void save(T dataObject) {
		entityManager.persist(dataObject);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> clazz) {
		return entityManager.createQuery(
				"select H  from " +clazz.getName() + " H")
				.getResultList();
	}

}
