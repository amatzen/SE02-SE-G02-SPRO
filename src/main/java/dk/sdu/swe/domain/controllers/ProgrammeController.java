package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.dao.CategoryDAOImpl;
import dk.sdu.swe.data.dao.CompanyDAOImpl;
import dk.sdu.swe.data.dao.ProgrammeDAOImpl;
import dk.sdu.swe.domain.models.Category;
import dk.sdu.swe.domain.models.Channel;
import dk.sdu.swe.domain.models.Company;
import dk.sdu.swe.domain.models.Programme;
import dk.sdu.swe.domain.persistence.IProgrammeDAO;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<Programme> search(String searchTerm, Channel channel, Category category) {
        List<Programme> programmes = ProgrammeDAOImpl.getInstance().search(searchTerm, channel, category);
        programmes.sort(Comparator.comparing(Programme::getTitle));
        return programmes;
    }

    public List<Category> getCategories() {
        List<Category> categories = CategoryDAOImpl.getInstance().getAll();
        categories.sort(Comparator.comparing(Category::getCategoryTitle));
        return categories;
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
