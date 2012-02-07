/**
 * 
 */
package com.example.test.task.server.dao.management;

import java.util.Date;

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
import com.example.test.task.shared.Role;
import com.example.test.task.shared.RuleType;
import com.example.test.task.shared.SubstitutionFull;
import com.example.test.task.shared.Substitutor;

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
		LOG.warn("TODO: Database patcher will check if database version is actual and will apply all needed patches in future. For now only data filling");
		Substitutor substitutor1=new Substitutor();
		substitutor1.setName("Ivan");
		
		Substitutor substitutor2=new Substitutor();
		substitutor2.setName("Bob");
		
		Substitutor substitutor3=new Substitutor();
		substitutor3.setName("Joe");
		
		RuleType alwaysRuleType=new RuleType();
		alwaysRuleType.setName("alwaysRuleType");
		
		RuleType intervalRuleType=new RuleType();
		intervalRuleType.setName("intervalRuleType");
		intervalRuleType.setInterval(true);
		
		RuleType inactiveRuleType=new RuleType();
		inactiveRuleType.setName("inactiveRuleType");
		
		Role fullTimeRole=new Role();
		fullTimeRole.setName("fullTimeRole");
		
		Role partTimeRole=new Role();
		partTimeRole.setName("partTimeRole");
		
		SubstitutionFull substitutionFull1=new SubstitutionFull();
		substitutionFull1.setRole(partTimeRole);
		substitutionFull1.setRuleType(intervalRuleType);
		substitutionFull1.setSubstitutor(substitutor1);
		substitutionFull1.setBeginDate(new Date());
		substitutionFull1.setEndDate(new Date(new Date().getTime()+1000));
		
		// the manual transaction 
		TransactionDefinition definition=new DefaultTransactionDefinition();
		TransactionStatus transactionStatus=transactionManager.getTransaction(definition);
		genericDataDAO.save(partTimeRole);
		genericDataDAO.save(fullTimeRole);
		
		genericDataDAO.save(intervalRuleType);
		genericDataDAO.save(alwaysRuleType);
		genericDataDAO.save(inactiveRuleType);
		
		genericDataDAO.save(substitutor1);
		genericDataDAO.save(substitutor2);
		genericDataDAO.save(substitutor3);
		
		genericDataDAO.save(substitutionFull1);
		transactionManager.commit(transactionStatus);
		
		LOG.debug("Initial data has been filled");
	}
	
}
