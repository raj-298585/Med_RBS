package com.rbs.mct.helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.rbs.mct.delegate.MCTDelegate;
import com.rbs.mct.entity.BaseEntity;
import com.rbs.mct.entity.ICD10Codes;
import com.rbs.mct.entity.Invoice;
import com.rbs.mct.entity.MCTRequest;
import com.rbs.mct.entity.MCTResponse;

public class ICDCodeHelper {

	public static List<ICD10Codes> retrieveICDCodes() {
		List<BaseEntity> resultList = null;
		List<ICD10Codes> codesList = null;
		MCTRequest req = new MCTRequest();
		req.setEntityType(com.rbs.mct.constants.Constants.ICDCODE_DAO);
		MCTDelegate delegate = new MCTDelegate();
		try {
			MCTResponse res = delegate.requestSearch(req);
			resultList = res.getBaseEntityList();
			codesList = new ArrayList<ICD10Codes>();
			Iterator<BaseEntity> itr = resultList.iterator();
			while (itr.hasNext()) {
				ICD10Codes currentUser = (ICD10Codes) itr.next();
				codesList.add(currentUser);
			}
			System.out.println("Size:" + codesList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codesList;
	}

	public static List<Invoice> retrieveInvoices() {
		List<BaseEntity> resultList = null;
		List<Invoice> codesList = null;
		Invoice invoice = null;
		MCTRequest req = new MCTRequest();
		req.setEntityType(com.rbs.mct.constants.Constants.ICDCODE_DAO);
		MCTDelegate delegate = new MCTDelegate();
		try {
			MCTResponse res = delegate.requestSearch(req);
			resultList = res.getBaseEntityList();
			codesList = new ArrayList<Invoice>();
			Iterator<BaseEntity> itr = resultList.iterator();
			Calendar cal = Calendar.getInstance();
			int i = 0;
			while (itr.hasNext()) {
				ICD10Codes code = (ICD10Codes) itr.next();
				cal.add(Calendar.DAY_OF_MONTH, -i);
				// invoice = new Invoice(code.getCategory(), code.getCpt(),
				// code.getDescription(), code.getFee());
				invoice = new Invoice(cal.getTime(), code.getId(),
						code.getCategory(), code.getCpt(),
						code.getDescription(), code.getFee());
				codesList.add(invoice);
			}
			resultList = null;
			System.out.println("Size:" + codesList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codesList;
	}
}
