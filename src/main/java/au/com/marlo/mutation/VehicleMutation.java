package au.com.marlo.mutation;

import au.com.marlo.dao.entity.Vehicle;
import au.com.marlo.service.VehicleService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleMutation implements GraphQLMutationResolver {

    @Autowired
    private VehicleService vehicleService;

    public Vehicle createVehicle(final String type, final String modelCode,
                                 final String brandName, final String launchDate) {
        return this.vehicleService.createVehicle(type, modelCode, brandName, launchDate);
    }

    public Vehicle removeVehicle (final int id) {
        return this.vehicleService.removeVehicle(id);
    }

    public Vehicle updateVehicle(final int id, final String type, final String modelCode,
                                 final String brandName, final String launchDate) {
        return this.vehicleService.updateVehicle(id, type, modelCode, brandName, launchDate);
    }
}
