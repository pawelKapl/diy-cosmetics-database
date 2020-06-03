package pl.biologicznieczynny.diycosmeticsdatabase.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Tool;
import pl.biologicznieczynny.diycosmeticsdatabase.services.ToolService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ToolController {

    private final ToolService toolService;

    public ToolController(ToolService toolService) {this.toolService = toolService;}


    //get's

    @GetMapping("/tools")
    public List<Tool> getToolList() {
        return toolService.getToolsList();
    }

    //post's

    @PostMapping("/tools")
    public ResponseEntity<Tool> addNewTool(@RequestBody Tool tool) {
        return toolService.addNewTool(tool);
    }

    //delete's

    @DeleteMapping("/tools/{id}")
    public void deleteTool(@PathVariable Long id) {
        toolService.deleteToolById(id);
    }

    //put's

    @PutMapping("/tools")
    public ResponseEntity<Tool> updateTool(@RequestBody Tool tool) {
        return toolService.updateTool(tool);
    }
}
