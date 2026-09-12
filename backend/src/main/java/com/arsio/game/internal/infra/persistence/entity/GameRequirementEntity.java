package com.arsio.game.internal.infra.persistence.entity;

import com.arsio.game.internal.domain.valueobject.RequirementCategory;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Table(name = "game_requirements")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class GameRequirementEntity {

    @Id
    @Column(nullable = false, unique = true)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private GameEntity game;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(nullable = false)
    private RequirementCategory category;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operating_system_id")
    private OperatingSystemEntity operatingSystem;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "processor_id")
    private ProcessorEntity processor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gpu_id")
    private GpuEntity gpu;

    @Column
    private Integer ram;

    @Column
    private Double storage;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column
    private String storageUnit;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
    private Instant createdAt;
}
