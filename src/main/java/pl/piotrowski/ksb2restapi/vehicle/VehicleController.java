package pl.piotrowski.ksb2restapi.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getVehicles(@RequestParam(required = false) String color) {
        if (color == null) {
            return new ResponseEntity<>(vehicleService.getVehicleList(), HttpStatus.OK);
        }

        List<Vehicle> vehiclesByColor = vehicleService.getVehicleByColor(color);

        return new ResponseEntity<>(vehiclesByColor, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable long id) {
        Optional<Vehicle> vehicleById = vehicleService.getVehicleById(id);

        return vehicleById
                .map(vehicle -> new ResponseEntity<>(vehicle, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle newVehicle) {
        boolean isVehicleAlreadyExist = vehicleService.getVehicleById(newVehicle.getId()).isPresent();

        if (isVehicleAlreadyExist) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        vehicleService.addVehicle(newVehicle);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> modifyVehicle(@PathVariable long id, @RequestBody Vehicle updatedVehicle) {
        Optional<Vehicle> vehicleById = vehicleService.getVehicleById(id);

        if (vehicleById.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        vehicleService.updateVehicle(id, updatedVehicle);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable long id, @RequestBody Vehicle updatedVehicle) {
        Optional<Vehicle> vehicleById = vehicleService.getVehicleById(id);

        if (vehicleById.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        vehicleService.updateVehicle(id, updatedVehicle);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Vehicle> deleteVehicle(@PathVariable long id) {
        Optional<Vehicle> vehicleById = vehicleService.getVehicleById(id);

        if (vehicleById.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        vehicleService.removeVehicle(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
