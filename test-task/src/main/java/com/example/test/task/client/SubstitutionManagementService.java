package com.example.test.task.client;

import java.util.List;

import com.example.test.task.shared.EditViewReferenceData;
import com.example.test.task.shared.Substitution;
import com.example.test.task.shared.SubstitutionDetails;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("substitution")
public interface SubstitutionManagementService extends RemoteService {
	List<SubstitutionDetails> getSubstitutions();
	int saveSubstitution(Substitution substitution);
	Substitution getSubstitution(int id);
	List<SubstitutionDetails> deleteSubstitution(List<Integer> ids);
	
	EditViewReferenceData getAllNamedData();
	
}
