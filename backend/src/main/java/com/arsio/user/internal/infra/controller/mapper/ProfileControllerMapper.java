package com.arsio.user.internal.infra.controller.mapper;

import com.arsio.user.internal.application.command.ConfirmFileUploadCommand;
import com.arsio.user.internal.application.command.UpdateProfileBioCommand;
import com.arsio.user.internal.application.command.UpdateProfileCountryCommand;
import com.arsio.user.internal.application.command.UploadFileUrlCommand;
import com.arsio.user.internal.domain.valueobject.Bio;
import com.arsio.user.internal.domain.valueobject.Country;
import com.arsio.user.internal.domain.valueobject.ObjectKey;
import com.arsio.user.internal.infra.controller.dto.request.ConfirmFileUploadRequest;
import com.arsio.user.internal.infra.controller.dto.request.UpdateProfileBioRequest;
import com.arsio.user.internal.infra.controller.dto.request.UpdateProfileCountryRequest;
import com.arsio.user.internal.infra.controller.dto.request.UploadFileUrlRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
typeConversionPolicy = ReportingPolicy.ERROR)
public interface ProfileControllerMapper {

    UploadFileUrlCommand toUploadFileUrlCommand(UploadFileUrlRequest request);

    ConfirmFileUploadCommand toConfirmFileUploadCommand(ConfirmFileUploadRequest request);

    UpdateProfileBioCommand toUpdateProfileBioCommand(UpdateProfileBioRequest request);

    UpdateProfileCountryCommand toUpdateProfileCountryCommand(UpdateProfileCountryRequest request);

    default ObjectKey stringToObjectKey(String objectKey) {
        return new ObjectKey(objectKey);
    }

    default Bio stringToBio(String bio) {
        return new Bio(bio);
    }

    default Country stringToCountry(String country) {
        return (Country.fromCode(country));
    }
}
