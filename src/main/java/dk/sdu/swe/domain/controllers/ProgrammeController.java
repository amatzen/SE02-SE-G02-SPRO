package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.dao.CategoryDAOImpl;
import dk.sdu.swe.data.dao.ProgrammeDAOImpl;
import dk.sdu.swe.domain.models.Category;
import dk.sdu.swe.domain.models.Channel;
import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.domain.models.Programme;
import dk.sdu.swe.domain.persistence.IProgrammeDAO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProgrammeController {

    private IProgrammeDAO programmeDAO;

    private static ProgrammeController instance;

    private ProgrammeController() {
        programmeDAO = ProgrammeDAOImpl.getInstance();
    }

    public static ProgrammeController getInstance() {
        if (instance == null) {
            instance = new ProgrammeController();
        }
        return instance;
    }

    public List<Programme> getAll() {
        return ProgrammeDAOImpl.getInstance().getAll();
    }

    public List<Programme> search(String searchTerm, Channel channel, Category category) {
        return ProgrammeDAOImpl.getInstance().search(searchTerm, channel, category);
    }

    public List<Category> getCategories() {
        return CategoryDAOImpl.getInstance().getAll();
    }

    public Programme createProgramme(String title, int prodYear, Channel channel, Set<Category> categories, Company company) {
        Programme programme = new Programme(title, channel, prodYear, categories, company);
        programmeDAO.save(programme);
        return programme;
    }


    public void updateProgramme(Programme programmeObj) {
        programmeDAO.update(programmeObj);
    }
}
