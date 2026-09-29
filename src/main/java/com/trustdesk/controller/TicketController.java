package com.trustdesk.controller;

import com.trustdesk.constants.MessageConstants;
import com.trustdesk.dto.TicketDetailsDto;
import com.trustdesk.response.ApiSuccessResponse;
import com.trustdesk.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tickets")
public class TicketController
{
    private final TicketService ticketService;

    //getTicketById
    @GetMapping("/{id}")
    public ApiSuccessResponse<TicketDetailsDto> getTicketById(@PathVariable String id) {
        TicketDetailsDto ticketDetailsDto = ticketService.getTicketById(id);
        return new ApiSuccessResponse<>(MessageConstants.TICKET_FETCH_SUCCESS, ticketDetailsDto);
    }
}
