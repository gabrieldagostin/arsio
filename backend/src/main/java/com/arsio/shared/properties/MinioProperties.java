package com.arsio.shared.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.minio")
@Getter
@Setter
public class MinioProperties {

    private String url;
    private String accessKey;
    private String secretKey;
    private Bucket bucket;

    public String getUserFilesBucket() {
        return bucket.userFiles;
    }

    public String getGameMediaBucket() {
        return bucket.gameMedia;
    }

    public String getGameBuildsBucket() {
        return bucket.gameBuilds;
    }

    private record Bucket(
        String userFiles,
        String gameMedia,
        String gameBuilds
    ) {}
}
