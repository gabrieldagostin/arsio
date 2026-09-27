package com.arsio.game.internal.application.usecase;

import com.arsio.game.internal.application.command.CreateProcessorCommand;
import com.arsio.game.internal.domain.model.Processor;
import com.arsio.game.internal.domain.repository.ProcessorRepository;
import com.arsio.game.internal.infra.Controller.dto.response.GetProcessorResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreateProcessorUseCase {

    private final ProcessorRepository processors;

    public CreateProcessorUseCase(ProcessorRepository processors) {
        this.processors = processors;
    }

    public GetProcessorResponse execute(CreateProcessorCommand command) {

        Processor processor = Processor.create(
                command.manufacturer(),
                command.model()
        );

        processors.save(processor);

        return new GetProcessorResponse(
                processor.getId().value(),
                processor.getManufacturer().value(),
                processor.getModel().value()
        );
    }
}
