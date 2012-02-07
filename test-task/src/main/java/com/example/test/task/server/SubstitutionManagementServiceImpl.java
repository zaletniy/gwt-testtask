package com.example.test.task.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import com.example.test.task.shared.RuleType;
import com.example.test.task.shared.Substitution;
import com.example.test.task.shared.SubstitutionDetails;
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
	 * Data
	 */
	public static Map<Integer, Substitution> data;

	/**
	 * Available substitutors
	 */
	public static NamedData[] substitutors = new NamedData[] { new NamedData(1, "Bob"), new NamedData(2, "Joe") };

	/**
	 * Available roles
	 */
	public static NamedData[] roles = new NamedData[] { new NamedData(1, "fullTimeRole"),
			new NamedData(2, "partTimeRole") };

	/**
	 * Available rule types
	 */
	public static RuleType[] ruleTypes = new RuleType[] { new RuleType(1, "alwaysRuleType", false),
			new RuleType(2, "intervalRuleType", true), new RuleType(3, "inactiveRuleType", true) };

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

		List<SubstitutionDetails> substitutions = new ArrayList<SubstitutionDetails>();
		for (Entry<Integer, Substitution> entry : getData().entrySet()) {
			Substitution source = entry.getValue();
			SubstitutionDetails substitutionDetails = new SubstitutionDetails();
			substitutionDetails.setId(source.getId());
			substitutionDetails.setName(getById(source.getSubstitutionNameId(), substitutors).getName());
			substitutionDetails.setRole(getById(source.getRoleId(), roles).getName());
			substitutionDetails.setRuleType(source.getRuleType().getName());
			substitutionDetails.setBeginDate(source.getBeginDate());
			substitutionDetails.setEndDate(source.getEndDate());

			substitutions.add(substitutionDetails);
		}

		return substitutions;
	}

	/**
	 * Saves new or updated substitution
	 * 
	 * @param substitution
	 *            to save
	 * @return id of saved item
	 */
	public int saveSubstitution(Substitution substitution) throws RemoteException {
		if (substitution.getId() <= 0) {
			substitution.setId(idCounter++);
		}
		data.put(substitution.getId(), substitution);
		return idCounter++;
	}

	/**
	 * Returns whole substitution model
	 * 
	 * @param id
	 *            of substitution
	 * @return Substitution object
	 */
	public Substitution getSubstitution(int id) throws RemoteException {
		return getData().get(id);
	}

	/**
	 * Deletes substitution by id
	 * 
	 * @param ids
	 * @return left substitutions
	 */
	public List<SubstitutionDetails> deleteSubstitution(List<Integer> ids) throws RemoteException {
		for (Integer id : ids) {
			getData().remove(id);
		}
		return getSubstitutions();
	}

	/**
	 * Cteares test data
	 * 
	 * @return data
	 */
	protected Map<Integer, Substitution> getData() {
		if (data == null) {
			data = new HashMap<Integer, Substitution>();
			Substitution substitution1 = new Substitution(1, 1, 1, ruleTypes[1], new Date(), new Date());
			Substitution substitution2 = new Substitution(2, 2, 1, ruleTypes[2], new Date(), new Date());
			Substitution substitution3 = new Substitution(3, 2, 1, ruleTypes[2], new Date(), new Date());
			Substitution substitution4 = new Substitution(4, 1, 1, ruleTypes[0], null, null);

			data.put(substitution1.getId(), substitution1);
			data.put(substitution2.getId(), substitution2);
			data.put(substitution3.getId(), substitution3);
			data.put(substitution4.getId(), substitution4);

		}
		return data;
	}

	/**
	 * Returns all reference data for edit view
	 * 
	 * @return EditViewReferenceData object
	 */
	
	//TODO use generics with ?
	public EditViewReferenceData getAllNamedData() {
		List<? extends NamedData> substitutors=substitutorDAO.findAll(Substitutor.class);
		return new EditViewReferenceData(Arrays.asList(roles),(List<NamedData>)substitutors, Arrays.asList(ruleTypes));
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
