package dk.sdu.swe.domain.controllers.contracts;

import dk.sdu.swe.domain.models.Channel;
import org.checkerframework.checker.units.qual.C;

import java.util.List;
import java.util.Optional;

/**
 * The interface Channel controller.
 */
public interface IChannelController {
    /**
     * Gets all.
     *
     * @return the all
     */
    List<Channel> getAll();

    Optional<Channel> get(int id);
}
