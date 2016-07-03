/**
 * Copyright 2016 Nikita Koksharov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.redisson;

import org.redisson.client.RedisPubSubListener;
import org.redisson.client.protocol.pubsub.PubSubType;
import org.redisson.core.StatusListener;

/**
 *
 * @author Nikita Koksharov
 *
 * @param <K>
 * @param <V>
 */
public class PubSubStatusListener<V> implements RedisPubSubListener<V> {

    private final StatusListener listener;
    private final String name;

    public String getName() {
        return name;
    }

    public PubSubStatusListener(StatusListener listener, String name) {
        super();
        this.listener = listener;
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((listener == null) ? 0 : listener.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PubSubStatusListener other = (PubSubStatusListener) obj;
        if (listener == null) {
            if (other.listener != null)
                return false;
        } else if (!listener.equals(other.listener))
            return false;
        return true;
    }

    @Override
    public void onMessage(String channel, V message) {
    }

    @Override
    public void onPatternMessage(String pattern, String channel, V message) {
    }

    @Override
    public boolean onStatus(PubSubType type, String channel) {
        if (type == PubSubType.SUBSCRIBE) {
            listener.onSubscribe(channel);
        } else if (type == PubSubType.UNSUBSCRIBE) {
            listener.onUnsubscribe(channel);
        }
        return true;
    }

}
