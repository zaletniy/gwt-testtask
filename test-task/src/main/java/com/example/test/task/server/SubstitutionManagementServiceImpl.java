package com.example.test.task.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.example.test.task.client.SubstitutionManagementService;
import com.example.test.task.shared.EditViewReferenceData;
import com.example.test.task.shared.NamedData;
import com.example.test.task.shared.RuleType;
import com.example.test.task.shared.Substitution;
import com.example.test.task.shared.SubstitutionDetails;
import com.google.gwt.rpc.client.impl.RemoteException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */

// TODO: make refactorung to extrat datasource layer
public class SubstitutionManagementServiceImpl extends RemoteServiceServlet
		implements SubstitutionManagementService {

	protected Map<Integer, Substitution> data;

	protected NamedData[] substitutors = new NamedData[] {
			new NamedData(1, "Bob"), new NamedData(2, "Joe") };
	protected NamedData[] roles = new NamedData[] {
			new NamedData(1, "fullTimeRole"), new NamedData(2, "partTimeRole") };

	protected RuleType[] ruleTypes = new RuleType[] {
			new RuleType(1, "alwaysRuleType", false),
			new RuleType(2, "intervalRuleType", true),
			new RuleType(3, "inactiveRuleType", true) };

	protected int idCounter = 100;

	public SubstitutionDetails[] getSubstitutions() throws RemoteException {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}

		List<SubstitutionDetails> substitutions = new ArrayList<SubstitutionDetails>();
		for (Entry<Integer, Substitution> entry : getData().entrySet()) {
			Substitution source = entry.getValue();
			SubstitutionDetails substitutionDetails = new SubstitutionDetails();
			substitutionDetails.setId(source.getId());
			substitutionDetails.setName(getById(source.getSubstitutionNameId(),
					substitutors).getName());
			substitutionDetails.setRole(getById(source.getRoleId(), roles)
					.getName());
			substitutionDetails.setRuleType(source.getRuleType().getName());
			substitutionDetails.setStartDate(source.getBeginDate());
			substitutionDetails.setEndDate(source.getEndDate());

			substitutions.add(substitutionDetails);
		}

		return substitutions.toArray(new SubstitutionDetails[] {});
	}

	public int saveSubstitution(Substitution substitution)
			throws RemoteException {
		if (substitution.getId() <= 0) {
			substitution.setId(idCounter++);
		}
		data.put(substitution.getId(), substitution);
		return idCounter++;
	}

	public Substitution getSubstitution(int id) throws RemoteException {
		return data.get(id);
	}

	public SubstitutionDetails[] deleteSubstitution(Integer[] ids)
			throws RemoteException {
		for (Integer id : ids) {
			getData().remove(id);
		}
		return getSubstitutions();
	}

	protected Map<Integer, Substitution> getData() {
		if (data == null) {
			data = new HashMap<Integer, Substitution>();
			Substitution substitution1 = new Substitution(1, 1, 1,
					ruleTypes[1], new Date(), new Date());
			Substitution substitution2 = new Substitution(2, 1, 1,
					ruleTypes[2], new Date(), new Date());
			Substitution substitution3 = new Substitution(3, 1, 1,
					ruleTypes[2], new Date(), new Date());
			Substitution substitution4 = new Substitution(4, 1, 1,
					ruleTypes[0], null, null);

			data.put(substitution1.getId(), substitution1);
			data.put(substitution2.getId(), substitution2);
			data.put(substitution3.getId(), substitution3);
			data.put(substitution4.getId(), substitution4);

		}
		return data;
	}

	public EditViewReferenceData getAllNamedData() {
		return new EditViewReferenceData(Arrays.asList(roles),
				Arrays.asList(substitutors), Arrays.asList(ruleTypes));
	}

	private NamedData getById(Integer id, NamedData[] namedDatas) {
		for (NamedData namedData : namedDatas) {
			if (id.equals(namedData.getId()))
				return namedData;
		}
		return null;
	}
}
