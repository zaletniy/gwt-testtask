package com.example.test.task.client;

import java.util.Map;

import com.example.test.task.shared.NamedData;
import com.example.test.task.shared.Substitution;
import com.example.test.task.shared.SubstitutionDetails;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("substitution")
public interface SubstitutionManagementService extends RemoteService {
	SubstitutionDetails[] getSubstitutions();
	int saveSubstitution(Substitution substitution);
	Substitution getSubstitution(int id);
	SubstitutionDetails[] deleteSubstitution(Integer[] ids);
	
	Map<String, NamedData[]> getAllNamedData();
	
}
