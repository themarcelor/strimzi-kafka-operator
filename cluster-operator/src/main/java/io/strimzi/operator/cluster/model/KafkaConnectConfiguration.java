/*
 * Copyright Strimzi authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */

package io.strimzi.operator.cluster.model;

import io.strimzi.api.kafka.model.KafkaConnectSpec;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

/**
 * Class for handling Kafka Connect configuration passed by the user
 */
public class KafkaConnectConfiguration extends AbstractConfiguration {
    private static final List<String> FORBIDDEN_PREFIXES;
    private static final List<String> FORBIDDEN_PREFIX_EXCEPTIONS;
    private static final Map<String, String> DEFAULTS;

    static {
        FORBIDDEN_PREFIXES = asList(KafkaConnectSpec.FORBIDDEN_PREFIXES.split(", "));
        FORBIDDEN_PREFIX_EXCEPTIONS = asList(KafkaConnectSpec.FORBIDDEN_PREFIX_EXCEPTIONS.split(", "));

        DEFAULTS = new HashMap<>();
        DEFAULTS.put("group.id", "connect-cluster");
        DEFAULTS.put("offset.storage.topic", "connect-cluster-offsets");
        DEFAULTS.put("config.storage.topic", "connect-cluster-configs");
        DEFAULTS.put("status.storage.topic", "connect-cluster-status");
        DEFAULTS.put("key.converter", "org.apache.kafka.connect.json.JsonConverter");
        DEFAULTS.put("value.converter", "org.apache.kafka.connect.json.JsonConverter");
    }

    /**
     * Constructor used to instantiate this class from JsonObject. Should be used to create configuration from
     * ConfigMap / CRD.
     *
     * @param jsonOptions     Json object with configuration options as key ad value pairs.
     */
    public KafkaConnectConfiguration(Iterable<Map.Entry<String, Object>> jsonOptions) {
        super(jsonOptions, FORBIDDEN_PREFIXES, FORBIDDEN_PREFIX_EXCEPTIONS, DEFAULTS);
    }
}
