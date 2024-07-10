package serviceImpl;

import Entity.HotelEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import repo.HotelRepository;
import service.MicroService;

import java.util.List;

public class BusinessImpl implements MicroService{
    @Autowired
    HotelRepository hotelrepo;






    @Override
    public HotelEntity Create(HotelEntity hotel) {
        return hotelrepo.save(hotel);
    }

    @Override
    public List<HotelEntity> getAll() {
        return hotelrepo.findAll();
    }

    @Override
    public HotelEntity get(String Id) {
        return hotelrepo.findById(Id).orElseThrow(()->new EntityNotFoundException("Hotel not found"));
    }
}