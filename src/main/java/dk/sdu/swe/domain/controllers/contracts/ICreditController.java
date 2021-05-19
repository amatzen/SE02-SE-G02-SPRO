package dk.sdu.swe.domain.controllers.contracts;

import dk.sdu.swe.domain.models.Credit;
import dk.sdu.swe.domain.models.CreditRole;
import dk.sdu.swe.domain.models.Person;
import dk.sdu.swe.domain.models.Programme;

import java.util.List;

public interface ICreditController {
    Credit createCredit(Person person, CreditRole creditRole, Programme programme);

    void update(Credit creditObj);

    void delete(Credit credit);

    List<Credit> getAll();
}
