//
// Copyright 2017 Amazon.com, Inc. or its affiliates (Amazon). All Rights Reserved.
//
// Code generated by AWS Mobile Hub. Amazon gives unlimited permission to 
// copy, distribute and modify it.
//
// Source code generated from template: aws-my-sample-app-android v0.15
//
package com.amazonaws.mobile;

import com.amazonaws.regions.Regions;

/**
 * This class defines constants for the developer's resource
 * identifiers and API keys. This configuration should not
 * be shared or posted to any public source code repository.
 */
public class AWSConfiguration {
    // AWS MobileHub user agent string
    public static final String AWS_MOBILEHUB_USER_AGENT =
        "MobileHub c3cbc859-572f-4e0a-8402-c334afdaa2eb aws-my-sample-app-android-v0.15";
    // AMAZON COGNITO
    public static final Regions AMAZON_COGNITO_REGION =
      Regions.fromName("us-east-1");
    public static final String  AMAZON_COGNITO_IDENTITY_POOL_ID =
        "us-east-1:f34fcb0a-7875-44da-894d-55705a536f98";
    public static final Regions AMAZON_DYNAMODB_REGION =
       Regions.fromName("us-east-1");
    public static final String AMAZON_COGNITO_USER_POOL_ID =
        "us-east-1_MPRjN2Ffb";
    public static final String AMAZON_COGNITO_USER_POOL_CLIENT_ID =
        "2e4dce4mvs7n6ojc13s0u7b9d3";
    public static final String AMAZON_COGNITO_USER_POOL_CLIENT_SECRET =
        "en2fjmd1p15glh8mi2mq5ajka28jbsps3rpajkm6pmj0tqsabbt";
}