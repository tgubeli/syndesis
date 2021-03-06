/*
 * Copyright (C) 2016 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.syndesis.connector.aws.s3;

import java.io.IOException;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.component.aws.s3.S3Constants;

import io.syndesis.integration.component.proxy.ComponentProxyComponent;
import io.syndesis.integration.component.proxy.ComponentProxyCustomizer;

public class AWSS3CopyObjectCustomizer implements ComponentProxyCustomizer {
	
	private String filenameKey;

	@Override
	public void customize(ComponentProxyComponent component, Map<String, Object> options) {
		filenameKey = (String) options.get("fileName");
		
		component.setBeforeProducer(this::beforeProducer);
	}

	public void beforeProducer(final Exchange exchange) throws IOException {
		final Message in = exchange.getIn();
		
		if (filenameKey != null) {
			in.setHeader(S3Constants.KEY, filenameKey);
		}
		
	}
}
