package com.trustdesk.repository;

import com.trustdesk.dto.TicketDetailsDto;
import com.trustdesk.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository <Ticket, String>
{

}
