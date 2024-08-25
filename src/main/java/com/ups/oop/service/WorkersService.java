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

    public WorkersService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public List<WorkerDTO> getWorkers() {
        Iterable<Worker> workerIterable = workerRepository.findAll();
        List<WorkerDTO> workerList = new ArrayList<>();
        for(Worker worker : workerIterable) {
            WorkerDTO workerDTO = new WorkerDTO(
                    worker.getPersonId(),
                    (worker.getName() + "-" + worker.getLastname()),
                    worker.getAge(),
                    worker.getWorkerCode()
            );
            workerList.add(workerDTO);
        }
        return workerList;
    }
}
