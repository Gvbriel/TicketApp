package com.gabrielpolak.ticket.Service;

import com.gabrielpolak.ticket.Model.DAO.Reservation;
import com.gabrielpolak.ticket.Model.DAO.Screening;
import com.gabrielpolak.ticket.Model.DAO.Ticket;
import com.gabrielpolak.ticket.Model.Request.TicketRequest;
import com.gabrielpolak.ticket.Repository.ReservationRepository;
import com.gabrielpolak.ticket.Repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ScreeningRepository screeningRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ScreeningRepository screeningRepository) {
        this.reservationRepository = reservationRepository;
        this.screeningRepository = screeningRepository;
    }

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public Reservation createReservation(Long screening_id, List<TicketRequest> ticketRequest, String email) {
        Screening screening = screeningRepository.findById(screening_id)
                .orElseThrow(() -> new RuntimeException("Can't find screening."));

        List<Ticket> ticketList = Ticket.CreateMultipleTickets(ticketRequest, screening);


//        for(int i = 0; i <= ticketRequest.getType().length-1; i++){
//            for(int j = 0; j < ticketRequest.getAmount()[i]; j++){
//                ticketList.add(Ticket.CreateNewTicket(ticketRequest.getType()[i], screening));
//            }
//        }
//
        screening.removeTickets(ticketList.size());
        screeningRepository.save(screening);

        Reservation reservation = Reservation.CreateNewReservation(ticketList, screening, email);
        reservationRepository.save(reservation);

        return reservation;
    }
}
