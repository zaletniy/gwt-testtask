package com.example.test.task.client.view;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.ListBox;

@Deprecated
public class ComplexObjectListBox<T> extends ListBox {
	/**
	 * Map to store data binding value/id usually <-> object.
	 */
	Map<String,T> objects=new HashMap<String, T>();
	
	public void addObjectItem(String item,String value,T object){
		addItem(item, value);
		objects.put(value, object);
	}
	
	public T getSelectedObject(){
		if(getSelectedIndex()!=-1){
			return objects.get(getValue(getSelectedIndex()));
		}else return null;
	}
	
	public void setSelectedValue(String value){
		for(int i=0;i<getItemCount();i++){
			if(getValue(i).equals(value)){
				setSelectedIndex(i);
			}
		}
	}
	
	public void setSelectedValue(Integer value){
		setSelectedValue(Integer.toString(value));
	}
}
