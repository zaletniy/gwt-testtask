package com.example.test.task.client;

import com.example.test.task.shared.Substitution;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("substitution")
public interface SubstitutionManagementService extends RemoteService {
	Substitution[] getSubstitutions();
	int saveSubstitution(Substitution substitution);
	Substitution getSubstitution(int id);
	void deleteSubstitution(int id);
}
