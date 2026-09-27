package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.GpuNotFoundException;
import com.arsio.game.internal.domain.model.Gpu;
import com.arsio.game.internal.domain.repository.GpuRepository;
import com.arsio.game.internal.domain.valueobject.RequirementId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class DeleteGpuUseCase {

    public final GpuRepository gpus;

    public DeleteGpuUseCase(GpuRepository gpus) {
        this.gpus = gpus;
    }

    public void execute(UUID value) {

        RequirementId gpuId = new RequirementId(value);

        Gpu gpu = gpus.findById(gpuId)
                .orElseThrow(GpuNotFoundException::new);

        gpu.deactivate();

        gpus.save(gpu);
    }
}
