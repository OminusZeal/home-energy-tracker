package com.jai.device_service.mapper;

import com.jai.device_service.dto.DeviceDto;
import com.jai.device_service.entity.Device;
import org.springframework.stereotype.Component;

@Component
public class DeviceMapper {

    public DeviceDto toDto(Device input){
        return DeviceDto.builder()
                .id(input.getId())
                .name(input.getName())
                .type(input.getType())
                .location(input.getLocation())
                .userId(input.getUserId())
                .build();
    }

    public Device toEntity(DeviceDto input){
        return Device.builder()
                .name(input.getName())
                .type(input.getType())
                .location(input.getLocation())
                .userId(input.getUserId())
                .build();
    }
}
