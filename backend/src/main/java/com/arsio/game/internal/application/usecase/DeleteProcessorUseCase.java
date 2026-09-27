package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.ProcessorNotFoundException;
import com.arsio.game.internal.domain.model.Processor;
import com.arsio.game.internal.domain.repository.ProcessorRepository;
import com.arsio.game.internal.domain.valueobject.RequirementId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class DeleteProcessorUseCase {

    private final ProcessorRepository processors;

    public DeleteProcessorUseCase(ProcessorRepository processors) {
        this.processors = processors;
    }

    public void execute(UUID value) {

        RequirementId processorId = new RequirementId(value);

        Processor processor = processors.findById(processorId)
                .orElseThrow(ProcessorNotFoundException::new);

        processor.deactivate();

        processors.save(processor);
    }
}
