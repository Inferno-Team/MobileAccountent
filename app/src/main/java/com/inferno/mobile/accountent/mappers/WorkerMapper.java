package com.inferno.mobile.accountent.mappers;

import com.inferno.mobile.accountent.models.PositionType;
import com.inferno.mobile.accountent.models.Worker;
import com.inferno.mobile.accountent.network.responses.WorkerDto;
import com.inferno.mobile.accountent.utils.DomainMapper;

import java.util.ArrayList;

import javax.inject.Inject;

public class WorkerMapper implements DomainMapper<WorkerDto, Worker> {
    private final UserMapper userMapper;

    @Inject
    public WorkerMapper(UserMapper userMapper) {

        this.userMapper = userMapper;
    }

    @Override
    public Worker mapToDomain(WorkerDto model) {
        return new Worker(PositionType.valueOf(model.getPosition()), model.getShopId(),
                model.getWorker() != null ? userMapper.mapToDomain(model.getWorker()) : null);
    }

    @Override
    public WorkerDto mapFromDomain(Worker worker) {
        return new WorkerDto(worker.getPosition().name(), worker.getShopId(),
                worker.getWorker() != null ? userMapper.mapFromDomain(worker.getWorker()) : null);
    }

    public ArrayList<Worker> toDomainList(ArrayList<WorkerDto> dtos) {
        ArrayList<Worker> workers = new ArrayList<>();
        if (dtos != null)
            for (WorkerDto dto : dtos)
                workers.add(mapToDomain(dto));
        return workers;
    }

    public ArrayList<WorkerDto> fromDomainList(ArrayList<Worker> dtos) {
        ArrayList<WorkerDto> workers = new ArrayList<>();
        for (Worker dto : dtos)
            workers.add(mapFromDomain(dto));
        return workers;
    }

}
