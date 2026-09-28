package com.arsio.game.internal.domain.valueobject;

import com.arsio.game.internal.domain.exception.InvalidStorageUnitException;

public enum StorageUnit {

    MB(0, "Mb"),
    GB(1, "Gb"),
    TB(2, "Tb"),;

    private final Integer cod;
    private final String description;

    StorageUnit(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public StorageUnit toEnum() {
        if (cod == null) return null;
        for (StorageUnit storageUnit : StorageUnit.values()) {
            if (cod.equals(storageUnit.cod)) return storageUnit;
        }
        throw new InvalidStorageUnitException();
    }
}
