package com.trustdesk.service.interfaces;

import com.trustdesk.dto.TicketDetailsDto;

public interface ITicketService
{
    TicketDetailsDto getTicketById(String id);
}
