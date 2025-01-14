package API;


import CRUD.ProgrammeurDAO;
import MODELS.Programmeur;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/programmeurs")
public class ProgrammeurController {

    @GetMapping("/")
    public List<Programmeur> getAllProgrammeurs() {
        try {
            return ProgrammeurDAO.getAllUsers();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des programmeurs.", e);
        }
    }

    @PostMapping("/")
    public Programmeur createProgrammeur(@RequestBody Programmeur programmeur) {
        try {
            ProgrammeurDAO.insertUser(programmeur);
            return programmeur;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création du programmeur.", e);
        }
    }

    @GetMapping("/{id}")
    public Programmeur getProgrammeurById(@PathVariable("id") int id) {
        try {
            return ProgrammeurDAO.getUserById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du programmeur.", e);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteProgrammeur(@PathVariable("id") int id) {
        try {
            ProgrammeurDAO.deleteUser(id);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression du programmeur.", e);
        }
    }
    @PutMapping("/{id}")
    public Programmeur updateProgrammeur(@PathVariable("id") int id, @RequestBody Programmeur programmeur) {
        try {
            programmeur.setId(id);
            ProgrammeurDAO.updateUser(programmeur);
            return programmeur;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la mise à jour du programmeur.", e);
        }
    }

}

