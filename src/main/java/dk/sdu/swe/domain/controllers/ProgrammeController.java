package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.dao.CategoryDAOImpl;
import dk.sdu.swe.data.dao.ProgrammeDAOImpl;
import dk.sdu.swe.domain.models.Category;
import dk.sdu.swe.domain.models.Channel;
import dk.sdu.swe.domain.models.Programme;

import java.util.List;

public class ProgrammeController {

    private static ProgrammeController instance;

    private ProgrammeController() {}

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


}
