package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.persistence.dao.CategoryDAOImpl;
import dk.sdu.swe.persistence.dao.ProgrammeDAOImpl;
import dk.sdu.swe.domain.controllers.contracts.IProgrammeController;
import dk.sdu.swe.domain.models.Category;
import dk.sdu.swe.domain.models.Channel;
import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.domain.models.Programme;
import dk.sdu.swe.domain.persistence.IProgrammeDAO;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Programme controller.
 */
public class ProgrammeController implements IProgrammeController {

    private IProgrammeDAO programmeDAO;

    private static IProgrammeController instance;

    private ProgrammeController() {
        programmeDAO = ProgrammeDAOImpl.getInstance();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static IProgrammeController getInstance() {
        if (instance == null) {
            instance = new ProgrammeController();
        }
        return instance;
    }

    @Override
    public List<Programme> getAll() {
        List<Programme> programmes = ProgrammeDAOImpl.getInstance().getAll();
        programmes.sort(Comparator.comparing(Programme::getTitle));

        if(AuthController.getInstance().getUser().hasPermission("programmes.list.all")) {
            return programmes;
        }

        return
            programmes
            .stream()
            .filter(i -> {
                if(Objects.isNull(i.getCompany())) return false;

                Company x = i.getCompany();

                boolean userCompany = Objects.equals(x.getId(), AuthController.getInstance().getUser().getCompany().getId());
                boolean subCompany = false;

                Company currentCompany = x.getParentCompany();
                while(Objects.nonNull(currentCompany)) {
                    subCompany = Objects.equals(currentCompany.getId(), AuthController.getInstance().getUser().getCompany().getId());
                    currentCompany = currentCompany.getParentCompany();
                }

                return userCompany || subCompany;
            })
            .collect(Collectors.toList());
    }

    @Override
    public List<Programme> search(String searchTerm, Channel channel, Category category) {
        List<Programme> programmes = ProgrammeDAOImpl.getInstance().search(searchTerm, channel, category);
        programmes.sort(Comparator.comparing(Programme::getTitle));
        return programmes;
    }

    @Override
    public List<Category> getCategories() {
        List<Category> categories = CategoryDAOImpl.getInstance().getAll();
        categories.sort(Comparator.comparing(Category::getCategoryTitle));
        return categories;
    }

    @Override
    public Programme createProgramme(String title, int prodYear, Channel channel, Set<Category> categories, Company company) {
        Programme programme = new Programme(title, channel, prodYear, categories, company);
        programmeDAO.save(programme);
        return programme;
    }

    @Override
    public void updateProgramme(Programme programmeObj) {
        programmeDAO.update(programmeObj);
    }
}
