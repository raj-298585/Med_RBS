package com.rbs.mct.pagebeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.rbs.mct.entity.Invoice;

public class AutoCompleteBean {
	private List<Invoice> players;
	private Invoice selectedPlayer1;
	private Invoice selectedPlayer2;

	public AutoCompleteBean() {
		players = PlayerConverter.playerDB;
	}

	public Invoice getSelectedPlayer1() {
		return selectedPlayer1;
	}

	public void setSelectedPlayer1(Invoice selectedPlayer1) {
		this.selectedPlayer1 = selectedPlayer1;
	}

	public Invoice getSelectedPlayer2() {
		return selectedPlayer2;
	}

	public void setSelectedPlayer2(Invoice selectedPlayer2) {
		this.selectedPlayer2 = selectedPlayer2;
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
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Selected:" + ((Invoice) event.getObject()).getDescription(), null);

		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
