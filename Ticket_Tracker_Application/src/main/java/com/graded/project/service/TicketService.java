package com.graded.project.service;

import java.util.List;

import com.graded.project.dto.TicketDTO;

public interface TicketService {

	List<TicketDTO> findAllTickets();

	void createTicket(TicketDTO ticketDto);

	TicketDTO findTicketById(Long id);

	void updateTicket(TicketDTO ticketDto);

	void deleteTicket(Long id);

	TicketDTO findTicketByUrl(String ticketUrl);

	List<TicketDTO> searchTickets(String query);

}
