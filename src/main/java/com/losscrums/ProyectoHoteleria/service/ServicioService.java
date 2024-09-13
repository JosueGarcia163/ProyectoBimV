package com.losscrums.ProyectoHoteleria.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.losscrums.ProyectoHoteleria.model.Servicio;
import com.losscrums.ProyectoHoteleria.repository.ServicioRepository;
import com.losscrums.ProyectoHoteleria.service.IService.IServicioService;

@Service
public class ServicioService implements IServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public List<Servicio> listServices() {
        return servicioRepository.findAll();
    }

    @Override
    public Servicio findService(Long idService) {
        return servicioRepository.findById(idService).orElse(null);
    }

    @Override
    public Servicio saveService(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @Override
    public void deleteService(Long idService) {
        servicioRepository.deleteById(idService);
    }
}
