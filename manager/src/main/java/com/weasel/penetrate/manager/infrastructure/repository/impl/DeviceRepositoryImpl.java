package com.weasel.penetrate.manager.infrastructure.repository.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import com.weasel.penetrate.manager.domain.device.Device;
import com.weasel.penetrate.manager.infrastructure.repository.DeviceRepository;
import com.weasel.penetrate.manager.infrastructure.repository.MybatisDaoSupport;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dylan
 * @date 2017/1/22.
 */
@Repository
public class DeviceRepositoryImpl extends MybatisDaoSupport implements DeviceRepository {

    @Override
    public Device add(Device device) {
        getSqlSession().insert(namespace().concat(".insert"),device);
        return device;
    }

    @Override
    public int update(Device device) {
        return getSqlSession().update(namespace().concat(".update"),device);
    }

    @Override
    public List<Device> query(Device device) {

        List<Device> devices = getSqlSession().selectList(namespace().concat(".query"),device);
        return null != device ? devices : Lists.newArrayList();
    }

    @Override
    public int delete(Device device) {
        return 0;
    }

    @Override
    public int countByPort(String listenPort) {
        return getSqlSession().selectOne(namespace().concat(".countByPort"),listenPort);
    }

    @Override
    public int countBySubDomain(String subdomain) {
        return getSqlSession().selectOne(namespace().concat(".countBySubDomain"),subdomain);
    }

    @Override
    protected String namespace() {
        return Device.class.getName();
    }
}
