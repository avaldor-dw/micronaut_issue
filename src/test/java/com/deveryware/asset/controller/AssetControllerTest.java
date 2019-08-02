/*
 * Copyright (C) 2019 Deveryware S.A.S. All Rights Reserved.
 */

package com.deveryware.asset.controller;

import com.deveryware.asset.dao.model.Asset;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.FixedHostPortGenericContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@MicronautTest(propertySources = "classpath:arangodb.yml")
class AssetControllerTest {
    @Container
    static GenericContainer arangodbcontainer = new FixedHostPortGenericContainer("arangodb/arangodb:3.4.7")
            .withFixedExposedPort(8529, 8529)
            .withEnv("ARANGO_NO_AUTH", "1")
            .waitingFor(Wait.forLogMessage(".*is ready for business.*", 1));

    @Inject
    @Client("/")
    RxHttpClient client;

    @Test
    void getAsset() {
        HttpRequest<Asset> request = HttpRequest.GET("asset-ws/assets/1");
        ((MutableHttpRequest<Asset>) request).header("sso_login_id", "tati");
        ((MutableHttpRequest<Asset>) request).header("case_id", "ert435ERT");

        // WHEN
        var httpResponse = client.toBlocking().exchange(request, Asset.class);

        // THEN
        assertThat(httpResponse.getStatus().getCode()).isEqualTo(HttpStatus.OK.getCode());
    }

    @Test
    void getAsset_with_wrongHeader_should_throwException() {
        HttpRequest<Asset> request = HttpRequest.GET("asset-ws/assets/1");
        ((MutableHttpRequest<Asset>) request).header("sso_login_idd", "tati");
        ((MutableHttpRequest<Asset>) request).header("case_id", "ert435ERT");

        // WHEN
        try {
            HttpResponse<Asset> httpResponse = client.toBlocking().exchange(request);
        } catch (HttpClientResponseException e) {
            // THEN
            assertThat(e.getStatus().getCode()).isEqualTo(HttpStatus.UNAUTHORIZED.getCode());
        }
    }

    @Test
    void saveAsset_should_returnCode201() {
        // GIVEN
        List<String> assetIds = new ArrayList<>();
        assetIds.add("asset1");
        assetIds.add("asset2");
        assetIds.add("asset3");

        Asset asset = new Asset();
        asset.setId("1122");
        asset.setName("test");

        HttpRequest<Asset> request = HttpRequest.POST("asset-ws/assets", asset);
        ((MutableHttpRequest<Asset>) request).header("sso_login_id", "tati");
        ((MutableHttpRequest<Asset>) request).header("case_id", "ert435ERT");

        // WHEN
        HttpResponse<Asset> httpResponse = client.toBlocking().exchange(request, Asset.class);

        // THEN
        assertThat(httpResponse.getStatus().getCode()).isEqualTo(HttpStatus.CREATED.getCode());
    }

    @Test
    void updateAsset() {
    }
}
