/*
 * Copyright (c) 2017 Baidu, Inc. All Rights Reserve.
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
package com.laiyw.micro.id.service.impl;

import com.laiyw.micro.id.domain.WorkerNode;
import com.laiyw.micro.id.enums.WorkerNodeType;
import com.laiyw.micro.id.mapper.WorkerNodeMapper;
import com.laiyw.micro.id.service.WorkerIdAssigner;
import com.laiyw.micro.common.utils.DockerUtils;
import com.laiyw.micro.common.utils.NetUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Represents an implementation of {@link WorkerIdAssigner},
 * the worker id will be discarded after assigned to the UidGenerator
 *
 * @author yutianbao
 */
public class DisposableWorkerIdAssigner implements WorkerIdAssigner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DisposableWorkerIdAssigner.class);

    @Resource
    private WorkerNodeMapper workerNodeMapper;

    /**
     * Assign worker id base on database.<p>
     * If there is host name & port in the environment, we considered that the node runs in Docker container<br>
     * Otherwise, the node runs on an actual machine.
     *
     * @return assigned worker id
     */
    @Override
    @Transactional
    public long assignWorkerId() {
        // build worker node entity
        WorkerNode workerNode = buildWorkerNode();

        // add worker node for new (ignore the same IP + PORT)
        workerNodeMapper.addWorkerNode(workerNode);
        LOGGER.info("Add worker node:" + workerNode);

        return workerNode.getId();
    }

    /**
     * Build worker node entity by IP and PORT
     */
    private WorkerNode buildWorkerNode() {
        WorkerNode workerNode = new WorkerNode();
        if (DockerUtils.isDocker()) {
            workerNode.setType(WorkerNodeType.CONTAINER.value());
            workerNode.setHostName(DockerUtils.getDockerHost());
            workerNode.setPort(DockerUtils.getDockerPort());
        } else {
            workerNode.setType(WorkerNodeType.ACTUAL.value());
            workerNode.setHostName(NetUtils.getLocalAddress());
            workerNode.setPort(System.currentTimeMillis() + "-" + RandomUtils.nextInt(0, 100000));
        }
        return workerNode;
    }

}
