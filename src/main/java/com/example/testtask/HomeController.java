package com.example.testtask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private PartRepository partRepository;

    @Autowired
    public HomeController(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @GetMapping("/")
    public String getAllParts(@PageableDefault Pageable pageable, Model model) {
        Page<Part> page = partRepository.findAll(pageable);
        List<Sort.Order> sortOrders = page.getSort().stream().collect(Collectors.toList());
        if (sortOrders.size() > 0) {
            Sort.Order order = sortOrders.get(0);
            model.addAttribute("sortProperty", order.getProperty());
            model.addAttribute("sortDesc", order.getDirection() == Sort.Direction.DESC);
        }
        model.addAttribute("searchPart", new Part());
        model.addAttribute("page", page);
        model.addAttribute("maxAssem", "You can assemble " + getMaxAssembleable() + " computers");
        return "home";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Part part, Model model) {
        model.addAttribute("part", part);
        return "add-part";
    }

    @PostMapping("/addpart")
    public String addPart(@Valid Part part, BindingResult result) {
        if (result.hasErrors())
            return "add-part";
        partRepository.save(part);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Part part = partRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid part id " + id));
        model.addAttribute("part", part);
        return "update-part";
    }

    @PostMapping("/update/{id}")
    public String updatePart(@PathVariable("id") int id, @Valid Part part, BindingResult result) {
        if (result.hasErrors()) {
            part.setId(id);
            return "update-part";
        }
        partRepository.save(part);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deletePart(@PathVariable("id") int id) {
        Part part = partRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid part Id:" + id));
        partRepository.delete(part);
        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchResult(Part searchPart, Model model) {
        Iterable<Part> parts = partRepository.findAll();
        ArrayList<Part> foundParts = new ArrayList<>();
        for (Part part : parts) {
            if (part.getName().equalsIgnoreCase(searchPart.getName()))
                foundParts.add(part);
        }
        if (!foundParts.isEmpty()) {
            model.addAttribute("foundParts", foundParts);
            return "search-results";
        } else
            return "redirect:/";
    }

    private int getMaxAssembleable() {
        Iterable<Part> parts = partRepository.findAll();
        int maxAssembleCount = Integer.MAX_VALUE;
        for (Part part : parts) {
            if (part.isOblig() && part.getCount() == 0)
                return 0;
            if (part.isOblig() && part.getCount() < maxAssembleCount)
                maxAssembleCount = part.getCount();
        }
        return maxAssembleCount == Integer.MAX_VALUE ?  0 : maxAssembleCount;
    }
}