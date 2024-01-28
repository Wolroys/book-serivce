package org.sber.resourcesservice.service;

import lombok.RequiredArgsConstructor;
import org.sber.bookingentity.entity.Hotel;
import org.sber.resourcesservice.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    public List<Hotel> getHotels(){
        return hotelRepository.findAll();
    }

    public Optional<Hotel> getHotelById(Long id){
        return hotelRepository.findById(id);
    }

    public Hotel addHotel(Hotel hotel){
        return hotelRepository.save(hotel);
    }

    public Optional<Hotel> updateHotel(Long id, Hotel hotel){
        return hotelRepository.findById(id)
                .map(hotelRepository::saveAndFlush);
    }

    public boolean deleteHotel(Long id){
        return hotelRepository.findById(id)
                .map(hotel -> {
                    hotelRepository.delete(hotel);
                    hotelRepository.flush();
                    return true;
                }).orElse(false);
    }
}
