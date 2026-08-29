package com.arsio.user.internal.application.command;

import com.arsio.user.internal.domain.valueobject.Country;

public record UpdateProfileCountryCommand(
        Country newCountry
) {
}
