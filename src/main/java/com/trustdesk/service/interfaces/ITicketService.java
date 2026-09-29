package com.trustdesk.service.interfaces;

import com.trustdesk.dto.TicketDetailsDto;

import java.util.List;

public interface ITicketService
{
    TicketDetailsDto getTicketById(String id);

    List<TicketDetailsDto>getAllTickets();
}
