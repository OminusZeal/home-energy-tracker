package com.jai.device_service.service;

import com.jai.device_service.dto.DeviceDto;
import com.jai.device_service.entity.Device;
import com.jai.device_service.exception.DeviceNotFoundException;
import com.jai.device_service.mapper.DeviceMapper;
import com.jai.device_service.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class DeviceService {

     final private DeviceRepository repo;
     final private DeviceMapper mapper;

    public DeviceService(DeviceRepository repo,DeviceMapper mapper){
        this.repo=repo;
        this.mapper=mapper;
    }

    public DeviceDto getDeviceById(Long id){
        Device device=repo.findById(id)
                .orElseThrow(()-> new DeviceNotFoundException("Device not found with id "+ id));
        return mapper.toDto(device);
    }

    public DeviceDto createDevice(DeviceDto dto){
        final Device newDevice=mapper.toEntity(dto);
        final Device createdDevice=repo.save(newDevice);
        return mapper.toDto(createdDevice);
    }

    public DeviceDto updateDevice(Long id,DeviceDto dto){
        final Device device=repo.findById(id)
                .orElseThrow(()-> new DeviceNotFoundException("Device not found with id "+ id));
        device.setName(dto.getName());
        device.setType(dto.getType());
        device.setLocation(dto.getLocation());
        device.setUserId(dto.getUserId());
        final Device updatedDevice=repo.save(device);
        return mapper.toDto(updatedDevice);
    }

    public List<DeviceDto> getAllDevicesByUserId(Long userId) {
        List<Device> devices = repo.findAllByUserId(userId);
        return devices.stream()
                .map(mapper::toDto)
                .toList();
    }

    public void deleteDevice(Long id){
        if(!repo.existsById(id)){
            throw new DeviceNotFoundException("Device not found with id"+id);
        }
        repo.deleteById(id);
    }

}
