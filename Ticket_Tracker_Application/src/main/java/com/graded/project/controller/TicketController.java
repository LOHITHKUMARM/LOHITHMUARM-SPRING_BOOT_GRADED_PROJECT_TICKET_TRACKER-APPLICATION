package com.graded.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.graded.project.dto.TicketDTO;
import com.graded.project.service.TicketService;

import jakarta.validation.Valid;

@Controller
public class TicketController {

	private TicketService ticketService;

	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	@GetMapping("/admin/tickets")
	public String tickets(Model model) {
		List<TicketDTO> tickets = ticketService.findAllTickets();
		model.addAttribute("tickets", tickets);
		return "/admin/tickets";
	}

	@GetMapping("/admin/tickets/newticket")
	public String newticketForm(Model model) {
		TicketDTO ticketDto = new TicketDTO();
		model.addAttribute("ticket", ticketDto);
		return "admin/createticket";
	}

	@PostMapping("/admin/tickets")
	public String createTicket(@Valid @ModelAttribute("ticket") TicketDTO ticketDto, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("ticket", ticketDto);
			return "admin/createticket";
		}
		ticketDto.setUrl(getUrl(ticketDto.getTitle()));
		ticketService.createTicket(ticketDto);
		return "redirect:/admin/tickets";
	}

	private static String getUrl(String ticketTitle) {

		String title = ticketTitle.trim().toLowerCase();
		String url = title.replaceAll("\\s+", "-");
		url = url.replaceAll("[A-Za-z0-9]", "=");
		return url;
	}

	@GetMapping("/admin/tickets/{ticketId}/edit")
	public String editTicketform(@PathVariable("ticketId") long ticketId, Model model) {
		TicketDTO ticketDto = ticketService.findTicketById(ticketId);
		model.addAttribute("ticket", ticketDto);
		return "admin/editticket";
	}

	@PostMapping("/admin/tickets/{ticketId}")
	public String updateTheTicket(@PathVariable("ticketId") Long ticketId,
			@Valid @ModelAttribute("ticket") TicketDTO ticketDto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("ticket", ticketDto);
			return "admin/editticket";
		}
		ticketDto.setId(ticketId);
		ticketService.updateTicket(ticketDto);
		return "redirect:/admin/tickets";

	}

	@GetMapping("/admin/tickets/{ticketId}/delete")
	public String delete(@PathVariable("ticketId") Long ticketId) {
		ticketService.deleteTicket(ticketId);
		return "redirect:/admin/tickets";
	}

	@GetMapping("/admin/tickets/{ticketUrl}/view")
	public String viewPost(@PathVariable("ticketUrl") String ticketUrl, Model model) {
		TicketDTO ticketDto = ticketService.findTicketByUrl(ticketUrl);
		model.addAttribute("ticket", ticketDto);
		return "admin/viewTicket";
	}

	@GetMapping("/admin/tickets/search")
	public String searchTickets(@RequestParam(value = "query") String query, Model model) {
		List<TicketDTO> ticketDto = ticketService.searchTickets(query);
		model.addAttribute("tickets", ticketDto);
		return "admin/tickets";
	}
}
