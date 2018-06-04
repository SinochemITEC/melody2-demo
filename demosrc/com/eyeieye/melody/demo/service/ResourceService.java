package com.eyeieye.melody.demo.service;

import java.util.List;

import com.eyeieye.melody.demo.domain.Resource;
import com.eyeieye.melody.demo.enums.ResourceType;

/**
 * ��ԴService�ӿ�
 * 
 * @author zhengdd
 * @version $Id: ResourceService.java,v 1.1 2011/06/20 07:43:14 fish Exp $
 */
public interface ResourceService {

    /**
     * ������Դ���ͻ�ȡ��Դ�б�
     * 
     * @param province
     * @return List<Resource>
     */
    public List<Resource> getResourcesByType(ResourceType province);

}
