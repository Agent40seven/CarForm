package cars.carform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("car", new Car());
        return "form";
    }

    @GetMapping("/cars")
    public String showCars(Model model) {
        List<Car> cars = carService.findAll();
        model.addAttribute("cars", cars);
        return "cars";
    }

    @PostMapping("/")
    public String saveCar(Car car) {
        carService.save(car);
        return "redirect:/cars";
    }

    @GetMapping("/search")
    public String showSearch(Model model) {
        return "search";
    }

    @PostMapping("/search")
    public String searchCars(@RequestParam("color") String color, Model model) {
        List<Car> cars = carService.findByColor(color);
        model.addAttribute("cars", cars);
        return "results";
    }
}
