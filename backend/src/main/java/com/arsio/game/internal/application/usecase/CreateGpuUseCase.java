package com.arsio.game.internal.application.usecase;

import com.arsio.game.internal.application.command.CreateGpuCommand;
import com.arsio.game.internal.domain.model.Gpu;
import com.arsio.game.internal.domain.repository.GpuRepository;
import com.arsio.game.internal.infra.Controller.dto.response.GetGpuResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreateGpuUseCase {

    private final GpuRepository gpus;

    public CreateGpuUseCase(GpuRepository gpus) {
        this.gpus = gpus;
    }

    public GetGpuResponse execute(CreateGpuCommand command) {

        Gpu gpu = Gpu.create(
                command.manufacturer(),
                command.model()
        );

        gpus.save(gpu);

        return new GetGpuResponse(
                gpu.getId().value(),
                gpu.getManufacturer().value(),
                gpu.getModel().value()
        );
    }
}
