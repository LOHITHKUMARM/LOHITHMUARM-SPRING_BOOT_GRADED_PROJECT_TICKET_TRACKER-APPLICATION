package com.graded.project.mapper;

import com.graded.project.dto.TicketDTO;
import com.graded.project.model.Ticket;

public class TicketMapper {
	
	public static TicketDTO mapToTicketDTO(Ticket ticket) {
		return TicketDTO.builder()
				.id(ticket.getId())
				.title(ticket.getTitle())
				.url(ticket.getUrl())
				.content(ticket.getContent())
				.shortDescription(ticket.getShortDescription())
				.createdOn(ticket.getCreatedOn())
				.updatedOn(ticket.getUpdatedOn())
				.build();
	}
	
	public static Ticket mapToTicket(TicketDTO ticketdto) {
		return Ticket.builder()
				.id(ticketdto.getId())
				.title(ticketdto.getTitle())
				.url(ticketdto.getUrl())
				.content(ticketdto.getContent())
				.shortDescription(ticketdto.getShortDescription())
				.createdOn(ticketdto.getCreatedOn())
				.updatedOn(ticketdto.getUpdatedOn())
				.build();
	}

}
