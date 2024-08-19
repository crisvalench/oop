package com.ups.oop.service;

import com.ups.oop.dto.WorkerDTO;
import com.ups.oop.entity.Worker;
import com.ups.oop.repository.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkersService {
        private final WorkerRepository workerRepository;

    public WorkersService( WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public List<WorkerDTO> getWorkers(){
        Iterable<Worker> workersIterable = workerRepository.findAll();
        List<WorkerDTO> clientList = new ArrayList<>();
        for (Worker workers : workersIterable) {
            WorkerDTO clientDTO = new WorkerDTO(workers.getPersonId(), workers.getName() + " " + workers.getLastname(), workers.getAge(), workers.getWorkerCode());
            clientList.add(clientDTO);
        }
        return clientList;
    }
}
