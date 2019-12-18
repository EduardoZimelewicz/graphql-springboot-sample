package au.com.marlo.service;

import au.com.marlo.dao.entity.Vehicle;
import au.com.marlo.dao.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository ;

    public VehicleService(final VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository ;
    }

    @Transactional
    public Vehicle createVehicle(final String type, final String modelCode,
                                 final String brandName, final String launchDate) {
        final Vehicle vehicle = new Vehicle();
        vehicle.setType(type);
        vehicle.setModelCode(modelCode);
        vehicle.setBrandName(brandName);
        vehicle.setLaunchDate(LocalDate.parse(launchDate));
        return this.vehicleRepository.save(vehicle);
    }

    @Transactional
    public Vehicle removeVehicle(final int id) {

        Optional<Vehicle> deletedVehicle = getVehicle(id);

        if (deletedVehicle.isPresent()) {
            this.vehicleRepository.delete(deletedVehicle.get());
            return deletedVehicle.get();
        }
        return null;
    }

    @Transactional
    public Vehicle updateVehicle(final int id, final String type, final String modelCode,
                                 final String brandName, final String launchDate) {
        Optional<Vehicle> updatedVehicle = getVehicle(id);

        if (updatedVehicle.isPresent()) {
            Vehicle newVehicle = updatedVehicle.get();
            newVehicle.setType(type);
            newVehicle.setModelCode(modelCode);
            newVehicle.setBrandName(brandName);
            newVehicle.setLaunchDate(LocalDate.parse(launchDate));
            return this.vehicleRepository.save(newVehicle);
        }
        return null;
    }

    @Transactional(readOnly = true)
    public List<Vehicle> getAllVehicles(final int count) {
        return this.vehicleRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<Vehicle> getVehicle(final int id) {
        return this.vehicleRepository.findById(id);
    }
}
