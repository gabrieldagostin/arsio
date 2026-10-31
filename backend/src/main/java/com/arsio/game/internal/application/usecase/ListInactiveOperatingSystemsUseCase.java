package com.arsio.game.internal.application.usecase;

import com.arsio.game.internal.domain.repository.OperatingSystemRepository;
import com.arsio.game.internal.infra.Controller.dto.response.GetOperatingSystemResponse;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ListInactiveOperatingSystemsUseCase {

    private final OperatingSystemRepository operatingSystems;

    public ListInactiveOperatingSystemsUseCase(OperatingSystemRepository operatingSystems) {
        this.operatingSystems = operatingSystems;
    }

    public PageResult<GetOperatingSystemResponse> execute(String search, Pagination pagination) {

        return operatingSystems.findAllByActiveFalse(search, pagination)
                .map(operatingSystem -> new GetOperatingSystemResponse(
                        operatingSystem.getId().value(),
                        operatingSystem.getName().value()
                ));
    }
}
