package com.arsio.user.internal.infra.controller.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.shared.storage.ConfirmFileUploadCommand;
import com.arsio.user.internal.application.command.UpdateProfileBioCommand;
import com.arsio.user.internal.application.command.UpdateProfileCountryCommand;
import com.arsio.shared.storage.UploadFileUrlCommand;
import com.arsio.user.internal.domain.valueobject.Bio;
import com.arsio.user.internal.domain.valueobject.Country;
import com.arsio.shared.storage.ObjectKey;
import com.arsio.shared.storage.ConfirmFileUploadRequest;
import com.arsio.user.internal.infra.controller.dto.request.UpdateProfileBioRequest;
import com.arsio.user.internal.infra.controller.dto.request.UpdateProfileCountryRequest;
import com.arsio.shared.storage.UploadFileUrlRequest;
import org.mapstruct.Mapper;

@Mapper(config =  CentralMapperConfig.class)
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
