package com.arsio.game.internal.application.usecase;

import com.arsio.game.internal.domain.repository.GpuRepository;
import com.arsio.game.internal.infra.Controller.dto.response.GetGpuResponse;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ListGpusUseCase {

    private final GpuRepository gpus;

    public ListGpusUseCase(GpuRepository gpus) {
        this.gpus = gpus;
    }

    public PageResult<GetGpuResponse> execute(String search, Pagination pagination) {

        return gpus.findAll(search, pagination)
                .map(gpu -> {
                    return new GetGpuResponse(
                            gpu.getId().value(),
                            gpu.getManufacturer().value(),
                            gpu.getModel().value()
                    );
                });
    }
}
