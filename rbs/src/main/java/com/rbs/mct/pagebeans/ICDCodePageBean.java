package com.rbs.mct.pagebeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.CellEditEvent;
import org.primefaces.model.LazyDataModel;

import com.rbs.mct.dataModel.LazyCodeModel;
import com.rbs.mct.delegate.MCTDelegate;
import com.rbs.mct.entity.BaseEntity;
import com.rbs.mct.entity.Category;
import com.rbs.mct.entity.ICD10Codes;
import com.rbs.mct.entity.MCTRequest;
import com.rbs.mct.entity.MCTResponse;
import com.rbs.mct.helper.ICDCodeHelper;

public class ICDCodePageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<SelectItem> categoryNames;
	private List<ICD10Codes> filteredicdCodes;
	private List<ICD10Codes> icdCodes;
	private LazyDataModel<ICD10Codes> codeModel;
	private ICD10Codes icdCode;
	private boolean selectAll;

	public ICDCodePageBean() {
		initload();
		icdCode = new ICD10Codes();
	}

	private void initload() {
		icdCodes = ICDCodeHelper.retrieveICDCodes();
		codeModel = new LazyCodeModel(icdCodes);
		categoryNames = retrieveCategory();
	}

	public LazyDataModel<ICD10Codes> getCodeModel() {
		System.out.println("Return:" + codeModel);
		return codeModel;
	}

	public void setCodeModel(LazyDataModel<ICD10Codes> codeModel) {
		this.codeModel = codeModel;
	}

	public ICD10Codes getIcdCode() {
		return icdCode;
	}

	public void setIcdCode(ICD10Codes icdCode) {
		this.icdCode = icdCode;
	}

	public List<ICD10Codes> getFilteredicdCodes() {
		return filteredicdCodes;
	}

	public void setFilteredicdCodes(List<ICD10Codes> filteredicdCodes) {
		this.filteredicdCodes = filteredicdCodes;
	}

	public List<ICD10Codes> getIcdCodes() {
		return icdCodes;
	}

	public List<SelectItem> getCategoryNames() {
		return categoryNames;
	}

	public void setCategoryNames(List<SelectItem> categoryNames) {
		this.categoryNames = categoryNames;
	}

	public boolean isSelectAll() {
		return selectAll;
	}

	public void setSelectAll(boolean selectAll) {
		this.selectAll = selectAll;
	}

	public void modifySelectAll(AjaxBehaviorEvent ae) {

	}

	public List<SelectItem> retrieveCategory() {
		List<BaseEntity> resultList = null;
		List<SelectItem> codesList = null;
		MCTRequest req = new MCTRequest();
		req.setEntityType(com.rbs.mct.constants.Constants.CATEGORY_DAO);
		MCTDelegate delegate = new MCTDelegate();
		try {
			MCTResponse res = delegate.requestSearch(req);
			resultList = res.getBaseEntityList();
			codesList = new ArrayList<SelectItem>();
			Iterator<BaseEntity> itr = resultList.iterator();
			while (itr.hasNext()) {
				Category category = (Category) itr.next();
				codesList.add(new SelectItem(category.getCategoryName(),
						category.getCategoryName()));
				// codesList.add(new SelectItem(category.getCategoryId()
				// .toString(), category.getCategoryName()));

			}
			System.out.println("Size:" + codesList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codesList;
		// return codesList.toArray(new SelectItem[codesList.size()]);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			ICD10Codes selectedCode = icdCodes.get(event.getRowIndex());
			System.out.println("ID: " + selectedCode.getId() + "\nDesc: "
					+ selectedCode.getDescription() + "\nFee:"
					+ selectedCode.getFee());
			MCTRequest req = new MCTRequest();
			req.setEntityType(com.rbs.mct.constants.Constants.ICDCODE_DAO);
			req.setBaseEntity(selectedCode);
			MCTDelegate delegate = new MCTDelegate();
			try {
				MCTResponse res = delegate.requestUpdate(req);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Cell Changed", "Old: " + oldValue + ", New:"
								+ newValue);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Failed to Update :", e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, msg);
				initload();
				e.printStackTrace();
			}

		}
	}

	public String save() {

		MCTRequest req = new MCTRequest();
		req.setEntityType(com.rbs.mct.constants.Constants.ICDCODE_DAO);
		req.setBaseEntity(icdCode);
		MCTDelegate delegate = new MCTDelegate();
		try {
			MCTResponse res = delegate.requestInsert(req);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Success :", "Code has been added");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			initload();
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Failed to add :", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
		}
		return "";
	}

}
