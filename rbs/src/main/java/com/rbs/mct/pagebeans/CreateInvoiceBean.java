package com.rbs.mct.pagebeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;

import com.rbs.mct.delegate.MCTDelegate;
import com.rbs.mct.entity.Hospital;
import com.rbs.mct.entity.ICD10Codes;
import com.rbs.mct.entity.Invoice;
import com.rbs.mct.entity.InvoiceFirst;
import com.rbs.mct.entity.MCTRequest;
import com.rbs.mct.entity.MCTResponse;

public class CreateInvoiceBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<SelectItem> hospitalNames = new ArrayList<SelectItem>();
	private List<SelectItem> patientNames = new ArrayList<SelectItem>();
	private Hospital addHospital;
	private String addPatient;
	private String selectedHospital;
	private String selectedPatient;
	private Date date;
	private Integer noOfEntry;
	private ICD10Codes selectedCode = new ICD10Codes();
	private InvoiceFirst selectedInvoice;
	private List<InvoiceFirst> invoices;
	private List<Invoice> invoiceEntries;
	private List<Invoice> players;
	private Invoice selectedPlayer1;

	public CreateInvoiceBean() {
		for (int i = 1; i < 6; i++) {
			hospitalNames.add(new SelectItem("Hospital " + i, "Hospital " + i));
			patientNames.add(new SelectItem("Patient " + i, "Patient " + i));
		}
		players = PlayerConverter.playerDB;
		addHospital = new Hospital();
	}

	public List<SelectItem> getHospitalNames() {
		return hospitalNames;
	}

	public void setHospitalNames(List<SelectItem> hospitalName) {
		this.hospitalNames = hospitalName;
	}

	public List<SelectItem> getPatientNames() {
		return patientNames;
	}

	public void setPatientNames(List<SelectItem> patientName) {
		this.patientNames = patientName;
	}

	public Hospital getAddHospital() {
		return addHospital;
	}

	public void setAddHospital(Hospital addHospital) {
		this.addHospital = addHospital;
	}

	public String getAddPatient() {
		return addPatient;
	}

	public void setAddPatient(String addPatient) {
		this.addPatient = addPatient;
	}

	public String getSelectedHospital() {
		return selectedHospital;
	}

	public void setSelectedHospital(String selectedHospital) {
		this.selectedHospital = selectedHospital;
	}

	public String getSelectedPatient() {
		return selectedPatient;
	}

	public void setSelectedPatient(String selectedPatient) {
		this.selectedPatient = selectedPatient;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getNoOfEntry() {
		return noOfEntry;
	}

	public void setNoOfEntry(Integer noOfEntry) {
		this.noOfEntry = noOfEntry;
	}

	public ICD10Codes getSelectedCode() {
		return selectedCode;
	}

	public void setSelectedCode(ICD10Codes selectedCode) {
		this.selectedCode = selectedCode;
	}

	public List<InvoiceFirst> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<InvoiceFirst> invoices) {
		this.invoices = invoices;
	}

	public InvoiceFirst getSelectedInvoice() {
		return selectedInvoice;
	}

	public void setSelectedInvoice(InvoiceFirst selectedInvoice) {
		this.selectedInvoice = selectedInvoice;
	}

	public List<Invoice> getInvoiceEntries() {
		return invoiceEntries;
	}

	public void setInvoiceEntries(List<Invoice> invoiceEntries) {
		this.invoiceEntries = invoiceEntries;
	}

	public String createInvoices() {
		invoiceEntries = new ArrayList<Invoice>();
		for (int i = 0; i < noOfEntry; i++) {
			invoiceEntries.add(new Invoice());
		}
		return "";
	}

	public Invoice getSelectedPlayer1() {
		return selectedPlayer1;
	}

	public void setSelectedPlayer1(Invoice selectedPlayer1) {
		this.selectedPlayer1 = selectedPlayer1;
	}

	public List<Invoice> completePlayer(String query) {
		List<Invoice> suggestions = new ArrayList<Invoice>();

		for (Invoice p : players) {
			if (p.getDescription().startsWith(query))
				suggestions.add(p);
		}

		return suggestions;
	}

	public void handleSelect(SelectEvent event) {
		String desc = ((Invoice) event.getObject()).getDescription();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Selected:" + desc, null);
		StringTokenizer s = new StringTokenizer(event.getComponent()
				.getCurrentComponent(FacesContext.getCurrentInstance())
				.getClientId(), ":");
		int rowInd = 0;
		int ind = 1;
		while (s.hasMoreElements()) {
			String element = s.nextElement().toString();
			if (ind == 3) {
				rowInd = Integer.parseInt(element);
			}
			ind++;
		}
		if (desc.length() > 1) {
			System.out.println("Description: " + desc);
			Invoice player = (Invoice) event.getObject();

			Invoice selectInvoice = new Invoice(player.getDate(),
					player.getId(), player.getCategory(), player.getCpt(),
					player.getDescription(), player.getFee());
			player.setUnits(0.0F);
			Invoice chosen = invoiceEntries.set(rowInd, selectInvoice);
			// chosen = player;
		}
		selectedPlayer1 = null;
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String saveHospital() {
		System.out.println(addHospital.getHospitalName());
		MCTRequest req = new MCTRequest();
		req.setEntityType(com.rbs.mct.constants.Constants.HOSPITAL_DAO);
		req.setBaseEntity(addHospital);
		MCTDelegate delegate = new MCTDelegate();
		try {
			MCTResponse res = delegate.requestInsert(req);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Success :", "Hospital has been added");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Failed to add :", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
		}
		return "";
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			Invoice selectedCode = invoiceEntries.get(event.getRowIndex());
			System.out.println("ID: " + selectedCode.getId() + "\nDesc: "
					+ selectedCode.getDescription() + "\nFee:"
					+ selectedCode.getFee());
			selectedCode.setTotal(selectedCode.getFee()
					* selectedCode.getUnits());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Cell Changed", "Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}
}
