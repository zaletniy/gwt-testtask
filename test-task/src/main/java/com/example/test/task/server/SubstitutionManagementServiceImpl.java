package com.example.test.task.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.test.task.client.SubstitutionManagementService;
import com.example.test.task.shared.Substitution;
import com.google.gwt.rpc.client.impl.RemoteException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */

public class SubstitutionManagementServiceImpl extends RemoteServiceServlet
		implements SubstitutionManagementService {

	public Substitution[] getSubstitutions() throws RemoteException {
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
		}
		List<Substitution> data=new ArrayList<Substitution>();
		Substitution substitution1 = new Substitution(1, "Ilya", "role1",
				"rule1", new Date(), new Date());
		Substitution substitution2 = new Substitution(2, "Vasya", "role2",
				"rule2", new Date(), new Date());
		Substitution substitution3 = new Substitution(3, "Ilya", "role1",
				"rule1", new Date(), new Date());
		Substitution substitution4 = new Substitution(4, "Vasya", "role2",
				"rule2", new Date(), new Date());
		
		
		data.add(substitution1);
		data.add(substitution2);
		data.add(substitution3);
		data.add(substitution4);
		return data.toArray(new Substitution[]{});
	}

	public int saveSubstitution(Substitution substitution)
			throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	public Substitution getSubstitution(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteSubstitution(int id) throws RemoteException {
		// TODO Auto-generated method stub
	}
}
