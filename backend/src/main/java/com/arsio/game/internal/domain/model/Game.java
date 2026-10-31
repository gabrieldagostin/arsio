package com.arsio.game.internal.domain.model;

import com.arsio.game.internal.domain.exception.GameNotPublishedException;
import com.arsio.game.internal.domain.valueobject.*;
import com.arsio.shared.money.Money;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Game {

    private final GameId id;
    private final UUID developerId;
    private GameTitle title;
    private Description description;
    private Money basePrice;
    private GameStatus status;
    private Set<GenreId> genres = new HashSet<>();;
    private Set<TagId> tags = new HashSet<>();;
    private LocalDate releaseDate;
    private boolean featured;

    public Game(GameId id, UUID developerId, GameTitle title, Description description, Money basePrice, GameStatus status, LocalDate releaseDate, boolean featured) {
        this.id = id;
        this.developerId = developerId;
        this.title = title;
        this.description = description;
        this.basePrice = basePrice;
        this.status = status;
        this.releaseDate = releaseDate;
        this.featured = featured;
    }

    public static Game create(UUID developerId, GameTitle title, Description description, Money basePrice, LocalDate releaseDate) {

        return new Game(
                GameId.generate(),
                developerId,
                title,
                description,
                basePrice,
                GameStatus.DRAFT,
                releaseDate,
                false
        );
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

    public Set<GenreId> getGenres() {
        return genres;
    }

    public Set<TagId> getTags() {
        return tags;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void associateGenres(Set<GenreId> genreIds) {

        if (this.genres != null) {
            this.genres.clear();
            if (genreIds != null && !genreIds.isEmpty()) {
                this.genres.addAll(genreIds);
            }
        }
    }

    public void associateTags(Set<TagId> tagIds) {

        if (this.genres != null) {
            this.tags.clear();
            if (tagIds != null && !tagIds.isEmpty()) {
                this.tags.addAll(tagIds);
            }
        }
    }

    public void update(GameTitle title, Description description, LocalDate releaseDate) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public void archive() {
        this.status = GameStatus.ARCHIVED;
    }

    public void changePrice(Money newPrice) {
        this.basePrice = newPrice;
    }

    public void feature() {

        if (status != GameStatus.PUBLISHED)
            throw new GameNotPublishedException();

        this.featured = true;
    }

    public void unFeature() {
        this.featured = false;
    }

    public void updateStatus(GameStatus status) {
        this.status = status;
    }
}
