package es.uma.taw_grupo12.service;

import es.uma.taw_grupo12.dao.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrabajadorService {

    @Autowired
    TrabajadorRepository trabajadorRepository;
}
