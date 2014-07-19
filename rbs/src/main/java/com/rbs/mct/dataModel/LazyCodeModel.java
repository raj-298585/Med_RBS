package com.rbs.mct.dataModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.rbs.mct.entity.ICD10Codes;

public class LazyCodeModel extends LazyDataModel<ICD10Codes> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ICD10Codes> datasource;

	public LazyCodeModel(List<ICD10Codes> datasource) {
		this.datasource = datasource;
	}

	@Override
	public ICD10Codes getRowData(String rowKey) {
		for (ICD10Codes ICD10Codes : datasource) {
			if (ICD10Codes.getDescription().equals(rowKey))
				return ICD10Codes;
		}

		return null;
	}

	@Override
	public Object getRowKey(ICD10Codes ICD10Codes) {
		return ICD10Codes.getDescription();
	}

	@Override
	public List<ICD10Codes> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		List<ICD10Codes> data = new ArrayList<ICD10Codes>();

		// filter
		for (ICD10Codes ICD10Codes : datasource) {
			boolean match = true;

			for (Iterator<String> it = filters.keySet().iterator(); it
					.hasNext();) {
				try {
					String filterProperty = it.next();
					String filterValue = filters.get(filterProperty);
					String fieldValue = String.valueOf(ICD10Codes.getClass()
							.getField(filterProperty).get(ICD10Codes));

					if (filterValue == null
							|| fieldValue.toLowerCase().contains(
									filterValue.toLowerCase())) {
						match = true;
					} else {
						match = false;
						break;
					}
				} catch (Exception e) {
					match = false;
				}
			}

			if (match) {
				data.add(ICD10Codes);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new LazySorter(sortField, sortOrder));
		}

		// rowCount
		int dataSize = data.size();
		this.setRowCount(dataSize);

		// paginate
		if (dataSize > pageSize) {
			try {
				return data.subList(first, first + pageSize);
			} catch (IndexOutOfBoundsException e) {
				return data.subList(first, first + (dataSize % pageSize));
			}
		} else {
			return data;
		}
	}
}
