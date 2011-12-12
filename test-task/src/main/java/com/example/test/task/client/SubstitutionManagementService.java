package com.example.test.task.client;

import java.util.List;

import com.example.test.task.shared.EditViewReferenceData;
import com.example.test.task.shared.Substitution;
import com.example.test.task.shared.SubstitutionDetails;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 * @author Ilya Sviridov
 */

@RemoteServiceRelativePath("substitution")
public interface SubstitutionManagementService extends RemoteService {
	/**
	 * 
	 * @return list of all substitutions
	 */
	List<SubstitutionDetails> getSubstitutions();
	
	/**
	 * Saves new or updated substitution
	 * @param substitution to save
	 * @return id of saved item
	 */
	int saveSubstitution(Substitution substitution);
	
	/**
	 * Returns whole substitution model
	 * @param id of substitution
	 * @return Substitution object
	 */
	Substitution getSubstitution(int id);
	
	/**
	 * Deletes substitution by id
	 * @param ids
	 * @return left substitutions
	 */
	List<SubstitutionDetails> deleteSubstitution(List<Integer> ids);
	
	/**
	 * Returns all reference data for edit view
	 * @return EditViewReferenceData object
	 */
	EditViewReferenceData getAllNamedData();
	
}
