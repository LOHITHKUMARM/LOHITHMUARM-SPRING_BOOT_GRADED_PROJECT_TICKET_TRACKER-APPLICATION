package com.graded.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.graded.project.dto.TicketDTO;
import com.graded.project.mapper.TicketMapper;
import com.graded.project.model.Ticket;
import com.graded.project.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	private TicketRepository ticketRepository;

	public TicketServiceImpl(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

	@Override
	public List<TicketDTO> findAllTickets() {
		List<Ticket> tickets = ticketRepository.findAll();
		return tickets.stream().map(TicketMapper::mapToTicketDTO).collect(Collectors.toList());
	}

	@Override
	public void createTicket(TicketDTO ticketDto) {
		Ticket ticket = TicketMapper.mapToTicket(ticketDto);
		ticketRepository.save(ticket);

	}

	@Override
	public TicketDTO findTicketById(Long id) {
		Ticket ticket = ticketRepository.findById(id).get();
		return TicketMapper.mapToTicketDTO(ticket);
	}

	@Override
	public void updateTicket(TicketDTO ticketDto) {
		Ticket ticket = TicketMapper.mapToTicket(ticketDto);
		ticketRepository.save(ticket);

	}

	@Override
	public void deleteTicket(Long id) {
		ticketRepository.deleteById(id);

	}

	@Override
	public TicketDTO findTicketByUrl(String ticketUrl) {
		Ticket ticket = ticketRepository.findByUrl(ticketUrl).get();
		return TicketMapper.mapToTicketDTO(ticket);
	}

	@Override
	public List<TicketDTO> searchTickets(String query) {
		List<Ticket> ticket = ticketRepository.searchTickets(query);
		return ticket.stream().map(TicketMapper::mapToTicketDTO).collect(Collectors.toList());
	}

}
