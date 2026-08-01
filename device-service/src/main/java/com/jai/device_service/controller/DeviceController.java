package com.jai.device_service.controller;

import com.jai.device_service.dto.DeviceDto;
import com.jai.device_service.entity.Device;
import com.jai.device_service.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/device")
public class DeviceController {

    private final DeviceService service;

    public DeviceController(DeviceService service){
        this.service=service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceDto> getDeviceById(@PathVariable Long id){
        DeviceDto dto = service.getDeviceById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DeviceDto> createDevice(@RequestBody DeviceDto deviceDto){
        DeviceDto dto=service.createDevice(deviceDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DeviceDto>> getAllDevicesByUserId(
            @PathVariable Long userId) {
        List<DeviceDto> devices = service.getAllDevicesByUserId(userId);
        return ResponseEntity.ok(devices);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceDto> updateDevice(@RequestBody DeviceDto deviceDto,@PathVariable Long id){
        DeviceDto dto=service.updateDevice(id,deviceDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id){
        service.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}
