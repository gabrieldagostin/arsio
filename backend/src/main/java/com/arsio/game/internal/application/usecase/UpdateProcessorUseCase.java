package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.ProcessorNotFoundException;
import com.arsio.game.internal.application.command.UpdateProcessorCommand;
import com.arsio.game.internal.domain.model.Processor;
import com.arsio.game.internal.domain.repository.ProcessorRepository;
import com.arsio.game.internal.infra.Controller.dto.response.GetProcessorResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UpdateProcessorUseCase {

    private final ProcessorRepository processors;

    public UpdateProcessorUseCase(ProcessorRepository processors) {
        this.processors = processors;
    }

    public GetProcessorResponse execute(UpdateProcessorCommand command) {

        Processor processor = processors.findById(command.processorId())
                .orElseThrow(ProcessorNotFoundException::new);

        processor.update(command.manufacturer(), command.model());

        processors.save(processor);

        return new GetProcessorResponse(
                processor.getId().value(),
                processor.getManufacturer().value(),
                processor.getModel().value()
        );
    }
}
