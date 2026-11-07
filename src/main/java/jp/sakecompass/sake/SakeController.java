package jp.sakecompass.sake;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/sakes")
public class SakeController {
    private final SakeRepository repository;

    public SakeController(SakeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Sake> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Sake get(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "銘柄が見つかりません"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sake create(@Valid @RequestBody Sake sake) {
        sake.setId(null);
        return repository.save(sake);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "銘柄が見つかりません");
        }
        repository.deleteById(id);
    }
}
