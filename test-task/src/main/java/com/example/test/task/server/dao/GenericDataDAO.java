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
 * @param <T>
 *            the data object type
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
				"select H  from " + clazz.getName() + " H").getResultList();
	}

	public T find(int id, Class<T> clazz) {
		return entityManager.find(clazz, id);
	}

	public void merge(T object) {
		entityManager.merge(object);
	}

	@SuppressWarnings("unchecked")
	public T findFirstByParamener(Class<T> clazz, String name, Object value) {
		return (T) entityManager
				.createQuery(
						"select H from " + clazz.getName()
								+ " where H." + name + "= :" + name)
				.setParameter(name, value).getSingleResult();

	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByParamener(Class<T> clazz, String name, Object value) {
		return entityManager
				.createQuery(
						"select H from " + clazz.getName()
								+ " where H." + name + "= :" + name)
				.setParameter(name, value).getResultList();

	}
	
	public void delete(int id, Class<T> clazz){
		entityManager.remove(find(id, clazz));
	}
	

}
