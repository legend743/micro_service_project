package service;

import Entity.HotelEntity;

import java.util.List;

public interface MicroService {
HotelEntity Create(HotelEntity hotel);
List<HotelEntity> getAll();
HotelEntity get(String Id);
}
