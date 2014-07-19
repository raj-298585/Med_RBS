package com.rbs.mct.dataModel;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import com.rbs.mct.entity.ICD10Codes;

public class LazySorter implements Comparator<ICD10Codes> {

	private String sortField;
	private SortOrder sortOrder;

	public LazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(ICD10Codes ICD10Codes1, ICD10Codes ICD10Codes2) {
		try {
			Object value1 = ICD10Codes.class.getField(this.sortField).get(
					ICD10Codes1);
			Object value2 = ICD10Codes.class.getField(this.sortField).get(
					ICD10Codes2);

			@SuppressWarnings({ "unchecked", "rawtypes" })
			int value = ((Comparable) value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}