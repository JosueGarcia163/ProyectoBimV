package com.losscrums.ProyectoHoteleria.service.IService;

import java.util.List;
import com.losscrums.ProyectoHoteleria.model.Servicio;

public interface IServicioService {

    List<Servicio> listServices();

    Servicio findService(Long idService);

    Servicio saveService(Servicio servicio);

    void deleteService(Long idService);

}
