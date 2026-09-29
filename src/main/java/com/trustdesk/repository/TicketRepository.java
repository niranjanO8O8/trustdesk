package com.trustdesk.repository;

import com.trustdesk.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository <Ticket, String>
{


}
