package com.example.test.task.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.test.task.shared.NamedData;

@Deprecated
@Repository("namedDataDAO")
public class NamedDataDAO {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public void save(NamedData namedData){
		entityManager.persist(namedData);
	}
	
	public List<NamedData> findAll(){
		return entityManager.createQuery("select H  from "+NamedData.class.getName() + " H").getResultList();
	}
	
}
