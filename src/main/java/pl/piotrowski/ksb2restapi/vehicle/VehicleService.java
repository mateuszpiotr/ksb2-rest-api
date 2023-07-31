package pl.piotrowski.ksb2restapi.vehicle;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    private final List<Vehicle> vehicleList;

    public VehicleService() {
        this.vehicleList = new ArrayList<>();

        Vehicle cupra = new Vehicle(1L, "Cupra", "Formentor", "white");
        Vehicle tesla = new Vehicle(2L, "Tesla", "Model 3", "white");
        Vehicle mini = new Vehicle(1L, "Mini", "Cooper", "silver");

        vehicleList.add(cupra);
        vehicleList.add(tesla);
        vehicleList.add(mini);
    }

    private Optional<Vehicle> findVehicleById(long id) {
        return vehicleList.stream()
                .filter(vehicle -> vehicle.getId() == id)
                .findFirst();
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public Optional<Vehicle> getVehicleById(long id) {
        return findVehicleById(id);
    }

    public List<Vehicle> getVehicleByColor(String color) {
        return vehicleList.stream()
                .filter(vehicle -> vehicle.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleList.add(vehicle);
    }

    public void removeVehicle(long id) {
        Vehicle vehicleById = findVehicleById(id).orElse(null);

        vehicleList.remove(vehicleById);
    }

    public void updateVehicle(long id, Vehicle updatedVehicle) {
        Vehicle vehicle = findVehicleById(id).orElse(null);
        if (updatedVehicle.getModel() != null) {
            assert vehicle != null;
            vehicle.setModel(updatedVehicle.getModel());
        }
        if (updatedVehicle.getColor() != null) {
            assert vehicle != null;
            vehicle.setColor(updatedVehicle.getColor());
        }
        if (updatedVehicle.getMark() != null) {
            assert vehicle != null;
            vehicle.setMark(updatedVehicle.getMark());
        }

        removeVehicle(id);
        addVehicle(vehicle);
    }
}
