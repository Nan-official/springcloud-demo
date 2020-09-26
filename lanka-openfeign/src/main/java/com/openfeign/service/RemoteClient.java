package com.openfeign.service;

import org.springframework.cloud.openfeign.FeignClient;


/**
 * @author Nxy
 * @title: RemoteClient
 * @projectName lanka
 * @description: TODO
 */
@FeignClient(name = "nacos-openfeign",fallback = RemoteClient.class)
public interface RemoteClient {

}
