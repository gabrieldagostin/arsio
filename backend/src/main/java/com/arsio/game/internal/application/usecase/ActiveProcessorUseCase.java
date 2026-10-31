package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.ProcessorNotFoundException;
import com.arsio.game.internal.domain.model.Processor;
import com.arsio.game.internal.domain.repository.ProcessorRepository;
import com.arsio.game.internal.domain.valueobject.RequirementId;
import com.arsio.game.internal.infra.Controller.dto.response.GetProcessorResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class ActiveProcessorUseCase {

    private final ProcessorRepository processors;

    public ActiveProcessorUseCase(ProcessorRepository processors) {
        this.processors = processors;
    }

    public GetProcessorResponse execute(UUID value) {

        RequirementId processorId = new RequirementId(value);

        Processor processor = processors.findById(processorId)
                .orElseThrow(ProcessorNotFoundException::new);

        processor.activate();

        processors.save(processor);

        return new GetProcessorResponse(
                processor.getId().value(),
                processor.getManufacturer().value(),
                processor.getModel().value()
        );
    }
}
