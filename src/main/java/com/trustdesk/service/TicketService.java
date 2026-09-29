package com.trustdesk.service;

import com.trustdesk.constants.MessageConstants;
import com.trustdesk.dto.TicketDetailsDto;
import com.trustdesk.entity.Ticket;
import com.trustdesk.exception.TicketNotFoundException;
import com.trustdesk.repository.TicketRepository;
import com.trustdesk.service.interfaces.ITicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService implements ITicketService
{
    private final TicketRepository ticketRepository;

    @Override
    public TicketDetailsDto getTicketById(String ticketId)
    {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException(String.format(MessageConstants.TICKET_NOT_FOUND, ticketId)));

        return TicketDetailsDto.builder()
                .ticketId(ticket.getId())
                .channel(ticket.getChannel())
                .subject(ticket.getSubject())
                .body(ticket.getBody())
                .createdAt(ticket.getCreatedAt())
                .status(ticket.getStatus())
                .build();
    }

    @Override
    public List<TicketDetailsDto> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();

        return tickets.stream()
                .map(ticket -> TicketDetailsDto.builder()
                        .ticketId(ticket.getId())
                        .channel(ticket.getChannel())
                        .subject(ticket.getSubject())
                        .body(ticket.getBody())
                        .status(ticket.getStatus())
                        .createdAt(ticket.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
