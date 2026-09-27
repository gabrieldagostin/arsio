package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.GpuNotFoundException;
import com.arsio.game.internal.application.command.UpdateGpuCommand;
import com.arsio.game.internal.domain.model.Gpu;
import com.arsio.game.internal.domain.repository.GpuRepository;
import com.arsio.game.internal.infra.Controller.dto.response.GetGpuResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UpdateGpuUseCase {

    private final GpuRepository gpus;

    public UpdateGpuUseCase(GpuRepository gpus) {
        this.gpus = gpus;
    }

    public GetGpuResponse execute(UpdateGpuCommand command) {

        Gpu gpu = gpus.findById(command.gpuId())
                .orElseThrow(GpuNotFoundException::new);

        gpu.update(command.manufacturer(), command.model());

        gpus.save(gpu);

        return new GetGpuResponse(
                gpu.getId().value(),
                gpu.getManufacturer().value(),
                gpu.getModel().value()
        );
    }
}
