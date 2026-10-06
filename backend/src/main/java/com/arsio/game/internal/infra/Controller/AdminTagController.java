package com.arsio.game.internal.infra.Controller;

import com.arsio.game.internal.application.command.CreateTagCommand;
import com.arsio.game.internal.application.command.UpdateTagCommand;
import com.arsio.game.internal.application.usecase.CreateTagUseCase;
import com.arsio.game.internal.application.usecase.DeleteTagUseCase;
import com.arsio.game.internal.application.usecase.UpdateTagUseCase;
import com.arsio.game.internal.infra.Controller.dto.request.CreateTagRequest;
import com.arsio.game.internal.infra.Controller.dto.request.UpdateTagRequest;
import com.arsio.game.internal.infra.Controller.dto.response.GetTagResponse;
import com.arsio.game.internal.infra.Controller.mapper.TagControllerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/game-tags")
public class AdminTagController {

    private final TagControllerMapper mapper;
    private final CreateTagUseCase createTagUseCase;
    private final UpdateTagUseCase updateTagUseCase;
    private final DeleteTagUseCase deleteTagUseCase;

    @PostMapping
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<GetTagResponse> create(@RequestBody @Valid CreateTagRequest request) {

        CreateTagCommand command = mapper.toCreateTagCommand(request);

        GetTagResponse response = createTagUseCase.execute(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{tagId}")
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<GetTagResponse> update(
            @PathVariable(name = "tagId") UUID tagId,
            @RequestBody @Valid UpdateTagRequest request) {

        UpdateTagCommand command = mapper.toUpdateTagCommand(tagId, request);

        GetTagResponse response = updateTagUseCase.execute(command);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{tagId}")
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable(name = "tagId") UUID tagId) {

        deleteTagUseCase.execute(tagId);

        return ResponseEntity.noContent().build();
    }
}
