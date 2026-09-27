package com.arsio.game.internal.application.usecase;

import com.arsio.game.internal.domain.repository.ProcessorRepository;
import com.arsio.game.internal.infra.Controller.dto.response.GetProcessorResponse;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ListProcessorsUseCase {

    private final ProcessorRepository processors;

    public ListProcessorsUseCase(ProcessorRepository processors) {
        this.processors = processors;
    }

    public PageResult<GetProcessorResponse> execute(String search, Pagination pagination) {

        return processors.findAll(search, pagination)
                .map(processor -> {
                    return new GetProcessorResponse(
                            processor.getId().value(),
                            processor.getManufacturer().value(),
                            processor.getModel().value()
                    );
                });
    }
}
