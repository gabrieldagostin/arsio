package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.GpuNotFoundException;
import com.arsio.game.internal.domain.model.Gpu;
import com.arsio.game.internal.domain.repository.GpuRepository;
import com.arsio.game.internal.domain.valueobject.RequirementId;
import com.arsio.game.internal.infra.Controller.dto.response.GetGpuResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class ActivateGpuUseCase {

    private GpuRepository gpus;

    public ActivateGpuUseCase(GpuRepository gpus) {
        this.gpus = gpus;
    }

    public GetGpuResponse execute(UUID value) {

        RequirementId gpuId = new RequirementId(value);

        Gpu gpu = gpus.findById(gpuId)
                .orElseThrow(GpuNotFoundException::new);

        gpu.activate();

        gpus.save(gpu);

        return new GetGpuResponse(
                gpu.getId().value(),
                gpu.getManufacturer().value(),
                gpu.getModel().value()
        );
    }
}
