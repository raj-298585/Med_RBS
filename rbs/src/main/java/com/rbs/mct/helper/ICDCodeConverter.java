package com.rbs.mct.helper;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.rbs.mct.entity.ICD10Codes;

public class ICDCodeConverter implements Converter {

	public static List<ICD10Codes> icdCodes;

	static {
		icdCodes = ICDCodeHelper.retrieveICDCodes();
	}

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component,
			String submittedValue) {
		// TODO Auto-generated method stub
		if (submittedValue.trim().equals("")) {
			System.out.println("NULL is submitted.");
			return null;
		} else {
			try {
				// int number = Integer.parseInt(submittedValue);
				for (ICD10Codes code : icdCodes) {
					if (code.getDescription() == submittedValue) {
						return code;
					}
				}

			} catch (NumberFormatException exception) {
				throw new ConverterException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Conversion Error",
						"Not a valid ICD Code"));
			}
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component,
			Object value) {
		// TODO Auto-generated method stub
		if (value == null || value.equals("")) {
			return "";
		} else {
			return String.valueOf(((ICD10Codes) value).getDescription());
		}
	}

}
