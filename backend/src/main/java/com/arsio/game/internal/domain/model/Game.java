package com.arsio.game.internal.domain.model;

import com.arsio.game.internal.domain.valueobject.*;
import com.arsio.shared.money.Money;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public class Game {

    private final GameId id;
    private final UUID developerId;
    private GameTitle title;
    private Description description;
    private Money basePrice;
    private GameStatus status;
    private Set<GenreId> genres;
    private Set<TagId> tags;
    private LocalDate releaseDate;

    public Game(GameId id, UUID developerId, GameTitle title, Description description, Money basePrice, GameStatus status, LocalDate releaseDate) {
        this.id = id;
        this.developerId = developerId;
        this.title = title;
        this.description = description;
        this.basePrice = basePrice;
        this.status = status;
        this.releaseDate = releaseDate;
    }

    public GameId getId() {
        return id;
    }

    public UUID getDeveloperId() {
        return developerId;
    }

    public GameTitle getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    public Money getBasePrice() {
        return basePrice;
    }

    public GameStatus getStatus() {
        return status;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
