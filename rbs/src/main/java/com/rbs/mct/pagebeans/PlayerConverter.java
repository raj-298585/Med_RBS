package com.rbs.mct.pagebeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.rbs.mct.entity.Invoice;
import com.rbs.mct.helper.ICDCodeHelper;

@FacesConverter(value = "player")
public class PlayerConverter implements Converter {
	public static List<Invoice> playerDB;

	static {
		playerDB = new ArrayList<Invoice>();
		playerDB = ICDCodeHelper.retrieveInvoices();
	}

	public Object getAsObject(FacesContext facesContext, UIComponent component,
			String submittedValue) {
		if (submittedValue.trim().equals("")) {
			return null;
		} else {
			try {
				// String[] input = submittedValue.split(":");
				int id = Integer.parseInt(submittedValue);
				// Long date = Date.parse(input[1]);
				for (Invoice p : playerDB) {
					if (p.getId() == id) {
						System.out.println("Submit: " + submittedValue);
						return p;
					}
				}

			} catch (NumberFormatException exception) {
				throw new ConverterException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Conversion Error",
						"Not a valid ICD Description"));
			}
		}

		return null;
	}

	public String getAsString(FacesContext facesContext, UIComponent component,
			Object value) {
		if (value == null || value.equals("")) {
			return "";
		} else {
			Invoice invoice = (Invoice) value;
			return String.valueOf(invoice.getId());
		}
	}
}
