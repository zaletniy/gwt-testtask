/**
 * 
 */
package com.example.test.task.server.dao.management;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.example.test.task.server.dao.GenericDataDAO;

/**
 * The class for patching database.
 * <br>
 * <li>checks database version
 * <li>applies patches one by one 
 * 
 * @author Ilya Sviridov
 *
 */
@Repository("databasePatcher")
public class DatabasePatcher {
	public static final String VERSION_TABLE_NAME="database_version";
	
	private static final Log LOG=LogFactory.getLog(DatabasePatcher.class);
	
	@SuppressWarnings("rawtypes")
	@Autowired
	GenericDataDAO genericDataDAO;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired(required=true)
	PlatformTransactionManager transactionManager;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.REQUIRED)
	public void run(){
		LOG.warn("Database patcher will check if database version is actual and will apply all needed patches in future. For now only data filling");
		
		//TODO: to check if it works
		//entityManager.getTransaction().begin();
		//entityManager.getTransaction().commit();
		
		
		// the manual transaction 
		TransactionDefinition definition=new DefaultTransactionDefinition();
		TransactionStatus transactionStatus=transactionManager.getTransaction(definition);
		
		// any updates of DB on start should be here
		transactionManager.commit(transactionStatus);
		
		LOG.debug("Initial data has been filled");
	}
	
}
