package com.example.test.task.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.test.task.client.SubstitutionManagementService;
import com.example.test.task.server.dao.GenericDataDAO;
import com.example.test.task.shared.EditViewReferenceData;
import com.example.test.task.shared.NamedData;
import com.example.test.task.shared.Role;
import com.example.test.task.shared.RuleType;
import com.example.test.task.shared.Substitution;
import com.example.test.task.shared.SubstitutionDetails;
import com.example.test.task.shared.SubstitutionFull;
import com.example.test.task.shared.Substitutor;
import com.google.gwt.rpc.client.impl.RemoteException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


/**
 * The server side fake implementation of the RPC service. Just fake, non thread
 * save so on.
 * 
 * @author Ilya Sviridov
 * 
 */

@Service("substitution")
public class SubstitutionManagementServiceImpl extends RemoteServiceServlet implements SubstitutionManagementService {
	private static final Log LOG=LogFactory.getLog(SubstitutionManagementServiceImpl.class);
	

	public SubstitutionManagementServiceImpl() {
		LOG.debug("The SubstitutionManagementServiceImpl service instance has been created "+this.hashCode());
	}

	private static final long serialVersionUID = 2442035806970897062L;

	/**
	 * Id generator
	 */
	public static int idCounter = 100;

	/**
	 * 
	 * @return list of all substitutions
	 */
	public List<SubstitutionDetails> getSubstitutions() throws RemoteException {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}
		
		List<SubstitutionFull>  subs= substitutionFullDAO.findAll(SubstitutionFull.class);
		
		List<SubstitutionDetails> detailsList=new ArrayList<SubstitutionDetails>();
		for(SubstitutionFull substitutionFull:subs){
			SubstitutionDetails details=new SubstitutionDetails();
			details.setId(substitutionFull.getId());
			details.setName(substitutionFull.getSubstitutor().getName());
			details.setRole(substitutionFull.getRole().getName());
			details.setRuleType(substitutionFull.getRuleType().getName());
			details.setBeginDate(substitutionFull.getBeginDate());
			details.setEndDate(substitutionFull.getEndDate());
			
			detailsList.add(details);
		}
		
		return detailsList;

	}

	/**
	 * Saves new or updated substitution
	 * 
	 * @param substitution
	 *            to save
	 * @return id of saved item
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public int saveSubstitution(Substitution substitution) throws RemoteException {
		SubstitutionFull substitutionFull=new SubstitutionFull();//substitutionFullDAO.find(substitution.getId(),SubstitutionFull.class);
		substitutionFull.setId(substitution.getId());
		substitutionFull.setRole(roleDAO.find(substitution.getRoleId(),Role.class));
		substitutionFull.setRuleType(ruleTypeDAO.find(substitution.getRuleType().getId(),RuleType.class));
		substitutionFull.setSubstitutor(substitutorDAO.find(substitution.getSubstitutionNameId(),Substitutor.class));
		substitutionFull.setBeginDate(substitution.getBeginDate());
		substitutionFull.setEndDate(substitution.getEndDate());
		
		substitutionFullDAO.merge(substitutionFull);
		
		return substitutionFull.getId();
	}

	/**
	 * Returns whole substitution model
	 * 
	 * @param id
	 *            of substitution
	 * @return Substitution object
	 */
	public Substitution getSubstitution(int id) throws RemoteException {
		
		SubstitutionFull subFull=substitutionFullDAO.find(id, SubstitutionFull.class);
		Substitution substitution=new Substitution();
		substitution.setId(subFull.getId());
		substitution.setRoleId(subFull.getRole().getId());
		substitution.setRuleType(subFull.getRuleType());
		substitution.setSubstitutionNameId(subFull.getSubstitutor().getId());
		substitution.setBeginDate(subFull.getBeginDate());
		substitution.setEndDate(subFull.getEndDate());
		
		return substitution;
	}

	/**
	 * Deletes substitution by id
	 * 
	 * @param ids
	 * @return left substitutions
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public List<SubstitutionDetails> deleteSubstitution(List<Integer> ids) throws RemoteException {
		for (Integer id : ids) {
			substitutionFullDAO.delete(id, SubstitutionFull.class);
		}
		return getSubstitutions();
	}

	/**
	 * Returns all reference data for edit view
	 * 
	 * @return EditViewReferenceData object
	 */
	
	//TODO use generics with ?
	public EditViewReferenceData getAllNamedData() {
		List<? extends NamedData> substitutors=substitutorDAO.findAll(Substitutor.class);
		List<? extends NamedData> roles=roleDAO.findAll(Role.class);
		List<RuleType> ruleTypes=ruleTypeDAO.findAll(RuleType.class);
		return new EditViewReferenceData((List<NamedData>)roles,(List<NamedData>)substitutors,ruleTypes);
	}

	/**
	 * returns data by id
	 */
	private NamedData getById(Integer id, NamedData[] namedDatas) {
		for (NamedData namedData : namedDatas) {
			if (id.equals(namedData.getId()))
				return namedData;
		}
		return null;
	}
	
	@Autowired
	GenericDataDAO<Substitutor> substitutorDAO;
	
	@Autowired
	GenericDataDAO<SubstitutionFull> substitutionFullDAO;
	
	@Autowired
	GenericDataDAO<Role> roleDAO;
	
	@Autowired
	GenericDataDAO<RuleType> ruleTypeDAO;
	

	/* (non-Javadoc)
	 * @see com.example.test.task.client.SubstitutionManagementService#getAllSubstitutors()
	 */
	public List<Substitutor> getAllSubstitutors() {
		return substitutorDAO.findAll(Substitutor.class);
	}

	/* (non-Javadoc)
	 * @see com.example.test.task.client.SubstitutionManagementService#saveSubstititor(com.example.test.task.shared.Substitutor)
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void saveSubstititor(Substitutor substitutor)throws Exception {
		substitutorDAO.save(substitutor);
	}
	
}
